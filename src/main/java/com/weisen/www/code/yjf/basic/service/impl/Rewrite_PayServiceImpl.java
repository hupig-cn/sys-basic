package com.weisen.www.code.yjf.basic.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weisen.www.code.yjf.basic.config.Constants;
import com.weisen.www.code.yjf.basic.domain.ActivityCon;
import com.weisen.www.code.yjf.basic.domain.ActivityPay;
import com.weisen.www.code.yjf.basic.domain.ActivitySer;
import com.weisen.www.code.yjf.basic.domain.Linkuser;
import com.weisen.www.code.yjf.basic.domain.Receiptpay;
import com.weisen.www.code.yjf.basic.domain.Userassets;
import com.weisen.www.code.yjf.basic.domain.Userlinkuser;
import com.weisen.www.code.yjf.basic.domain.Userorder;
import com.weisen.www.code.yjf.basic.repository.ActivityConRepository;
import com.weisen.www.code.yjf.basic.repository.ActivityPayRepository;
import com.weisen.www.code.yjf.basic.repository.ActivitySerRepository;
import com.weisen.www.code.yjf.basic.repository.ReceiptpayRepository;
import com.weisen.www.code.yjf.basic.repository.Rewrite_000_UserassetsRepository;
import com.weisen.www.code.yjf.basic.repository.Rewrite_000_UserorderRepository;
import com.weisen.www.code.yjf.basic.repository.Rewrite_LinkuserRepository;
import com.weisen.www.code.yjf.basic.repository.Rewrite_UserlinkuserRepository;
import com.weisen.www.code.yjf.basic.service.Rewrite_PayService;
import com.weisen.www.code.yjf.basic.service.dto.submit_dto.Rewrite_DistributionDTO;
import com.weisen.www.code.yjf.basic.service.dto.submit_dto.Rewrite_PayDTO;
import com.weisen.www.code.yjf.basic.service.rewrite.Rewrite_InformationService;
import com.weisen.www.code.yjf.basic.service.rewrite.dto.Rewrite_submitInformationDTO;
import com.weisen.www.code.yjf.basic.service.util.OrderConstant;
import com.weisen.www.code.yjf.basic.service.util.ReceiptpayConstant;
import com.weisen.www.code.yjf.basic.util.Result;
import com.weisen.www.code.yjf.basic.util.TimeUtil;

@Service
@Transactional
public class Rewrite_PayServiceImpl implements Rewrite_PayService {

    private final Rewrite_000_UserorderRepository userorderRepository;

    private final Rewrite_UserlinkuserRepository rewrite_UserlinkuserRepository;

    private final ReceiptpayRepository receiptpayRepository;

    private final Rewrite_000_UserassetsRepository userassetsRepository;

    private final PasswordEncoder passwordEncoder;
    
    private final ActivityConRepository activityConRepository;
    
    private final ActivityPayRepository activityPayRepository;

    private final ActivitySerRepository activitySerRepository;

    private final Rewrite_LinkuserRepository rewrite_LinkuserRepository;

    private final Rewrite_InformationService rewrite_informationService;

    public Rewrite_PayServiceImpl(Rewrite_000_UserorderRepository userorderRepository,
    		Rewrite_UserlinkuserRepository rewrite_UserlinkuserRepository,
    		ReceiptpayRepository receiptpayRepository,
    		Rewrite_000_UserassetsRepository userassetsRepository,
    		ActivityConRepository activityConRepository,
    		ActivityPayRepository activityPayRepository,
    		ActivitySerRepository activitySerRepository,
    		PasswordEncoder passwordEncoder,
    		Rewrite_LinkuserRepository rewrite_LinkuserRepository,
    		Rewrite_InformationService rewrite_informationService) {
        this.userorderRepository = userorderRepository;
        this.receiptpayRepository = receiptpayRepository;
        this.userassetsRepository = userassetsRepository;
        this.rewrite_UserlinkuserRepository = rewrite_UserlinkuserRepository;
        this.activityConRepository = activityConRepository;
        this.activityPayRepository = activityPayRepository;
        this.activitySerRepository = activitySerRepository;
        this.passwordEncoder = passwordEncoder;
        this.rewrite_LinkuserRepository = rewrite_LinkuserRepository;
        this.rewrite_informationService = rewrite_informationService;
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
        
		double sum = userorderRepository.findOneSumById(rewrite_PayDTO.getOrderid());
		if (sum < 0) {
			return Result.fail("订单异常，总金额为负数");
		}
		if (userorder.getOther() != null && !userorder.getOther().equals("")) {
			double price = userorderRepository.queryprice(rewrite_PayDTO.getOrderid());
			double num1 = userorderRepository.queryunm(rewrite_PayDTO.getOrderid());
			if (num1 < 0) {
				return Result.fail("订单异常，数量为负数");
			}
			if (num1 * price != sum) {
				return Result.fail("订单异常，请尝试单个购买");
			}
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

        Rewrite_submitInformationDTO rewrite_submitInformationDTO = new Rewrite_submitInformationDTO(
            Constants.CONSUMPTION.toString(),
            userorder.getUserid(),
            userorder.getUserid(),
            "消费了"+userorder.getSum().toString()+"元余额。",
            null
        );
        rewrite_informationService.insertInformation(rewrite_submitInformationDTO);

        //更新我的资产
        userassets.setUsablebalance((new BigDecimal(userassets.getUsablebalance()).subtract(userorder.getSum())).setScale(3).toString());
        userassets.setBalance((new BigDecimal(userassets.getBalance()).subtract(userorder.getSum())).setScale(3).toString());
        userassetsRepository.save(userassets);

        // 支出流水
        Receiptpay receiptpay = new Receiptpay();
        receiptpay.setAmount(userorder.getSum());
        receiptpay.setDealtype(ReceiptpayConstant.BALANCE_PAY); //余额支出
        receiptpay.setDealstate(ReceiptpayConstant.PAY);
        receiptpay.setPayway(userorder.getPayway());
        receiptpay.setUserid(userorder.getUserid());
        receiptpay.setCreatedate(TimeUtil.getDate());
        receiptpay.setBalance(new BigDecimal(userassets.getBalance()));
        receiptpay.setCoupon(new BigDecimal(userassets.getCouponsum()));
        receiptpay.setFreezebalance(new BigDecimal(userassets.getFrozenbalance()));
        receiptpay.setIntegral(new BigDecimal(userassets.getIntegral()));
        receiptpay.setUseablebalance(new BigDecimal(userassets.getUsablebalance()));
        receiptpay.setOther(userorder.getPayee());
        receiptpay.setExplain("余额付款"+userorder.getSum()+"元");
        receiptpayRepository.save(receiptpay);

        Rewrite_DistributionDTO rewrite_DistributionDTO = new Rewrite_DistributionDTO(userorder.getSum().toString(),userorder.getId(),userorder.getPayway());

        if(null != userorder.getOther()
        		&& userorder.getOther().equals("1")){ // 圆帅
            judgeYuanShuai(rewrite_DistributionDTO);
        }else{
            distribution(rewrite_DistributionDTO);
        }

        return Result.suc("支付成功");
    }
    // 余额支付
    @Override
    public Result BalencePayments(Rewrite_PayDTO rewrite_PayDTO) {

//        Userorder userorder = userorderRepository.findById(orederId);
        Optional<Userorder> option = userorderRepository.findById(rewrite_PayDTO.getOrderid());
        if(!option.isPresent()){
            return  Result.fail("订单不存在");
        }
        Userorder userorders = option.get();
        Linkuser linkuser = rewrite_LinkuserRepository.findByUserid(userorders.getUserid());
        if(!passwordEncoder.matches(rewrite_PayDTO.getPassword(),linkuser.getPaypassword())){
            return  Result.fail("支付密码错误");
        }

        Userlinkuser userlinkuser = rewrite_UserlinkuserRepository.findByUserid(userorders.getUserid());
        if(null != userorders.getOther() && userorders.getOther().equals("1") && userlinkuser.isPartner() == true){
            return  Result.fail("用户已经是圆帅");
        }
        
        List<Userorder> userorderss = userorderRepository.findByOrdercodes(userorders.getOrdercode());
        for (int i = 0; i < userorderss.size(); i++) {
            Userorder userorder = userorderss.get(i);
            double sum = userorderRepository.findOneSumById(userorder.getId());
    		if (sum < 0) {
    			return Result.fail("订单异常，总金额为负数");
    		}
    		if (userorders.getOther() != null && !userorders.getOther().equals("")) {
    			double num1 = userorderRepository.queryunm(userorder.getId());
    			if (num1 < 0) {
    				return Result.fail("订单异常，数量为负数");
    			}
    		}

            // 我的资产
            Userassets userassets = userassetsRepository.findByUserId(userorder.getUserid());
            int num = new BigDecimal(userorder.getModifier()).compareTo(new BigDecimal(userassets.getUsablebalance()));
            if( num > 0 ){
                return  Result.fail("您的余额不足");
            }
            //翻转订单状态
            userorder.setPayway(OrderConstant.BALANCE_PAY);
            userorder.setPaytime(TimeUtil.getDate());
            userorder.setOrderstatus(OrderConstant.PAID);
            userorderRepository.saveAndFlush(userorder);

            Rewrite_submitInformationDTO rewrite_submitInformationDTO = new Rewrite_submitInformationDTO(
                Constants.CONSUMPTION.toString(),
                userorder.getUserid(),
                userorder.getUserid(),
                "消费了"+userorder.getModifier()+"元余额。",
                null
            );
            rewrite_informationService.insertInformation(rewrite_submitInformationDTO);

            //更新我的资产
            userassets.setUsablebalance((new BigDecimal(userassets.getUsablebalance()).subtract(new BigDecimal(userorder.getModifier()))).setScale(3).toString());
            userassets.setBalance((new BigDecimal(userassets.getBalance()).subtract(new BigDecimal(userorder.getModifier()))).setScale(3).toString());
            userassetsRepository.save(userassets);

            // 支出流水
            Receiptpay receiptpay = new Receiptpay();
            receiptpay.setAmount(new BigDecimal(userorder.getModifier()));
            receiptpay.setDealtype(ReceiptpayConstant.BALANCE_PAY); //余额支出
            receiptpay.setDealstate(ReceiptpayConstant.PAY);
            receiptpay.setPayway(userorder.getPayway());
            receiptpay.setUserid(userorder.getUserid());
            receiptpay.setCreatedate(TimeUtil.getDate());
            receiptpay.setBalance(new BigDecimal(userassets.getBalance()));
            receiptpay.setCoupon(new BigDecimal(userassets.getCouponsum()));
            receiptpay.setFreezebalance(new BigDecimal(userassets.getFrozenbalance()));
            receiptpay.setIntegral(new BigDecimal(userassets.getIntegral()));
            receiptpay.setUseablebalance(new BigDecimal(userassets.getUsablebalance()));
            receiptpayRepository.save(receiptpay);

            Rewrite_DistributionDTO rewrite_DistributionDTO = new Rewrite_DistributionDTO(userorder.getModifier(),userorder.getId(),userorder.getPayway());

            if(null != userorder.getOther()
                && userorder.getOther().equals("1")){ // 圆帅
                judgeYuanShuai(rewrite_DistributionDTO);
            }else{
                distribution(rewrite_DistributionDTO);
            }
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

        if(null != userorder.getOther() && userorder.getOther().equals("1")){
            return  Result.fail("开通圆帅不能使用此支付方式");
        }
        
        double sum = userorderRepository.findOneSumById(rewrite_PayDTO.getOrderid());
		if (sum < 0) {
			return Result.fail("订单异常，总金额为负数");
		}
		if (userorder.getOther() != null && !userorder.getOther().equals("")) {
			double price = userorderRepository.queryprice(rewrite_PayDTO.getOrderid());
			double num1 = userorderRepository.queryunm(rewrite_PayDTO.getOrderid());
			if (num1 < 0) {
				return Result.fail("订单异常，数量为负数");
			}
			if (num1 * price != sum) {
				return Result.fail("订单异常，请尝试单个购买");
			}
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

        Rewrite_submitInformationDTO rewrite_submitInformationDTO = new Rewrite_submitInformationDTO(
            Constants.CONSUMPTION.toString(),
            userorder.getUserid(),
            userorder.getUserid(),
            "消费了"+userorder.getSum().toString()+"个积分。",
            null
        );
        rewrite_informationService.insertInformation(rewrite_submitInformationDTO);

        //更新我的资产
        userassets.setIntegral((new BigDecimal(userassets.getIntegral()).subtract(userorder.getSum()).setScale(3).toString()));
        userassetsRepository.save(userassets);

        // 支出流水
        Receiptpay receiptpay = new Receiptpay();
        receiptpay.setAmount(userorder.getSum());
        receiptpay.setDealtype(ReceiptpayConstant.INTEGRAL_PAY); //积分支出
        receiptpay.setDealstate(ReceiptpayConstant.PAY);
        receiptpay.setPayway(userorder.getPayway());
        receiptpay.setUserid(userorder.getUserid());
        receiptpay.setCreatedate(TimeUtil.getDate());
        receiptpay.setBalance(new BigDecimal(userassets.getBalance()));
        receiptpay.setCoupon(new BigDecimal(userassets.getCouponsum()));
        receiptpay.setFreezebalance(new BigDecimal(userassets.getFrozenbalance()));
        receiptpay.setIntegral(new BigDecimal(userassets.getIntegral()));
        receiptpay.setUseablebalance(new BigDecimal(userassets.getUsablebalance()));
        receiptpayRepository.save(receiptpay);

        return Result.suc("支付成功");
    }

    // 积分支付
    @Override
    public Result IntegralPayments(Rewrite_PayDTO rewrite_PayDTO) {
        Optional<Userorder> option = userorderRepository.findById(rewrite_PayDTO.getOrderid());
        if(!option.isPresent()){
            return  Result.fail("订单不存在");
        }
        Userorder userorders = option.get();
        Linkuser linkuser = rewrite_LinkuserRepository.findByUserid(userorders.getUserid());
        if(!passwordEncoder.matches(rewrite_PayDTO.getPassword(),linkuser.getPaypassword())){
            return  Result.fail("支付密码错误");
        }

        if(userorders.getOther().equals("1")){
            return  Result.fail("开通圆帅不能使用此支付方式");
        }

        List<Userorder> userorderss = userorderRepository.findByOrdercodes(userorders.getOrdercode());
        for (int i = 0; i < userorderss.size(); i++) {
            Userorder userorder = userorderss.get(i);
            double sum = userorderRepository.findOneSumById(userorder.getId());
    		if (sum < 0) {
    			return Result.fail("订单异常，总金额为负数");
    		}
    		if (userorders.getOther() != null && !userorders.getOther().equals("")) {
    			double num1 = userorderRepository.queryunm(userorder.getId());
    			if (num1 < 0) {
    				return Result.fail("订单异常，数量为负数");
    			}
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

            Rewrite_submitInformationDTO rewrite_submitInformationDTO = new Rewrite_submitInformationDTO(
                Constants.CONSUMPTION.toString(),
                userorder.getUserid(),
                userorder.getUserid(),
                "消费了"+userorder.getModifier().toString()+"个积分。",
                null
            );
            rewrite_informationService.insertInformation(rewrite_submitInformationDTO);

            //更新我的资产
            userassets.setIntegral((new BigDecimal(userassets.getIntegral()).subtract(new BigDecimal(userorder.getModifier())).setScale(3).toString()));
            userassetsRepository.save(userassets);

            // 支出流水
            Receiptpay receiptpay = new Receiptpay();
            receiptpay.setAmount(new BigDecimal(userorder.getModifier()));
            receiptpay.setDealtype(ReceiptpayConstant.INTEGRAL_PAY); //积分支出
            receiptpay.setDealstate(ReceiptpayConstant.PAY);
            receiptpay.setPayway(userorder.getPayway());
            receiptpay.setUserid(userorder.getUserid());
            receiptpay.setCreatedate(TimeUtil.getDate());
            receiptpay.setBalance(new BigDecimal(userassets.getBalance()));
            receiptpay.setCoupon(new BigDecimal(userassets.getCouponsum()));
            receiptpay.setFreezebalance(new BigDecimal(userassets.getFrozenbalance()));
            receiptpay.setIntegral(new BigDecimal(userassets.getIntegral()));
            receiptpay.setUseablebalance(new BigDecimal(userassets.getUsablebalance()));
            receiptpayRepository.save(receiptpay);

        }

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

        if(null != userorder.getOther() && userorder.getOther().equals("1")){
            return  Result.fail("开通圆帅不能使用此支付方式");
        }
        
        double sum = userorderRepository.findOneSumById(rewrite_PayDTO.getOrderid());
		if (sum < 0) {
			return Result.fail("订单异常，总金额为负数");
		}
		if (userorder.getOther() != null && !userorder.getOther().equals("")) {
			double price = userorderRepository.queryprice(rewrite_PayDTO.getOrderid());
			double num1 = userorderRepository.queryunm(rewrite_PayDTO.getOrderid());
			if (num1 < 0) {
				return Result.fail("订单异常，数量为负数");
			}
			if (num1 * price != sum) {
				return Result.fail("订单异常，请尝试单个购买");
			}
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

        Rewrite_submitInformationDTO rewrite_submitInformationDTO = new Rewrite_submitInformationDTO(
            Constants.CONSUMPTION.toString(),
            userorder.getUserid(),
            userorder.getUserid(),
            "消费了"+userorder.getSum().toString()+"元优惠券。",
            null
        );
        rewrite_informationService.insertInformation(rewrite_submitInformationDTO);

        //更新我的资产
        userassets.setCouponsum((new BigDecimal(userassets.getCouponsum()).subtract(userorder.getSum()).setScale(3).toString()));
        userassetsRepository.save(userassets);

        // 支出流水
        Receiptpay receiptpay = new Receiptpay();
        receiptpay.setAmount(userorder.getSum());
        receiptpay.setDealtype(ReceiptpayConstant.COUPON_PAY); // 优惠券支出
        receiptpay.setDealstate(ReceiptpayConstant.PAY);
        receiptpay.setPayway(userorder.getPayway());
        receiptpay.setUserid(userorder.getUserid());
        receiptpay.setCreatedate(TimeUtil.getDate());
        receiptpay.setBalance(new BigDecimal(userassets.getBalance()));
        receiptpay.setCoupon(new BigDecimal(userassets.getCouponsum()));
        receiptpay.setFreezebalance(new BigDecimal(userassets.getFrozenbalance()));
        receiptpay.setIntegral(new BigDecimal(userassets.getIntegral()));
        receiptpay.setUseablebalance(new BigDecimal(userassets.getUsablebalance()));
        receiptpayRepository.save(receiptpay);

        if(userorder.getPayee() != null && !"".equals(userorder.getPayee())) {
            BigDecimal am = userorder.getSum();
            BigDecimal mBigPrice = userorder.getSum();
            BigDecimal ma = new BigDecimal(userorder.getConcession());
            ma = ma.divide(new BigDecimal("100"));
            mBigPrice = mBigPrice.multiply(ma).setScale(3, BigDecimal.ROUND_HALF_UP);
            mBigPrice = am.subtract(mBigPrice);
            craeteReceiptpay(ReceiptpayConstant.BALANCE_INCOME, userorder.getUserid(), userorder.getPayee(), mBigPrice,userorder.getPayway(),userorder.getSum().toString());

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
            craeteReceiptpay(ReceiptpayConstant.BALANCE_INCOME_DIR,userorder.getUserid(),userlinkuser.getRecommendid(),mBigPrice,rewrite_DistributionDTO.getPayWay(),rewrite_DistributionDTO.getAmount());
        }
        // 是否有合伙人
        String id = findPartner(userorder.getUserid());
        if(id != null){
            BigDecimal mBigPrice = new BigDecimal(rewrite_DistributionDTO.getAmount());
            BigDecimal ma = new BigDecimal("4");
            ma = ma.divide(new BigDecimal("1000"));
            mBigPrice = mBigPrice.multiply(ma).setScale(3, BigDecimal.ROUND_HALF_UP);
            craeteReceiptpay(ReceiptpayConstant.BALANCE_INCOME_PER,userorder.getUserid(),id,mBigPrice,rewrite_DistributionDTO.getPayWay(),rewrite_DistributionDTO.getAmount());
        }
        // 上面支付者的分销分完了  下面如果有收款方 还需要再分一轮
        if(userorder.getPayee() != null && !"".equals(userorder.getPayee())){
            BigDecimal am = new BigDecimal(rewrite_DistributionDTO.getAmount());
            BigDecimal mBigPrice = new BigDecimal(rewrite_DistributionDTO.getAmount());
            BigDecimal ma = new BigDecimal(userorder.getConcession());
            ma = ma.divide(new BigDecimal("100"));
            mBigPrice = mBigPrice.multiply(ma).setScale(3, BigDecimal.ROUND_HALF_UP);
            mBigPrice = am.subtract(mBigPrice);
            craeteReceiptpay(ReceiptpayConstant.BALANCE_INCOME,userorder.getUserid(),userorder.getPayee(),mBigPrice,rewrite_DistributionDTO.getPayWay(),rewrite_DistributionDTO.getAmount());

            Userlinkuser payeeuserlinkuser = rewrite_UserlinkuserRepository.findByUserid(userorder.getPayee());

            // 收款方推荐人分销
            if(payeeuserlinkuser.getRecommendid() != null && !"".equals(payeeuserlinkuser.getRecommendid()) && !"1".equals(payeeuserlinkuser.getRecommendid())){
                BigDecimal mPrice = new BigDecimal(rewrite_DistributionDTO.getAmount());
                BigDecimal kma = new BigDecimal("5");
                kma = kma.divide(new BigDecimal("1000"));
                mPrice = mPrice.multiply(kma).setScale(3, BigDecimal.ROUND_HALF_UP);
                craeteReceiptpay(ReceiptpayConstant.BALANCE_INCOME_DIR,userorder.getPayee(),payeeuserlinkuser.getRecommendid(),mPrice,rewrite_DistributionDTO.getPayWay(),rewrite_DistributionDTO.getAmount());
            }
            // 是否有合伙人
            String kid = findPartner(userorder.getPayee());
            if(kid != null){
                BigDecimal mPrice = new BigDecimal(rewrite_DistributionDTO.getAmount());
                BigDecimal kma = new BigDecimal("4");
                kma = kma.divide(new BigDecimal("1000"));
                mPrice = mPrice.multiply(kma).setScale(3, BigDecimal.ROUND_HALF_UP);
                craeteReceiptpay(ReceiptpayConstant.BALANCE_INCOME_PER,userorder.getPayee(),kid,mPrice,rewrite_DistributionDTO.getPayWay(),rewrite_DistributionDTO.getAmount());
            }
        }

        //分配积分，
        if(userorder.getPayee() != null && !"".equals(userorder.getPayee())){ // 线下
            handleIntgerl(userorder.getRebate().toString(),userorder.getUserid(),userorder.getSum(),rewrite_DistributionDTO.getPayWay());
        }else{  // 线上
            handleIntgerl("50",userorder.getUserid(),userorder.getSum(),rewrite_DistributionDTO.getPayWay());
            //分配优惠券
            handleCoupon("50",userorder.getUserid(),userorder.getSum(),rewrite_DistributionDTO.getPayWay());
        }
        
        /**
         	逻辑判断是否有活动，如果有活动，判断收益金额是否大于1块钱，大于1块钱进行返利活动
			将收款金额的百分之十放到活动资金中，每天进行统计，并将资金转到可用资金中。
         * */
        //遍历查询活动配置表
        List<ActivityCon> activityConList = activityConRepository.findAll();
        //判断是否有进行着的活动
        if (!activityConList.isEmpty()) {
        	//如果活动表中有数据,且活动正在进行着
        	Boolean del = false;
        	for (ActivityCon activityCon : activityConList) {
        		if (activityCon.getLogicaldel() == 1) {
        			//有活动时，这里为真
        			del = true;
				}
			}
        	if (del) {
        		//获取用户付款金额
        		BigDecimal amount = new BigDecimal(rewrite_DistributionDTO.getAmount()).setScale(2, BigDecimal.ROUND_DOWN);
        		//当用户付款金额小于一块钱时没有优惠
        		BigDecimal compare = new BigDecimal("1");
        		//比较是否小于1
        		if (!(amount.compareTo(compare)== -1)) {
        			//利率
        			BigDecimal interestRate = new BigDecimal("0.1").setScale(4, BigDecimal.ROUND_DOWN);
        			//转化金额
        			BigDecimal transformationAmo = amount.multiply(interestRate).setScale(4, BigDecimal.ROUND_DOWN);
        			//新建实体类保存数据
        			ActivityPay activityPay = new ActivityPay();
        			activityPay.setUserId(userorder.getPayee());			//用户id
        			activityPay.setSource(userorder.getUserid());			//资金来源用户id
        			activityPay.setType(1);									//类型
        			activityPay.setIncomeAmo(amount);						//收入资金
        			activityPay.setTransformationAmo(transformationAmo);	//转化资金
        			activityPay.setInterestRate(interestRate);				//利率
        			activityPay.setCreateTime(TimeUtil.getDate());			//创建时间
        			activityPayRepository.save(activityPay);				//保存到表中
        			//根据用户id查找活动服务表数据
        			ActivitySer activitySerData = activitySerRepository.findByUserId(userorder.getPayee());
        			//新建实体类保存数据
        			ActivitySer activitySer = new ActivitySer();
        			//如果该用户在活动服务表中还没有数据
        			if (activitySerData == null) {
        				activitySer.setUserId(userorder.getPayee());		//用户id
        				activitySer.setActivityAmo(transformationAmo);		//活动资金
        				activitySer.setAvailableAmo(new BigDecimal("0"));	//可用资金
        				activitySer.setCashWithdrawal(new BigDecimal("0")); //提现金额
        				activitySer.setCreateTime(TimeUtil.getDate());		//创建时间
        				activitySer.setUpdateTime(TimeUtil.getDate());		//修改时间
        				activitySerRepository.save(activitySer);			//保存数据
					}else {
						activitySerData.setId(activitySerData.getId());
						//如果已有该用户的数据,将原有的活动资金和转化资金相加
						BigDecimal activityAmo = activitySerData.getActivityAmo();
						BigDecimal activityAmoSum = activityAmo.add(transformationAmo);
						activitySerData.setActivityAmo(activityAmoSum);			//活动资金
						activitySerData.setUpdateTime(TimeUtil.getDate());		//修改时间
						activitySerRepository.save(activitySerData);			//保存数据
					}
        		}
			}
			
		}
        return Result.suc("成功");
    }



    // 创建收支明细
    private void craeteReceiptpay(String Dealtype,String userId,String sourcerId,BigDecimal amount,String payway,String total){

        Userassets userassets = userassetsRepository.findByUserId(sourcerId);
        BigDecimal balance = new BigDecimal(userassets.getBalance());
        balance = balance.add(amount);
        userassets.setBalance(balance.toString());
        BigDecimal usebalance = new BigDecimal(userassets.getUsablebalance());
        usebalance = usebalance.add(amount);
        userassets.setUsablebalance(usebalance.toString());
        userassetsRepository.saveAndFlush(userassets);
        // 收支记录
        Receiptpay receiptpay = new Receiptpay();
        receiptpay.setDealtype(Dealtype);
        receiptpay.setDealstate(ReceiptpayConstant.INCOME);
        receiptpay.setUserid(sourcerId);
        receiptpay.setAmount(amount);  // 受益金额
        receiptpay.setBenefit(total);  // 交易金额
        receiptpay.setSourcer(userId);
        receiptpay.setCreatedate(TimeUtil.getDate());
        receiptpay.setLogicdelete(false);
        receiptpay.setPayway(payway);
        receiptpay.setBalance(new BigDecimal(userassets.getBalance()));
        receiptpay.setCoupon(new BigDecimal(userassets.getCouponsum()));
        receiptpay.setFreezebalance(new BigDecimal(userassets.getFrozenbalance()));
        receiptpay.setIntegral(new BigDecimal(userassets.getIntegral()));
        receiptpay.setUseablebalance(new BigDecimal(userassets.getUsablebalance()));
        receiptpayRepository.save(receiptpay);

        if(Dealtype.equals(ReceiptpayConstant.BALANCE_INCOME)){
            Rewrite_submitInformationDTO rewrite_submitInformationDTO = new Rewrite_submitInformationDTO(
                Constants.COLLECTION.toString(),
                userId,
                sourcerId,
                "收款"+amount.toString()+"元",
                total
                );
            rewrite_informationService.insertInformation(rewrite_submitInformationDTO);
        }
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
        receiptpay.setAmount(sum); // 受益金额
        receiptpay.setBenefit(amount.toString()); // 交易金额
        receiptpay.setDealtype(ReceiptpayConstant.INTEGRAL_GET); // 积分收入
        receiptpay.setDealstate(ReceiptpayConstant.INCOME);
        receiptpay.setUserid(userId);
        receiptpay.setPayway(payway);
        receiptpay.setCreatedate(TimeUtil.getDate());
        receiptpay.setBalance(new BigDecimal(userassets.getBalance()));
        receiptpay.setCoupon(new BigDecimal(userassets.getCouponsum()));
        receiptpay.setFreezebalance(new BigDecimal(userassets.getFrozenbalance()));
        receiptpay.setIntegral(new BigDecimal(userassets.getIntegral()));
        receiptpay.setUseablebalance(new BigDecimal(userassets.getUsablebalance()));
        receiptpayRepository.save(receiptpay);

            Rewrite_submitInformationDTO rewrite_submitInformationDTO = new Rewrite_submitInformationDTO(
                Constants.COLLECTION.toString(),
                userId,
                userId,
                "消费了"+amount.toString()+"元，获得了"+sum.toString()+"个积分",
                null
            );
            rewrite_informationService.insertInformation(rewrite_submitInformationDTO);

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
        receiptpay.setBalance(new BigDecimal(userassets.getBalance()));
        receiptpay.setCoupon(new BigDecimal(userassets.getCouponsum()));
        receiptpay.setFreezebalance(new BigDecimal(userassets.getFrozenbalance()));
        receiptpay.setIntegral(new BigDecimal(userassets.getIntegral()));
        receiptpay.setUseablebalance(new BigDecimal(userassets.getUsablebalance()));
        receiptpayRepository.save(receiptpay);


        Rewrite_submitInformationDTO rewrite_submitInformationDTO = new Rewrite_submitInformationDTO(
            Constants.COLLECTION.toString(),
            userId,
            userId,
            "消费了"+amount.toString()+"元，获得了"+sum.toString()+"元优惠券",
            null
        );
        rewrite_informationService.insertInformation(rewrite_submitInformationDTO);
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
                craeteReceiptpay(ReceiptpayConstant.BALANCE_INCOME_PER,userorder.getUserid(),id,mBigPrice,rewrite_DistributionDTO.getPayWay(),rewrite_DistributionDTO.getAmount());
            }
        }
        return Result.suc("成功");
    }

}
