package com.weisen.www.code.yjf.basic.service;

import java.text.ParseException;

import com.weisen.www.code.yjf.basic.util.Result;

/**
 * @Author: 阮铭辉
 * @Date: 2019/10/23 11:37
 */
public interface Rewrite_BalanceService {
    Result findAllBalance(String userid);

    Result Receiptpaylist(String userid, Integer pageNum, Integer pageSize);

    Result receiptpays(Long id);

    Result Isitamerchant(String userid);

    Result operatingIncome(String userid, String startTime, String endTime) throws ParseException;
}
