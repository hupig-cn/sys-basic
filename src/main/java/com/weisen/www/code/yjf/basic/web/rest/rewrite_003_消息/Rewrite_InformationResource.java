package com.weisen.www.code.yjf.basic.web.rest.rewrite_003_消息;

import com.weisen.www.code.yjf.basic.service.rewrite.Rewrite_InformationService;
import com.weisen.www.code.yjf.basic.service.rewrite.dto.Rewrite_submitInformationDTO;
import com.weisen.www.code.yjf.basic.service.rewrite.submit_dto.Rewrite_InformationDetailsDTO;
import com.weisen.www.code.yjf.basic.util.Result;
import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * REST controller for managing {@link com.weisen.www.code.yjf.basic.domain.Information}.
 */
@RestController
@RequestMapping("/api")
@Api(tags = "000-消息")
public class Rewrite_InformationResource {

    private final Logger log = LoggerFactory.getLogger(Rewrite_InformationResource.class);

    private static final String ENTITY_NAME = "basicInformation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final Rewrite_InformationService informationService;

    public Rewrite_InformationResource(Rewrite_InformationService informationService) {
        this.informationService = informationService;
    }


    @PostMapping("/insert-Message")
    @ApiOperation(value = "保存消息")
    public ResponseEntity<?> insertMessage(@RequestBody Rewrite_submitInformationDTO rewrite_submitInformationDTO){
        Result result = informationService.insertInformation(rewrite_submitInformationDTO);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
    }
    @PostMapping("/information-details")
    @ApiOperation("获取消息详情")
    public ResponseEntity<?> readMessage(@RequestBody Rewrite_InformationDetailsDTO details){
        Result result = informationService.readInformation(details.getId(),details.getUserId());
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
    }
    @PostMapping("/read-all-information")
    @ApiOperation("全部消息已读")
    public ResponseEntity<?> readAllMessage(@RequestBody Rewrite_InformationDetailsDTO details){
        Result result = informationService.readAllInformation(details.getUserId());
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
    }
    @PostMapping("/information-delete")
    @ApiOperation("批量删除消息")
    public ResponseEntity<?> deleteMessage(@RequestBody Rewrite_InformationDetailsDTO details){
        Result result = informationService.deleteInformations(details.getIds(),details.getUserId());
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
    }
    @PostMapping("/get-account-information")
    @ApiOperation("获取账号消息列表")
    public ResponseEntity<?> getInformations(@RequestBody Rewrite_InformationDetailsDTO details){
        Result result = informationService.getInformations(details);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
    }
}
