package com.weisen.www.code.yjf.basic.service;

import com.weisen.www.code.yjf.basic.util.Result;

public interface Rewrite_PayService {

    // 余额支付
    Result BalencePayment(Long orederId,String password);

    // 积分支付
    Result IntegralPayment(Long orederId,String password);

}
