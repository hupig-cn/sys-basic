package com.weisen.www.code.yjf.basic.util;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.request.AlipaySystemOauthTokenRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;

import static com.weisen.www.code.yjf.basic.config.AlipayConstants.*;

public class AlipayUtil {

    /**
     * 获取支付宝会员信息
     * @param authCode
     * @return
     */
    public static final AlipaySystemOauthTokenResponse getUserInfo (String authCode) {
        AlipaySystemOauthTokenResponse userInfo = null;
        try {
            AlipayClient alipayClient = new DefaultAlipayClient(GATEWAY, APP_ID, APP_PRIVATE_KEY, FORMAT, CHARSET, ALIPAY_PUBLIC_KEY, SIGN_TYPE);
            AlipaySystemOauthTokenRequest alipaySystemOauthTokenRequest = new AlipaySystemOauthTokenRequest();
            alipaySystemOauthTokenRequest.setCode(authCode);
            alipaySystemOauthTokenRequest.setGrantType("authorization_code");
            //获取支付宝用户信息
            userInfo = alipayClient.execute(alipaySystemOauthTokenRequest);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return userInfo;
    }

    public static final String alipay(String outTradeNo, String subject, String totalAmount, Long orderId) {
        String form = "";
        try {
            AlipayClient alipayClient = new DefaultAlipayClient(GATEWAY, APP_ID, APP_PRIVATE_KEY, FORMAT, CHARSET, ALIPAY_PUBLIC_KEY, SIGN_TYPE);
            AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();
            String body = ""; //请求体
            //封装请求体信息
            AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();
            model.setOutTradeNo(outTradeNo); //商户订单号，商户网站中必须唯一，必填字段
            model.setSubject(subject); //订单名称
            model.setTotalAmount(totalAmount); //订单金额，必填字段
            model.setBody(body); //商品描述，可选字段
            model.setTimeoutExpress(TIMEOUT_VALUE); //支付超时时间
            model.setProductCode(PRODUCT_H5_CODE); //H5支付方式
            alipayRequest.setReturnUrl(NOTIFY_URL); //回调地址
            alipayRequest.setNotifyUrl(RETURN_URL + orderId); //支付完成后返回地址
            form = alipayClient.pageExecute(alipayRequest).getBody(); //调用Alipay-SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return form;
    }
}
