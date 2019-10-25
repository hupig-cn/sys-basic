package com.weisen.www.code.yjf.basic.service;

import com.weisen.www.code.yjf.basic.util.Result;
import org.springframework.stereotype.Service;

/**
 * @Author: 阮铭辉
 * @Date: 2019/10/23 11:37
 */
public interface Rewrite_BalanceService {
    Result findAllBalance(String userid);

    Result Receiptpaylist(String userid, String endTime, String startTime,Integer pageNum,Integer pageSize);

    Result receiptpays(Long id);

    Result Isitamerchant(String userid);
}
