package com.weisen.www.code.yjf.basic.service.dto.show_dto;

import java.math.BigDecimal;

public class Rewrite_MercProfitDto {

    private String balance;

    private BigDecimal yestoday_income;

    private BigDecimal this_month;

    private BigDecimal last_month;

    private BigDecimal amount;

    public Rewrite_MercProfitDto(String balance,BigDecimal yestoday_income,BigDecimal this_month,BigDecimal last_month,BigDecimal amount){
        this.balance = balance;
        this.yestoday_income = yestoday_income;
        this.this_month = this_month;
        this.last_month = last_month;
        this.amount = amount;
    }

    public Rewrite_MercProfitDto(){

    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public BigDecimal getYestoday_income() {
        return yestoday_income;
    }

    public void setYestoday_income(BigDecimal yestoday_income) {
        this.yestoday_income = yestoday_income;
    }

    public BigDecimal getThis_month() {
        return this_month;
    }

    public void setThis_month(BigDecimal this_month) {
        this.this_month = this_month;
    }

    public BigDecimal getLast_month() {
        return last_month;
    }

    public void setLast_month(BigDecimal last_month) {
        this.last_month = last_month;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
