package com.weisen.www.code.yjf.basic.service.rewrite;


import com.weisen.www.code.yjf.basic.util.Result;

/**
 * 查询优惠券明细页
 * 
 * @author LuoJinShui
 *
 */
public interface Rewrite_CouponService {

	// 查询用户优惠券
	Result getCoupon(String userId);

	// 查询优惠券明细支出收入
	Result getUserCoupon(String userId, Integer pageNum, Integer pageSize);

}
