package com.weisen.www.code.yjf.basic.web.rest;

import com.weisen.www.code.yjf.basic.service.Rewrite_UsercardService;
import com.weisen.www.code.yjf.basic.service.UsercardService;
import com.weisen.www.code.yjf.basic.service.dto.UsercardDTO;
import com.weisen.www.code.yjf.basic.util.Result;
import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/usercard")
public class Rewrite_UsercardResource {

    private final Logger log = LoggerFactory.getLogger(UsercardResource.class);

    private final Rewrite_UsercardService rewrite_UsercardService;

    public Rewrite_UsercardResource(Rewrite_UsercardService rewrite_UsercardService) {
        this.rewrite_UsercardService = rewrite_UsercardService;
    }

    @PostMapping("/createUserCard")
    @ApiOperation(value = "创建银行卡信息")
    public ResponseEntity<Result> createUserCard(@RequestBody UsercardDTO usercardDTO) {
        log.debug("REST request to get createUserCard : {}", usercardDTO);
        Result result = rewrite_UsercardService.createUserCard(usercardDTO);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
    }

}
