package com.weisen.www.code.yjf.basic.web.rewrite.rewrite_004_订单;

import com.weisen.www.code.yjf.basic.service.Rewrite_001_UserorderService;
import com.weisen.www.code.yjf.basic.service.dto.submit_dto.Rewrite_UserOrderDTO;
import com.weisen.www.code.yjf.basic.util.Result;
import com.weisen.www.code.yjf.basic.web.rest.Rewrite_UserOrderResource;
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
@RequestMapping("/api/userOrder")
@Api(tags = "订单")
public class Rewrite_UserOrderResources {

    private final Logger log = LoggerFactory.getLogger(Rewrite_UserOrderResources.class);

    private final Rewrite_001_UserorderService rewrite_001_userorderService;

    public Rewrite_UserOrderResources(Rewrite_001_UserorderService rewrite_001_userorderService) {
        this.rewrite_001_userorderService = rewrite_001_userorderService;
    }


    @PostMapping("/myUserOrder")
    @ApiOperation("全部订单")
    public ResponseEntity<?> myUserOrder(@RequestParam(required = false) String userid,
                                         @RequestParam(required = false) String orderState,
                                         @RequestParam(required = false) Integer pageNum,
                                         @RequestParam(required = false) Integer pageSize){
        Result result = rewrite_001_userorderService.myUserOrder(userid,orderState,pageNum,pageSize);
        log.debug("访问地址: {},传入值: {},返回值: {}","/api/balance/myUserOrder", "传入值:"+userid, result);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));

    }

    @PostMapping("/OrdersConfirmation")
    @ApiOperation("对订单的操作")
    public ResponseEntity<?> OrdersConfirmation(@RequestParam(required = false) String userid,
                                                @RequestParam(required = false) String ordercode){
        Result result = rewrite_001_userorderService.OrdersConfirmation(userid,ordercode);
        log.debug("访问地址: {},传入值: {},返回值: {}","/api/balance/OrdersConfirmation", "传入值:"+userid+":"+ordercode, result);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
    }
    @PostMapping("/Orderdetail")
    @ApiOperation("订单详情")
    public ResponseEntity<?> Orderdetail(@RequestParam(required = false) String userid,
                                         @RequestParam(required = false) String ordercode){
        Result result = rewrite_001_userorderService.Orderdetail(userid,ordercode);
        log.debug("访问地址: {},传入值: {},返回值: {}","/api/balance/Orderdetail", "传入值:"+userid+":"+ordercode, result);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
    }

    @PostMapping("/CreateOrder")
    @ApiOperation("用户下单")
    public ResponseEntity<?> CreateOrder(@RequestBody Rewrite_UserOrderDTO dto){
        Result result = rewrite_001_userorderService.CreateOrder(dto);
        log.debug("访问地址: {},传入值: {},返回值: {}","/api/balance/Orderdetail", "传入值:"+dto.toString(), result);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
    }
}
