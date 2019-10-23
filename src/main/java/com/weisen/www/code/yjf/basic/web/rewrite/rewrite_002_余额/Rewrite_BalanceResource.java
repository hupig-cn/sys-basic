package com.weisen.www.code.yjf.basic.web.rewrite.rewrite_002_余额;

import com.weisen.www.code.yjf.basic.service.Rewrite_BalanceService;
import com.weisen.www.code.yjf.basic.util.Result;
import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @Author: 阮铭辉
 * @Date: 2019/10/23 11:29
 */
@RestController
@RequestMapping("/api/balance")
@Api(tags = "02_余额")
public class Rewrite_BalanceResource {

    private final Logger log = LoggerFactory.getLogger(Rewrite_BalanceResource.class);

    private final Rewrite_BalanceService rewrite_balanceService;

    public Rewrite_BalanceResource(Rewrite_BalanceService rewrite_balanceService) {
        this.rewrite_balanceService = rewrite_balanceService;
    }

    @PostMapping("/balance")
    @ApiOperation("我的余额")
    public ResponseEntity<?> findAllBalance(@RequestParam(required = false) String userid) {
        Result result = rewrite_balanceService.findAllBalance(userid);
        log.debug("访问地址: {},传入值: {},返回值: {}","/api/balance/balance", "传入值:"+userid, result);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
    }

    @PostMapping("/Receiptpaylist")
    @ApiOperation("消费明细")
    public ResponseEntity<?> Receiptpaylist(@RequestParam(required = false) String startTime,
                                         @RequestParam(required = false) String endTime,
                                         @RequestParam(required = false) String userid) {
        Result result = rewrite_balanceService.Receiptpaylist(userid,endTime,startTime);
        log.debug("访问地址: {},传入值: {},返回值: {}","/api/balance/balancelist", "传入值:"+userid+":"+startTime+":"+endTime, result);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
    }

    @PostMapping("/receiptpays")
    @ApiOperation("消费明细")
    public ResponseEntity<?> Receiptpay(@RequestParam(required = false) Long id,
                                        @RequestParam(required = false) String userid) {
        Result result = rewrite_balanceService.receiptpays(id,userid);
        log.debug("访问地址: {},传入值: {},返回值: {}","/api/balance/receiptpays", "传入值:"+id+":"+userid, result);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
    }
}
