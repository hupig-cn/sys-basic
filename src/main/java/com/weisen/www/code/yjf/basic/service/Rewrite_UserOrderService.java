package com.weisen.www.code.yjf.basic.service;

import com.weisen.www.code.yjf.basic.domain.Userorder;
import com.weisen.www.code.yjf.basic.service.dto.UserorderDTO;
import com.weisen.www.code.yjf.basic.service.dto.show_dto.Rewrite_OrderCoDto;
import com.weisen.www.code.yjf.basic.service.dto.submit_dto.Rewrite_AnOrder;
import com.weisen.www.code.yjf.basic.util.Result;

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

    //用户下单
    Result placeAnOrder(Rewrite_AnOrder rewrite_AnOrder);

    // 收益+当日订单+各种订单状态
    Result somethingData(Long userId);

    // 商户订单查询
    Rewrite_OrderCoDto getMerchantorderCount(Long userId);

}
