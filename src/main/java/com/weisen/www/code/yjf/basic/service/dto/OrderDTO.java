package com.weisen.www.code.yjf.basic.service.dto;

import java.io.Serializable;

/**
 * @Author: 阮铭辉
 * @Date: 2019/10/28 9:49
 */
public class OrderDTO implements Serializable {

    private IntroductionOrderDTO order;

    private String consignee;

    private String mobile;

    private String address;

    public IntroductionOrderDTO getOrder() {
        return order;
    }

    public void setOrder(IntroductionOrderDTO order) {
        this.order = order;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}