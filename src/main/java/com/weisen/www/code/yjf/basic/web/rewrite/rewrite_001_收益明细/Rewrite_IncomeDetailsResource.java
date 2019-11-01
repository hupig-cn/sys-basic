package com.weisen.www.code.yjf.basic.web.rewrite.rewrite_001_收益明细;


import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.weisen.www.code.yjf.basic.service.rewrite.Rewrite_IncomeDetailsService;
import com.weisen.www.code.yjf.basic.service.rewrite.dto.Rewrite_GetIncomeAfferentDTO;
import com.weisen.www.code.yjf.basic.util.Result;

import io.github.jhipster.web.util.ResponseUtil;
import io.micrometer.core.annotation.Timed;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * REST controller for managing {@link com.weisen.www.code.yjf.basic.domain.Withdrawal}.
 */
@RestController
@RequestMapping("/api/incomeDetails")
@Api(tags = "001-收益明细")
public class Rewrite_IncomeDetailsResource {

	private final Logger log = LoggerFactory.getLogger(Rewrite_IncomeDetailsResource.class);

    private final Rewrite_IncomeDetailsService incomeDetailsService;

    public Rewrite_IncomeDetailsResource(Rewrite_IncomeDetailsService incomeDetailsService) {
        this.incomeDetailsService = incomeDetailsService;
    }
    /**
     * 获取各推荐人总数
     *
    	 * @author sxx
    	 * @date 2019-10-23 15:55:31
     */
    @PostMapping("/getRecommend/Total")
    @ApiOperation("获取推荐人数量信息")
    public ResponseEntity<?> getRecommendTotal(@RequestParam String recommendId){
		Result result = incomeDetailsService.getRecommendTotal(recommendId);
		log.debug("访问地址: {},传入值: {},返回值: {}", "/getRecommend/Total",recommendId , result);
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
    }

    /**
     * 获取推荐人列表
     *
    	 * @author sxx
    	 * @date 2019-10-23 15:55:31
     */
    @PostMapping("/getRecommend/List")
    @ApiOperation("获取推荐人明细")
    public ResponseEntity<?> getRecommendList(@RequestBody Rewrite_GetIncomeAfferentDTO getIncomeAfferentDTO){
    	Result result = incomeDetailsService.getRecommendList(getIncomeAfferentDTO);
    	log.debug("访问地址: {},传入值: {},返回值: {}", "/getRecommend/Total",getIncomeAfferentDTO , result);
    	return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
    }

    /**
     * 收益列表
     *
    	 * @author sxx
    	 * @date 2019-10-26 15:55:31
     */
    @PostMapping("/Profit/List")
    @ApiOperation("收益列表")
    public ResponseEntity<?> getProfitList(@RequestParam String userId){
        Result result = incomeDetailsService.getProfitList(userId);
        log.debug("访问地址: {},传入值: {},返回值: {}", "/Profit/List",userId, result);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
    }

    /**
     * 推荐用户信息列表
     *
    	 * @author sxx
    	 * @date 2019-10-31 15:55:31
     */
    @PostMapping("/UserInformation/List")
    @ApiOperation("推荐用户信息列表")
    public ResponseEntity<?> userInformationList(@RequestParam String userId,@RequestParam Integer Type
    		,@RequestParam Integer pageNum,@RequestParam Integer pageSize){
        Result result = incomeDetailsService.userInformationList(userId,Type,pageNum,pageSize);
        log.debug("访问地址: {},传入值: {},返回值: {}", "/UserInformation/List",userId, result);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
    }
    
    /**
     * 查询用户商家端收益列表倒叙(重写)
     *
    	 * @author sxx
    	 * @date 2019-11-01 15:55:31
     */
    @PostMapping("/newFindMerchantProfitInfo")
    @ApiOperation("查询用户商家端收益列表倒叙(重写)")
    public ResponseEntity<Result> newFindMerchantProfitInfo(@RequestParam String userid, 
    		@RequestParam Integer startPage, @RequestParam Integer pageSize) {
        Result result = incomeDetailsService.newFindMerchantProfitInfo(userid,startPage,pageSize);
        log.debug("访问地址: {},传入值: {},返回值: {}", "/newFindMerchantProfitInfo",userid+startPage+pageSize, result);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
    }


}
