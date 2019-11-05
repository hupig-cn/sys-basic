package com.weisen.www.code.yjf.basic.web.rest.rewrite_009_数据统计;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weisen.www.code.yjf.basic.service.rewrite.Rewrite_StatisticsService;
import com.weisen.www.code.yjf.basic.util.Result;

import io.github.jhipster.web.util.ResponseUtil;
import io.micrometer.core.annotation.Timed;
import io.swagger.annotations.ApiOperation;

/**
 * REST controller for managing Statistics.
 */
@RestController
@RequestMapping("/api")
public class Rewrite_StatisticsResource {

    private final Rewrite_StatisticsService statisticsService;

    public Rewrite_StatisticsResource(Rewrite_StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

   @GetMapping("/get-user-asset/{id}")
   @ApiOperation("获取用户当日统计数据")
   @Timed
   public ResponseEntity<?> getAssetToDay(@PathVariable("id") Long userId){
       Result result = statisticsService.findUserAsset(userId);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
   }

}
