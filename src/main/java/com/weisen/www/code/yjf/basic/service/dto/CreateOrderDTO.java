package com.weisen.www.code.yjf.basic.service.dto;

public class CreateOrderDTO {

    private String userId;

    private String type;

    private String sum;

    private String payee;

    private String payway;

    private Integer concession;

    private Integer rebate;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    public String getPayway() {
        return payway;
    }

    public void setPayway(String payway) {
        this.payway = payway;
    }

    public Integer getConcession() {
        return concession;
    }

    public void setConcession(Integer concession) {
        this.concession = concession;
    }

    public Integer getRebate() {
        return rebate;
    }

    public void setRebate(Integer rebate) {
        this.rebate = rebate;
    }
}
