package com.weisen.www.code.yjf.basic.service.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class FinalUtil {
    public final static String version ="1.0.0";

    public final static String ORDER_DISH_ACT = "0";// 订单类型：点餐
    public final static String ORDER_MALL_ACT = "1"; // 订单类型：商城

    public final static String ORDER_DISH_ACT_PREFIX = "D";// 订单类型前缀：点餐
    public final static String ORDER_MALL_ACT_PREFIX = "M"; // 订单类型前缀：商城

    /***
     * 生成订单编号 生成规则 订单动作的前缀 商品-C 会员-V +时间（年月日）+6位随机数
     *
     * @author 作者:lanzhengjun
     * @createDate 创建时间：2019年3月13日 下午4:15:27
     * @parameter mAct 订单动作
     * @return  订单号
     */
    public static String createTradeNo(String mAct) {
        StringBuffer mPrefix = new StringBuffer();
        // 前缀
        if (ORDER_DISH_ACT.equals(mAct)) {// 订单类型 -点餐
            mPrefix.append(ORDER_DISH_ACT_PREFIX);
        } else if (ORDER_MALL_ACT.equals(mAct)) {// 订单类型-商城
            mPrefix.append(ORDER_MALL_ACT_PREFIX);
        }
        // 增加年月日
        SimpleDateFormat lFormat;
        lFormat = new SimpleDateFormat("yyyyMMdd");
        String mDateStr = lFormat.format(new Date());
        mPrefix.append(mDateStr);
        // 增加随机数
        int mRadom = new Random().nextInt(999999);
        if (mRadom < 100000) {
            mRadom += 100000;
        }
        mPrefix.append(mRadom);
        return mPrefix.toString();
    }

    public static void main(String[] args) {
        System.out.println(createTradeNo("1"));
    }

}

