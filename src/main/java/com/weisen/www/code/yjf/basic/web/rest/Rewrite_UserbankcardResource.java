package com.weisen.www.code.yjf.basic.web.rest;

import com.weisen.www.code.yjf.basic.service.Rewrite_UserbankcardService;
import com.weisen.www.code.yjf.basic.service.Rewrite_UserlinkuserService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bankcard")
@Api(tags = "000-推荐人信息")
public class Rewrite_UserbankcardResource {

    private final Logger log = LoggerFactory.getLogger(Rewrite_UserbankcardResource.class);

    private final Rewrite_UserbankcardService Rewrite_UserbankcardService;

    public Rewrite_UserbankcardResource(Rewrite_UserbankcardService Rewrite_UserbankcardService) {
        this.Rewrite_UserbankcardService = Rewrite_UserbankcardService;
    }
}
