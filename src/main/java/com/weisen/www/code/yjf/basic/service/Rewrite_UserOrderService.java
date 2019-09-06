package com.weisen.www.code.yjf.basic.service;

import java.util.List;

import com.weisen.www.code.yjf.basic.service.dto.UserorderDTO;
import com.weisen.www.code.yjf.basic.service.dto.show_dto.Rewrite_OrderCoDto;
import com.weisen.www.code.yjf.basic.service.dto.submit_dto.Rewrite_AnOrder;
import com.weisen.www.code.yjf.basic.service.dto.submit_dto.Rewrite_UserOrderPage;
import com.weisen.www.code.yjf.basic.service.dto.submit_dto.Rewrite_UserSendGoods;
import com.weisen.www.code.yjf.basic.util.Result;

public interface Rewrite_UserOrderService {

	// 获取订单列表（后台）
	Result getOrderList(Rewrite_UserOrderPage rewrite_UserOrderPage);
	
	// 回填快递单号，改变发货状态（后台）
	Result sendGoods(Rewrite_UserSendGoods rewrite_UserSendGoods);

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
