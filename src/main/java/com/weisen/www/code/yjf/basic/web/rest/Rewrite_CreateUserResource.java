package com.weisen.www.code.yjf.basic.web.rest;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.weisen.www.code.yjf.basic.service.Rewrite_CreateUserService;
import com.weisen.www.code.yjf.basic.service.dto.LinkuserDTO;
import com.weisen.www.code.yjf.basic.util.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api(tags = "000-用户操作接口")
public class Rewrite_CreateUserResource {

	private Rewrite_CreateUserService createUserService;

	public Rewrite_CreateUserResource(Rewrite_CreateUserService createUserService) {
		this.createUserService = createUserService;
	}

	/**
	 * 手机app创建用户接口
	 * 
	 * @return
	 */
	@PostMapping("/public/user/createUserByPhone")
	@ApiOperation(value = "手机app创建用户接口")
	public Result createUserByPhone(@Valid @RequestBody LinkuserDTO linkuserDTO) {
		return createUserService.createUserByPhone(linkuserDTO.getUserid(), linkuserDTO.getPhone());
	}

	/**
	 * 手机扫描分享的推荐码，创建用户接口
	 * 
	 * @return
	 */
	@PostMapping("/public/user/createUserByScanning")
	@ApiOperation(value = "手机扫描分享的推荐码，创建用户接口")
	public Result createUserByScanning(@Valid @RequestBody LinkuserDTO linkuserDTO) {
		return createUserService.createUserByScanning(linkuserDTO.getUserid(), linkuserDTO.getPhone(),
				linkuserDTO.getCreator());
	}

	/**
	 * 手机扫描商家付款码，创建用户
	 * 
	 * @return
	 */
	@GetMapping("/public/user/createUserByScanningMerchant")
	@ApiOperation(value = "手机扫描商家付款码，创建用户")
	public String createUserByScanningMerchant(@RequestParam String userid, String token, String accounttype) {
		return createUserService.createUserByScanningMerchant(userid, token, accounttype);
	}
	
	/**
	 * 该接口适用于分享链接后创建用户
	 * 
	 * @return
	 */
	@GetMapping("/public/user/createUserByShareLink")
	@ApiOperation(value = "该接口适用于分享链接后创建用户")
	public String createUserByShareLink(@RequestParam String userid, String token, String accounttype, String articleid) {
		return createUserService.createUserByShareLink(userid, token, accounttype, articleid);
	}
}
