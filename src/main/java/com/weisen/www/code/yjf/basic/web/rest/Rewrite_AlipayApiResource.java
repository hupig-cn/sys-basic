package com.weisen.www.code.yjf.basic.web.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.weisen.www.code.yjf.basic.service.Rewrite_000_AlipayService;
import com.weisen.www.code.yjf.basic.service.Rewrite_000_UserOrderService;
import com.weisen.www.code.yjf.basic.util.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/public/alipay")
@Api(tags = "000-支付宝操作接口")
public class Rewrite_AlipayApiResource {

    private Rewrite_000_AlipayService alipayService;

    private Rewrite_000_UserOrderService userOrderService;
    
	public Rewrite_AlipayApiResource(Rewrite_000_AlipayService alipayService,
			Rewrite_000_UserOrderService userOrderService) {
		this.alipayService = alipayService;
		this.userOrderService = userOrderService;
	}

	/**
     * 扫码时创建用户流程
     * 1.扫码判断用户是否存在，若存在，直接跳出该流程；若不存在，执行以下步骤<br>
     * 2.前端从{basic服务}中的checkUserExist接口获取的userId作为账号调用{login服务}创建用户，密码随机生成
     * 3.前端从{login服务}创建用户之后获取userId，调用{basic服务}中的createUserByScan接口创建用户，判断改接口返回数据，若suc则结束
     * @param userId
     * @return
     */
    @GetMapping("/checkUserExist")
    @ApiOperation(value = "回调获取支付宝会员信息是否存在")
    public Result checkUserExist (@RequestParam(value = "auth_code") String authCode) {
        return alipayService.scaning(authCode);
    }

    /**
     * 支付宝付款
     * @param orderId
     * @return
     */
    @GetMapping("/payOrder")
    @ApiOperation(value = "支付订单")
    public Result payOrder (String orderId) {
        return userOrderService.alipay(orderId);
    }

    @PostMapping("/notify")
    @ApiOperation(value = "回调地址")
    public String notifyMessage () {
        return "Ok";
    }
}
