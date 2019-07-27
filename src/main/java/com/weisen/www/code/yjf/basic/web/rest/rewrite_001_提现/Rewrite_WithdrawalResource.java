package com.weisen.www.code.yjf.basic.web.rest.rewrite_001_提现;

import com.weisen.www.code.yjf.basic.service.rewrite.Rewrite_WithdrawalService;
import com.weisen.www.code.yjf.basic.service.rewrite.dto.Rewrite_WithdrawalDTO;
import com.weisen.www.code.yjf.basic.service.rewrite.submit_dto.Rewrite_submitWithdrawalDTO;
import com.weisen.www.code.yjf.basic.util.Result;
import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * REST controller for managing {@link com.weisen.www.code.yjf.basic.domain.Withdrawal}.
 */
@RestController
@RequestMapping("/api/withdrawal")
@Api(tags = "000-提现")
public class Rewrite_WithdrawalResource {

    private final Logger log = LoggerFactory.getLogger(Rewrite_WithdrawalResource.class);

    private static final String ENTITY_NAME = "basicWithdrawal";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final Rewrite_WithdrawalService withdrawalService;

    public Rewrite_WithdrawalResource(Rewrite_WithdrawalService withdrawalService) {
        this.withdrawalService = withdrawalService;
    }
    //用户提交提现记录
    @PostMapping("/insert-withdrawal")
    @ApiOperation("申请提现")
    public ResponseEntity<?> insertWithdrawal(@RequestBody Rewrite_WithdrawalDTO rewrite_withdrawalDTO){
        Result result = withdrawalService.insertWithdrawal(rewrite_withdrawalDTO);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
    }
    //获取用户提现记录
    @PostMapping("/get-withdrawal-by-account")
    @ApiOperation("获取用户提现记录")
    public ResponseEntity<?> getWithdrawalByAccount(@RequestBody Rewrite_submitWithdrawalDTO rewrite_submitWithdrawalDTO){
        Result result = withdrawalService.getWithdrawalByAccount(rewrite_submitWithdrawalDTO.getUserId(), rewrite_submitWithdrawalDTO.getPageNum(), rewrite_submitWithdrawalDTO.getPageSize());
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
    }
    //后台获取所有提现记录
    @PostMapping("/admin/get-all-withdrawal")
    @ApiOperation("后台获取所有提现信息")
    public ResponseEntity<?> getWithdrawal(@RequestBody Rewrite_submitWithdrawalDTO rewrite_submitWithdrawalDTO){
        Result result = withdrawalService.getWithdrawals(rewrite_submitWithdrawalDTO.getPageNum(), rewrite_submitWithdrawalDTO.getPageSize());
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
    }
    //后台审核用户提现记录
    @PostMapping("/admin/audit-withdrawal")
    @ApiOperation("审核用户提现")
    public ResponseEntity<?> auditWithdrawal(@RequestBody Rewrite_submitWithdrawalDTO rewrite_submitWithdrawalDTO){
        Result result = withdrawalService.auditWithdrawal(rewrite_submitWithdrawalDTO.getId(), rewrite_submitWithdrawalDTO.getOther(), rewrite_submitWithdrawalDTO.getModifier());
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
    }

    // 获取用户提现信息
    @PostMapping("/getUserInfo/{userid}")
    @ApiOperation("获取用户提现信息")
    public ResponseEntity<?> getUserInfo(@PathVariable Long userid){
        Result result = withdrawalService.getUserInfo(userid);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
    }

    @GetMapping("/getWithdrawalInfo/{withdrawalId}")
    @ApiOperation("获取一条提现数据详细信息")
    public ResponseEntity<?> getWithdrawalInfo(@PathVariable Long withdrawalId){
        Result result = withdrawalService.getWithdrawalInfo(withdrawalId);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
    }
}
