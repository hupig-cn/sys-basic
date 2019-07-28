package com.weisen.www.code.yjf.basic.service.util;

public class WithdrawalConstant {

//    public final static String READY = "0";  // 可提现
//
//    public final static String FROZEN = "1";  // 冻结

    public final static String FAIL = "3";  // 提现失败

    public final static String IN_READY = "1";  // 提现中

    public final static String ALREADY = "2"; // 提现成功

    public final static String PAY = "1"; // 支出

    public final static String INCOME = "2"; // 收入

    public final static String SUCCESS = "1"; // 审批通过

    public final static String FAIL_S = "2"; // 审批拒绝

    public final static String BANK_CARD = "1"; // 银行卡
    public final static String ALI = "3"; // 支付宝
    public final static String WECHAT = "2"; // 微信

    public final static String TITLE_LEFT = "申请(";
    public final static String TITLE_RIGHT = ")提现";


    public static String getInfo(String type){
        switch (type){
            case BANK_CARD
                return  "申请(银行卡)提现";
            case ALI
                return  "申请(支付宝)提现";
            case WECHAT
                return  "申请(微信)提现";
            break;
        }
        return null;
    }




}
