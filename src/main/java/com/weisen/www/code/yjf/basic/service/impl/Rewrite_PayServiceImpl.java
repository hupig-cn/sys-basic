package com.weisen.www.code.yjf.basic.service.impl;

import com.weisen.www.code.yjf.basic.domain.*;
import com.weisen.www.code.yjf.basic.repository.*;
import com.weisen.www.code.yjf.basic.service.Rewrite_PayService;
import com.weisen.www.code.yjf.basic.service.util.OrderConstant;
import com.weisen.www.code.yjf.basic.util.Result;
import com.weisen.www.code.yjf.basic.util.TimeUtil;
import jdk.nashorn.internal.runtime.options.Option;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Transactional
public class Rewrite_PayServiceImpl implements Rewrite_PayService {

    private final Logger log = LoggerFactory.getLogger(Rewrite_PayServiceImpl.class);

    private Rewrite_000_UserorderRepository userorderRepository;

    private Rewrite_UserlinkuserRepository userlinkuserRepository;

    private Rewrite_PercentageRepository percentageRepository;

    private ReceiptpayRepository receiptpayRepository;

    private Rewrite_000_UserassetsRepository userassetsRepository;

    private LinkuserRepository linkuserRepository;

    public Rewrite_PayServiceImpl(Rewrite_000_UserorderRepository userorderRepository, Rewrite_UserlinkuserRepository userlinkuserRepository, Rewrite_PercentageRepository percentageRepository, ReceiptpayRepository receiptpayRepository, Rewrite_000_UserassetsRepository userassetsRepository
            ,LinkuserRepository linkuserRepository ) {
        this.userorderRepository = userorderRepository;
        this.userlinkuserRepository = userlinkuserRepository;
        this.percentageRepository = percentageRepository;
        this.receiptpayRepository = receiptpayRepository;
        this.userassetsRepository = userassetsRepository;
        this.linkuserRepository = linkuserRepository;
    }

    // 余额支付
    @Override
    public Result BalencePayment(Long orederId, String password) {

//        Userorder userorder = userorderRepository.findById(orederId);
        Optional<Userorder> option = userorderRepository.findById(orederId);
        if(!option.isPresent()){
            return  Result.fail("订单不存在");
        }
        Userorder userorder = option.get();
        Linkuser linkuser = linkuserRepository.getOne(Long.valueOf(userorder.getUserid()));
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if(!passwordEncoder.matches(password,linkuser.getPaypassword())){
            return  Result.fail("支付密码错误");
        }
        //翻转订单状态
        userorder.setPayway(OrderConstant.BALANCE_PAY);
        userorder.setPaytime(TimeUtil.getDate());
        userorder.setOrderstatus(OrderConstant.PAID);
        userorderRepository.saveAndFlush(userorder);

        // 支出流水
        Receiptpay receiptpay = new Receiptpay();
        receiptpay.setAmount(userorder.getSum());
        receiptpay.dealstate(null); //余额支出
        receiptpay.setUserid(userorder.getUserid());
        receiptpay.setCreatedate(TimeUtil.getDate());
        receiptpayRepository.save(receiptpay);

        // 我的资产
        Userassets userassets = userassetsRepository.findByUserId(userorder.getUserid());


        return null;
    }

    // 积分支付
    @Override
    public Result IntegralPayment(Long orederId, String password) {
        return null;
    }
}
