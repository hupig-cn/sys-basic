package com.weisen.www.code.yjf.basic.web.rest;

import com.weisen.www.code.yjf.basic.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.weisen.www.code.yjf.basic.service.Rewrite_UserlinkuserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * REST controller for managing {@link com.weisen.www.code.yjf.basic.domain.Userlinkuser}.
 */
@RestController
@RequestMapping("/api")
@Api(tags = "000-推荐人信息")
public class Rewrite_UserlinkuserResource {

    private final Logger log = LoggerFactory.getLogger(Rewrite_UserlinkuserResource.class);

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final Rewrite_UserlinkuserService rewrite_UserlinkuserService;

    public Rewrite_UserlinkuserResource(Rewrite_UserlinkuserService rewrite_UserlinkuserService) {
        this.rewrite_UserlinkuserService = rewrite_UserlinkuserService;
    }

    @GetMapping("/getMyRecommendName/{userid}")
    @ApiOperation(value = "获取当前用户的推荐人姓名")
    public String getMyRecommendName(@PathVariable String userid) {
        log.debug("REST request to get Userlinkuser : {}", userid);
        return rewrite_UserlinkuserService.findRecommendName(userid);
    }
    
    @GetMapping("/getMyPartner/{userid}")
    @ApiOperation(value = "获取当前用户是否是合伙人")
    public Boolean getMyPartner(@PathVariable String userid) {
        log.debug("REST request to get Userlinkuser : {}", userid);
        return rewrite_UserlinkuserService.getMyPartner(userid);
    }
    
    @GetMapping("/getMyPartner2/{userid}")
    @ApiOperation(value = "获取当前用户是否是合伙人")
    public Result getMyPartner2(@PathVariable String userid) {
        log.debug("REST request to get Userlinkuser : {}", userid);
        return rewrite_UserlinkuserService.getMyPartner2(userid);
    }

    
    @GetMapping("/findAllByRecommendAndInfo")
    @ApiOperation(value = "分页查询用户的推荐人（时间 电话或token 做处理）")
    public Result findAllByRecommendAndInfo(@RequestParam String userid, int startPage, int pageSize) {
        log.debug("REST request to get Userlinkuser : {}", userid);
        Result result = rewrite_UserlinkuserService.findAllByRecommendAndInfo(userid,startPage,pageSize);
        return result;
    }
}
