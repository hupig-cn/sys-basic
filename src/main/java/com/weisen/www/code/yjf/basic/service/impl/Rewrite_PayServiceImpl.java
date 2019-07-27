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
import jdk.nashorn.internal.runtime.options.Option;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    public Rewrite_PayServiceImpl(Rewrite_000_UserorderRepository userorderRepository, Rewrite_UserlinkuserRepository userlinkuserRepository, Rewrite_PercentageRepository percentageRepository, ReceiptpayRepository receiptpayRepository, Rewrite_000_UserassetsRepository userassetsRepository
            ,LinkuserRepository linkuserRepository,Rewrite_UserlinkuserRepository rewrite_UserlinkuserRepository ) {
        this.userorderRepository = userorderRepository;
        this.userlinkuserRepository = userlinkuserRepository;
        this.percentageRepository = percentageRepository;
        this.receiptpayRepository = receiptpayRepository;
        this.userassetsRepository = userassetsRepository;
        this.linkuserRepository = linkuserRepository;
        this.rewrite_UserlinkuserRepository = rewrite_UserlinkuserRepository;
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
        Linkuser linkuser = linkuserRepository.getOne(Long.valueOf(userorder.getUserid()));
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if(!passwordEncoder.matches(rewrite_PayDTO.getPassword(),linkuser.getPaypassword())){
            return  Result.fail("支付密码错误");
        }

        // 我的资产
        Userassets userassets = userassetsRepository.findByUserId(userorder.getUserid());
        int num = userorder.getSum().compareTo(new BigDecimal(Integer.valueOf(userassets.getUsablebalance())));

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
        receiptpay.dealstate(ReceiptpayConstant.BALANCE_PAY); //余额支出
        receiptpay.setUserid(userorder.getUserid());
        receiptpay.setCreatedate(TimeUtil.getDate());
        receiptpayRepository.save(receiptpay);

        //更新我的资产
        userassets.setUsablebalance(new BigDecimal(Integer.valueOf(userassets.getUsablebalance())).subtract(userorder.getSum()).toString());
        userassets.setBalance(new BigDecimal(Integer.valueOf(userassets.getBalance())).subtract(userorder.getSum()).toString());
        userassetsRepository.save(userassets);

        Rewrite_DistributionDTO rewrite_DistributionDTO = new Rewrite_DistributionDTO(userorder.getSum().toString(),userorder.getId(),userorder.getPayway(),rewrite_PayDTO.getConcession(),rewrite_PayDTO.getRebate());
        distribution(rewrite_DistributionDTO);

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
        Linkuser linkuser = linkuserRepository.getOne(Long.valueOf(userorder.getUserid()));
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if(!passwordEncoder.matches(rewrite_PayDTO.getPassword(),linkuser.getPaypassword())){
            return  Result.fail("支付密码错误");
        }

        // 我的资产
        Userassets userassets = userassetsRepository.findByUserId(userorder.getUserid());
        int num = Integer.valueOf(rewrite_PayDTO.getIntegral()).compareTo(Integer.valueOf(userassets.getIntegral()));

        if( num > 0 ){
            return  Result.fail("您的积分不足");
        }
        //翻转订单状态
        userorder.setPayway(OrderConstant.BALANCE_PAY);
        userorder.setPaytime(TimeUtil.getDate());
        userorder.setOrderstatus(OrderConstant.PAID);
        userorderRepository.saveAndFlush(userorder);

        // 支出流水
        Receiptpay receiptpay = new Receiptpay();
        receiptpay.setAmount(userorder.getSum());
        receiptpay.dealstate(ReceiptpayConstant.INTEGRAL_PAY); //积分支出
        receiptpay.setUserid(userorder.getUserid());
        receiptpay.setCreatedate(TimeUtil.getDate());
        receiptpayRepository.save(receiptpay);

        //更新我的资产
        userassets.setIntegral(userassets.getIntegral().substring(Integer.valueOf(rewrite_PayDTO.getIntegral())));
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
        Linkuser linkuser = linkuserRepository.getOne(Long.valueOf(userorder.getUserid()));
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if(!passwordEncoder.matches(rewrite_PayDTO.getPassword(),linkuser.getPaypassword())){
            return  Result.fail("支付密码错误");
        }

        // 我的资产
        Userassets userassets = userassetsRepository.findByUserId(userorder.getUserid());
        int num = Integer.valueOf(rewrite_PayDTO.getIntegral()).compareTo(Integer.valueOf(userassets.getCouponsum()));

        if( num > 0 ){
            return  Result.fail("您的积分不足");
        }
        //翻转订单状态
        userorder.setPayway(OrderConstant.BALANCE_PAY);
        userorder.setPaytime(TimeUtil.getDate());
        userorder.setOrderstatus(OrderConstant.PAID);
        userorderRepository.saveAndFlush(userorder);

        // 支出流水
        Receiptpay receiptpay = new Receiptpay();
        receiptpay.setAmount(userorder.getSum());
        receiptpay.dealstate(ReceiptpayConstant.COUPON_PAY); // 优惠券支出
        receiptpay.setUserid(userorder.getUserid());
        receiptpay.setCreatedate(TimeUtil.getDate());
        receiptpayRepository.save(receiptpay);

        //更新我的资产
        userassets.setCouponsum(userassets.getCouponsum().substring(Integer.valueOf(rewrite_PayDTO.getIntegral())));
        userassetsRepository.save(userassets);


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
        if(userlinkuser.getRecommendid() ==null || "".equals(userlinkuser.getRecommendid())){
            userlinkuser.setRecommendid(userorder.getPayee());
            userlinkuser = rewrite_UserlinkuserRepository.saveAndFlush(userlinkuser);
        }

        // 支付者推荐人分销
        if(userlinkuser.getRecommendid() != null && !"".equals(userlinkuser.getRecommendid()) ){
            BigDecimal mBigPrice = new BigDecimal(rewrite_DistributionDTO.getAmount());
            BigDecimal ma = new BigDecimal("5");
            ma = ma.divide(new BigDecimal("1000"));
            mBigPrice = mBigPrice.multiply(ma).setScale(3, BigDecimal.ROUND_HALF_UP);
            craeteReceiptpay(ReceiptpayConstant.BALANCE_INCOME_DIR,userorder.getUserid(),userlinkuser.getRecommendid(),mBigPrice);
        }
        // 是否有合伙人
        String id = findPartner(userlinkuser.getRecommendid());
        if(id != null){
            BigDecimal mBigPrice = new BigDecimal(rewrite_DistributionDTO.getAmount());
            BigDecimal ma = new BigDecimal("4");
            ma = ma.divide(new BigDecimal("1000"));
            mBigPrice = mBigPrice.multiply(ma).setScale(3, BigDecimal.ROUND_HALF_UP);
            craeteReceiptpay(ReceiptpayConstant.BALANCE_INCOME_DIR,userorder.getUserid(),id,mBigPrice);
        }
        // 上面支付者的分销分完了  下面如果有收款方 还需要再分一轮
        if(userorder.getPayee() != null && !"".equals(userorder.getPayee())){
            BigDecimal am = new BigDecimal(rewrite_DistributionDTO.getAmount());
            BigDecimal mBigPrice = new BigDecimal(rewrite_DistributionDTO.getAmount());
            BigDecimal ma = new BigDecimal(rewrite_DistributionDTO.getConcession());
            ma = ma.divide(new BigDecimal("100"));
            mBigPrice = mBigPrice.multiply(ma).setScale(3, BigDecimal.ROUND_HALF_UP);
            mBigPrice = am.subtract(mBigPrice);
            craeteReceiptpay(ReceiptpayConstant.BALANCE_INCOME,userorder.getUserid(),userorder.getPayee(),mBigPrice);

            Userlinkuser payeeuserlinkuser = rewrite_UserlinkuserRepository.findByUserid(userorder.getUserid());

            // 收款方推荐人分销
            if(payeeuserlinkuser.getRecommendid() != null && !"".equals(payeeuserlinkuser.getRecommendid())){
                BigDecimal mPrice = new BigDecimal(rewrite_DistributionDTO.getAmount());
                BigDecimal kma = new BigDecimal("5");
                kma = kma.divide(new BigDecimal("1000"));
                mPrice = mPrice.multiply(kma).setScale(3, BigDecimal.ROUND_HALF_UP);
                craeteReceiptpay(ReceiptpayConstant.BALANCE_INCOME_DIR,userorder.getUserid(),payeeuserlinkuser.getRecommendid(),mPrice);
            }
            // 是否有合伙人
            String kid = findPartner(payeeuserlinkuser.getRecommendid());
            if(kid != null){
                BigDecimal mPrice = new BigDecimal(rewrite_DistributionDTO.getAmount());
                BigDecimal kma = new BigDecimal("4");
                kma = kma.divide(new BigDecimal("1000"));
                mPrice = mPrice.multiply(kma).setScale(3, BigDecimal.ROUND_HALF_UP);
                craeteReceiptpay(ReceiptpayConstant.BALANCE_INCOME_DIR,userorder.getUserid(),kid,mPrice);
            }

            //分配积分，
            if(userorder.getPayee() != null && !"".equals(userorder.getPayee())){ // 线下
                handleIntgerl(userorder.getRebate().toString(),userorder.getUserid(),userorder.getSum());
            }else{  // 线上
                handleIntgerl("50",userorder.getUserid(),userorder.getSum());
                //分配优惠券
                handleCoupon("50",userorder.getUserid(),userorder.getSum());
            }

        }

        return Result.suc("成功");
    }



    // 创建收支明细
    private void craeteReceiptpay(String Dealtype,String userId,String sourcerId,BigDecimal amount){
        Receiptpay receiptpay = new Receiptpay();
        receiptpay.setDealtype(Dealtype);
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
            if(null != userlinkuser.isPartner() || userlinkuser.isPartner() == true){
                return userId;
            }else if(userlinkuser.getRecommendid() != null && !"".equals(userlinkuser.getRecommendid())){
                findPartner(userlinkuser.getRecommendid());
            }
        }
        return null;
    }

    // 积分处理
    private void handleIntgerl(String percentage,String userId,BigDecimal amount){
        BigDecimal mPrice = amount;
        BigDecimal kma = new BigDecimal(percentage);
        kma = kma.divide(new BigDecimal("100"));
        mPrice = mPrice.multiply(kma).setScale(2, BigDecimal.ROUND_HALF_UP);

        Userassets userassets = userassetsRepository.findByUserId(userId);
        BigDecimal am = new BigDecimal(userassets.getIntegral());
        mPrice = am.add(mPrice);
        userassets.setIntegral(mPrice.toString());
        userassetsRepository.saveAndFlush(userassets);

        Receiptpay receiptpay = new Receiptpay();
        receiptpay.setAmount(amount);
        receiptpay.dealstate(ReceiptpayConstant.INTEGRAL_GET); // 积分收入
        receiptpay.setUserid(userId);
        receiptpay.setCreatedate(TimeUtil.getDate());
        receiptpayRepository.save(receiptpay);
    }

    // 优惠券处理
    private void handleCoupon(String percentage,String userId,BigDecimal amount){
        BigDecimal mPrice = amount;
        BigDecimal kma = new BigDecimal(percentage);
        kma = kma.divide(new BigDecimal("100"));
        mPrice = mPrice.multiply(kma).setScale(2, BigDecimal.ROUND_HALF_UP);

        Userassets userassets = userassetsRepository.findByUserId(userId);
        BigDecimal am = new BigDecimal(userassets.getCouponsum());
        mPrice = am.add(mPrice);
        userassets.setCouponsum(mPrice.toString());
        userassetsRepository.saveAndFlush(userassets);

        Receiptpay receiptpay = new Receiptpay();
        receiptpay.setAmount(amount);
        receiptpay.dealstate(ReceiptpayConstant.COUPON_GET); // 优惠券收入
        receiptpay.setUserid(userId);
        receiptpay.setCreatedate(TimeUtil.getDate());
        receiptpayRepository.save(receiptpay);
    }



}
