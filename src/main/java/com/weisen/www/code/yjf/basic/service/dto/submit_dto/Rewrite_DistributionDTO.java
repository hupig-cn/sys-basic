package com.weisen.www.code.yjf.basic.service.dto.submit_dto;

public class Rewrite_DistributionDTO {

    private String amount;

    private Long orderId;

    private String payWay;

    private String concession; // 让利比例

    private String rebate; // 返积分比例

    public Rewrite_DistributionDTO(String amount,Long orderId,String payWay,String concession,String rebate){
        this.amount = amount;
        this.orderId = orderId;
        this.payWay = payWay;
        this.concession = concession;
        this.rebate = rebate;

    }

    public String getConcession() {
        return concession;
    }

    public void setConcession(String concession) {
        this.concession = concession;
    }

    public String getRebate() {
        return rebate;
    }

    public void setRebate(String rebate) {
        this.rebate = rebate;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }
}
