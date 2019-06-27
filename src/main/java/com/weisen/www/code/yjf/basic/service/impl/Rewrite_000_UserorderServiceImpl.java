package com.weisen.www.code.yjf.basic.service.impl;

import com.alipay.api.internal.util.AlipaySignature;
import com.weisen.www.code.yjf.basic.config.AlipayConstants;
import com.weisen.www.code.yjf.basic.domain.*;
import com.weisen.www.code.yjf.basic.repository.*;
import com.weisen.www.code.yjf.basic.service.Rewrite_000_UserorderService;
import com.weisen.www.code.yjf.basic.util.AlipayUtil;
import com.weisen.www.code.yjf.basic.util.Result;
import com.weisen.www.code.yjf.basic.util.Rewrite_Constant;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class Rewrite_000_UserorderServiceImpl implements Rewrite_000_UserorderService {

    private final Logger log = LoggerFactory.getLogger(Rewrite_000_UserorderServiceImpl.class);

    private Rewrite_000_UserorderRepository userorderRepository;

    private Rewrite_000_UserlinkuserRepository userlinkuserRepository;

    private Rewrite_000_PercentageRepository percentageRepository;

    private ReceiptpayRepository receiptpayRepository;

    private Rewrite_000_UserassetsRepository userassetsRepository;

    public Rewrite_000_UserorderServiceImpl(Rewrite_000_UserorderRepository userorderRepository, Rewrite_000_UserlinkuserRepository userlinkuserRepository, Rewrite_000_PercentageRepository percentageRepository, ReceiptpayRepository receiptpayRepository, Rewrite_000_UserassetsRepository userassetsRepository) {
        this.userorderRepository = userorderRepository;
        this.userlinkuserRepository = userlinkuserRepository;
        this.percentageRepository = percentageRepository;
        this.receiptpayRepository = receiptpayRepository;
        this.userassetsRepository = userassetsRepository;
    }

    @Override
    public Result alipay(Long orderId) {
        log.debug("调用支付宝支付:{}", orderId);
        if (null == orderId) {
            return Result.fail("订单号不能为空");
        }
        //1.检查订单是否存在
        Optional<Userorder> optional = userorderRepository.findById(orderId);
        if (!optional.isPresent()) {
            return Result.fail("订单不存在");
        }
        //2.判断订单状态
        Userorder userorder = optional.get();
        if (userorder.getOrderstatus() != Rewrite_Constant.ORDER_WAIT_PAY) {
            return Result.fail("当前订单不能支付");
        }
        //3.准备调起支付宝
        String outTradeNo = userorder.getOrdercode();
        String subject = "";//订单名称字段暂时没有，等待加入
        String totalAmount = userorder.getSum().toString();
        //4.支付宝返回
        String form = AlipayUtil.alipay(outTradeNo, subject, totalAmount, orderId);
        if (StringUtils.isBlank(form)) {
            log.debug("支付宝支付错误");
            return Result.fail("支付宝支付错误");
        }
        return Result.suc("调用支付宝成功", form);
    }

    @Override
    public void notifyMessage(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String[]> requestParams = request.getParameterMap();
        try {
            response.getWriter().write("success");
            // 解析支付宝服务器回调过来的数据。
            Map<String, String> params = new HashMap<>();
            try {
                for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
                    String name = (String) iter.next();
                    String[] values = requestParams.get(name);
                    String valueStr = "";
                    for (int i = 0; i < values.length; i++) {
                        valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
                    }
                    params.put(name, valueStr);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                boolean flag = AlipaySignature.rsaCheckV1(params, AlipayConstants.ALIPAY_PUBLIC_KEY, AlipayConstants.CHARSET,
                    AlipayConstants.SIGN_TYPE);
                if (flag) { // 验证支付是否异常
                    String trade_status = params.get("trade_status");
                    if ("TRADE_SUCCESS".equals(trade_status)) { // 判断是否是支付成功的状态
                        String tradeNo = params.get("out_trade_no");// 订单号
                        String totalAmount = params.get("total_amount");
                        String payWay = Rewrite_Constant.ACCOUNTTYPE_ALIPAY;
                        // 根据解析出来的数据验证订单，用户
                        Userorder userorder = userorderRepository.findByOrdercode(tradeNo);
                        //不是待支付订单不能支付
                        if (!Rewrite_Constant.ORDER_WAIT_PAY.equals(userorder.getOrderstatus())) {
                            return;
                        }
                        // TODO 翻转订单状态
                        userorder.setOrderstatus(Rewrite_Constant.ORDER_WAIT_DELIVER); //将订单状态更改成待发货
                        // 生成支付流水
                        createFlow(userorder);
                    } else {
                        log.debug("并不是支付成功的返回");
                    }
                } else {
                    log.debug("支付宝支付回调参数有误");
                }
            } catch (Exception e) {
                e.printStackTrace();
                log.debug("支付宝回调中错误");
            }
            log.debug("支付宝回调成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createFlow(Userorder userorder) {
        //创建自身的收支明细
        createSelfReceiptpay(userorder);
        //获取分配比例
        Percentage percentageRecommend = percentageRepository.findByName(Rewrite_Constant.PERCENTAGE_RECOMMEND, Rewrite_Constant.PERCENTAGE_TYPE_CASH);
        Percentage percentagePartner = percentageRepository.findByName(Rewrite_Constant.PERCENTAGE_PARTNER, Rewrite_Constant.PERCENTAGE_TYPE_CASH);
        Percentage percentageBenefit = percentageRepository.findByName(Rewrite_Constant.PERCENTAGE_BENEFIT, Rewrite_Constant.PERCENTAGE_TYPE_CASH);
        BigDecimal sumBigDecimal = userorder.getSum();
        //计算收益手续费
        BigDecimal benefitPercentage = BigDecimal.ONE.subtract(new BigDecimal(percentageBenefit.getValue()));
        //获取推荐人
        Userlinkuser userlinkuser = userlinkuserRepository.findByUserId(userorder.getId());
        //计算推荐收益
        String percentageRecommendValue = percentageRecommend.getValue();
        BigDecimal amountRecommend = sumBigDecimal.multiply(new BigDecimal(percentageRecommendValue)).multiply(benefitPercentage); //计算后金额
        String recommendId = userlinkuser.getRecommendid();
        createRecommendAndPartner(Rewrite_Constant.DEALTYPE_RECOMMEND, Rewrite_Constant.DEALSTATE_NORMAL, recommendId, userorder.getPayee(), amountRecommend);
        //获取合伙人
        Userlinkuser partner = userlinkuserRepository.findByUserId(userorder.getId());//先默认取推荐人
        while (partner.isPartner()) {
            String tempRecommendId = partner.getRecommendid();
            partner = userlinkuserRepository.findByUserId(Long.valueOf(tempRecommendId));
        }
        //计算推荐收益
        String percentagePartnerValue = percentagePartner.getValue();
        BigDecimal amountPartner = sumBigDecimal.multiply(new BigDecimal(percentagePartnerValue)).multiply(benefitPercentage); //计算后金额
        createRecommendAndPartner(Rewrite_Constant.DEALTYPE_PARTNER, Rewrite_Constant.DEALSTATE_NORMAL, partner.getUserid(), userorder.getPayee(), amountPartner);
    }

    /**
     * 创建自身的收支明细记录
     * @param userorder
     */
    private void createSelfReceiptpay (Userorder userorder) {
        Receiptpay receiptpaySelf = new Receiptpay();//自身的收益流水
        receiptpaySelf.setDealtype(Rewrite_Constant.DEALTYPE_CONSUMPTION);
        receiptpaySelf.setUserid(userorder.getUserid());
        receiptpaySelf.setSourcer(Rewrite_Constant.SOURCE_ALIPAY);
        receiptpaySelf.setBenefit(userorder.getPayee());
        receiptpaySelf.setAmount(userorder.getSum());
        receiptpaySelf.setHappendate("发生时间");
        receiptpaySelf.setDealstate(Rewrite_Constant.DEALSTATE_NORMAL);
        receiptpaySelf.setCreator("SYSTEM");
        receiptpaySelf.setCreatedate("创建时间");
    }

    /**
     * 创建推荐人或合伙人的收支明细记录
     * @param dealtype
     * @param dealstate
     * @param userId
     * @param benegit
     * @param bigDecimal
     */
    private void createRecommendAndPartner (String dealtype, String dealstate, String userId, String benegit, BigDecimal bigDecimal) {
        Userassets userassets = userassetsRepository.findByUserId(userId);
        //更新余额
        BigDecimal balanceBigDecimal = new BigDecimal(userassets.getBalance());
        balanceBigDecimal = balanceBigDecimal.add(bigDecimal);
        userassets.setBalance(balanceBigDecimal.toString());//更新余额
        Receiptpay receiptpay = new Receiptpay();
        receiptpay.setDealtype(dealtype);
        receiptpay.setUserid(userId);
        receiptpay.setBenefit(benegit);
        receiptpay.setAmount(bigDecimal);
        receiptpay.setHappendate("发生时间");
        receiptpay.setDealstate(dealstate);
        receiptpay.setCreator("SYSTEM");
        receiptpay.setCreatedate("创建时间");
        receiptpayRepository.save(receiptpay);
    }
}
