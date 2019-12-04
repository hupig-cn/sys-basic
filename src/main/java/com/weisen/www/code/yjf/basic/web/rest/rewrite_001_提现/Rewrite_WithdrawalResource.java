package com.weisen.www.code.yjf.basic.web.rest.rewrite_001_提现;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.weisen.www.code.yjf.basic.service.dto.WithdrawalDTO;
import com.weisen.www.code.yjf.basic.service.rewrite.Rewrite_WithdrawalService;
import com.weisen.www.code.yjf.basic.service.rewrite.submit_dto.Rewrite_submitWithdrawalDTO;
import com.weisen.www.code.yjf.basic.util.Result;
import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * REST controller for managing
 * {@link com.weisen.www.code.yjf.basic.domain.Withdrawal}.
 */
@RestController
@RequestMapping("/api/withdrawal")
@Api(tags = "000-提现")
public class Rewrite_WithdrawalResource {

	@Value("${jhipster.clientApp.name}")
	private String applicationName;

	private final Rewrite_WithdrawalService withdrawalService;

	private final Logger logger = LoggerFactory.getLogger(Rewrite_WithdrawalResource.class);

	public Rewrite_WithdrawalResource(Rewrite_WithdrawalService withdrawalService) {
		this.withdrawalService = withdrawalService;
	}

	// 用户提交提现记录
	@PostMapping("/insert-withdrawal")
	@ApiOperation("申请提现")
	public ResponseEntity<?> insertWithdrawal(@RequestBody WithdrawalDTO rewrite_withdrawalDTO) {
		Result result = withdrawalService.insertWithdrawal(rewrite_withdrawalDTO);
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
	}

	// 获取用户提现记录
	@PostMapping("/get-withdrawal-by-account")
	@ApiOperation("获取用户提现记录")
	public ResponseEntity<?> getWithdrawalByAccount(
			@RequestBody Rewrite_submitWithdrawalDTO rewrite_submitWithdrawalDTO) {
		Result result = withdrawalService.getWithdrawalByAccount(rewrite_submitWithdrawalDTO.getUserId(),
				rewrite_submitWithdrawalDTO.getPageNum(), rewrite_submitWithdrawalDTO.getPageSize());
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
	}

	// 后台获取所有提现记录
	@PostMapping("/admin/get-all-withdrawal")
	@ApiOperation("后台获取所有提现信息")
	public ResponseEntity<?> getWithdrawal(@RequestBody Rewrite_submitWithdrawalDTO rewrite_submitWithdrawalDTO) {
		Result result = withdrawalService.getWithdrawals(rewrite_submitWithdrawalDTO.getPageNum(),
				rewrite_submitWithdrawalDTO.getPageSize(), rewrite_submitWithdrawalDTO.getType());
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
	}

	// 后台审核用户提现记录
	@GetMapping("/admin/audit-withdrawal/{withdrawalid}&{type}&{content}")
	@ApiOperation("审核用户提现")
	public ResponseEntity<?> auditWithdrawal(@PathVariable Long withdrawalid, @PathVariable String type,
			@PathVariable String content) {
		Result result = withdrawalService.auditWithdrawal(withdrawalid, type, content);
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
	}

	// 获取用户提现信息
	@PostMapping("/getUserInfo/{userid}")
	@ApiOperation("获取用户提现信息")
	public ResponseEntity<?> getUserInfo(@PathVariable Long userid) {
		Result result = withdrawalService.getUserInfo(userid);
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
	}

	@GetMapping("/getWithdrawalInfo/{withdrawalId}")
	@ApiOperation("获取一条提现数据详细信息")
	public ResponseEntity<?> getWithdrawalInfo(@PathVariable Long withdrawalId) {
		Result result = withdrawalService.getWithdrawalInfo(withdrawalId);
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
	}

	// 查询商家的可用资金和活动资金 LuoJinShui
	@PostMapping(value = "/user/get/AvailableAmoAndActivityAmo")
	@ApiOperation(value = "查询用户可用资金和活动资金")
	public ResponseEntity<Result> getAvailableAmoAndActivityAmo(@RequestParam(value = "userId") String userId) {
		Result result = withdrawalService.getAvailableAmoAndActivityAmo(userId);
		logger.debug("访问成功:{},传入值:{},返回值:{}", "/user/get/AvailableAmoAndActivityAmo", userId, result);
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));

	}

	// 可用资金达到50元可提现到余额 LuoJinShui
	@PostMapping("/user/availableAmoWithdrawalBalance")
	@ApiOperation("可用资金达到50元可提现到余额")
	public ResponseEntity<Result> availableAmoWithdrawalBalance(@RequestParam(value = "userId") String userId,
			@RequestParam(value = "availableAmo") String availableAmo) {
		Result result = withdrawalService.availableAmoWithdrawalBalance(userId, availableAmo);
		logger.debug("访问成功:{},传入值:{},返回值:{}", "/user/availableAmoWithdrawalBalance", userId + "," + availableAmo,
				result);
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
	}

	// 查询可用资金流水明细 LuoJinShui
	@PostMapping("/user/getWithdrawalDetails")
	@ApiOperation("查询可用资金流水明细")
	public ResponseEntity<Result> getWithdrawalDetails(@RequestParam(value = "userId") String userId,
			@RequestParam(value = "pageNum") Integer pageNum, @RequestParam(value = "pageSize") Integer pageSize) {
		Result result = withdrawalService.getWithdrawalDetails(userId, pageNum, pageSize);
		logger.debug("访问成功:{},传入值:{},返回值:{}", "/user/getWithdrawalDetails", userId + "," + pageNum + "," + pageSize,
				result);
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
	}

    // 查询可用资金流水明细 LuoJinShui
    @PostMapping("/user/zhuanhuankeyongzhijin")
    @ApiOperation("查询可用资金流水明细")
    public ResponseEntity<Result> zhuanhuankeyongzhijin(@RequestParam(value = "userId") String userId,
                                                       @RequestParam(value = "pageNum") Integer pageNum, @RequestParam(value = "pageSize") Integer pageSize) {
        Result result = withdrawalService.zhuanhuankeyongzhijin(userId, pageNum, pageSize);
        logger.debug("访问成功:{},传入值:{},返回值:{}", "/user/getWithdrawalDetails", userId + "," + pageNum + "," + pageSize,
            result);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
    }
}
