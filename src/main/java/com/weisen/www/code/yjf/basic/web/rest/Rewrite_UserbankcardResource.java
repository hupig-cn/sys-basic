package com.weisen.www.code.yjf.basic.web.rest;

import com.weisen.www.code.yjf.basic.service.Rewrite_UserbankcardService;
import com.weisen.www.code.yjf.basic.service.Rewrite_UserlinkuserService;
import com.weisen.www.code.yjf.basic.service.dto.UserbankcardDTO;
import com.weisen.www.code.yjf.basic.service.dto.UsercardDTO;
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

    @PostMapping("/createBankCard")
    @ApiOperation(value = "用户添加银行卡")
    public ResponseEntity<Result> createBankCard(@RequestBody UserbankcardDTO userbankcardDTO) {
        log.debug("REST request to get createBankCard : {}", userbankcardDTO);
        Result result = Rewrite_UserbankcardService.createBankCard(userbankcardDTO);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
    }

    @PostMapping("/deleteBackCard/{userId}")
    @ApiOperation(value = "用户删除银行卡")
    public ResponseEntity<Result> deleteBackCard(@PathVariable Long userId) {
        log.debug("REST request to get deleteBackCard : {}", userId);
        Result result = Rewrite_UserbankcardService.deleteBackCard(userId);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
    }




}
