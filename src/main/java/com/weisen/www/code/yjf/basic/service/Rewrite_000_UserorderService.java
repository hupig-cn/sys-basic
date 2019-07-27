package com.weisen.www.code.yjf.basic.service;

import com.weisen.www.code.yjf.basic.service.dto.CreateOrderDTO;
import com.weisen.www.code.yjf.basic.util.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Rewrite_000_UserorderService {

	Result alipay(Long orderId);

	Result alipay(String merchantId, String userId, Integer concession, Integer rebate, String amount);

	void notifyMessage(HttpServletRequest request, HttpServletResponse response);

	Result queryOrder(String orderId);

	Result createOrder(CreateOrderDTO createOrderDTO);

	String merchantPayment(String authCode, String money, String merchantid, Integer concession, Integer rebate, String name);

}
