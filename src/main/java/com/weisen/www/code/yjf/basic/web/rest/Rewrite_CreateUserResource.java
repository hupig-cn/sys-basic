package com.weisen.www.code.yjf.basic.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.weisen.www.code.yjf.basic.service.Rewrite_000_CreateUserService;
import com.weisen.www.code.yjf.basic.service.dto.CoderecordDTO;
import com.weisen.www.code.yjf.basic.util.Result;
import com.weisen.www.code.yjf.basic.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api(tags = "000-用户创建操作接口")
public class Rewrite_CreateUserResource {

	private Rewrite_000_CreateUserService createUserService;
	
	public Rewrite_CreateUserResource(Rewrite_000_CreateUserService createUserService) {
		this.createUserService = createUserService;
	}

	/**
	 * 支付宝或者微信扫描二维码创建用户接口
	 * @return
	 */
	@PostMapping("/public/user/createUserByScan")
	@ApiOperation(value = "支付宝或者微信扫描二维码创建用户接口")
	public Result createUserByScan (String userId, String token, String accounttype, String recommendId, String coordinate) {
		return createUserService.createUserByScan(userId, token, accounttype, recommendId, coordinate);
	}
	
	/**
	 * app注册手机创建用户接口
	 * @return
	 */
	@PostMapping("/public/user/createUserByPhone")
	@ApiOperation(value = "app注册手机创建用户接口")
	public Result createUserByPhone (String userId, String phone, String coordinate) {
		return createUserService.createUserByPhone(userId, phone, coordinate);
	}
	
	//测试接口(增删改查)
	@PostMapping("/test")
    public String createTest(@RequestBody String name) {
        return "调用了BASIC的增加接口";
    }
    @DeleteMapping("/test/{name}")
    public String deleteTest(@PathVariable String name) {
        return "调用了BASIC的删除接口";
    }
    @PutMapping("/test")
    public String updateTest(@RequestBody String name) {
        return "调用了BASIC的修改接口";
    }
    @GetMapping("/test/{name}")
    public String getTest(@PathVariable String name) {
        return "调用了BASIC的查询接口";
    }
}
