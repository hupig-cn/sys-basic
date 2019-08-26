package com.weisen.www.code.yjf.basic.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.weisen.www.code.yjf.basic.config.AlipayConstants;
import com.weisen.www.code.yjf.basic.domain.*;
import com.weisen.www.code.yjf.basic.repository.*;
import com.weisen.www.code.yjf.basic.service.Rewrite_000_UserorderService;
import com.weisen.www.code.yjf.basic.service.Rewrite_PayService;
import com.weisen.www.code.yjf.basic.service.dto.CreateOrderDTO;
import com.weisen.www.code.yjf.basic.service.dto.submit_dto.Rewrite_DistributionDTO;
import com.weisen.www.code.yjf.basic.service.util.FinalUtil;
import com.weisen.www.code.yjf.basic.service.util.OrderConstant;
import com.weisen.www.code.yjf.basic.util.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.*;

@Service
@Transactional
public class Rewrite_000_UserorderServiceImpl implements Rewrite_000_UserorderService {

	private final Logger log = LoggerFactory.getLogger(Rewrite_000_UserorderServiceImpl.class);

	private final Rewrite_000_UserorderRepository userorderRepository;

	private final Rewrite_UserlinkuserRepository userlinkuserRepository;

	private final Rewrite_PercentageRepository percentageRepository;

	private final ReceiptpayRepository receiptpayRepository;

	private final Rewrite_000_UserassetsRepository userassetsRepository;

	private final Rewrite_LinkaccountRepository rewrite_LinkaccountRepository;

	private Rewrite_PayService rewrite_PayService;

	public Rewrite_000_UserorderServiceImpl(Rewrite_LinkaccountRepository rewrite_LinkaccountRepository,
			Rewrite_000_UserorderRepository userorderRepository, Rewrite_UserlinkuserRepository userlinkuserRepository,
			Rewrite_PercentageRepository percentageRepository, ReceiptpayRepository receiptpayRepository,
			Rewrite_000_UserassetsRepository userassetsRepository,Rewrite_PayService rewrite_PayService) {
		this.userorderRepository = userorderRepository;
		this.userlinkuserRepository = userlinkuserRepository;
		this.percentageRepository = percentageRepository;
		this.receiptpayRepository = receiptpayRepository;
		this.userassetsRepository = userassetsRepository;
		this.rewrite_PayService = rewrite_PayService;
		this.rewrite_LinkaccountRepository = rewrite_LinkaccountRepository;
	}

	@Override
	public Result alipay(Long orderId) {
		log.debug("调用支付宝支付:{}", orderId);
		if (null == orderId) {
			return Result.fail("订单号不能为空");
		}
		// 1.检查订单是否存在
		Optional<Userorder> optional = userorderRepository.findById(orderId);
		if (!optional.isPresent()) {
			return Result.fail("订单不存在");
		}
		// 2.判断订单状态
		Userorder userorder = optional.get();
		if (!userorder.getOrderstatus().equals(Rewrite_Constant.ORDER_WAIT_PAY)) {
			return Result.fail("当前订单不能支付");
		}
        userorder.setPayway(OrderConstant.ALI_PAY);
		// 3.准备调起支付宝
		String outTradeNo = userorder.getOrdercode();
		String subject = "圆积分消费,祝你生活愉快,详情商品信息请在APP内查看";// 订单名称字段暂时没有，等待加入
		BigDecimal totalAmount = userorder.getSum();
		// 4.支付宝返回
		String form = AlipayUtil.alipay(outTradeNo, subject, totalAmount, orderId);
		if (StringUtils.isBlank(form)) {
			log.debug("支付宝支付错误");
			return Result.fail("支付宝支付错误");
		}
		return Result.suc("调用支付宝成功", form);
	}

	@Override
	public Result alipay(String merchantId, String userId, Integer concession, Integer rebate, String amount) {
		// 1.先创建订单信息
		Userorder userorder = new Userorder();
		String orderCode = FinalUtil.createTradeNo(Rewrite_Constant.ORDER_PREFIX_OFFLINE);
		userorder.setUserid(userId);
		userorder.setSum(new BigDecimal(amount));// 设置金额
		userorder.setOrderstatus(Rewrite_Constant.ORDER_WAIT_PAY);// 设置待支付
		userorder.setOrdercode(orderCode);
		userorder.setPayee(merchantId);
		userorder.setConcession(concession);
		userorder.setRebate(rebate);
		userorder.setCreatedate("创建时间");
		userorderRepository.save(userorder);
		return alipay(userorder.getId());
	}

	@Override
	public Result queryOrder(String orderId) {
		if (StringUtils.isBlank(orderId)) {
			return Result.fail("订单号不能为空");
		}
		Optional<Userorder> optional = userorderRepository.findById(Long.valueOf(orderId));
		if (!optional.isPresent()) {
			return Result.fail("订单不存在");
		}
		Userorder userorder = optional.get();
		if (!userorder.getOrderstatus().equals(Rewrite_Constant.ORDER_WAIT_DELIVER)) {
			return Result.fail("当前订单状态有误");
		}
		return Result.suc("支付成功");
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
//                        String totalAmount = params.get("total_amount");
//                        String payWay = Rewrite_Constant.ACCOUNTTYPE_ALIPAY;
                        // 根据解析出来的数据验证订单，用户
                        Userorder userorder = userorderRepository.findByOrdercode(tradeNo);
                        //不是待支付订单不能支付
                        if (!Rewrite_Constant.ORDER_WAIT_PAY.equals(userorder.getOrderstatus())) {
                            return;
                        }
                        // 翻转订单状态
                        userorder.setOrderstatus(Rewrite_Constant.ORDER_WAIT_DELIVER); //将订单状态更改成待发货
                        userorder.setPayway(OrderConstant.ALI_PAY);
                        userorder.setPaytime(TimeUtil.getDate());
                        userorderRepository.saveAndFlush(userorder);
                        // 生成支付流水
//                        createFlow(userorder);
                        Rewrite_DistributionDTO rewrite_DistributionDTO = new Rewrite_DistributionDTO(userorder.getSum().toString(),userorder.getId()
                            ,OrderConstant.ALI_PAY);

                        if(userorder.getOther() != null && userorder.getOther().equals("1")){ // 圆帅
                            rewrite_PayService.judgeYuanShuai(rewrite_DistributionDTO);
                        }else{
                            rewrite_PayService.distribution(rewrite_DistributionDTO);
                        }
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
		// 创建自身的收支明细
		createSelfReceiptpay(userorder);
		// 获取分配比例
		Percentage percentageRecommend = percentageRepository.findByName(Rewrite_Constant.PERCENTAGE_RECOMMEND,
				Rewrite_Constant.PERCENTAGE_TYPE_CASH);
		Percentage percentagePartner = percentageRepository.findByName(Rewrite_Constant.PERCENTAGE_PARTNER,
				Rewrite_Constant.PERCENTAGE_TYPE_CASH);
		Percentage percentageBenefit = percentageRepository.findByName(Rewrite_Constant.PERCENTAGE_BENEFIT,
				Rewrite_Constant.PERCENTAGE_TYPE_CASH);
		// 创建商家的收支明细
		createMerchantReceiptpay(userorder, percentageBenefit);
		BigDecimal sumBigDecimal = userorder.getSum();
		// 计算收益手续费
		BigDecimal benefitPercentage = BigDecimal.ONE
				.subtract(new BigDecimal(percentageBenefit.getValue()).divide(BigDecimal.valueOf(100)));
		// 获取推荐人
		Userlinkuser userlinkuser = userlinkuserRepository.findByUserId(userorder.getId());
		// 计算推荐收益
		String percentageRecommendValue = percentageRecommend.getValue();
		BigDecimal amountRecommend = sumBigDecimal.multiply(new BigDecimal(percentageRecommendValue))
				.multiply(benefitPercentage); // 计算后金额
		String recommendId = userlinkuser.getRecommendid();
		createRecommendAndPartner(Rewrite_Constant.DEALTYPE_RECOMMEND, Rewrite_Constant.DEALSTATE_NORMAL, recommendId,
				userorder.getPayee(), amountRecommend);
		// 获取合伙人
		Userlinkuser partner = userlinkuserRepository.findByUserId(userorder.getId());// 先默认取推荐人
		while (partner.isPartner()) {
			String tempRecommendId = partner.getRecommendid();
			partner = userlinkuserRepository.findByUserId(Long.valueOf(tempRecommendId));
		}
		// 计算推荐收益
		String percentagePartnerValue = percentagePartner.getValue();
		BigDecimal amountPartner = sumBigDecimal.multiply(new BigDecimal(percentagePartnerValue))
				.multiply(benefitPercentage); // 计算后金额
		createRecommendAndPartner(Rewrite_Constant.DEALTYPE_PARTNER, Rewrite_Constant.DEALSTATE_NORMAL,
				partner.getUserid(), userorder.getPayee(), amountPartner);
	}

	/**
	 * 创建自身的收支明细记录
	 *
	 * @param userorder
	 */
	private void createSelfReceiptpay(Userorder userorder) {
		Receiptpay receiptpaySelf = new Receiptpay();// 自身的收益流水
		receiptpaySelf.setDealtype(Rewrite_Constant.DEALTYPE_CONSUMPTION);
		receiptpaySelf.setUserid(userorder.getUserid());
		receiptpaySelf.setSourcer(Rewrite_Constant.SOURCE_ALIPAY);
		receiptpaySelf.setBenefit(userorder.getPayee());
		// 获取到的积分
		if (userorder.getRebate() != null) {
			// 现在的积分 = 原本的积分 + 商家积分百分比 * 支付金额
			Userassets userassets = userassetsRepository.findByUserId(userorder.getUserid());
			String originIntegral = userassets.getIntegral();
			BigDecimal rebateBigDecimal = new BigDecimal(userorder.getRebate()).divide(BigDecimal.valueOf(100));
			BigDecimal integralBigDecimal = userorder.getSum().multiply(rebateBigDecimal);
			integralBigDecimal = integralBigDecimal.add(new BigDecimal(originIntegral));
			userassets.setIntegral(integralBigDecimal.toString());
		}
		receiptpaySelf.setAmount(userorder.getSum());
		receiptpaySelf.setHappendate("发生时间");
		receiptpaySelf.setDealstate(Rewrite_Constant.DEALSTATE_NORMAL);
		receiptpaySelf.setCreator("SYSTEM");
		receiptpaySelf.setCreatedate("创建时间");
	}

	/**
	 * 创建商家的收支明细记录
	 *
	 * @param userorder
	 */
	private void createMerchantReceiptpay(Userorder userorder, Percentage percentageBenefit) {
		Userassets userassets = userassetsRepository.findByUserId(userorder.getPayee());
		// 更新余额
		BigDecimal balanceBigDecimal = new BigDecimal(userassets.getBalance());
		// 让利百分比
		BigDecimal afterConcessionBigDecimal = new BigDecimal(100 - userorder.getConcession())
				.divide(BigDecimal.valueOf(100));
		// 提现手续费
		BigDecimal benefitPercentage = BigDecimal.ONE
				.subtract(new BigDecimal(percentageBenefit.getValue()).divide(BigDecimal.valueOf(100)));
		// 收到的金额
		BigDecimal receiveBigDecimal = userorder.getSum().multiply(afterConcessionBigDecimal)
				.multiply(benefitPercentage);
		balanceBigDecimal = balanceBigDecimal.add(receiveBigDecimal);
		userassets.setBalance(balanceBigDecimal.toString());
		Receiptpay receiptpayMerchant = new Receiptpay();
		receiptpayMerchant.setDealtype(Rewrite_Constant.DEALTYPE_RECEIVABLES);
		receiptpayMerchant.setUserid(userorder.getPayee());
		receiptpayMerchant.setSourcer(Rewrite_Constant.SOURCE_ALIPAY);
		receiptpayMerchant.setBenefit(userorder.getUserid());
		receiptpayMerchant.setAmount(userorder.getSum());
		receiptpayMerchant.setHappendate("发生时间");
		receiptpayMerchant.setDealstate(Rewrite_Constant.DEALSTATE_NORMAL);
		receiptpayMerchant.setCreator("SYSTEM");
		receiptpayMerchant.setCreatedate("创建时间");
	}

	/**
	 * 创建推荐人或合伙人的收支明细记录
	 *
	 * @param dealtype
	 * @param dealstate
	 * @param userId
	 * @param benegit
	 * @param bigDecimal
	 */
	private void createRecommendAndPartner(String dealtype, String dealstate, String userId, String benegit,
			BigDecimal bigDecimal) {
		Userassets userassets = userassetsRepository.findByUserId(userId);
		// 更新余额
		BigDecimal balanceBigDecimal = new BigDecimal(userassets.getBalance());
		balanceBigDecimal = balanceBigDecimal.add(bigDecimal);
		userassets.setBalance(balanceBigDecimal.toString());// 更新余额
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

	@Override
	public Result createOrder(CreateOrderDTO createOrderDTO) {
		String orderCode = FinalUtil.createTradeNo(createOrderDTO.getType());
		Userorder userorder = new Userorder();
		userorder.setUserid(createOrderDTO.getUserId());
		userorder.setOrdercode(orderCode);
		userorder.setOrderstatus(Rewrite_Constant.ORDER_WAIT_PAY);
		userorder.setSum(new BigDecimal(createOrderDTO.getSum()));
		return null;
	}

	public String merchantPayment(String authCode, String money, String merchantid, Integer concession,
			Integer rebate, String name) {
		String thisDate = DateUtils.getDateForNow();
		AlipaySystemOauthTokenResponse userInfo = AlipayUtil.getUserInfo(authCode);
		if (userInfo == null)
			return "获取支付宝会员信息失败";
		Linkaccount linkaccount = rewrite_LinkaccountRepository.findFirstByAccounttypeAndToken("支付宝", userInfo.getUserId());// 判断系统是否有这个支付宝
		if (linkaccount == null)
			return "获取支付宝会员信息失败";
		// 1.先创建订单信息
		Userorder userorder = new Userorder();
		userorder.setUserid(linkaccount.getUserid());
		userorder.setSum(new BigDecimal(money));// 设置金额
		userorder.setOrderstatus(Rewrite_Constant.ORDER_WAIT_PAY);// 设置待支付
		userorder.setOrdercode(RandomStringUtils.randomAlphanumeric(32));
		userorder.setPayee(merchantid);
		userorder.setPayway(OrderConstant.ALI_PAY);
		userorder.setConcession(concession);
		userorder.setRebate(rebate);
		userorder.setCreator(linkaccount.getUserid());
		userorder.setCreatedate(thisDate);
		userorder.setModifier(linkaccount.getUserid());
		userorder.setModifierdate(thisDate);
		userorder = userorderRepository.save(userorder);
		if (userorder.getId() != null && userorder.getId() > 0) {
			String subject = name;// 订单名称字段暂时没有，等待加入
			String address = "http://app.yuanscore.com/?result=" + userorder.getOrdercode();
			return AlipayUtil.alipay(userorder.getOrdercode(), subject, userorder.getSum(), address);
		} else {
			return "订单生成错误";
		}
	}

	public Result merchantPaymentWeChat(String userid, String money, String merchantid, Integer concession,
			Integer rebate, String name) {
		String thisDate = DateUtils.getDateForNow();
		Linkaccount linkaccount = rewrite_LinkaccountRepository.findFirstByUseridAndAccounttype(userid,"微信");// 判断系统是否有这个微信
		if (linkaccount == null)
			return Result.fail("获取微信会员信息失败");
		// 1.先创建订单信息
		Userorder userorder = new Userorder();
		userorder.setUserid(linkaccount.getUserid());
		userorder.setSum(new BigDecimal(money));// 设置金额
		userorder.setOrderstatus(Rewrite_Constant.ORDER_WAIT_PAY);// 设置待支付
		userorder.setOrdercode(RandomStringUtils.randomAlphanumeric(32));
		userorder.setPayee(merchantid);
		userorder.setPayway(OrderConstant.WECHAT_PAY);
		userorder.setConcession(concession);
		userorder.setRebate(rebate);
		userorder.setCreator(linkaccount.getUserid());
		userorder.setCreatedate(thisDate);
		userorder.setModifier(linkaccount.getUserid());
		userorder.setModifierdate(thisDate);
		userorder = userorderRepository.save(userorder);
		if (userorder.getId() != null && userorder.getId() > 0) {
            Map<String, String> resultMap = null;
//            String mPrepayId = "";
			String subject = name + "消费购物,祝你购物愉快";// 订单名称字段暂时没有，等待加入
//			String address = "http://app.yuanscore.com/?result=" + userorder.getOrdercode();
//            System.out.println(linkaccount + "微信标识");
            String xml = WechatUtils.packagePayPara(linkaccount.getToken(), subject, userorder.getOrdercode(), "123.12.12.123", money.toString(), WechatUtils.TRADE_TYPE_JSAPI);
            resultMap = WechatUtils.unifiedOrder(xml);
            System.out.println(resultMap);
            String return_code = resultMap.get("return_code");
//            String return_msg = resultMap.get("return_msg");
            String result_code = resultMap.get("result_code");
//            System.out.println("return_code返回的=" + return_code);
//            System.out.println("return_msg返回的=" + return_msg);
//            System.out.println("prepay_id的返回=" + resultMap.get("prepay_id"));
            if ("SUCCESS".equals(return_code) && "SUCCESS".equals(result_code)) {
                JSONObject result = new JSONObject();
                result.put("appId",resultMap.get("appid"));
                result.put("nonceStr",resultMap.get("nonce_str"));
                result.put("package","prepay_id="+resultMap.get("prepay_id"));
                //二次加密开始
                long l = new Date().getTime() / 1000;
                SortedMap<String, String> para = new TreeMap<String, String>();
                para.put("appId",resultMap.get("appid"));
                para.put("timeStamp",String.valueOf(l));
                para.put("nonceStr",resultMap.get("nonce_str"));
                para.put("package","prepay_id="+resultMap.get("prepay_id"));
                para.put("signType","MD5");
                result.put("paySign",WechatUtils.createSign(para));
                //二次加密结束
                result.put("signType","MD5");
                result.put("timeStamp",l);
                System.out.println(result);
                log.debug("正确返回");
                log.debug(result.toString());
                return Result.suc("",result);
            }else {
                return Result.fail("调用微信支付失败,请稍后重试");
            }
		} else {
            return Result.fail("订单生成错误,请稍后重试");
		}
	}

	public Long merchantPaymentYue(String userid, String money, String merchantid, Integer concession,
			Integer rebate) {
		String thisDate = DateUtils.getDateForNow();
		// 1.先创建订单信息
		Userorder userorder = new Userorder();
		userorder.setUserid(userid);
		userorder.setSum(new BigDecimal(money));// 设置金额
		userorder.setOrderstatus(Rewrite_Constant.ORDER_WAIT_PAY);// 设置待支付
		userorder.setOrdercode(RandomStringUtils.randomAlphanumeric(32));
		userorder.setPayee(merchantid);
		userorder.setPayway(OrderConstant.BALANCE_PAY);
		userorder.setConcession(concession);
		userorder.setRebate(rebate);
		userorder.setCreator(userid);
		userorder.setCreatedate(thisDate);
		userorder.setModifier(userid);
		userorder.setModifierdate(thisDate);
		return userorderRepository.save(userorder).getId();
	}

	public Long merchantPaymentCoupon(String userid, String money, String merchantid, Integer concession,
			Integer rebate) {
		String thisDate = DateUtils.getDateForNow();
		// 1.先创建订单信息
		Userorder userorder = new Userorder();
		userorder.setUserid(userid);
		userorder.setSum(new BigDecimal(money));// 设置金额
		userorder.setOrderstatus(Rewrite_Constant.ORDER_WAIT_PAY);// 设置待支付
		userorder.setOrdercode(RandomStringUtils.randomAlphanumeric(32));
		userorder.setPayee(merchantid);
		userorder.setPayway(OrderConstant.COUPON_PAY);
		userorder.setConcession(concession);
		userorder.setRebate(rebate);
		userorder.setCreator(userid);
		userorder.setCreatedate(thisDate);
		userorder.setModifier(userid);
		userorder.setModifierdate(thisDate);
		return userorderRepository.save(userorder).getId();
	}

    public Result weChatRefundNotify(Map<String, String> map) {
        System.out.println("微信开始回调");
        log.debug("微信支付開始回調");
        // 解析微信服务器回调过来的数据。
        String result_code = "";
        String return_code = "";
        String out_trade_no = "";
//        String total_fee = "";
//        String mPayWay = "2";
        result_code = map.get("result_code");
        out_trade_no = map.get("out_trade_no");
        return_code = map.get("return_code");
//        total_fee = map.get("total_fee");
        if ("SUCCESS".equals(result_code) && "SUCCESS".equals(return_code)) {
            //这是成功
            System.out.println("微信调用成功");
            log.debug("微信回调开始");
            Userorder userorder = userorderRepository.findByOrdercode(out_trade_no);
            //不是待支付订单不能支付
            if (!Rewrite_Constant.ORDER_WAIT_PAY.equals(userorder.getOrderstatus())) {
                return Result.fail("订单状态不正确");
            }
            // 翻转订单状态
            userorder.setOrderstatus(Rewrite_Constant.ORDER_WAIT_DELIVER); //将订单状态更改成待发货
            userorder.setPayway(OrderConstant.WECHAT_PAY);
            userorder.setPaytime(TimeUtil.getDate());
            userorderRepository.saveAndFlush(userorder);
            // 生成支付流水
//                        createFlow(userorder);
            Rewrite_DistributionDTO rewrite_DistributionDTO = new Rewrite_DistributionDTO(userorder.getSum().toString(),userorder.getId()
                ,OrderConstant.WECHAT_PAY);

            if(userorder.getOther() != null && userorder.getOther().equals("1")){ // 圆帅
                rewrite_PayService.judgeYuanShuai(rewrite_DistributionDTO);
            }else{
                rewrite_PayService.distribution(rewrite_DistributionDTO);
            }
        }
        else {
            //这是失败
            System.out.println("微信调用失败");

        }
        return Result.suc("支付成功");
    }
}
