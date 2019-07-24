package com.weisen.www.code.yjf.basic.web.rest;

import com.weisen.www.code.yjf.basic.service.Rewrite_PayService;
import com.weisen.www.code.yjf.basic.service.Rewrite_ReceiptpayService;
import com.weisen.www.code.yjf.basic.service.dto.show_dto.Rewrite_PriceDTO;
import com.weisen.www.code.yjf.basic.service.dto.submit_dto.Rewrite_PayDTO;
import com.weisen.www.code.yjf.basic.util.Result;
import io.github.jhipster.web.util.ResponseUtil;
import io.micrometer.core.annotation.Timed;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/pay")
@Api(tags = "支付")
public class Rewrite_PayResource {

    private Rewrite_PayService rewrite_PayService;

    public Rewrite_PayResource(Rewrite_PayService rewrite_PayService) {
        this.rewrite_PayService = rewrite_PayService;
    }


    /**
     * 余额支付
     * @return
     */
    @PostMapping("/BalencePayment")
    @ApiOperation(value = "余额支付")
    @Timed
    public ResponseEntity<Result> BalencePayment(@RequestBody Rewrite_PayDTO rewrite_PayDTO) {
        Result result = rewrite_PayService.BalencePayment(rewrite_PayDTO);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
    }

    /**
     * 积分支付
     * @return
     */
    @PostMapping("/IntegralPayment")
    @ApiOperation(value = "积分支付")
    @Timed
    public ResponseEntity<Result> IntegralPayment(@RequestBody Rewrite_PayDTO rewrite_PayDTO) {
        Result result = rewrite_PayService.IntegralPayment(rewrite_PayDTO);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
    }

    /**
     * 优惠券
     * @return
     */
    @PostMapping("/couponPayment")
    @ApiOperation(value = "优惠券支付")
    @Timed
    public ResponseEntity<Result> couponPayment(@RequestBody Rewrite_PayDTO rewrite_PayDTO) {
        Result result = rewrite_PayService.couponPayment(rewrite_PayDTO);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
    }



}
