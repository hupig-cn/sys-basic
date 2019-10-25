package com.weisen.www.code.yjf.basic.web.rewrite.rewrite_003_积分明细;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.weisen.www.code.yjf.basic.service.rewrite.Rewrite_IntegralService;
import com.weisen.www.code.yjf.basic.util.Result;

import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 查询用户积分明细页
 * 
 * @author LuoJinShui
 *
 */
@RestController
@RequestMapping("/api")
@Api(tags = "003-查询用户积分明细页")
public class Rewrite_IntegralResource {

	private final Rewrite_IntegralService rewrite_IntegralService;

	public Rewrite_IntegralResource(Rewrite_IntegralService rewrite_IntegralService) {
		this.rewrite_IntegralService = rewrite_IntegralService;
	}

	@PostMapping(value = "/user/get/Integral")
	@ApiOperation(value = "查询用户总积分")
	public ResponseEntity<Result> getIntegral(@RequestParam(value = "userId") String userId) {
		Result result = rewrite_IntegralService.getIntegral(userId);
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));

	}

	@PostMapping(value = "/user/get/Expenditure")
	@ApiOperation(value = "查询用户积分支出收入")
	public ResponseEntity<Result> getExpenditure(@RequestParam(value = "userId") String userId) {
		Result result = rewrite_IntegralService.getExpenditure(userId);
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));

	}
}
