package com.weisen.www.code.yjf.basic.service.rewrite.dto;

import java.io.Serializable;

public class Rewrite_submitPayMethodDTO implements Serializable {
    private String os;
    private Boolean online;

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
