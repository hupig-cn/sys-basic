package com.weisen.www.code.yjf.basic.web.rest;

import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weisen.www.code.yjf.basic.service.Rewrite_FeedbackService;
import com.weisen.www.code.yjf.basic.service.dto.submit_dto.Rewrite_AdvertisementDTO;
import com.weisen.www.code.yjf.basic.util.Result;

import io.github.jhipster.web.util.ResponseUtil;
import io.micrometer.core.annotation.Timed;
import io.swagger.annotations.ApiOperation;

/**
 * REST controller for managing {@link com.weisen.www.code.yjf.basic.domain.Feedback}.
 */
@RestController
@RequestMapping("/weisen")
public class Rewrite_FeedbackResource {

    private final Logger log = LoggerFactory.getLogger(Rewrite_FeedbackResource.class);

    private final Rewrite_FeedbackService rewrite_FeedbackService;

    public Rewrite_FeedbackResource(Rewrite_FeedbackService rewrite_FeedbackService) {
        this.rewrite_FeedbackService = rewrite_FeedbackService;
    }
    
    /**
     * 反馈管理
     * @author Carson
     * @date 2019-03-16 14:28:25
     * @param Rewrite_AdvertisementDTO
     * @return
     */
    @PostMapping("/create-advertisement")
    @ApiOperation(value = "反馈管理")
    @Timed
    public ResponseEntity<Result> createAdvertisement(@Valid @RequestBody Rewrite_AdvertisementDTO rewrite_AdvertisementDTO) {
    	Result result = null;
    	if(null != rewrite_AdvertisementDTO.getId()) {
    		result = Result.fail("非法传参，ID必须为空");
    	}else {
    		result = rewrite_FeedbackService.createAdvertisement(rewrite_AdvertisementDTO);
    	}
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
    }

}
