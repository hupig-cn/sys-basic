package com.weisen.www.code.yjf.basic.service.dto;

import java.io.Serializable;

/**
 * @Author: 阮铭辉
 * @Date: 2019/10/26 9:33
 */
public class IntroductionOrderDTO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String ordercode;

    private String url;

    private String price;

    private String specificatinons;

    private String staus;

    private String paytime;

    private String payway;

    private String orderid;

    private String num;

    private String oneprice;

    private String MerchantName;

    private String createdate;

    private String other;

    private String express_company;

    private String express_no;

    public String getExpress_company() {
        return express_company;
    }

    public void setExpress_company(String express_company) {
        this.express_company = express_company;
    }

    public String getExpress_no() {
        return express_no;
    }

    public void setExpress_no(String express_no) {
        this.express_no = express_no;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getPayway() {
        return payway;
    }

    public void setPayway(String payway) {
        this.payway = payway;
    }

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
