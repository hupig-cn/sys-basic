package com.weisen.www.code.yjf.basic.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.weisen.www.code.yjf.basic.config.Constants;


public class SmsUtils {


    /**
     *  传入手机号码,验证码,和短信类型,调用阿里大于发送短信
     * @param phoneNumber
     * @param content
     * @param TemplateCode
     * @return
     */
    public static String SmsSendByAli(String phoneNumber,String content,String TemplateCode){
        if(!CheckUtils.checkPhoneNumber(phoneNumber))
            return "电话号码错误";
        if(!CheckUtils.checkContent(content))
            return "短信内容异常" ;
        DefaultProfile profile = DefaultProfile.getProfile("default", Constants.AccessKey_ID, Constants.Access_Key_Secret);
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("PhoneNumbers", phoneNumber);
        request.putQueryParameter("SignName", Constants.SIGNNAME);
        request.putQueryParameter("TemplateCode", TemplateCode);
        request.putQueryParameter("TemplateParam", "{code : " + content + "}");
        String result = null;
        try {
            CommonResponse response = client.getCommonResponse(request);
            JSONObject parse = (JSONObject) JSON.parse(response.getData());
            result = parse.get("Message").toString();
        } catch (ServerException e) {
            e.printStackTrace();
            result = "网络繁忙";
        } catch (ClientException e) {
            e.printStackTrace();
            result = "系统异常";
        }
            return result;
    }

}
