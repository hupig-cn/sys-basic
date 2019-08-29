package com.weisen.www.code.yjf.basic.service.rewrite.dto;

import com.weisen.www.code.yjf.basic.service.dto.StatisticsDTO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the Statistics entity.
 */
public class Rewrite_StatisticsDTO implements Serializable {

    private Long id;

    private String userid;

    private BigDecimal balance;

    private BigDecimal useablebalance;

    private BigDecimal freezebalance;

    private BigDecimal integral;

    private BigDecimal coupon;

    private BigDecimal expendintegral;

    private BigDecimal incomeintegral;

    private BigDecimal expendcoupon;

    private BigDecimal incomecoupon;

    private BigDecimal proceeds;

    private BigDecimal earnings;

    private String createdate;

    private BigDecimal expense;

    private BigDecimal withdraw;

    private BigDecimal withdrawsuccess;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getUseablebalance() {
        return useablebalance;
    }

    public void setUseablebalance(BigDecimal useablebalance) {
        this.useablebalance = useablebalance;
    }

    public BigDecimal getFreezebalance() {
        return freezebalance;
    }

    public void setFreezebalance(BigDecimal freezebalance) {
        this.freezebalance = freezebalance;
    }

    public BigDecimal getIntegral() {
        return integral;
    }

    public void setIntegral(BigDecimal integral) {
        this.integral = integral;
    }

    public BigDecimal getCoupon() {
        return coupon;
    }

    public void setCoupon(BigDecimal coupon) {
        this.coupon = coupon;
    }

    public BigDecimal getExpendintegral() {
        return expendintegral;
    }

    public void setExpendintegral(BigDecimal expendintegral) {
        this.expendintegral = expendintegral;
    }

    public BigDecimal getIncomeintegral() {
        return incomeintegral;
    }

    public void setIncomeintegral(BigDecimal incomeintegral) {
        this.incomeintegral = incomeintegral;
    }

    public BigDecimal getExpendcoupon() {
        return expendcoupon;
    }

    public void setExpendcoupon(BigDecimal expendcoupon) {
        this.expendcoupon = expendcoupon;
    }

    public BigDecimal getIncomecoupon() {
        return incomecoupon;
    }

    public void setIncomecoupon(BigDecimal incomecoupon) {
        this.incomecoupon = incomecoupon;
    }

    public BigDecimal getProceeds() {
        return proceeds;
    }

    public void setProceeds(BigDecimal proceeds) {
        this.proceeds = proceeds;
    }

    public BigDecimal getEarnings() {
        return earnings;
    }

    public void setEarnings(BigDecimal earnings) {
        this.earnings = earnings;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public BigDecimal getExpense() {
        return expense;
    }

    public void setExpense(BigDecimal expense) {
        this.expense = expense;
    }

    public BigDecimal getWithdraw() {
        return withdraw;
    }

    public void setWithdraw(BigDecimal withdraw) {
        this.withdraw = withdraw;
    }

    public BigDecimal getWithdrawsuccess() {
        return withdrawsuccess;
    }

    public void setWithdrawsuccess(BigDecimal withdrawsuccess) {
        this.withdrawsuccess = withdrawsuccess;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        StatisticsDTO statisticsDTO = (StatisticsDTO) o;
        if (statisticsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), statisticsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "StatisticsDTO{" +
            "id=" + getId() +
            ", userid='" + getUserid() + "'" +
            ", balance=" + getBalance() +
            ", useablebalance=" + getUseablebalance() +
            ", freezebalance=" + getFreezebalance() +
            ", integral=" + getIntegral() +
            ", coupon=" + getCoupon() +
            ", expendintegral=" + getExpendintegral() +
            ", incomeintegral=" + getIncomeintegral() +
            ", expendcoupon=" + getExpendcoupon() +
            ", incomecoupon=" + getIncomecoupon() +
            ", proceeds=" + getProceeds() +
            ", earnings=" + getEarnings() +
            ", createdate='" + getCreatedate() + "'" +
            ", expense=" + getExpense() +
            ", withdraw=" + getWithdraw() +
            ", withdrawsuccess=" + getWithdrawsuccess() +
            "}";
    }
}
