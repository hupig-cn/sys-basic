package com.weisen.www.code.yjf.basic.service.impl;
import java.math.BigDecimal;

import com.weisen.www.code.yjf.basic.domain.User;
import com.weisen.www.code.yjf.basic.domain.Userassets;
import com.weisen.www.code.yjf.basic.domain.Userorder;
import com.weisen.www.code.yjf.basic.domain.Withdrawal;
import com.weisen.www.code.yjf.basic.repository.Rewrite_UserassetsRepository;
import com.weisen.www.code.yjf.basic.repository.Rewrite_UserorderRepository;
import com.weisen.www.code.yjf.basic.repository.Rewrite_WithdrawaldetailsRepository;
import com.weisen.www.code.yjf.basic.repository.rewrite.Rewrite_UserRepository;
import com.weisen.www.code.yjf.basic.repository.rewrite.Rewrite_WithdrawalRepository;
import com.weisen.www.code.yjf.basic.service.Rewrite_BalanceService;
import com.weisen.www.code.yjf.basic.service.dto.submit_dto.TouchBalanceDTO;
import com.weisen.www.code.yjf.basic.service.rewrite.dto.Rewrite_ReceiptpayDTO;
import com.weisen.www.code.yjf.basic.util.Result;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 阮铭辉
 * @Date: 2019/10/23 11:38
 */
@Service
public class Rewrite_BalanceServiceImpl implements Rewrite_BalanceService {

    private final Rewrite_UserassetsRepository rewrite_userassetsRepository;

    private final Rewrite_UserRepository rewrite_userRepository;

    private final Rewrite_UserorderRepository rewrite_userorderRepository;

    private final Rewrite_WithdrawalRepository rewrite_withdrawalRepository;

    private final Rewrite_WithdrawaldetailsRepository rewrite_withdrawaldetailsRepository;

    public Rewrite_BalanceServiceImpl(Rewrite_UserassetsRepository rewrite_userassetsRepository, Rewrite_UserRepository rewrite_userRepository, Rewrite_UserorderRepository rewrite_userorderRepository, Rewrite_WithdrawalRepository rewrite_withdrawalRepository, Rewrite_WithdrawaldetailsRepository rewrite_withdrawaldetailsRepository) {
        this.rewrite_userassetsRepository = rewrite_userassetsRepository;
        this.rewrite_userRepository = rewrite_userRepository;
        this.rewrite_userorderRepository = rewrite_userorderRepository;
        this.rewrite_withdrawalRepository = rewrite_withdrawalRepository;
        this.rewrite_withdrawaldetailsRepository = rewrite_withdrawaldetailsRepository;
    }


    @Override
    public Result findAllBalance(String userid) {
        Userassets byUserid = rewrite_userassetsRepository.findByUserid(userid);
        return Result.suc("查询成功",byUserid.getUsablebalance());
    }

    @Override
    public Result Receiptpaylist(String userid, String endTime, String startTime) {
        List<Userorder> payee = rewrite_userorderRepository.findByTimeAndUserid(userid, startTime, endTime);
        //所有的消费订单

        List<TouchBalanceDTO> touchbalancelist = new ArrayList<>();
        for (int i = 0; i < payee.size(); i++) {
            Userorder userorder = payee.get(i);
            String userid1 = userorder.getUserid();
            User id = rewrite_userRepository.findJhiUserById(Long.valueOf(userid1));
            TouchBalanceDTO t = new TouchBalanceDTO();
            String payway = userorder.getPayway();
            if (payway.equals("1")){
                t.setPayeeTitle(id.getFirstName()+"支付宝支付"+userorder.getSum());
            }else if(payway.equals("2")){
                t.setPayeeTitle(id.getFirstName()+"微信支付"+userorder.getSum());
            }else if(payway.equals("3")){
                t.setPayeeTitle(id.getFirstName()+"余额"+userorder.getSum());
            }else if(payway.equals("4")){
                t.setPayeeTitle(id.getFirstName()+"积分支付"+userorder.getSum());
            }else if(payway.equals("5")){
                t.setPayeeTitle(id.getFirstName()+"优惠卷支付"+userorder.getSum());
            }
            t.setUserorderid(userorder.getId()+"");
            t.setPaytime(userorder.getPaytime());
            t.setPayway(userorder.getPayway());
            touchbalancelist.add(t);
            //给钱订单
        }
        return Result.suc("查询成功",touchbalancelist,touchbalancelist.size());
    }

    @Override
    public Result receiptpays(Long id,String userid) {
        Userorder user = rewrite_userorderRepository.findUserorderById(id);
        String payee = user.getPayee();
        User jhiUserById = rewrite_userRepository.findJhiUserById(Long.valueOf(payee));
        String firstName = jhiUserById.getFirstName();
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
}
