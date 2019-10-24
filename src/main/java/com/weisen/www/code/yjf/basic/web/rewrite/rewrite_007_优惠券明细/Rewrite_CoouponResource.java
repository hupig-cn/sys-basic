package com.weisen.www.code.yjf.basic.web.rewrite.rewrite_007_优惠券明细;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

	private final Rewrite_CouponService rewrite_CouponService;

	public Rewrite_CoouponResource(Rewrite_CouponService rewrite_CouponService) {
		this.rewrite_CouponService = rewrite_CouponService;
	}

	@GetMapping(value = "/user/get/Coupon")
	@ApiOperation(value = "查询优惠券明细页")
	public ResponseEntity<Result> getCoupon(@RequestParam(value = "userId") String userId) {
		Result result = rewrite_CouponService.getCoupon(userId);
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));

	}
}
