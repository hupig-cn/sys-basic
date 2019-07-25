package com.weisen.www.code.yjf.basic.web.rest;

import com.weisen.www.code.yjf.basic.service.Rewrite_UserOrderService;
import com.weisen.www.code.yjf.basic.service.dto.UserorderDTO;
import com.weisen.www.code.yjf.basic.service.dto.submit_dto.Rewrite_AnOrder;
import com.weisen.www.code.yjf.basic.util.Result;
import io.github.jhipster.web.util.ResponseUtil;
import io.micrometer.core.annotation.Timed;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/userorder")
@Api(tags = "000-用户订单")
public class Rewrite_UserOrderResource {

    private Rewrite_UserOrderService rewrite_UserOrderService;

    public Rewrite_UserOrderResource(Rewrite_UserOrderService rewrite_UserOrderService) {
        this.rewrite_UserOrderService = rewrite_UserOrderService;
    }

    /**
     * 获取用户当日的订单量（区分端）
     * @param userId
     * @return
     */
    @GetMapping("/getTodayOrderNum/{userId}")
    @ApiOperation(value = "获取用户当日的订单量（区分端）")
    @Timed
    public ResponseEntity<Result> getTodayOrderNum(@PathVariable Long userId) {
        int count = rewrite_UserOrderService.getTodayOrderNum(userId);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(Result.suc("成功",count)));
    }

    /**
     * 获取待付款订单
     * @param userId
     * @return
     */
    @GetMapping("/getUnpaidOrder/{userId}")
    @ApiOperation(value = "获取待付款订单")
    @Timed
    public ResponseEntity<Result> getUnpaidOrder(@PathVariable Long userId) {
        List<UserorderDTO> userorder = rewrite_UserOrderService.getUnpaidOrder(userId);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(Result.suc("成功",userorder)));
    }

    /**
     * 获取已支付订单
     * @param userId
     * @return
     */
    @GetMapping("/getPaidOrder/{userId}")
    @ApiOperation(value = "获取已支付订单")
    @Timed
    public ResponseEntity<Result> getPaidOrder(@PathVariable Long userId) {
        List<UserorderDTO> userorder = rewrite_UserOrderService.getPaidOrder(userId);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(Result.suc("成功",userorder)));
    }


    /**
     * 获取退款订单
     * @param userId
     * @return
     */
    @GetMapping("/getRefundOrder/{userId}")
    @ApiOperation(value = "获取退款订单")
    @Timed
    public ResponseEntity<Result> getRefundOrder(@PathVariable Long userId) {
        List<UserorderDTO> userorder = rewrite_UserOrderService.getRefundOrder(userId);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(Result.suc("成功",userorder)));
    }

    /**
     * 获取当日订单
     * @param userId
     * @return
     */
    @GetMapping("/getTodayOrder/{userId}")
    @ApiOperation(value = "获取当日订单")
    @Timed
    public ResponseEntity<Result> getTodayOrder(@PathVariable Long userId) {
        List<UserorderDTO> userorder = rewrite_UserOrderService.getTodayOrder(userId);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(Result.suc("成功",userorder)));
    }

    /**
     * 获取用户全部订单
     * @param userId
     * @return
     */
    @GetMapping("/getAllOrder/{userId}")
    @ApiOperation(value = "获取用户全部订单")
    @Timed
    public ResponseEntity<Result> getAllOrder(@PathVariable Long userId) {
        List<UserorderDTO> userorder = rewrite_UserOrderService.getAllOrder(userId);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(Result.suc("成功",userorder)));
    }

    /**
     * 用户下单
     * @param rewrite_AnOrder
     * @return
     */
    @PostMapping("/placeAnOrder")
    @ApiOperation(value = "用户下单")
    @Timed
    public ResponseEntity<Result> placeAnOrder(@RequestBody Rewrite_AnOrder rewrite_AnOrder) {
        Result result = rewrite_UserOrderService.placeAnOrder(rewrite_AnOrder);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
    }




}
