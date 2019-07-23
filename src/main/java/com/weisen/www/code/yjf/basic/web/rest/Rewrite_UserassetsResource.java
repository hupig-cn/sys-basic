package com.weisen.www.code.yjf.basic.web.rest;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weisen.www.code.yjf.basic.service.Rewrite_UserassetsService;
import com.weisen.www.code.yjf.basic.service.dto.UserassetsDTO;
import com.weisen.www.code.yjf.basic.service.dto.show_dto.Rewrite_PriceDTO;
import com.weisen.www.code.yjf.basic.util.Result;

import io.github.jhipster.web.util.ResponseUtil;
import io.micrometer.core.annotation.Timed;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/userassets")
@Api(tags = "000-用户资产")
public class Rewrite_UserassetsResource {

    private final Logger log = LoggerFactory.getLogger(Rewrite_UserassetsResource.class);

    private final Rewrite_UserassetsService rewrite_UserassetsService;

    public Rewrite_UserassetsResource(Rewrite_UserassetsService rewrite_UserassetsService) {
        this.rewrite_UserassetsService = rewrite_UserassetsService;
    }

    /**
     * 查询用户余额
     * @param userId
     * @return
     */
    @GetMapping("/findUserBalance/{userId}")
    @ApiOperation(value = "查询用户余额")
    @Timed
    public ResponseEntity<Result> findUserBalance(@PathVariable Long userId) {
        Rewrite_PriceDTO rewrite_PriceDTO = rewrite_UserassetsService.findUserBalance(userId);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(Result.suc("成功",rewrite_PriceDTO)));
    }

    /**
     * 查询用户的余额 积分 优惠券
     * @param userId
     * @return
     */
    @GetMapping("/findUserInfo/{userId}")
    @ApiOperation(value = "查询用户的余额 积分 优惠券")
    @Timed
    public ResponseEntity<Result> findUserInfo(@PathVariable Long userId) {
        Rewrite_PriceDTO rewrite_PriceDTO = rewrite_UserassetsService.findUserInfo(userId);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(Result.suc("成功",rewrite_PriceDTO)));
    }
    
    @GetMapping("/findUserAssets/{userId}")
    @ApiOperation(value = "查询用户的可用余额 积分 优惠券")
    public UserassetsDTO findUserAssets(@PathVariable String userid) {
    	return rewrite_UserassetsService.findUserAssets(userid);
    }
}
