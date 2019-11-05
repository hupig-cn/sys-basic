package com.weisen.www.code.yjf.basic.service.rewrite.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weisen.www.code.yjf.basic.domain.Receiptpay;
import com.weisen.www.code.yjf.basic.domain.Statistics;
import com.weisen.www.code.yjf.basic.domain.Withdrawal;
import com.weisen.www.code.yjf.basic.repository.Rewrite_ReceiptpayRepository;
import com.weisen.www.code.yjf.basic.repository.rewrite.Rewrite_StatisticsRepository;
import com.weisen.www.code.yjf.basic.repository.rewrite.Rewrite_WithdrawalRepository;
import com.weisen.www.code.yjf.basic.service.rewrite.Rewrite_StatisticsService;
import com.weisen.www.code.yjf.basic.service.rewrite.dto.Rewrite_StatisticsDTO;
import com.weisen.www.code.yjf.basic.service.rewrite.mapper.Rewrite_StatisticsMapper;
import com.weisen.www.code.yjf.basic.service.util.WithdrawalConstant;
import com.weisen.www.code.yjf.basic.util.Result;

/**
 * Service Implementation for managing Statistics.
 */
@Service
@Transactional
public class Rewrite_StatisticsServiceImpl implements Rewrite_StatisticsService {

    private final Rewrite_ReceiptpayRepository rewrite_receiptpayRepository;

    private final Rewrite_WithdrawalRepository rewrite_withdrawalRepository;

    private final Rewrite_StatisticsRepository rewrite_statisticsRepository;

    private final Rewrite_StatisticsMapper rewrite_statisticsMapper;

    public Rewrite_StatisticsServiceImpl(Rewrite_ReceiptpayRepository rewrite_receiptpayRepository, Rewrite_WithdrawalRepository rewrite_withdrawalRepository, Rewrite_StatisticsRepository rewrite_statisticsRepository, Rewrite_StatisticsMapper rewrite_statisticsMapper) {
        this.rewrite_receiptpayRepository = rewrite_receiptpayRepository;
        this.rewrite_withdrawalRepository = rewrite_withdrawalRepository;
        this.rewrite_statisticsRepository = rewrite_statisticsRepository;
        this.rewrite_statisticsMapper = rewrite_statisticsMapper;
    }

    public Result findUserAsset(Long userId) {
        // 根据用户id 和 当前时间去查询用户的收支明细数据，根据不同的类型去查询,限定一个时间段,不搜索上线之前的数据.
        // 今日消费是否统计非余额消费
        List<Receiptpay> allByUserIdAndDate = rewrite_receiptpayRepository.findAllByUserIdAndDate(userId);
        // 今日消费额
        BigDecimal expense = new BigDecimal(0);
        // 提现中金额
        BigDecimal withdraw = new BigDecimal(0);
        // 提现成功金额
        BigDecimal withdrawsuccess = new BigDecimal(0);
        // 今日分享收益
        BigDecimal earnings = new BigDecimal(0);
        // 今日商家收入
        BigDecimal proceeds = new BigDecimal(0);
        // 今日优惠券收入
        BigDecimal incomecoupon = new BigDecimal(0);
        // 今日优惠券支出
        BigDecimal expendcoupon = new BigDecimal(0);
        // 今日积分收入
        BigDecimal incomeintegral = new BigDecimal(0);
        // 今日积分支出
        BigDecimal expendintegral = new BigDecimal(0);
        for (Receiptpay x : allByUserIdAndDate) {
            switch (x.getDealtype()) {
                case "1":

                case "2":

                case "4":
                    expense = expense.add(x.getAmount());
                    break;
                case "3":
                    proceeds = proceeds.add(x.getAmount())
                    ;
                    break;
                case "5":
                    expendintegral = expendintegral.add(x.getAmount())
                    ;
                    break;
                case "6":
                    incomeintegral = incomeintegral.add(x.getAmount());
                    break;
                case "7":
                    expendcoupon = expendcoupon.add(x.getAmount());
                case "8":
                    incomecoupon = incomecoupon.add(x.getAmount())
                    ;
                case "9":

                case "10":
                    earnings = earnings.add(x.getAmount());
                    break;
            }
        }
        // 类型：1 2 4 决定了今日消费
        // 类型：9 10 决定了今日收益
        // 类型：3 决定了商家收款
        // 类型：7 决定了优惠券收入
        // 类型：6 决定了积分收入
        // 根据用户id和时间去提现表查询用户当前的提现中金额和提现成功金额
        List<Withdrawal> userWithdrawals = rewrite_withdrawalRepository.findAllByUserIdAndDate(userId);
        // 进行类型判断然后得到用户当天的提现中金额和提现成功金额
        userWithdrawals.forEach(userWithdrawal -> {
            if (null == userWithdrawal.getWithdrawaltype()) {
            } else if (WithdrawalConstant.IN_READY == userWithdrawal.getWithdrawaltype())
                // 提现中
                withdraw.add(new BigDecimal(userWithdrawal.getWithdrawalamount()));
            else if (WithdrawalConstant.INCOME == userWithdrawal.getWithdrawaltype())
                // 提现成功
                withdrawsuccess.add(new BigDecimal(userWithdrawal.getWithdrawalamount()));
            else {
                // 提现失败暂时不错处理

            }
        });
        List<Statistics> byUserId = rewrite_statisticsRepository.findByUserId(userId);
        // 待定
//        if(!CheckUtils.checkList(allByUserIdAndDate) && !CheckUtils.checkList(byUserId))
//            return Result.fail("无数据");
        Rewrite_StatisticsDTO data_today = new Rewrite_StatisticsDTO();
        // 保存余额
        data_today.setBalance(allByUserIdAndDate.get(0).getBalance() == null ? new BigDecimal(0) : allByUserIdAndDate.get(0).getBalance());
        // 保存优惠券
        data_today.setCoupon(allByUserIdAndDate.get(0).getCoupon() == null ? new BigDecimal(0) : allByUserIdAndDate.get(0).getCoupon());
        // 保存积分
        data_today.setIntegral(allByUserIdAndDate.get(0).getIntegral() == null ? new BigDecimal(0) : allByUserIdAndDate.get(0).getIntegral());
        // 冻结余额
        data_today.setFreezebalance(allByUserIdAndDate.get(0).getFreezebalance() == null ? new BigDecimal(0) : allByUserIdAndDate.get(0).getFreezebalance());
        data_today.setUseablebalance(allByUserIdAndDate.get(0).getUseablebalance() == null ? new BigDecimal(0) : allByUserIdAndDate.get(0).getUseablebalance());
        data_today.setEarnings(earnings);
        data_today.setExpendcoupon(expendcoupon);
        data_today.setExpendintegral(expendintegral);
        data_today.setExpense(expense);
        data_today.setProceeds(proceeds);
        data_today.setWithdraw(withdraw);
        data_today.setWithdrawsuccess(withdrawsuccess);
        data_today.setIncomecoupon(incomecoupon);
        data_today.setIncomeintegral(incomeintegral);
        List<Rewrite_StatisticsDTO> rewrite_statisticsDTOS = rewrite_statisticsMapper.toDto(byUserId);
        rewrite_statisticsDTOS.add(data_today);
        return Result.suc("", rewrite_statisticsDTOS);
    }
    // 昨日数据统计
    public void test(Long userId){
        //
        rewrite_receiptpayRepository.findYesToDayAsset(userId);
    }
}
