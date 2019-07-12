package com.weisen.www.code.yjf.basic.service.dto.submit_dto;

import java.math.BigDecimal;
import java.util.List;

public class Rewrite_AnOrder {

    private BigDecimal price;

    private String userId;

    private String payWay;

    private List<Long> specId;

    public List<Long> getSpecId() {
        return specId;
    }

    public void setSpecId(List<Long> specId) {
        this.specId = specId;
    }

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
