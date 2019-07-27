package com.weisen.www.code.yjf.basic.service.dto.submit_dto;

public class Rewrite_200_PayPasswordSetDTO {

    private String payPassword;

    private String vertifyCode;

    public String getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword;
    }

    public String getVertifyCode() {
        return vertifyCode;
    }

    public void setVertifyCode(String vertifyCode) {
        this.vertifyCode = vertifyCode;
    }

    public String toString() {
        return "Rewrite_200_PayPasswordSetDTO{" +
            "payPassword='" + payPassword + '\'' +
            ", vertifyCode='" + vertifyCode + '\'' +
            '}';
    }
}
