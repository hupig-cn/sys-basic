package com.weisen.www.code.yjf.basic.service.rewrite.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Rewrite_submitPayMethodDTO2 implements Serializable {
    private String os;
    private Boolean online;
    private Integer goodsid;

    public Integer getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(Integer goodsid) {
        this.goodsid = goodsid;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }
}
