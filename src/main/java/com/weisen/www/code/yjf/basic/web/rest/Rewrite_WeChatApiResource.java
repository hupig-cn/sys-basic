package com.weisen.www.code.yjf.basic.web.rest;

import com.weisen.www.code.yjf.basic.util.Result;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.weisen.www.code.yjf.basic.service.Rewrite_000_UserorderService;
import com.weisen.www.code.yjf.basic.service.Rewrite_WeChatService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.Optional;

@RestController
@RequestMapping("/api")
@Api(tags = "000-微信操作接口")
public class Rewrite_WeChatApiResource {

	private final Logger log = LoggerFactory.getLogger(Rewrite_WeChatApiResource.class);

	private Rewrite_WeChatService rewrite_WeChatService;

	private Rewrite_000_UserorderService userorderService;

	public Rewrite_WeChatApiResource(Rewrite_000_UserorderService userorderService,
			Rewrite_WeChatService rewrite_WeChatService) {
		this.userorderService = userorderService;
		this.rewrite_WeChatService = rewrite_WeChatService;
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
	public String bindingWeChat(@RequestParam String userid, String code) {
		return rewrite_WeChatService.scaningWeChat(userid, code);
	}

	@GetMapping("/public/queryWeChat")
	@ApiOperation(value = "查询微信绑定状态")
	public String queryWeChat(@RequestParam String userid) {
		return rewrite_WeChatService.queryWeChat(userid);
	}

	@GetMapping("/public/queryWeChatUser/{code}")
	@ApiOperation(value = "查询这个微信账户是否有用户")
	public String queryWeChatUser(@PathVariable("code") String code) {
		return rewrite_WeChatService.queryWeChatUser(code);
	}

	@GetMapping("/public/merchantPaymentWeChat")
	@ApiOperation(value = "根据金额生成随机订单,用于微信")
	public ResponseEntity<?> merchantPaymentWeChat(@RequestParam String userid, String money, String merchantid,
                                                Integer concession, Integer rebate, String name) {
        Result result = userorderService.merchantPaymentWeChat(userid, money, merchantid, concession, rebate, name);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
	}
}
