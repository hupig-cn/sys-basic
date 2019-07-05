package com.weisen.www.code.yjf.basic.util;

public class CodeUtil {
    public static Integer getCode(){
        return (int)((Math.random()* 9 +1)*100000);
    }
}
