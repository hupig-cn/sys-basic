package com.weisen.www.code.yjf.basic.web.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weisen.www.code.yjf.basic.service.Rewrite_000_CreateUserService;
import com.weisen.www.code.yjf.basic.util.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/public/user")
@Api(tags = "000-用户创建操作接口")
public class Rewrite_000_CreateUserResource {

	private Rewrite_000_CreateUserService createUserService;
	
	public Rewrite_000_CreateUserResource(Rewrite_000_CreateUserService createUserService) {
		this.createUserService = createUserService;
	}

	/**
	 * 支付宝或者微信扫描二维码创建用户接口
	 * @return
	 */
	@PostMapping("/createUserByScan")
	@ApiOperation(value = "支付宝或者微信扫描二维码创建用户接口")
	public Result createUserByScan (String userId, String token, String accounttype) {
		return createUserService.createUserByScan(userId, token, accounttype);
	}
	
	/**
	 * app注册手机创建用户接口
	 * @return
	 */
	@PostMapping("/createUserByPhone")
	@ApiOperation(value = "app注册手机创建用户接口")
	public Result createUserByPhone (String userId, String phone) {
		return createUserService.createUserByPhone(userId, phone);
	}
}
