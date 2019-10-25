package com.weisen.www.code.yjf.basic.service.impl;

import com.weisen.www.code.yjf.basic.domain.Merchant;
import com.weisen.www.code.yjf.basic.domain.User;
import com.weisen.www.code.yjf.basic.domain.Userassets;
import com.weisen.www.code.yjf.basic.domain.Userorder;
import com.weisen.www.code.yjf.basic.repository.Rewrite_UserassetsRepository;
import com.weisen.www.code.yjf.basic.repository.Rewrite_UserorderRepository;
import com.weisen.www.code.yjf.basic.repository.Rewrite_WithdrawaldetailsRepository;
import com.weisen.www.code.yjf.basic.repository.rewrite.Rewrite_MerchantRepository;
import com.weisen.www.code.yjf.basic.repository.rewrite.Rewrite_UserRepository;
import com.weisen.www.code.yjf.basic.repository.rewrite.Rewrite_WithdrawalRepository;
import com.weisen.www.code.yjf.basic.service.Rewrite_BalanceService;
import com.weisen.www.code.yjf.basic.service.dto.submit_dto.TouchBalanceDTO;
import com.weisen.www.code.yjf.basic.service.dto.submit_dto.operatingIncomeDTO;
import com.weisen.www.code.yjf.basic.service.rewrite.dto.Rewrite_ReceiptpayDTO;
import com.weisen.www.code.yjf.basic.util.DateUtils;
import com.weisen.www.code.yjf.basic.util.Result;
import com.weisen.www.code.yjf.basic.util.TimeUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: 阮铭辉
 * @Date: 2019/10/23 11:38
 */
@Service
@Transactional
public class Rewrite_BalanceServiceImpl implements Rewrite_BalanceService {

    private final Rewrite_UserassetsRepository rewrite_userassetsRepository;

    private final Rewrite_UserRepository rewrite_userRepository;

    private final Rewrite_UserorderRepository rewrite_userorderRepository;

    private final Rewrite_WithdrawalRepository rewrite_withdrawalRepository;

    private final Rewrite_WithdrawaldetailsRepository rewrite_withdrawaldetailsRepository;

    private final Rewrite_MerchantRepository rewrite_merchantRepository;

    public Rewrite_BalanceServiceImpl(Rewrite_UserassetsRepository rewrite_userassetsRepository, Rewrite_UserRepository rewrite_userRepository, Rewrite_UserorderRepository rewrite_userorderRepository, Rewrite_WithdrawalRepository rewrite_withdrawalRepository, Rewrite_WithdrawaldetailsRepository rewrite_withdrawaldetailsRepository, Rewrite_MerchantRepository rewrite_merchantRepository) {
        this.rewrite_userassetsRepository = rewrite_userassetsRepository;
        this.rewrite_userRepository = rewrite_userRepository;
        this.rewrite_userorderRepository = rewrite_userorderRepository;
        this.rewrite_withdrawalRepository = rewrite_withdrawalRepository;
        this.rewrite_withdrawaldetailsRepository = rewrite_withdrawaldetailsRepository;
        this.rewrite_merchantRepository = rewrite_merchantRepository;
    }


    @Override
    public Result findAllBalance(String userid) {
        Userassets byUserid = rewrite_userassetsRepository.findByUserid(userid);
        return Result.suc("查询成功",byUserid.getUsablebalance());
    }

    @Override
    public Result Receiptpaylist(String userid, String endTime, String startTime,Integer pageNum,Integer pageSize) {
        if (pageNum == null || pageSize == null ){
            pageNum = 0;
            pageSize = 10;
        }
        if (startTime == null || startTime.equals("") || endTime== null || endTime.equals("")){
            endTime = TimeUtil.getDate();
            long time = System.currentTimeMillis();
            Date date = new Date(time - 1000 * 60 * 60 * 24 * 7);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            startTime = format.format(date);
        }
        List<Userorder> payee = rewrite_userorderRepository.findByTimeAndUserid(userid, startTime, endTime);
        //所有的消费订单

        List<TouchBalanceDTO> touchbalancelist = new ArrayList<>();

        for (int i = 1 + (pageSize * pageNum); i <= (pageNum + 1) * (pageSize); i++) {
            if (i >= payee.size()) {
                return Result.suc("查询成功，这是最后一页了", touchbalancelist, touchbalancelist.size());
            }
            Userorder userorder = payee.get(i);
            String userid1 = userorder.getUserid();
            User id = rewrite_userRepository.findJhiUserById(Long.valueOf(userid1));
            TouchBalanceDTO t = new TouchBalanceDTO();
            String payway = userorder.getPayway();
            if (payway.equals("1")){
                t.setPayeeTitle("支付宝支付");
            }else if(payway.equals("2")){
                t.setPayeeTitle("微信支付");
            }else if(payway.equals("3")){
                t.setPayeeTitle("余额支付");
            }else if(payway.equals("4")){
                t.setPayeeTitle("积分支付");
            }else if(payway.equals("5")){
                t.setPayeeTitle("优惠卷支付");
            }
            t.setUserorderid(userorder.getId()+"");
            t.setPaytime(userorder.getPaytime());
            t.setPayway(userorder.getPayway());
            t.setSum(userorder.getSum());
            touchbalancelist.add(t);
            //给钱订单
        }
        return Result.suc("查询成功",touchbalancelist,touchbalancelist.size());
    }

    @Override
    public Result receiptpays(Long id) {
        Userorder user = rewrite_userorderRepository.findUserorderById(id);
        String payee = user.getPayee();
        User jhiUserById = rewrite_userRepository.findJhiUserById(Long.valueOf(payee));
        String firstName = jhiUserById.getFirstName();
        String userid = user.getUserid();
        User byId = rewrite_userRepository.findJhiUserById(Long.valueOf(userid));
        String userName = byId.getFirstName();
        Rewrite_ReceiptpayDTO rewrite_receiptpayDTO = new Rewrite_ReceiptpayDTO();

        rewrite_receiptpayDTO.setOrdercode(user.getOrdercode());
        rewrite_receiptpayDTO.setOrderstatus(user.getOrderstatus());
        rewrite_receiptpayDTO.setSum(user.getSum());
        rewrite_receiptpayDTO.setUserid(user.getUserid());
        rewrite_receiptpayDTO.setUsername(userName);
        rewrite_receiptpayDTO.setPayee(user.getPayee());
        rewrite_receiptpayDTO.setPayeeName(firstName);
        rewrite_receiptpayDTO.setPayway(user.getPayway());
        rewrite_receiptpayDTO.setPayresult(user.getPayresult());
        rewrite_receiptpayDTO.setPaytime(user.getPaytime());
        rewrite_receiptpayDTO.setStats(0L);

        return Result.suc("查询成功",rewrite_receiptpayDTO);
    }

    @Override
    public Result Isitamerchant(String userid) {
        //判断这个用户是否存在
        if (userid == null|| userid.equals("")){
            return Result.fail("传入参数有误");
        }
        User jhiUserById = rewrite_userRepository.findJhiUserById(Long.valueOf(userid));
        if (jhiUserById == null){
            return Result.fail("没有这个用户");
        }
        Merchant byUserid = rewrite_merchantRepository.findByUserid(userid);
        if (byUserid == null){
            return Result.suc("不是商家",0);
        }
        return Result.suc("是商家",1);

    }

    @Override
    public Result operatingIncome(String userid, String startTime, String endTime) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (startTime == null || startTime.equals("") || endTime== null || endTime.equals("")){
            LocalDateTime today_start = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);//当天零点
            endTime = today_start.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            long time = System.currentTimeMillis();
            Date date = new Date(time - 1000 * 60 * 60 * 24 * 7);////
//            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            startTime = format.format(date);
        }
        //将时间分成7份
        List<operatingIncomeDTO> list = new ArrayList<>();
        Long end = format.parse(endTime).getTime();
        Long start = format.parse(startTime).getTime();
        long l = (end - start) / (1000 * 60 * 60 * 24);
        for (int i = 0; i <= l; i++) {

            BigDecimal one = new BigDecimal(0.00).setScale(4, BigDecimal.ROUND_DOWN);
            Long time = format.parse(endTime).getTime();
            Date date = new Date(time - 1000 * 60 * 60 * 24 );
            startTime = format.format(date);
            List<Userorder> payee = rewrite_userorderRepository.findByTimeAndPayee(userid,startTime,endTime);
                for (int j = 0; j < payee.size(); j++) {
                    Userorder userorder = payee.get(j);
                    BigDecimal sum = userorder.getSum();
                    one = one.add(sum);
                }
            operatingIncomeDTO operatingIncomeDTO = new operatingIncomeDTO();
            operatingIncomeDTO.setEarn(one);
            operatingIncomeDTO.setDate(endTime);
            list.add(operatingIncomeDTO);
            endTime = startTime;

        }
        return Result.suc("查询成功",list);
    }
}
