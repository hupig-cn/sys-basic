package com.weisen.www.code.yjf.basic.web.rest.rewrite_005_地区;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weisen.www.code.yjf.basic.domain.Area;
import com.weisen.www.code.yjf.basic.service.rewrite.Rewrite_AreaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api(tags = "000-地区信息")
public class Rewrite_AreaResource {

    private final Rewrite_AreaService rewrite_AreaService;

    public Rewrite_AreaResource(Rewrite_AreaService rewrite_AreaService) {
        this.rewrite_AreaService = rewrite_AreaService;
    }

    @ApiOperation(value = "查询下级城市")
    @GetMapping("/get-next-area-pname/{pname}")
    public ResponseEntity<List<Area>> getNextAreaByPname(@PathVariable String pname){
    	List<Area> area = rewrite_AreaService.findNextAreaByPname(pname);
    	return ResponseEntity.ok().body(area);
    }
}
