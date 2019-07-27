package com.weisen.www.code.yjf.basic.util;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.request.AlipaySystemOauthTokenRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;

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

    public static final String alipay(String outTradeNo, String subject, BigDecimal totalAmount, Long orderId) {
        String form = "";
        String result = "";
        try {
            AlipayClient alipayClient = new DefaultAlipayClient(GATEWAY, APP_ID, APP_PRIVATE_KEY, FORMAT, CHARSET, ALIPAY_PUBLIC_KEY, SIGN_TYPE);
            AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();
            String body = ""; //请求体
            //封装请求体信息
            AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();
            model.setOutTradeNo(outTradeNo); //商户订单号，商户网站中必须唯一，必填字段
            model.setSubject(subject); //订单名称
            System.out.println("已传入支付宝请求");
            model.setTotalAmount(totalAmount.toString()); //订单金额，必填字段
            model.setBody(body); //商品描述，可选字段
            model.setTimeoutExpress(TIMEOUT_VALUE); //支付超时时间
            model.setProductCode(PRODUCT_H5_CODE); //H5支付方式
            alipayRequest.setReturnUrl(RETURN_URL); //支付完成后返回地址
            alipayRequest.setNotifyUrl(NOTIFY_URL); //回调地址
            alipayRequest.setBizModel(model);
            form =  alipayClient.pageExecute(alipayRequest,"get").getBody();
            System.out.println("回调开始");
            System.out.println(form);
            try {
                result = java.net.URLEncoder.encode(form, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            System.out.println(result);
            System.out.println("回调结束");
//            form = alipayClient.pageExecute(alipayRequest).getBody(); //调用Alipay-SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return result;
    }

}
