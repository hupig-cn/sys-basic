package com.weisen.www.code.yjf.basic.service.dto;

import java.io.Serializable;

/**
 * @Author: 阮铭辉
 * @Date: 2019/10/26 9:33
 */
public class IntroductionOrderDTO implements Serializable {

    private String ordercode;

    private String url;

    private String price;

    private String specificatinons;

    private String staus;

    private String paytime;

    private String orderid;

    private String num;

    private String oneprice;

    private String MerchantName;

    private String createdate;

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getMerchantName() {
        return MerchantName;
    }

    public void setMerchantName(String merchantName) {
        MerchantName = merchantName;
    }

    public String getOneprice() {
        return oneprice;
    }

    public void setOneprice(String oneprice) {
        this.oneprice = oneprice;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getOrdercode() {
        return ordercode;
    }

    public void setOrdercode(String ordercode) {
        this.ordercode = ordercode;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSpecificatinons() {
        return specificatinons;
    }

    public void setSpecificatinons(String specificatinons) {
        this.specificatinons = specificatinons;
    }

    public String getStaus() {
        return staus;
    }

    public void setStaus(String staus) {
        this.staus = staus;
    }

    public String getPaytime() {
        return paytime;
    }

    public void setPaytime(String paytime) {
        this.paytime = paytime;
    }
}
