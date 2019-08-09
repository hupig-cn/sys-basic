package com.weisen.www.code.yjf.basic.service.dto.show_dto;

public class Rewrite_WithdrawalShowDTO {

    private String id;

    private String userAccount;

    private String userName;

    private String amount;

    private String incomeWay;

    private String incomeAccount;

    private String incomeName;

    private String belongBankName;

    private String state;

    private String startTime;

    private String endTime;

    public String getBelongBankName() {
        return belongBankName;
    }

    public void setBelongBankName(String belongBankName) {
        this.belongBankName = belongBankName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getIncomeWay() {
        return incomeWay;
    }

    public void setIncomeWay(String incomeWay) {
        this.incomeWay = incomeWay;
    }

    public String getIncomeAccount() {
        return incomeAccount;
    }

    public void setIncomeAccount(String incomeAccount) {
        this.incomeAccount = incomeAccount;
    }

    public String getIncomeName() {
        return incomeName;
    }

    public void setIncomeName(String incomeName) {
        this.incomeName = incomeName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
