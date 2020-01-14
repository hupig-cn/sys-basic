package com.weisen.www.code.yjf.basic.web.rest;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weisen.www.code.yjf.basic.service.Rewrite_UserassetsService;
import com.weisen.www.code.yjf.basic.service.dto.UserassetsDTO;
import com.weisen.www.code.yjf.basic.service.dto.show_dto.Rewrite_PriceDTO;
import com.weisen.www.code.yjf.basic.service.dto.show_dto.Rewrite_UserPriceDTO;
import com.weisen.www.code.yjf.basic.service.dto.submit_dto.Rewrite_000_RechangeDTO;
import com.weisen.www.code.yjf.basic.util.Result;

import io.github.jhipster.web.util.ResponseUtil;
import io.micrometer.core.annotation.Timed;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/userassets")
@Api(tags = "000-用户资产")
public class Rewrite_UserassetsResource {

    private final Rewrite_UserassetsService rewrite_UserassetsService;

    public Rewrite_UserassetsResource(Rewrite_UserassetsService rewrite_UserassetsService) {
        this.rewrite_UserassetsService = rewrite_UserassetsService;
    }
    
    /**
     * 
     * @author Carson
     * @date 2019-08-23 20:39:22
     * @param rechangeDTO
     * @return
     */
    @PostMapping(value = "/admin/rechange-yue")
	@Timed
	@ApiOperation(value = "余额充值")
	public Result rechangeYue (@RequestBody Rewrite_000_RechangeDTO rechangeDTO) {
    	if(rechangeDTO.getYue() == null || rechangeDTO.getYue().equals("")) {
    		return Result.fail("余额不能为空");
    	}
		return rewrite_UserassetsService.rechangeYue(rechangeDTO.getAccount(), rechangeDTO.getYue());
	}
    
    /**
     * 
     * @author Carson
     * @date 2019-08-23 20:39:54
     * @param rechangeDTO
     * @return
     */
    @PostMapping(value = "/admin/deduct-yue")
	@Timed
	@ApiOperation(value = "余额扣减")
	public Result deductYue (@RequestBody Rewrite_000_RechangeDTO rechangeDTO) {
    	if(rechangeDTO.getYue() == null || rechangeDTO.getYue().equals("")) {
    		return Result.fail("余额不能为空");
    	}
		return rewrite_UserassetsService.deductYue(rechangeDTO.getAccount(), rechangeDTO.getYue());
	}
    
    /**
     * 
     * @author Carson
     * @date 2019-08-23 20:39:22
     * @param rechangeDTO
     * @return
     */
    @PostMapping(value = "/admin/rechange-integral")
	@Timed
	@ApiOperation(value = "积分充值")
	public Result rechangeIntegral (@RequestBody Rewrite_000_RechangeDTO rechangeDTO) {
    	if(rechangeDTO.getIntegral() == null || rechangeDTO.getIntegral().equals("")) {
    		return Result.fail("积分不能为空");
    	}
		return rewrite_UserassetsService.rechangeIntegral(rechangeDTO.getAccount(), rechangeDTO.getIntegral());
	}
    
    /**
     * 
     * @author Carson
     * @date 2019-08-23 20:39:54
     * @param rechangeDTO
     * @return
     */
    @PostMapping(value = "/admin/deduct-integral")
	@Timed
	@ApiOperation(value = "积分扣减")
	public Result deductIntegral (@RequestBody Rewrite_000_RechangeDTO rechangeDTO) {
    	if(rechangeDTO.getIntegral() == null || rechangeDTO.getIntegral().equals("")) {
    		return Result.fail("积分不能为空");
    	}
		return rewrite_UserassetsService.deductIntegral(rechangeDTO.getAccount(), rechangeDTO.getIntegral());
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
        Rewrite_UserPriceDTO rewrite_PriceDTO = rewrite_UserassetsService.findUserBalance(userId);
        if(rewrite_PriceDTO == null){
            return ResponseUtil.wrapOrNotFound(Optional.ofNullable(Result.suc("成功",null)));
        }
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

    @GetMapping("/findUserAssets/{userid}")
    @ApiOperation(value = "查询用户的可用余额 积分 优惠券")
    public UserassetsDTO findUserAssets(@PathVariable String userid) {
    	return rewrite_UserassetsService.findUserAssets(userid);
    }
}
