package com.weisen.www.code.yjf.basic.web.rest;

import com.weisen.www.code.yjf.basic.service.Rewrite_000_AlipayService;
import com.weisen.www.code.yjf.basic.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public/alipay")
@Api(tags = "000-支付宝操作接口")
public class Rewrite_AlipayApiResource {

    private Rewrite_000_AlipayService alipayService;

    public Rewrite_AlipayApiResource(Rewrite_000_AlipayService alipayService) {
        this.alipayService = alipayService;
    }

    @GetMapping("/checkUserExist")
    @ApiOperation(value = "回调获取支付宝会员信息是否存在")
    public Result checkUserExist (@RequestParam(value = "auth_code") String authCode) {
        return alipayService.scaning(authCode);
    }

    @GetMapping("/payOrder")
    @ApiOperation(value = "支付订单")
    public Result payOrder () {
        return Result.suc("支付订单");
    }

    @PostMapping("/notify")
    @ApiOperation(value = "回调地址")
    public String notifyMessage () {
        return "Ok";
    }
}
