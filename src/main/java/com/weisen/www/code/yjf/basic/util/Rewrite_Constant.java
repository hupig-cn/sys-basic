package com.weisen.www.code.yjf.basic.util;

import com.weisen.www.code.yjf.basic.domain.Percentage;

public class Rewrite_Constant {

	public static boolean productionMode =true;

	public static String DES_KEY ="12345678";
    /**包路径**/
    public final static String PACKAGE_NAME = "com.atencn.enumresource";
	//用户名
	public static String USER_NAME = "username";
	//用户标识
	public static String USER_ID = "user_id";
	//用户管理员类型
	public static String MANA_TYPE = "mana_type";

	public static final int DIS_ABLE = 0; //禁用操作

	public static final int EN_ABLE = 1; //启用操作

	public static final int DELETE = 2; //删除操作
	
    public static final Integer SMS_REGISTER  = 1;

    public static final Integer SMS_CHECKNAME = 2;

    public static final Integer SMS_BANK = 3;
    public static final Integer CODE = 1;

    public static final String ACCOUNTTYPE_ALIPAY = "alipay";

    public static final String ACCOUNTTYPE_WECHAT = "wechat";

    /**
     * 订单状态
     */
    public static final String ORDER_WAIT_PAY = "wait_pay"; //待支付
    public static final String ORDER_WAIT_DELIVER = "wait_deliver"; //待发货
    public static final String ORDER_FINISHED = "finished"; //已完成

    /**
     * 交易类型
     */
    public static final String DEALTYPE_CONSUMPTION = "consumption";
    public static final String DEALTYPE_RECEIVABLES = "receivables";
    public static final String DEALTYPE_RECOMMEND = "recommend";
    public static final String DEALTYPE_PARTNER = "partner";

    /**
     * 来源类型
     */
    public static final String SOURCE_ALIPAY = "alipay";
    public static final String SOURCE_WECHAT = "wechat";
    public static final String SOURCE_YUE = "yue";
    public static final String SOURCE_INTEGRAL = "integral";
    public static final String SOURCE_COUPON = "coupon";

    /**
     * 交易状态
     */
    public static final String DEALSTATE_NORMAL = "1";//完成
    public static final String DEALSTATE_ABNORMAL = "2";//异常

    /**
     * 分配比例名称
     */
    public static final String PERCENTAGE_RECOMMEND = "recommend";
    public static final String PERCENTAGE_PARTNER = "partner";
    public static final String PERCENTAGE_BENEFIT = "benefit";

    /**
     * 分配比例类型
     */
    public static final String PERCENTAGE_TYPE_CASH = "cash";
    public static final String PERCENTAGE_TYPE_INTEGRAL = "integral";
    public static final String PERCENTAGE_TYPE_COUPON = "coupon";
}
