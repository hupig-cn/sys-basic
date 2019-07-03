package com.weisen.www.code.yjf.basic.web.rest.rewrite_000_验证码;

import com.weisen.www.code.yjf.basic.service.rewrite.Rewrite_CoderecordService;
import com.weisen.www.code.yjf.basic.service.rewrite.submit_dto.Rewrite_submitCoderecordDTO;
import com.weisen.www.code.yjf.basic.util.Result;
import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.Api;
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
 * REST controller for managing {@link com.weisen.www.code.yjf.basic.domain.Coderecord}.
 */
@RestController
@RequestMapping("/api/public")
@Api(tags = "000-验证码")
public class Rewrite_CoderecordResource {

    private final Logger log = LoggerFactory.getLogger(Rewrite_CoderecordResource.class);

    private static final String ENTITY_NAME = "basicCoderecord";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final Rewrite_CoderecordService coderecordService;

    public Rewrite_CoderecordResource(Rewrite_CoderecordService coderecordService) {
        this.coderecordService = coderecordService;
    }

    @PostMapping("/send-code")
    public ResponseEntity<?> sendCode(@RequestBody Rewrite_submitCoderecordDTO coderecordDTO) {
        Result result = coderecordService.sendCode(coderecordDTO);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
    }

    @PostMapping("/check-code")
    public ResponseEntity<?> checkCode(@RequestBody Rewrite_submitCoderecordDTO coderecordDTO){
        Result result = coderecordService.checkCode(coderecordDTO);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
    }
}
