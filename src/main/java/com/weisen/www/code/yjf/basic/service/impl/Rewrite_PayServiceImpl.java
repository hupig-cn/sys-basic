package com.weisen.www.code.yjf.basic.service.impl;

import com.weisen.www.code.yjf.basic.domain.*;
import com.weisen.www.code.yjf.basic.repository.*;
import com.weisen.www.code.yjf.basic.service.Rewrite_PayService;
import com.weisen.www.code.yjf.basic.service.dto.submit_dto.Rewrite_DistributionDTO;
import com.weisen.www.code.yjf.basic.service.dto.submit_dto.Rewrite_PayDTO;
import com.weisen.www.code.yjf.basic.service.util.OrderConstant;
import com.weisen.www.code.yjf.basic.service.util.ReceiptpayConstant;
import com.weisen.www.code.yjf.basic.util.Result;
import com.weisen.www.code.yjf.basic.util.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@Transactional
public class Rewrite_PayServiceImpl implements Rewrite_PayService {

    private final Logger log = LoggerFactory.getLogger(Rewrite_PayServiceImpl.class);

    private final Rewrite_000_UserorderRepository userorderRepository;

    private final Rewrite_UserlinkuserRepository userlinkuserRepository;

    private final Rewrite_PercentageRepository percentageRepository;

    private final Rewrite_UserlinkuserRepository rewrite_UserlinkuserRepository;

    private final ReceiptpayRepository receiptpayRepository;

    private final Rewrite_000_UserassetsRepository userassetsRepository;

    private final LinkuserRepository linkuserRepository;

    private PasswordEncoder passwordEncoder;

    private final Rewrite_LinkuserRepository rewrite_LinkuserRepository;

    public Rewrite_PayServiceImpl(Rewrite_000_UserorderRepository userorderRepository, Rewrite_UserlinkuserRepository userlinkuserRepository, Rewrite_PercentageRepository percentageRepository, ReceiptpayRepository receiptpayRepository, Rewrite_000_UserassetsRepository userassetsRepository
            ,LinkuserRepository linkuserRepository,Rewrite_UserlinkuserRepository rewrite_UserlinkuserRepository, PasswordEncoder passwordEncoder, Rewrite_LinkuserRepository rewrite_LinkuserRepository ) {
        this.userorderRepository = userorderRepository;
        this.userlinkuserRepository = userlinkuserRepository;
        this.percentageRepository = percentageRepository;
        this.receiptpayRepository = receiptpayRepository;
        this.userassetsRepository = userassetsRepository;
        this.linkuserRepository = linkuserRepository;
        this.rewrite_UserlinkuserRepository = rewrite_UserlinkuserRepository;
        this.passwordEncoder = passwordEncoder;
        this.rewrite_LinkuserRepository = rewrite_LinkuserRepository;
    }
    // 余额支付
    @Override
    public Result BalencePayment(Rewrite_PayDTO rewrite_PayDTO) {

//        Userorder userorder = userorderRepository.findById(orederId);
        Optional<Userorder> option = userorderRepository.findById(rewrite_PayDTO.getOrderid());
        if(!option.isPresent()){
            return  Result.fail("订单不存在");
        }
        Userorder userorder = option.get();
        Linkuser linkuser = rewrite_LinkuserRepository.findByUserid(userorder.getUserid());
        if(!passwordEncoder.matches(rewrite_PayDTO.getPassword(),linkuser.getPaypassword())){
            return  Result.fail("支付密码错误");
        }

        Userlinkuser userlinkuser = rewrite_UserlinkuserRepository.findByUserid(userorder.getUserid());
        if(null != userorder.getOther() && userorder.getOther().equals("1") && userlinkuser.isPartner() == true){
            return  Result.fail("用户已经是圆帅");
        }

        // 我的资产
        Userassets userassets = userassetsRepository.findByUserId(userorder.getUserid());
        int num = userorder.getSum().compareTo(new BigDecimal(userassets.getUsablebalance()));
        if( num > 0 ){
            return  Result.fail("您的余额不足");
        }
        //翻转订单状态
        userorder.setPayway(OrderConstant.BALANCE_PAY);
        userorder.setPaytime(TimeUtil.getDate());
        userorder.setOrderstatus(OrderConstant.PAID);
        userorderRepository.saveAndFlush(userorder);

        // 支出流水
        Receiptpay receiptpay = new Receiptpay();
        receiptpay.setAmount(userorder.getSum());
        receiptpay.setDealtype(ReceiptpayConstant.BALANCE_PAY); //余额支出
        receiptpay.setDealstate(ReceiptpayConstant.PAY);
        receiptpay.setPayway(userorder.getPayway());
        receiptpay.setUserid(userorder.getUserid());
        receiptpay.setCreatedate(TimeUtil.getDate());
        receiptpayRepository.save(receiptpay);

        //更新我的资产
        userassets.setUsablebalance((new BigDecimal(userassets.getUsablebalance()).subtract(userorder.getSum())).setScale(3).toString());
        userassets.setBalance((new BigDecimal(userassets.getBalance()).subtract(userorder.getSum())).setScale(3).toString());
        userassetsRepository.save(userassets);

        Rewrite_DistributionDTO rewrite_DistributionDTO = new Rewrite_DistributionDTO(userorder.getSum().toString(),userorder.getId(),userorder.getPayway());

        if(null != userorder.getOther()
        		&& userorder.getOther().equals("1")){ // 圆帅
            judgeYuanShuai(rewrite_DistributionDTO);
        }else{
            distribution(rewrite_DistributionDTO);
        }

        return Result.suc("支付成功");
    }


    // 积分支付
    @Override
    public Result IntegralPayment(Rewrite_PayDTO rewrite_PayDTO) {
        Optional<Userorder> option = userorderRepository.findById(rewrite_PayDTO.getOrderid());
        if(!option.isPresent()){
            return  Result.fail("订单不存在");
        }
        Userorder userorder = option.get();
        Linkuser linkuser = rewrite_LinkuserRepository.findByUserid(userorder.getUserid());
        if(!passwordEncoder.matches(rewrite_PayDTO.getPassword(),linkuser.getPaypassword())){
            return  Result.fail("支付密码错误");
        }

        if(userorder.getOther().equals("1")){
            return  Result.fail("开通圆帅不能使用此支付方式");
        }

        // 我的资产
        Userassets userassets = userassetsRepository.findByUserId(userorder.getUserid());
        int num = userorder.getSum().compareTo(new BigDecimal(userassets.getIntegral()));

        if( num > 0 ){
            return  Result.fail("您的积分不足");
        }
        //翻转订单状态
        userorder.setPayway(OrderConstant.INTEGRAL_PAY);
        userorder.setPaytime(TimeUtil.getDate());
        userorder.setOrderstatus(OrderConstant.PAID);
        userorderRepository.saveAndFlush(userorder);

        // 支出流水
        Receiptpay receiptpay = new Receiptpay();
        receiptpay.setAmount(userorder.getSum());
        receiptpay.setDealtype(ReceiptpayConstant.INTEGRAL_PAY); //积分支出
        receiptpay.setDealstate(ReceiptpayConstant.PAY);
        receiptpay.setPayway(userorder.getPayway());
        receiptpay.setUserid(userorder.getUserid());
        receiptpay.setCreatedate(TimeUtil.getDate());
        receiptpayRepository.save(receiptpay);

        //更新我的资产
        userassets.setIntegral((new BigDecimal(userassets.getIntegral()).subtract(userorder.getSum()).setScale(3).toString()));
        userassetsRepository.save(userassets);


        return Result.suc("支付成功");
    }

    // 优惠券支付
    @Override
    public Result couponPayment(Rewrite_PayDTO rewrite_PayDTO) {
        Optional<Userorder> option = userorderRepository.findById(rewrite_PayDTO.getOrderid());
        if(!option.isPresent()){
            return  Result.fail("订单不存在");
        }
        Userorder userorder = option.get();
        Linkuser linkuser = rewrite_LinkuserRepository.findByUserid(userorder.getUserid());
        if(!passwordEncoder.matches(rewrite_PayDTO.getPassword(),linkuser.getPaypassword())){
            return  Result.fail("支付密码错误");
        }

        if(userorder.getOther().equals("1")){
            return  Result.fail("开通圆帅不能使用此支付方式");
        }

        // 我的资产
        Userassets userassets = userassetsRepository.findByUserId(userorder.getUserid());
        int num = userorder.getSum().compareTo(new BigDecimal(userassets.getCouponsum()));

        if( num > 0 ){
            return  Result.fail("您的优惠券不足");
        }
        //翻转订单状态
        userorder.setPayway(OrderConstant.COUPON_PAY);
        userorder.setPaytime(TimeUtil.getDate());
        userorder.setOrderstatus(OrderConstant.PAID);
        userorderRepository.saveAndFlush(userorder);

        // 支出流水
        Receiptpay receiptpay = new Receiptpay();
        receiptpay.setAmount(userorder.getSum());
        receiptpay.setDealtype(ReceiptpayConstant.COUPON_PAY); // 优惠券支出
        receiptpay.setDealstate(ReceiptpayConstant.PAY);
        receiptpay.setPayway(userorder.getPayway());
        receiptpay.setUserid(userorder.getUserid());
        receiptpay.setCreatedate(TimeUtil.getDate());
        receiptpayRepository.save(receiptpay);

        //更新我的资产
        userassets.setCouponsum((new BigDecimal(userassets.getCouponsum()).subtract(userorder.getSum()).setScale(3).toString()));
        userassetsRepository.save(userassets);

        if(userorder.getPayee() != null && !"".equals(userorder.getPayee())) {
            BigDecimal am = userorder.getSum();
            BigDecimal mBigPrice = userorder.getSum();
            BigDecimal ma = new BigDecimal(userorder.getConcession());
            ma = ma.divide(new BigDecimal("100"));
            mBigPrice = mBigPrice.multiply(ma).setScale(3, BigDecimal.ROUND_HALF_UP);
            mBigPrice = am.subtract(mBigPrice);
            craeteReceiptpay(ReceiptpayConstant.BALANCE_INCOME, userorder.getUserid(), userorder.getPayee(), mBigPrice,userorder.getPayway());

        }
        return Result.suc("支付成功");
    }

    /**
     * 支付后的分销
     * @param rewrite_DistributionDTO
     * @return
     */
    @Override
    public Result distribution(Rewrite_DistributionDTO rewrite_DistributionDTO) {
        Userorder userorder = userorderRepository.getOne(rewrite_DistributionDTO.getOrderId());
        if(userorder == null || userorder.getOrderstatus().equals(OrderConstant.UN_PAID)){
            return Result.fail("订单不存在或订单未支付");
        }

        // ** 先给支付者方面分销 推荐人，千分之5 ，合伙人，千分之4 合伙人没有需要无限层往上找
        // 付款人关系
        Userlinkuser userlinkuser = rewrite_UserlinkuserRepository.findByUserid(userorder.getUserid());

        // 如何付款用户没有推荐人，把给第一个付款的商家用户自动绑定
        if((null  == userlinkuser.getRecommendid() || "".equals(userlinkuser.getRecommendid())) && null != userorder.getPayee()){
            userlinkuser.setRecommendid(userorder.getPayee());
            userlinkuser = rewrite_UserlinkuserRepository.saveAndFlush(userlinkuser);
        }

        // 支付者推荐人分销
        if(userlinkuser.getRecommendid() != null && !"".equals(userlinkuser.getRecommendid()) && !"1".equals(userlinkuser.getRecommendid())){
            BigDecimal mBigPrice = new BigDecimal(rewrite_DistributionDTO.getAmount());
            BigDecimal ma = new BigDecimal("5");
            ma = ma.divide(new BigDecimal("1000"));
            mBigPrice = mBigPrice.multiply(ma).setScale(3, BigDecimal.ROUND_HALF_UP);
            craeteReceiptpay(ReceiptpayConstant.BALANCE_INCOME_DIR,userorder.getUserid(),userlinkuser.getRecommendid(),mBigPrice,rewrite_DistributionDTO.getPayWay());
        }
        // 是否有合伙人
        String id = findPartner(userorder.getUserid());
        if(id != null){
            BigDecimal mBigPrice = new BigDecimal(rewrite_DistributionDTO.getAmount());
            BigDecimal ma = new BigDecimal("4");
            ma = ma.divide(new BigDecimal("1000"));
            mBigPrice = mBigPrice.multiply(ma).setScale(3, BigDecimal.ROUND_HALF_UP);
            craeteReceiptpay(ReceiptpayConstant.BALANCE_INCOME_PER,userorder.getUserid(),id,mBigPrice,rewrite_DistributionDTO.getPayWay());
        }
        // 上面支付者的分销分完了  下面如果有收款方 还需要再分一轮
        if(userorder.getPayee() != null && !"".equals(userorder.getPayee())){
            BigDecimal am = new BigDecimal(rewrite_DistributionDTO.getAmount());
            BigDecimal mBigPrice = new BigDecimal(rewrite_DistributionDTO.getAmount());
            BigDecimal ma = new BigDecimal(userorder.getConcession());
            ma = ma.divide(new BigDecimal("100"));
            mBigPrice = mBigPrice.multiply(ma).setScale(3, BigDecimal.ROUND_HALF_UP);
            mBigPrice = am.subtract(mBigPrice);
            craeteReceiptpay(ReceiptpayConstant.BALANCE_INCOME,userorder.getUserid(),userorder.getPayee(),mBigPrice,rewrite_DistributionDTO.getPayWay());

            Userlinkuser payeeuserlinkuser = rewrite_UserlinkuserRepository.findByUserid(userorder.getPayee());

            // 收款方推荐人分销
            if(payeeuserlinkuser.getRecommendid() != null && !"".equals(payeeuserlinkuser.getRecommendid()) && !"1".equals(payeeuserlinkuser.getRecommendid())){
                BigDecimal mPrice = new BigDecimal(rewrite_DistributionDTO.getAmount());
                BigDecimal kma = new BigDecimal("5");
                kma = kma.divide(new BigDecimal("1000"));
                mPrice = mPrice.multiply(kma).setScale(3, BigDecimal.ROUND_HALF_UP);
                craeteReceiptpay(ReceiptpayConstant.BALANCE_INCOME_DIR,userorder.getPayee(),payeeuserlinkuser.getRecommendid(),mPrice,rewrite_DistributionDTO.getPayWay());
            }
            // 是否有合伙人
            String kid = findPartner(userorder.getPayee());
            if(kid != null){
                BigDecimal mPrice = new BigDecimal(rewrite_DistributionDTO.getAmount());
                BigDecimal kma = new BigDecimal("4");
                kma = kma.divide(new BigDecimal("1000"));
                mPrice = mPrice.multiply(kma).setScale(3, BigDecimal.ROUND_HALF_UP);
                craeteReceiptpay(ReceiptpayConstant.BALANCE_INCOME_PER,userorder.getPayee(),kid,mPrice,rewrite_DistributionDTO.getPayWay());
            }

            //分配积分，
            if(userorder.getPayee() != null && !"".equals(userorder.getPayee())){ // 线下
                handleIntgerl(userorder.getRebate().toString(),userorder.getUserid(),userorder.getSum(),rewrite_DistributionDTO.getPayWay());
            }else{  // 线上
                handleIntgerl("50",userorder.getUserid(),userorder.getSum(),rewrite_DistributionDTO.getPayWay());
                //分配优惠券
                handleCoupon("50",userorder.getUserid(),userorder.getSum(),rewrite_DistributionDTO.getPayWay());
            }

        }

        return Result.suc("成功");
    }



    // 创建收支明细
    private void craeteReceiptpay(String Dealtype,String userId,String sourcerId,BigDecimal amount,String payway){
        Receiptpay receiptpay = new Receiptpay();
        receiptpay.setDealtype(Dealtype);
        receiptpay.setDealstate(ReceiptpayConstant.INCOME);
        receiptpay.setUserid(sourcerId);
        receiptpay.setAmount(amount);
        receiptpay.setSourcer(userId);
        receiptpay.setCreatedate(TimeUtil.getDate());
        receiptpay.setLogicdelete(false);
        receiptpayRepository.save(receiptpay);

        Userassets userassets = userassetsRepository.findByUserId(sourcerId);
        BigDecimal balance = new BigDecimal(userassets.getBalance());
        balance = balance.add(amount);
        userassets.setBalance(balance.toString());
        BigDecimal usebalance = new BigDecimal(userassets.getUsablebalance());
        usebalance = usebalance.add(amount);
        userassets.setUsablebalance(usebalance.toString());
        userassetsRepository.saveAndFlush(userassets);
    }

    // 判断用户是否有上层合伙人
    private String findPartner(String userId){
        Userlinkuser userlinkuser = rewrite_UserlinkuserRepository.findByUserid(userId);
        if(null != userlinkuser){
            if(null != userlinkuser.isPartner() && userlinkuser.isPartner() == true){
                return userId;
            }else if(userlinkuser.getRecommendid() != null && !"".equals(userlinkuser.getRecommendid()) && !"1".equals(userlinkuser.getRecommendid())){
                return findPartner(userlinkuser.getRecommendid());
            }
        }
        return null;
    }

    // 积分处理
    private void handleIntgerl(String percentage,String userId,BigDecimal amount,String payway){
        BigDecimal mPrice = amount;
        BigDecimal kma = new BigDecimal(percentage);
        kma = kma.divide(new BigDecimal("100"));
        mPrice = mPrice.multiply(kma).setScale(3, BigDecimal.ROUND_HALF_UP);

        Userassets userassets = userassetsRepository.findByUserId(userId);
        BigDecimal am = new BigDecimal(userassets.getIntegral());
        BigDecimal sum = mPrice;

        mPrice = am.add(mPrice);
        userassets.setIntegral(mPrice.toString());
        userassetsRepository.saveAndFlush(userassets);

        Receiptpay receiptpay = new Receiptpay();
        receiptpay.setAmount(sum);
        receiptpay.setDealtype(ReceiptpayConstant.INTEGRAL_GET); // 积分收入
        receiptpay.setDealstate(ReceiptpayConstant.INCOME);
        receiptpay.setUserid(userId);
        receiptpay.setPayway(payway);
        receiptpay.setCreatedate(TimeUtil.getDate());
        receiptpayRepository.save(receiptpay);
    }

    // 优惠券处理
    private void handleCoupon(String percentage,String userId,BigDecimal amount,String payway){
        BigDecimal mPrice = amount;
        BigDecimal kma = new BigDecimal(percentage);
        kma = kma.divide(new BigDecimal("100"));
        mPrice = mPrice.multiply(kma).setScale(3, BigDecimal.ROUND_HALF_UP);

        Userassets userassets = userassetsRepository.findByUserId(userId);
        BigDecimal am = new BigDecimal(userassets.getCouponsum());
        BigDecimal sum = mPrice;

        mPrice = am.add(mPrice);
        userassets.setCouponsum(mPrice.toString());
        userassetsRepository.saveAndFlush(userassets);

        Receiptpay receiptpay = new Receiptpay();
        receiptpay.setAmount(sum);
        receiptpay.setPayway(payway);
        receiptpay.setDealtype(ReceiptpayConstant.COUPON_GET); // 优惠券收入
        receiptpay.setDealstate(ReceiptpayConstant.INCOME);
        receiptpay.setUserid(userId);
        receiptpay.setCreatedate(TimeUtil.getDate());
        receiptpayRepository.save(receiptpay);
    }


    //订单商品是元帅的流程
    public Result judgeYuanShuai(Rewrite_DistributionDTO rewrite_DistributionDTO){
        Userorder userorder = userorderRepository.getOne(rewrite_DistributionDTO.getOrderId());
        if(userorder == null || userorder.getOrderstatus().equals(OrderConstant.UN_PAID)){
            return Result.fail("订单不存在或订单未支付");
        }

        // 付款人关系
        Userlinkuser userlinkuser = rewrite_UserlinkuserRepository.findByUserid(userorder.getUserid());
        userlinkuser.setPartner(true);
        rewrite_UserlinkuserRepository.saveAndFlush(userlinkuser);

        if(userlinkuser.getRecommendid() != null && !"".equals(userlinkuser.getRecommendid())){
            String id = findPartner(userlinkuser.getRecommendid());
            if(id != null){
                BigDecimal mBigPrice = new BigDecimal(rewrite_DistributionDTO.getAmount());
                BigDecimal ma = new BigDecimal("25");
                ma = ma.divide(new BigDecimal("100"));
                mBigPrice = mBigPrice.multiply(ma).setScale(3, BigDecimal.ROUND_HALF_UP);
                craeteReceiptpay(ReceiptpayConstant.BALANCE_INCOME_PER,userorder.getUserid(),id,mBigPrice,rewrite_DistributionDTO.getPayWay());
            }
        }
        return Result.suc("成功");
    }



}
