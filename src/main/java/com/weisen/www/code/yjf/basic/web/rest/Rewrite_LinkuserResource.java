package com.weisen.www.code.yjf.basic.web.rest;

import java.net.URISyntaxException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weisen.www.code.yjf.basic.service.Rewrite_LinkuserService;
import com.weisen.www.code.yjf.basic.service.dto.LinkuserDTO;
import com.weisen.www.code.yjf.basic.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * REST controller for managing {@link com.weisen.www.code.yjf.basic.domain.Linkuser}.
 */
@RestController
@RequestMapping("/api")
@Api(tags = "000-用户附加信息操作")
public class Rewrite_LinkuserResource {

    private final Logger log = LoggerFactory.getLogger(Rewrite_LinkuserResource.class);

    private static final String ENTITY_NAME = "basicLinkuser";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final Rewrite_LinkuserService rewrite_LinkuserService;

    public Rewrite_LinkuserResource(Rewrite_LinkuserService rewrite_LinkuserService) {
        this.rewrite_LinkuserService = rewrite_LinkuserService;
    }

    @PutMapping("/authentication")
    @ApiOperation(value = "实名认证")
    public String updateLinkuser(@RequestBody LinkuserDTO linkuserDTO) throws URISyntaxException {
        log.debug("REST request to update Linkuser : {}", linkuserDTO);
        return rewrite_LinkuserService.authentication(linkuserDTO);
    }

    @GetMapping("/getlinkusers/{userid}")
    @ApiOperation(value = "根据用户id获取用户附加信息")
    public ResponseEntity<LinkuserDTO> getLinkuser(@PathVariable String userid) {
        log.debug("REST request to get Linkuser : {}", userid);
        Optional<LinkuserDTO> linkuserDTO = rewrite_LinkuserService.findByUserid(userid);
        return ResponseUtil.wrapOrNotFound(linkuserDTO);
    }
}
