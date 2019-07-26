package com.weisen.www.code.yjf.basic.service.dto.show_dto;

public class Rewrite_OrderCoDto {

    private String today_income;

    private int day_order;

    private int unpaid;

    private int paid;

    private int refund;

    public Rewrite_OrderCoDto(String today_income,int day_order,int unpaid,int paid,int refund){
        this.today_income = today_income;
        this.day_order =day_order;
        this.unpaid = unpaid;
        this.paid = paid;
        this.refund = refund;
    }


    public String getToday_income() {
        return today_income;
    }

    public void setToday_income(String today_income) {
        this.today_income = today_income;
    }

    public int getDay_order() {
        return day_order;
    }

    public void setDay_order(int day_order) {
        this.day_order = day_order;
    }

    public int getUnpaid() {
        return unpaid;
    }

    public void setUnpaid(int unpaid) {
        this.unpaid = unpaid;
    }

    public int getPaid() {
        return paid;
    }

    public void setPaid(int paid) {
        this.paid = paid;
    }

    public int getRefund() {
        return refund;
    }

    public void setRefund(int refund) {
        this.refund = refund;
    }
}
