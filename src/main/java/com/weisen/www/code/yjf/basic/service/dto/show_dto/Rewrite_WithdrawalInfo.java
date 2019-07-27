package com.weisen.www.code.yjf.basic.service.dto.show_dto;

public class Rewrite_WithdrawalInfo {

    private String income;

    private String freeze;

    private String withdrawal;

    private String carry;

    private String amount;

    private String rc_ed;

    public Rewrite_WithdrawalInfo(String income,String freeze,String withdrawal,String carry,String amount,String rc_ed){
        this.income = income;
        this.income = freeze;
        this.income = withdrawal;
        this.income = carry;
        this.income = amount;
        this.income = rc_ed;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getFreeze() {
        return freeze;
    }

    public void setFreeze(String freeze) {
        this.freeze = freeze;
    }

    public String getWithdrawal() {
        return withdrawal;
    }

    public void setWithdrawal(String withdrawal) {
        this.withdrawal = withdrawal;
    }

    public String getCarry() {
        return carry;
    }

    public void setCarry(String carry) {
        this.carry = carry;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getRc_ed() {
        return rc_ed;
    }

    public void setRc_ed(String rc_ed) {
        this.rc_ed = rc_ed;
    }
}
