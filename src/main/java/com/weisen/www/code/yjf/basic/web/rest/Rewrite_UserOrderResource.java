package com.weisen.www.code.yjf.basic.web.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weisen.www.code.yjf.basic.service.Rewrite_UserOrderService;
import com.weisen.www.code.yjf.basic.service.dto.UserorderDTO;
import com.weisen.www.code.yjf.basic.service.dto.submit_dto.Rewrite_AnOrder;
import com.weisen.www.code.yjf.basic.service.dto.submit_dto.Rewrite_UserOrderPage;
import com.weisen.www.code.yjf.basic.service.dto.submit_dto.Rewrite_UserSendGoods;
import com.weisen.www.code.yjf.basic.util.Result;

import io.github.jhipster.web.util.ResponseUtil;
import io.micrometer.core.annotation.Timed;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/userorder")
@Api(tags = "000-用户订单")
public class Rewrite_UserOrderResource {

    private Rewrite_UserOrderService rewrite_UserOrderService;

    public Rewrite_UserOrderResource(Rewrite_UserOrderService rewrite_UserOrderService) {
        this.rewrite_UserOrderService = rewrite_UserOrderService;
    }

    /**
     * 订单详情列表（方便发货+回填物流单号）
     * @author Carson
     * @date 2019-09-04 14:26:12
     * @param rewrite_UserOrderPage
     * @return
     */
    @PostMapping("/admin/getOrderList")
    @ApiOperation(value = "订单详情列表（方便发货+回填物流单号）")
    @Timed
    public ResponseEntity<Result> getOrderList(@RequestBody Rewrite_UserOrderPage rewrite_UserOrderPage) {
        Result result = rewrite_UserOrderService.getOrderList(rewrite_UserOrderPage);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
    }
    
    /**
     * 发货（后台）
     * @author Carson
     * @date 2019-09-04 14:25:55
     * @param rewrite_UserSendGoods
     * @return
     */
    @PostMapping("/admin/send-goods")
	@ApiOperation(value = "发货（后台）")
    @Timed
	public ResponseEntity<Result> sendGoods(@RequestBody Rewrite_UserSendGoods rewrite_UserSendGoods) {
		Result result = rewrite_UserOrderService.sendGoods(rewrite_UserSendGoods);
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
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

    /**
     * 获取用户全部订单
     * @param userId
     * @return
     */
    @GetMapping("/somethingData/{userId}")
    @ApiOperation(value = "收益+当日订单+各种订单状态")
    @Timed
    public ResponseEntity<Result> somethingData(@PathVariable Long userId) {
        Result result = rewrite_UserOrderService.somethingData(userId);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
    }



}
