package com.weisen.www.code.yjf.basic.web.rest;

import java.net.URISyntaxException;

import com.weisen.www.code.yjf.basic.service.dto.submit_dto.Rewrite_BIndAliWechat;
import com.weisen.www.code.yjf.basic.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import com.weisen.www.code.yjf.basic.domain.Linkuser;
import com.weisen.www.code.yjf.basic.service.Rewrite_LinkuserService;
import com.weisen.www.code.yjf.basic.service.dto.LinkuserDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * REST controller for managing
 * {@link com.weisen.www.code.yjf.basic.domain.Linkuser}.
 */
@RestController
@RequestMapping("/api")
@Api(tags = "000-用户附加信息操作")
public class Rewrite_LinkuserResource {

	private final Logger log = LoggerFactory.getLogger(Rewrite_LinkuserResource.class);

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
	
	@GetMapping("/queryRealName/{userid}")
	@ApiOperation(value = "查询实名信息")
	public String queryRealName(@PathVariable String userid) {
		return rewrite_LinkuserService.queryRealName(userid);
	}

	@GetMapping("/getlinkusers/{userid}")
	@ApiOperation(value = "根据用户id获取用户附加信息")
	public Linkuser getLinkuser(@PathVariable String userid) {
		log.debug("REST request to get Linkuser : {}", userid);
		Linkuser linkuser = rewrite_LinkuserService.findByUserid(userid);
		return linkuser;
	}

    @PostMapping("/bindALiPayOrWeChat")
    @ApiOperation(value = "绑定支付宝或微信账号")
    public Result bindALiPayOrWeChat(@RequestBody Rewrite_BIndAliWechat rewrite_BIndAliWechat) {
        log.debug("REST request to get Linkuser : {}", rewrite_BIndAliWechat);
        Result result = rewrite_LinkuserService.bindALiPayOrWeChat(rewrite_BIndAliWechat);
        return result;
    }
    
    @PostMapping("/deleteALiPayorWeChat")
    @ApiOperation(value = "删除支付宝或微信账号")
    public Result deleteALiPayorWeChat(String userid ,Integer type ) {
        log.debug("REST request to get Linkuser : {}", userid,type);
        Result result = rewrite_LinkuserService.deleteALiPayorWeChat(userid,type);
        return result;
    }
    
    
}
