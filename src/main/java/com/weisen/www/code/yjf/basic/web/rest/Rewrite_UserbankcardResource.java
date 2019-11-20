package com.weisen.www.code.yjf.basic.web.rest;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.weisen.www.code.yjf.basic.service.Rewrite_UserbankcardService;
import com.weisen.www.code.yjf.basic.service.dto.UserbankcardDTO;
import com.weisen.www.code.yjf.basic.util.Result;

import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/userbankcard")
@Api(tags = "000-用户银行卡信息")
public class Rewrite_UserbankcardResource {

	private final Logger log = LoggerFactory.getLogger(Rewrite_UserbankcardResource.class);

	private final Rewrite_UserbankcardService Rewrite_UserbankcardService;

	public Rewrite_UserbankcardResource(Rewrite_UserbankcardService Rewrite_UserbankcardService) {
		this.Rewrite_UserbankcardService = Rewrite_UserbankcardService;
	}

	@GetMapping("/findAllUserBankCard/{userId}")
	@ApiOperation(value = "查询用户银行卡列表")
	public ResponseEntity<Result> findAllUserBankCard(@PathVariable Long userId) {
		log.debug("REST request to get findAllUserBankCard : {}", userId);
		Result result = Rewrite_UserbankcardService.findAllUserBankCard(userId);
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
	}

	/**
	 * 重写查询用户银行卡列表
	 * 
	 * @param userId
	 * @return
	 */

	@GetMapping("/getAllUserBankCard")
	@ApiOperation(value = "重写查询用户银行卡列表")
	public ResponseEntity<Result> getAllUserBankCard(@RequestParam(value = "userId") String userId) {
		Result result = Rewrite_UserbankcardService.getAllUserBankCard(userId);
		log.debug("访问成功:{},传入值:{},返回值:{}", "/getAllUserBankCard", userId, result);
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
	}

	/**
	 * 查询所有银行卡列表
	 * 
	 * @param userId
	 * @return
	 */

	@GetMapping("/findAllBankCard")
	@ApiOperation(value = "查询所有银行卡列表")
	public ResponseEntity<Result> findAllBankCard(@RequestParam(value = "userId") String userId) {
		Result result = Rewrite_UserbankcardService.findAllBankCard(userId);
		log.debug("访问成功:{},传入值:{},返回值:{}", "/findAllBankCard", userId, result);
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
	}

	@PostMapping("/createBankCard")
	@ApiOperation(value = "用户添加银行卡")
	public ResponseEntity<Result> createBankCard(@RequestBody UserbankcardDTO userbankcardDTO) {
		log.debug("REST request to get createBankCard : {}", userbankcardDTO);
		Result result = Rewrite_UserbankcardService.createBankCard(userbankcardDTO);
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
	}

	@PostMapping("/deleteBackCard/{bankcardId}")
	@ApiOperation(value = "用户删除银行卡")
	public ResponseEntity<Result> deleteBackCard(@PathVariable Long bankcardId) {
		log.debug("REST request to get deleteBackCard : {}", bankcardId);
		Result result = Rewrite_UserbankcardService.deleteBackCard(bankcardId);
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
	}

	@PostMapping("/deleteBackCards")
	@ApiOperation(value = "用户删除多个银行卡")
	public ResponseEntity<Result> deleteBackCards(@RequestBody List<String> bankcardId) {
		log.debug("REST request to get deleteBackCard : {}", bankcardId);
		Result result = Rewrite_UserbankcardService.deleteBackCards(bankcardId);
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
	}
}
