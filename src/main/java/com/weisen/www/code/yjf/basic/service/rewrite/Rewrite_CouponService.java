package com.weisen.www.code.yjf.basic.service.rewrite;

import com.weisen.www.code.yjf.basic.util.Result;

/**
 * 查询优惠券明细页
 * @author LuoJinShui
 *
 */
public interface Rewrite_CouponService {

	// 根据用户UserId和创建时间createDate查询优惠券明细
	// 查询用户优惠券
	Result getCoupon(String userId);

}
