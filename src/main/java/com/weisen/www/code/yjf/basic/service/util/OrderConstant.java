package com.weisen.www.code.yjf.basic.service.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class OrderConstant {

    public static final String UN_PAID = "1";  // 待支付

    public static final String PAID = "2";	// 已支付

    public static final String SHIPPED = "3"; // 已发货

    public static final String REFUNDED = "4"; // 已退款

    public static final String COMPLETED = "5"; // 已完成

    //生成订单编号
    public static String getOrderCode(String id){
        String sources = "0123456789"; // 加上一些字母，就可以生成pc站的验证码了
        Random rand = new Random();
        StringBuffer flag = new StringBuffer();
        for (int j = 0; j < 6; j++)
        {
            flag.append(sources.charAt(rand.nextInt(10)) + "");
        }
        return getDateTime()+id+flag.toString();
    }

    public static String getDateTime(){
        DateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return sdf.format(new Date());
    }

//    public static void main(String[] args) {
//        String sources = "0123456789"; // 加上一些字母，就可以生成pc站的验证码了
//        Random rand = new Random();
//        StringBuffer flag = new StringBuffer();
//        for (int j = 0; j < 6; j++)
//        {
//            flag.append(sources.charAt(rand.nextInt(10)) + "");
//        }
//        System.out.println(flag.toString());
//        System.out.println(flag.toString().length());
//    }


}
