package com.weisen.www.code.yjf.basic.web.rest.rewrite_005_地区;

import com.weisen.www.code.yjf.basic.service.rewrite.Rewrite_AreaService;
import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
@Api(tags = "地区信息")
public class Rewrite_AreaResource {

    private static final String ENTITY_NAME = "utilsboxArea";

    private final Rewrite_AreaService areaService;

    public Rewrite_AreaResource(Rewrite_AreaService areaService) {
        this.areaService = areaService;
    }


    @ApiOperation(value = "查询下级城市")
    @PostMapping("/get-next-area-name")
    public ResponseEntity<?> getNextAreaByName(@RequestBody String name){
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(areaService.findNextAreaByName(name)));
    }

}
