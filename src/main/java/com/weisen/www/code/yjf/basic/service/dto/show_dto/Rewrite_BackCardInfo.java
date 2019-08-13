package com.weisen.www.code.yjf.basic.service.dto.show_dto;

import java.util.List;

public class Rewrite_BackCardInfo {

    private List<Rewrite_BankCardDTO> listbank;

    private String alipay;

    private String wechat;

    private String alipayName;

    private String wechatName;

    public Rewrite_BackCardInfo(List<Rewrite_BankCardDTO> listbank,String alipay,String wechat,String alipayName,String wechatName){
        this.listbank = listbank;
        this.alipay = alipay;
        this.wechat = wechat;
        this.alipayName = alipayName;
        this.wechatName = wechatName;
    }

    public String getAlipayName() {
        return alipayName;
    }

    public void setAlipayName(String alipayName) {
        this.alipayName = alipayName;
    }

    public String getWechatName() {
        return wechatName;
    }

    public void setWechatName(String wechatName) {
        this.wechatName = wechatName;
    }

    public List<Rewrite_BankCardDTO> getListbank() {
        return listbank;
    }

    public void setListbank(List<Rewrite_BankCardDTO> listbank) {
        this.listbank = listbank;
    }

    public String getAlipay() {
        return alipay;
    }

    public void setAlipay(String alipay) {
        this.alipay = alipay;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }
}