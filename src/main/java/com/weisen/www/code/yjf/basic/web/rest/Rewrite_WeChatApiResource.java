package com.weisen.www.code.yjf.basic.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.weisen.www.code.yjf.basic.service.Rewrite_000_UserorderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api(tags = "000-微信操作接口")
public class Rewrite_WeChatApiResource {

	private final Logger log = LoggerFactory.getLogger(Rewrite_WeChatApiResource.class);

	private Rewrite_000_UserorderService userorderService;

	public Rewrite_WeChatApiResource(Rewrite_000_UserorderService userorderService) {
		this.userorderService = userorderService;
	}

	/**
	 * 扫码时创建用户流程<br>
	 * 1.扫码判断用户是否存在，若存在，直接跳出该流程；若不存在，执行以下步骤<br>
	 * 2.前端从{basic服务}中的checkUserExist接口获取的userId作为账号调用{login服务}创建用户，密码随机生成<br>
	 * 3.前端从{login服务}创建用户之后获取userId，调用{basic服务}中的createUserByScan接口创建用户，判断改接口返回数据，若suc则结束<br>
	 * 
	 * @param authCode
	 * @return
	 */
	@GetMapping("/public/bindingWeChat")
	@ApiOperation(value = "用户绑定微信")
	public String bindingWeChat(@RequestParam String userid, String authCode) {
		return null;
	}

	@GetMapping("/public/queryWeChat")
	@ApiOperation(value = "查询微信绑定状态")
	public String queryWeChat(@RequestParam String userid) {
		return null;
	}

	@GetMapping("/public/queryWeChatUser")
	@ApiOperation(value = "查询这个微信账户是否有用户")
	public String queryWeChatUser(@RequestParam String authCode) {
		return null;
	}

	@GetMapping("/public/merchantPaymentWeChat")
	@ApiOperation(value = "根据金额生成随机订单,用于微信")
	public String merchantPaymentWeChat(@RequestParam String authCode, String money, String merchantid,
			Integer concession, Integer rebate, String name) {
//		return userorderService.merchantPayment(authCode, money, merchantid, concession, rebate, name);
		return null;
	}
}
