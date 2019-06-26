package com.weisen.www.code.yjf.basic.service;

import com.weisen.www.code.yjf.basic.domain.Userorder;
import com.weisen.www.code.yjf.basic.service.dto.UserorderDTO;

import java.util.List;

public interface Rewrite_UserOrderService {


    // 获取用户当日的订单量（区分端）
    int getTodayOrderNum(Long userId);

    // 获取待付款订单
    List<UserorderDTO> getUnpaidOrder(Long userId);

    // 获取已支付订单
    List<UserorderDTO> getPaidOrder(Long userId);

    // 获取退款订单
    List<UserorderDTO> getRefundOrder(Long userId);

    // 获取当日订单
    List<UserorderDTO> getTodayOrder(Long userId);

    // 获取全部订单
    List<UserorderDTO> getAllOrder(Long userId);

}
