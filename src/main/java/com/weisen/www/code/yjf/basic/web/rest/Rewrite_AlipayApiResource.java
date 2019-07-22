package com.weisen.www.code.yjf.basic.web.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.weisen.www.code.yjf.basic.service.Rewrite_000_UserorderService;
import com.weisen.www.code.yjf.basic.service.Rewrite_AlipayService;
import com.weisen.www.code.yjf.basic.service.rewrite.submit_dto.Rewrite_AliPaySubmitDTO;
import com.weisen.www.code.yjf.basic.util.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api(tags = "000-支付宝操作接口")
public class Rewrite_AlipayApiResource {

    private final Logger log = LoggerFactory.getLogger(Rewrite_AlipayApiResource.class);

    private Rewrite_AlipayService alipayService;

    private Rewrite_000_UserorderService userorderService;

	public Rewrite_AlipayApiResource(Rewrite_AlipayService alipayService,
            Rewrite_000_UserorderService userorderService) {
		this.alipayService = alipayService;
		this.userorderService = userorderService;
	}

	/**
     * 扫码时创建用户流程<br>
     * 1.扫码判断用户是否存在，若存在，直接跳出该流程；若不存在，执行以下步骤<br>
     * 2.前端从{basic服务}中的checkUserExist接口获取的userId作为账号调用{login服务}创建用户，密码随机生成<br>
     * 3.前端从{login服务}创建用户之后获取userId，调用{basic服务}中的createUserByScan接口创建用户，判断改接口返回数据，若suc则结束<br>
     * @param authCode
     * @return
     */
    @GetMapping("/public/bindingAlipay/{userid}{authCode}")
    @ApiOperation(value = "用户绑定支付宝")
    public String callback (@PathVariable String userid, String authCode) {
    	System.out.println(userid);
    	System.out.println(authCode);
        return alipayService.scaning(userid, authCode);
    }

    /**
     * 支付宝付款
     * @param orderId
     * @return
     */
    @PostMapping("/payOrder")
    @ApiOperation(value = "支付订单")
    public Result payOrder (@RequestParam(value = "orderId") Long orderId) {
        log.debug("支付订单:{}", orderId);
        return userorderService.alipay(orderId);
    }

    /**
     * 支付宝付款(线下)
     * @param merchantId
     * @return
     */
    @PostMapping("/payOrderOffline")
    @ApiOperation(value = "支付订单")
    public Result payOrderOffline (@RequestBody Rewrite_AliPaySubmitDTO rewrite_aliPaySubmitDTO) {
        return userorderService.alipay(rewrite_aliPaySubmitDTO.getMerchantId()
            , rewrite_aliPaySubmitDTO.getUserId(), rewrite_aliPaySubmitDTO.getConcession(), rewrite_aliPaySubmitDTO.getRebate(), rewrite_aliPaySubmitDTO.getAmount());
    }

    /**
     * 支付异步回调
     * @param request
     * @param response
     * @return
     */
    @PostMapping("/notify")
    @ApiOperation(value = "异步地址")
    public void notifyMessage (HttpServletRequest request, HttpServletResponse response) {
        log.debug("回调地址");
        userorderService.notifyMessage(request, response);
    }

    /**
     * 支付同步回调
     * @param orderId
     * @return
     */
    @PostMapping("/queryOrder")
    @ApiOperation(value = "同步地址")
    public Result queryOrder (@RequestParam(value = "orderId") String orderId) {
        log.debug("同步地址");
        return userorderService.queryOrder(orderId);
    }
}
