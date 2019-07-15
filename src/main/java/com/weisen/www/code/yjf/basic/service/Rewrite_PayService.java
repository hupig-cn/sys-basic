package com.weisen.www.code.yjf.basic.service;

import com.weisen.www.code.yjf.basic.service.dto.submit_dto.Rewrite_PayDTO;
import com.weisen.www.code.yjf.basic.util.Result;

public interface Rewrite_PayService {

    // 余额支付
    Result BalencePayment(Rewrite_PayDTO rewrite_PayDTO);

    // 积分支付
    Result IntegralPayment(Rewrite_PayDTO rewrite_PayDTO);

}
