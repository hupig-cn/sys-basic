package com.weisen.www.code.yjf.basic.web.rest;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weisen.www.code.yjf.basic.service.Rewrite_FeedbackService;
import com.weisen.www.code.yjf.basic.service.dto.submit_dto.Rewrite_FeedbackDTO;
import com.weisen.www.code.yjf.basic.util.Result;

import io.github.jhipster.web.util.ResponseUtil;
import io.micrometer.core.annotation.Timed;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/feedback")
@Api(tags = "000-反馈管理")
public class Rewrite_FeedbackResource {

    private final Rewrite_FeedbackService rewrite_FeedbackService;

    public Rewrite_FeedbackResource(Rewrite_FeedbackService rewrite_FeedbackService) {
        this.rewrite_FeedbackService = rewrite_FeedbackService;
    }
    
    /**
     * 创建反馈信息
     * @author Carson
     * @date 2019-03-16 14:28:25
     * @param rewrite_FeedbackDTO
     * @return
     */
    @PostMapping("/create-feedback")
    @ApiOperation(value = "创建反馈信息")
    @Timed
    public ResponseEntity<Result> createFeedback(@Valid @RequestBody Rewrite_FeedbackDTO rewrite_FeedbackDTO) {
    	Result result = null;
    	if(null != rewrite_FeedbackDTO.getId()) {
    		result = Result.fail("非法传参，ID必须为空");
    	}else {
    		result = rewrite_FeedbackService.createFeedback(rewrite_FeedbackDTO);
    	}
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
    }
    
    /**
     * 查询反馈信息列表
     * @author Carson
     * @date 2019-03-16 14:28:25
     * @param Rewrite_AdvertisementOperationDTO
     * @return
     */
    @GetMapping("/find-feedback-list/{start}&{end}")
    @ApiOperation(value = "查询反馈信息列表")
    @Timed
    public ResponseEntity<Result> findFeedbackList() {
    	Result result = rewrite_FeedbackService.findFeedbackList();
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
    }

}
