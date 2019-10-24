package com.weisen.www.code.yjf.basic.web.rewrite.rewrite_003_积分明细;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weisen.www.code.yjf.basic.util.Result;

import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 查询用户积分明细
 * 
 * @author LuoJinShui
 *
 */
@RestController
@RequestMapping("/api")
@Api(tags = "查询用户积分明细")
public class Rewrite_IntegralResource {

	@GetMapping(value = "/user/get/Integral")
	@ApiOperation(value = "查询用户积分明细")
	public ResponseEntity<Result> getIntegral() {
		Result result = null;
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));

	}
}
