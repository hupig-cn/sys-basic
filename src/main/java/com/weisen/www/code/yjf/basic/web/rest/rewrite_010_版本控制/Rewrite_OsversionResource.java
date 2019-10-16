package com.weisen.www.code.yjf.basic.web.rest.rewrite_010_版本控制;

import com.weisen.www.code.yjf.basic.service.dto.AppControlDataDTO;
import com.weisen.www.code.yjf.basic.service.dto.AppControlUpdateDTO;
import com.weisen.www.code.yjf.basic.service.rewrite.Rewrite_OsversionService;
import com.weisen.www.code.yjf.basic.util.Result;
import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * REST controller for managing Osversion.
 */
@RestController
@RequestMapping("/api")
@Api(tags = "版本控制")
public class Rewrite_OsversionResource {

    private final Logger log = LoggerFactory.getLogger(Rewrite_OsversionResource.class);
//
//    private static final String ENTITY_NAME = "basicOsversion";

    private final Rewrite_OsversionService osversionService;

    public Rewrite_OsversionResource(Rewrite_OsversionService osversionService) {
        this.osversionService = osversionService;
    }

//    @PostMapping("/chenck-os-version")
//    @ApiOperation(value = "比对设备版本")
//    public ResponseEntity<?> checkOsVersion(@RequestBody Rewrite_submitOsVersionDTO rewrite_submitOsVersionDTO){
//        Result result = osversionService.checkOsVersion(rewrite_submitOsVersionDTO);
//        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
//    }
//    @PostMapping("/insert-os-version")
//    @ApiOperation(value = "保存版本信息")
//    public ResponseEntity<?> insertOsVersion(@RequestBody Rewrite_OsversionDTO rewrite_osversionDTO){
//        Result result = osversionService.insertOsVersion(rewrite_osversionDTO);
//        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
//    }
//    @GetMapping("/get-os-list")
//    @ApiOperation(value = "后台获取版本控制列表")
//    public ResponseEntity<?> getOsVersionList(@RequestBody Rewrite_submitOsVersionListDTO rewrite_submitOsVersionListDTO){
//        Result result = osversionService.getOsVersionList(rewrite_submitOsVersionListDTO);
//        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
//    }
	/**
	 * 添加App版本
	 * @author sxx
	 * @date 2019-10-14 15:55:31
	 */
	@PostMapping("/public/addAppControl")
	@ApiOperation(value = "添加App版本")
	public ResponseEntity<Result> AdminAddAppControl(@RequestBody AppControlDataDTO appControllerDataDTO){
		
		Result result = osversionService.adminAddAppControl(appControllerDataDTO);
		log.debug("访问地址: {},传入值: {},返回值: {}", "/public/addAppControl", appControllerDataDTO, result);
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
	}
	/**
	 * 修改App版本
	 * 
	 * @param memberDTO
	 * @return
	 * @throws URISyntaxException
	 */
	@PostMapping("/public/updateControl")
	@ApiOperation(value = "修改App版本")
	public ResponseEntity<Result> adminUpdateControl() {
		Result result = osversionService.adminUpdateControl();
		log.debug("访问地址: {},传入值: {},返回值: {}", "/public/updateControl", "", result);
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
	}
//	
	/**
	 * App版本更新
	 * @author sxx
	 * @date 2019-10-14 15:55:31
	 */
	@PostMapping("/public/appVersionUpdate")
	@ApiOperation(value = "App版本更新")
	public ResponseEntity<Result> adminAppVersionUpdate(AppControlUpdateDTO appControllerUpdateDTO) {
		Result result = osversionService.appVersionUpdate(appControllerUpdateDTO);
		log.debug("访问地址: {},传入值: {},返回值: {}", "/public/AppVersionUpdate", appControllerUpdateDTO, result);
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
	}
}
