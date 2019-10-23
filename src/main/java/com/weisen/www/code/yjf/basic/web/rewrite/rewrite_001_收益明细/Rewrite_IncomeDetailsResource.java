package com.weisen.www.code.yjf.basic.web.rewrite.rewrite_001_收益明细;


import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.weisen.www.code.yjf.basic.service.rewrite.Rewrite_IncomeDetailsService;
import com.weisen.www.code.yjf.basic.util.Result;

import io.github.jhipster.web.util.ResponseUtil;
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
    @ApiOperation("获取各推荐人总数")
    public ResponseEntity<?> getRecommendTotal(String userId){
		Result result = incomeDetailsService.getRecommendTotal(userId);
		log.debug("访问地址: {},传入值: {},返回值: {}", "/getRecommend/Total",userId , result);
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
    }
    
    /**
     * 获取推荐人列表
     * 
    	 * @author sxx
    	 * @date 2019-10-23 15:55:31
     */
    @PostMapping("/getRecommend/List")
    @ApiOperation("获取推荐人列表")
    public ResponseEntity<?> getRecommendList(String userId){
    	Result result = incomeDetailsService.getRecommendList(userId);
    	log.debug("访问地址: {},传入值: {},返回值: {}", "/getRecommend/Total",userId , result);
    	return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
    }
    
    
}
