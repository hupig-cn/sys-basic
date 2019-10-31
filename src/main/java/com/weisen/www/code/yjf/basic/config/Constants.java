package com.weisen.www.code.yjf.basic.config;

/**
 * Application constants.
 */
public final class Constants {

    public static final String SYSTEM_ACCOUNT = "system";

    public static final String REGEX_MOBILE = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";

    public static final String AccessKey_ID = "LTAIO0A18h5ZjM6h";
    //短信签名
    public static final String SIGNNAME = "园积分";

    public static final String Access_Key_Secret = "IjcmZ4II6Dm9CQtDRlfqOyjnUOijco";
    // 短信模板-{
    //身份验证模板
    public static final String AUTHENTICATION =  "SMS_168096211";
    //登录确认
    public static final String LOGIN = "SMS_168096210";
    //登录异常
    public static final String ABNORMAL_LOGIN = "SMS_168096209";
    //注册模板
    public static final String REGISTER = "SMS_168096208";
    //修改密码
    public static final String CHANGE_PASSWORD = "SMS_168096207";
    //信息变更
    public static final String CHANGE_INFO = "SMS_168096206";
    // }-短信模板

    public static final String SMS_REGISTER = "1";

    public static final String SMS_CHANGE_PASSWORD = "2";

    public static final Integer REGISTER_NUMBER = 3;

    public static final Integer CHANGE_PASSWORD_NUMBER = 3;
    //消费消息
    public static final Integer CONSUMPTION = 1 ;
    //收款消息
    public static final Integer COLLECTION = 2 ;
    //注册消息
    public static final Integer REGISTER_INFORMATION = 3 ;

    // Validate pay password
    public static final String SMS_PAY_PASSWORD_CODE_REGEXP = "\\d{6}";

    public static final String SMS_PAY_PASSWORD_REGEXP = "\\d{6}";

    public static final String SMS_PAY_PASSWORD = "pay_password";

    public static final Integer SMS_TYPE_PAY_PASSWORD = 5;
    
    
    // Regex for acceptable logins
    public static final String LOGIN_REGEX = "^[_.@A-Za-z0-9-]*$";
    

    private Constants() {
    }
}
