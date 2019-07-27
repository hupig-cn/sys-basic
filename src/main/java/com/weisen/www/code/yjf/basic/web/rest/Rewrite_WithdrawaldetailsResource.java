package com.weisen.www.code.yjf.basic.web.rest;


import com.weisen.www.code.yjf.basic.service.Rewrite_UserOrderService;
import com.weisen.www.code.yjf.basic.service.Rewrite_WithdrawaldetailsService;
import com.weisen.www.code.yjf.basic.util.Result;
import io.github.jhipster.web.util.ResponseUtil;
import io.micrometer.core.annotation.Timed;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/withdrawaldetails")
@Api(tags = "000-提现明细流水表")
public class Rewrite_WithdrawaldetailsResource {

    private Rewrite_WithdrawaldetailsService rewrite_WithdrawaldetailsService;

    public Rewrite_WithdrawaldetailsResource(Rewrite_WithdrawaldetailsService rewrite_WithdrawaldetailsService) {
        this.rewrite_WithdrawaldetailsService = rewrite_WithdrawaldetailsService;
    }

    @GetMapping("/findUserWithdrawaldetails/{userId}&{startNum}&{pageSize}")
    @ApiOperation(value = "查询用户的提现明细列表")
    @Timed
    public ResponseEntity<Result> findUserWithdrawaldetails(@PathVariable Long userId,@PathVariable int startNum ,@PathVariable int pageSize) {
        Result result = rewrite_WithdrawaldetailsService.findUserWithdrawaldetails(userId,startNum,pageSize);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
    }


}
