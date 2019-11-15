package com.weisen.www.code.yjf.basic.web.rewrite.rewrite_007_优惠券明细;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.weisen.www.code.yjf.basic.service.rewrite.Rewrite_CouponService;
import com.weisen.www.code.yjf.basic.util.Result;

import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 查询用户优惠券明细页
 * 
 * @author LuoJinShui
 *
 */
@RestController
@RequestMapping("/api")
@Api(tags = "优惠券")
public class Rewrite_CoouponResource {

	private final Logger logger = LoggerFactory.getLogger(Rewrite_CoouponResource.class);
	
	private final Rewrite_CouponService rewrite_CouponService;

	public Rewrite_CoouponResource(Rewrite_CouponService rewrite_CouponService) {
		this.rewrite_CouponService = rewrite_CouponService;
	}

	@PostMapping(value = "/user/get/Coupon")
	@ApiOperation(value = "查询用户优惠券")
	public ResponseEntity<Result> getCoupon(@RequestParam(value = "userId") String userId) {
		Result result = rewrite_CouponService.getCoupon(userId);
		logger.debug("访问成功:{},传入值:{},返回值:{}", "/user/get/Coupon", userId, result);
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
	}

	@PostMapping(value = "/user/get/UserCoupon")
	@ApiOperation(value = "查询优惠券明细支出收入")
	public ResponseEntity<Result> getExpenditure(@RequestParam(value = "userId") String userId,
			@RequestParam(required = false) Integer pageNum, @RequestParam(required = false) Integer pageSize) {
		Result result = rewrite_CouponService.getUserCoupon(userId, pageNum, pageSize);
		logger.debug("访问成功:{},传入值:{},返回值:{}", "/user/get/UserCoupon", userId + "," + pageNum + "," + pageSize, result);
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));

	}
}
