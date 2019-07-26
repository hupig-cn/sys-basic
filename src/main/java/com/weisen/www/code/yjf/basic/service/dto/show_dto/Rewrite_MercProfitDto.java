package com.weisen.www.code.yjf.basic.service.dto.show_dto;

public class Rewrite_MercProfitDto {

    private String balance;

    private String yestoday_income;

    private String this_month;

    private String last_month;

    private String amount;

    public Rewrite_MercProfitDto(String balance,String yestoday_income,String this_month,String last_month,String amount){
        this.balance = balance;
        this.yestoday_income = yestoday_income;
        this.this_month = this_month;
        this.last_month = last_month;
        this.amount = amount;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getYestoday_income() {
        return yestoday_income;
    }

    public void setYestoday_income(String yestoday_income) {
        this.yestoday_income = yestoday_income;
    }

    public String getThis_month() {
        return this_month;
    }

    public void setThis_month(String this_month) {
        this.this_month = this_month;
    }

    public String getLast_month() {
        return last_month;
    }

    public void setLast_month(String last_month) {
        this.last_month = last_month;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
