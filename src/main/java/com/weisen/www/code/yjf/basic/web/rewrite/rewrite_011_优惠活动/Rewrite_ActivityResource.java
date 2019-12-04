package com.weisen.www.code.yjf.basic.web.rewrite.rewrite_011_优惠活动;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.weisen.www.code.yjf.basic.service.rewrite.Rewrite_ActivityService;
import com.weisen.www.code.yjf.basic.service.rewrite.Rewrite_CouponService;
import com.weisen.www.code.yjf.basic.service.rewrite.dto.Rewrite_GetActivityPayDTO;
import com.weisen.www.code.yjf.basic.util.Result;

import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 查询用户优惠券明细页
 * 
 * @author sxx
 *
 */
@RestController
@RequestMapping("/api")
@Api(tags = "优惠活动")
public class Rewrite_ActivityResource {

	private final Logger logger = LoggerFactory.getLogger(Rewrite_ActivityResource.class);
	
	private final Rewrite_ActivityService rewrite_activityService;

	public Rewrite_ActivityResource(Rewrite_ActivityService rewrite_activityService) {
		this.rewrite_activityService = rewrite_activityService;
	}

	@PostMapping(value = "/query/amount")
	@ApiOperation(value = "查询用户优惠券")
	public ResponseEntity<Result> getCoupon(@RequestBody Rewrite_GetActivityPayDTO getActivityPayDTO) {
		Result result = rewrite_activityService.queryAmount(getActivityPayDTO);
		logger.debug("访问成功:{},传入值:{},返回值:{}", "/user/get/Coupon", getActivityPayDTO, result);
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
	}

}
