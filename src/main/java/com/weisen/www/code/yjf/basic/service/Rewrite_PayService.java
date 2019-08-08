package com.weisen.www.code.yjf.basic.service;

import com.weisen.www.code.yjf.basic.service.dto.submit_dto.Rewrite_DistributionDTO;
import com.weisen.www.code.yjf.basic.service.dto.submit_dto.Rewrite_PayDTO;
import com.weisen.www.code.yjf.basic.util.Result;

public interface Rewrite_PayService {

    // 余额支付
    Result BalencePayment(Rewrite_PayDTO rewrite_PayDTO);

    // 积分支付
    Result IntegralPayment(Rewrite_PayDTO rewrite_PayDTO);

    // 分销
    Result distribution(Rewrite_DistributionDTO rewrite_DistributionDTO);

    // 优惠券支付
    Result couponPayment(Rewrite_PayDTO rewrite_PayDTO);

    //订单商品是元帅的流程
    Result judgeYuanShuai(Rewrite_DistributionDTO rewrite_DistributionDTO);

//    Result weChatPay(Rewrite_submitWeChatPayDTO rewrite_submitWeChatPayDTO);

}
