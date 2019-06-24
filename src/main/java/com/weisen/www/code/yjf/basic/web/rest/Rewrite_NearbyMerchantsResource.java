package com.weisen.www.code.yjf.basic.web.rest;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weisen.www.code.yjf.basic.service.Rewrite_NearbyMerchantsService;
import com.weisen.www.code.yjf.basic.service.dto.submit_dto.Rewrite_AdvertisementPageDTO;
import com.weisen.www.code.yjf.basic.util.Result;

import io.github.jhipster.web.util.ResponseUtil;
import io.micrometer.core.annotation.Timed;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/weisen/nearby-merchants")
@Api(tags = "附近商家")
public class Rewrite_NearbyMerchantsResource {

    private Rewrite_NearbyMerchantsService rewrite_NearbyMerchantsService;

    public Rewrite_NearbyMerchantsResource(Rewrite_NearbyMerchantsService rewrite_NearbyMerchantsService) {
        this.rewrite_NearbyMerchantsService = rewrite_NearbyMerchantsService;
    }

    /**
     * 查询附近商家列表
     * @author Carson
     * @date 2019-03-16 14:28:25
     * @param Rewrite_AdvertisementOperationDTO
     * @return
     */
    @PostMapping("/find-list")
    @ApiOperation(value = "查询附近商家列表")
    @Timed
    public ResponseEntity<Result> findList(@Valid @RequestBody Rewrite_AdvertisementPageDTO rewrite_AdvertisementPageDTO) {
    	Result result = rewrite_NearbyMerchantsService.findList(rewrite_AdvertisementPageDTO);
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
    }

}
