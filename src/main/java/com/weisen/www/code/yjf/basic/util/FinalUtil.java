package com.weisen.www.code.yjf.basic.util;

import java.util.Random;

public final class FinalUtil {

    public static String createOrderNumber (String prefix) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(prefix);
        buffer.append(System.currentTimeMillis());
        //增加随机数
        int mRadom = new Random().nextInt(999999);
        if (mRadom < 100000) {
            mRadom += 100000;
        }
        buffer.append(mRadom);
        return buffer.toString();
    }

    public static void main(String[] args) {
        System.out.println(createOrderNumber("S"));
    }
}
