package com.weisen.www.code.yjf.basic.web.rest;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weisen.www.code.yjf.basic.service.Rewrite_AdvertisementService;
import com.weisen.www.code.yjf.basic.service.dto.submit_dto.Rewrite_AdvertisementDTO;
import com.weisen.www.code.yjf.basic.service.dto.submit_dto.Rewrite_AdvertisementOperationDTO;
import com.weisen.www.code.yjf.basic.service.dto.submit_dto.Rewrite_AdvertisementPageDTO;
import com.weisen.www.code.yjf.basic.util.Result;

import io.github.jhipster.web.util.ResponseUtil;
import io.micrometer.core.annotation.Timed;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/advertisement")
@Api(tags = "000-广告管理")
public class Rewrite_AdvertisementResource {

    private Rewrite_AdvertisementService rewrite_AdvertisementService;

    public Rewrite_AdvertisementResource(Rewrite_AdvertisementService rewrite_AdvertisementService) {
        this.rewrite_AdvertisementService = rewrite_AdvertisementService;
    }

    /**
     * APP首页轮播图显示
     * @author Carson
     * @date 2019-03-16 14:40:33
     * @param
     * @return
     */
    @GetMapping("/app-homepage-advertisement")
    @ApiOperation(value = "APP首页轮播图显示")
    @Timed
    public ResponseEntity<Result> appHomepageAdvertisement() {
    	Result result = rewrite_AdvertisementService.appHomepageAdvertisement();
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
    }

    /**
     * 新增广告管理
     * @author Carson
     * @date 2019-03-16 14:28:25
     * @param Rewrite_AdvertisementDTO
     * @return
     */
    @PostMapping("/create-advertisement")
    @ApiOperation(value = "新增广告管理")
    @Timed
    public ResponseEntity<Result> createAdvertisement(@Valid @RequestBody Rewrite_AdvertisementDTO rewrite_AdvertisementDTO) {
    	Result result = null;
    	if(null != rewrite_AdvertisementDTO.getId()) {
    		result = Result.fail("非法传参，ID必须为空");
    	}else {
    		result = rewrite_AdvertisementService.createAdvertisement(rewrite_AdvertisementDTO);
    	}
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
    }

    /**
     * 修改广告管理
     * @author Carson
     * @date 2019-03-16 14:28:25
     * @param Rewrite_AdvertisementDTO
     * @return
     */
    @PostMapping("/update-advertisement")
    @ApiOperation(value = "修改广告管理")
    @Timed
    public ResponseEntity<Result> updateAdvertisement(@Valid @RequestBody Rewrite_AdvertisementDTO rewrite_AdvertisementDTO) {
    	Result result = null;
    	if(null == rewrite_AdvertisementDTO.getId()) {
    		result = Result.fail("非法传参，ID不能为空");
    	}else {
    		result = rewrite_AdvertisementService.updateAdvertisement(rewrite_AdvertisementDTO);
    	}
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
    }

    /**
     * 操作广告管理(启用、禁用、删除)
     * @author Carson
     * @date 2019-03-16 14:28:25
     * @param Rewrite_AdvertisementOperationDTO
     * @return
     */
    @PostMapping("/operation-advertisement")
    @ApiOperation(value = "操作广告管理(启用、禁用、删除)")
    @Timed
    public ResponseEntity<Result> operationAdvertisement(@Valid @RequestBody Rewrite_AdvertisementOperationDTO rewrite_AdvertisementOperationDTO) {
    	Result result = null;
    	if(null == rewrite_AdvertisementOperationDTO.getId()) {
    		result = Result.fail("非法传参，ID不能为空");
    	}else if(null == rewrite_AdvertisementOperationDTO.getOperation()){
    		result = Result.fail("非法传参，Operation不能为空");
    	}else {
    		result = rewrite_AdvertisementService.operationAdvertisement(rewrite_AdvertisementOperationDTO);
    	}
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
    }
    
    /**
     * 批量广告管理
     * @author Carson
     * @date 2019-03-16 14:28:25
     * @param Rewrite_AdvertisementOperationDTO
     * @return
     */
    @PostMapping("/batch-delete-advertisement")
    @ApiOperation(value = "批量广告管理")
    @Timed
    public ResponseEntity<Result> batchDeleteAdvertisement(@RequestBody List<Long> deleteIds) {
    	Result result = null;
    	if(null == deleteIds || deleteIds.size() == 0) {
    		result = Result.fail("非法传参，请选择数据");
    	} else {
    		result = rewrite_AdvertisementService.batchDeleteAdvertisement(deleteIds);
    	}
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
    }

    /**
     * 查询单条广告管理
     * @author Carson
     * @date 2019-03-16 14:28:25
     * @param Rewrite_AdvertisementOperationDTO
     * @return
     */
    @PostMapping("/get-one-advertisement")
    @ApiOperation(value = "查询单条广告管理")
    @Timed
    public ResponseEntity<Result> getOneAdvertisement(@Valid @RequestBody Rewrite_AdvertisementOperationDTO rewrite_AdvertisementOperationDTO) {
    	Result result = null;
    	if(null == rewrite_AdvertisementOperationDTO.getId()) {
    		result = Result.fail("非法传参，ID不能为空");
    	}else {
    		result = rewrite_AdvertisementService.getOneAdvertisement(rewrite_AdvertisementOperationDTO);
    	}
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
    }

    /**
     * 查询广告管理列表
     * @author Carson
     * @date 2019-03-16 14:28:25
     * @param Rewrite_AdvertisementOperationDTO
     * @return
     */
    @PostMapping("/find-advertisement-list-by-name")
    @ApiOperation(value = "查询广告管理列表")
    @Timed
    public ResponseEntity<Result> findAdvertisementListByName(@Valid @RequestBody Rewrite_AdvertisementPageDTO rewrite_AdvertisementPageDTO) {
    	Result result = rewrite_AdvertisementService.findAdvertisementListByName(rewrite_AdvertisementPageDTO);
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
    }

}
