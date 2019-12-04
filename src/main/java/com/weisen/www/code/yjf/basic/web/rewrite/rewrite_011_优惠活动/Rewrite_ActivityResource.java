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
import com.weisen.www.code.yjf.basic.service.rewrite.dto.Rewrite_GetActivityPayDTO;
import com.weisen.www.code.yjf.basic.util.Result;
import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 优惠活动接口
 *
 * @author sxx
 *
 */
@RestController
@RequestMapping("/api")
@Api(tags = "011-优惠活动")
public class Rewrite_ActivityResource {

	private final Logger logger = LoggerFactory.getLogger(Rewrite_ActivityResource.class);

	private final Rewrite_ActivityService rewrite_activityService;

	public Rewrite_ActivityResource(Rewrite_ActivityService rewrite_activityService) {
		this.rewrite_activityService = rewrite_activityService;
	}

	@PostMapping(value = "/query/amount")
	@ApiOperation(value = "查询用户活动流水")
	public ResponseEntity<Result> getCoupon(@RequestBody Rewrite_GetActivityPayDTO getActivityPayDTO) {
		Result result = rewrite_activityService.queryAmount(getActivityPayDTO);
		logger.debug("访问成功:{},传入值:{},返回值:{}", "/user/get/Coupon", getActivityPayDTO, result);
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
	}

	// 查询商家的可用资金和活动资金 LuoJinShui
	@PostMapping(value = "/user/get/AvailableAmoAndActivityAmo")
	@ApiOperation(value = "查询用户可用资金和活动资金")
	public ResponseEntity<Result> getAvailableAmoAndActivityAmo(@RequestParam(value = "userId") String userId) {
		Result result = rewrite_activityService.getAvailableAmoAndActivityAmo(userId);
		logger.debug("访问成功:{},传入值:{},返回值:{}", "/user/get/AvailableAmoAndActivityAmo", userId, result);
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));

	}

	// 可用资金达到50元可提现到余额 LuoJinShui
	@PostMapping("/user/availableAmoWithdrawalBalance")
	@ApiOperation("可用资金达到50元可提现到余额")
	public ResponseEntity<Result> availableAmoWithdrawalBalance(@RequestParam(value = "userId") String userId,
			@RequestParam(value = "availableAmo") String availableAmo) {
		Result result = rewrite_activityService.availableAmoWithdrawalBalance(userId, availableAmo);
		logger.debug("访问成功:{},传入值:{},返回值:{}", "/user/availableAmoWithdrawalBalance", userId + "," + availableAmo,
				result);
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
	}

	// 查询可用资金流水明细 LuoJinShui
	@PostMapping("/user/getWithdrawalDetails")
	@ApiOperation("查询可用资金流水明细")
	public ResponseEntity<Result> getWithdrawalDetails(@RequestParam(value = "userId") String userId,
			@RequestParam(value = "pageNum") Integer pageNum, @RequestParam(value = "pageSize") Integer pageSize) {
		Result result = rewrite_activityService.getWithdrawalDetails(userId, pageNum, pageSize);
		logger.debug("访问成功:{},传入值:{},返回值:{}", "/user/getWithdrawalDetails", userId + "," + pageNum + "," + pageSize,
				result);
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
	}

	// 查询转换资金流水明细 RuanMingHui
	@PostMapping("/user/getConversionFunds")
	@ApiOperation("查询转换资金流水明细")
	public ResponseEntity<Result> getConversionFunds(@RequestParam(value = "userId") String userId,
			@RequestParam(value = "pageNum") Integer pageNum, @RequestParam(value = "pageSize") Integer pageSize) {
		Result result = rewrite_activityService.getConversionFunds(userId, pageNum, pageSize);
		logger.debug("访问成功:{},传入值:{},返回值:{}", "/user/getConversionFunds", userId + "," + pageNum + "," + pageSize,
				result);
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
	}

}
