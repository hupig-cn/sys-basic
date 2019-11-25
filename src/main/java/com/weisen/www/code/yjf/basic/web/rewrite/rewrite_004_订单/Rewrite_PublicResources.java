package com.weisen.www.code.yjf.basic.web.rewrite.rewrite_004_订单;

import com.weisen.www.code.yjf.basic.service.Rewrite_001_UserorderService;
import com.weisen.www.code.yjf.basic.service.dto.submit_dto.Rewrite_UserOrderDTO;
import com.weisen.www.code.yjf.basic.util.Result;
import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @Author: 阮铭辉
 * @Date: 2019/10/25 15:52
 */
@RestController
@RequestMapping("/api/commodity")
@Api(tags = "商品列表")
public class Rewrite_PublicResources {

    private final Logger log = LoggerFactory.getLogger(Rewrite_PublicResources.class);

    private final Rewrite_001_UserorderService rewrite_001_userorderService;

    public Rewrite_PublicResources(Rewrite_001_UserorderService rewrite_001_userorderService) {
        this.rewrite_001_userorderService = rewrite_001_userorderService;
    }

    @PostMapping("/findAllByTime2")
    @ApiOperation("商品列表")
    public ResponseEntity<?> myfilesList(@RequestParam Integer pageSize,
                                         @RequestParam Integer pageNum,
                                         @RequestParam Integer type,
                                         @RequestParam Integer condition){
        Result result = rewrite_001_userorderService.myfilesList(pageSize,pageNum,type,condition);
        log.debug("访问地址: {},传入值: {},返回值: {}","/api/balance/Orderdetail", "传入值:"+pageSize+";"+pageNum, result);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
    }

    @PostMapping("/findCommodityInfo2")
    @ApiOperation("商品详情")
    public ResponseEntity<?> myfilesLists(@RequestParam String commodityId){
        Result result = rewrite_001_userorderService.myfilesLists(commodityId);
        log.debug("访问地址: {},传入值: {},返回值: {}","/api/balance/Orderdetail", "传入值:"+commodityId, result);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
    }

}
