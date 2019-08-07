package com.weisen.www.code.yjf.basic.web.rest.rewrite_008_会员管理;

import com.weisen.www.code.yjf.basic.service.Rewrite_LinkuserService;
import com.weisen.www.code.yjf.basic.service.rewrite.dto.Rewrite_submitMemberDTO;
import com.weisen.www.code.yjf.basic.util.Result;
import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.checkerframework.checker.units.qual.Time;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
@Api(tags = "会员管理")
public class Rewrite_MemberResource {

    private final Logger log = LoggerFactory.getLogger(Rewrite_MemberResource.class);

    private static final String ENTITY_NAME = "basicMember";

    private final Rewrite_LinkuserService rewrite_linkuserService;

    public Rewrite_MemberResource(Rewrite_LinkuserService rewrite_linkuserService) {
        this.rewrite_linkuserService = rewrite_linkuserService;
    }

    @PostMapping("/get-member-info")
    @ApiOperation(value = "查询会员信息")
    @Time
    public ResponseEntity<?> getMemberInfo(@RequestBody Rewrite_submitMemberDTO rewrite_submitMemberDTO){
        Result result = rewrite_linkuserService.getMemberInfo(rewrite_submitMemberDTO);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
    }
}
