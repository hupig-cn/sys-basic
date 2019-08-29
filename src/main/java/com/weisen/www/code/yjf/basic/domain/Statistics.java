package com.weisen.www.code.yjf.basic.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A Statistics.
 */
@Entity
@Table(name = "statistics")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Statistics implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userid")
    private String userid;

    @Column(name = "balance", precision = 10, scale = 2)
    private BigDecimal balance;

    @Column(name = "useablebalance", precision = 10, scale = 2)
    private BigDecimal useablebalance;

    @Column(name = "freezebalance", precision = 10, scale = 2)
    private BigDecimal freezebalance;

    @Column(name = "integral", precision = 10, scale = 2)
    private BigDecimal integral;

    @Column(name = "coupon", precision = 10, scale = 2)
    private BigDecimal coupon;

    @Column(name = "expendintegral", precision = 10, scale = 2)
    private BigDecimal expendintegral;

    @Column(name = "incomeintegral", precision = 10, scale = 2)
    private BigDecimal incomeintegral;

    @Column(name = "expendcoupon", precision = 10, scale = 2)
    private BigDecimal expendcoupon;

    @Column(name = "incomecoupon", precision = 10, scale = 2)
    private BigDecimal incomecoupon;

    @Column(name = "proceeds", precision = 10, scale = 2)
    private BigDecimal proceeds;

    @Column(name = "earnings", precision = 10, scale = 2)
    private BigDecimal earnings;

    @Column(name = "createdate")
    private String createdate;

    @Column(name = "expense", precision = 10, scale = 2)
    private BigDecimal expense;

    @Column(name = "withdraw", precision = 10, scale = 2)
    private BigDecimal withdraw;

    @Column(name = "withdrawsuccess", precision = 10, scale = 2)
    private BigDecimal withdrawsuccess;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public Statistics userid(String userid) {
        this.userid = userid;
        return this;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public Statistics balance(BigDecimal balance) {
        this.balance = balance;
        return this;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getUseablebalance() {
        return useablebalance;
    }

    public Statistics useablebalance(BigDecimal useablebalance) {
        this.useablebalance = useablebalance;
        return this;
    }

    public void setUseablebalance(BigDecimal useablebalance) {
        this.useablebalance = useablebalance;
    }

    public BigDecimal getFreezebalance() {
        return freezebalance;
    }

    public Statistics freezebalance(BigDecimal freezebalance) {
        this.freezebalance = freezebalance;
        return this;
    }

    public void setFreezebalance(BigDecimal freezebalance) {
        this.freezebalance = freezebalance;
    }

    public BigDecimal getIntegral() {
        return integral;
    }

    public Statistics integral(BigDecimal integral) {
        this.integral = integral;
        return this;
    }

    public void setIntegral(BigDecimal integral) {
        this.integral = integral;
    }

    public BigDecimal getCoupon() {
        return coupon;
    }

    public Statistics coupon(BigDecimal coupon) {
        this.coupon = coupon;
        return this;
    }

    public void setCoupon(BigDecimal coupon) {
        this.coupon = coupon;
    }

    public BigDecimal getExpendintegral() {
        return expendintegral;
    }

    public Statistics expendintegral(BigDecimal expendintegral) {
        this.expendintegral = expendintegral;
        return this;
    }

    public void setExpendintegral(BigDecimal expendintegral) {
        this.expendintegral = expendintegral;
    }

    public BigDecimal getIncomeintegral() {
        return incomeintegral;
    }

    public Statistics incomeintegral(BigDecimal incomeintegral) {
        this.incomeintegral = incomeintegral;
        return this;
    }

    public void setIncomeintegral(BigDecimal incomeintegral) {
        this.incomeintegral = incomeintegral;
    }

    public BigDecimal getExpendcoupon() {
        return expendcoupon;
    }

    public Statistics expendcoupon(BigDecimal expendcoupon) {
        this.expendcoupon = expendcoupon;
        return this;
    }

    public void setExpendcoupon(BigDecimal expendcoupon) {
        this.expendcoupon = expendcoupon;
    }

    public BigDecimal getIncomecoupon() {
        return incomecoupon;
    }

    public Statistics incomecoupon(BigDecimal incomecoupon) {
        this.incomecoupon = incomecoupon;
        return this;
    }

    public void setIncomecoupon(BigDecimal incomecoupon) {
        this.incomecoupon = incomecoupon;
    }

    public BigDecimal getProceeds() {
        return proceeds;
    }

    public Statistics proceeds(BigDecimal proceeds) {
        this.proceeds = proceeds;
        return this;
    }

    public void setProceeds(BigDecimal proceeds) {
        this.proceeds = proceeds;
    }

    public BigDecimal getEarnings() {
        return earnings;
    }

    public Statistics earnings(BigDecimal earnings) {
        this.earnings = earnings;
        return this;
    }

    public void setEarnings(BigDecimal earnings) {
        this.earnings = earnings;
    }

    public String getCreatedate() {
        return createdate;
    }

    public Statistics createdate(String createdate) {
        this.createdate = createdate;
        return this;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public BigDecimal getExpense() {
        return expense;
    }

    public Statistics expense(BigDecimal expense) {
        this.expense = expense;
        return this;
    }

    public void setExpense(BigDecimal expense) {
        this.expense = expense;
    }

    public BigDecimal getWithdraw() {
        return withdraw;
    }

    public Statistics withdraw(BigDecimal withdraw) {
        this.withdraw = withdraw;
        return this;
    }

    public void setWithdraw(BigDecimal withdraw) {
        this.withdraw = withdraw;
    }

    public BigDecimal getWithdrawsuccess() {
        return withdrawsuccess;
    }

    public Statistics withdrawsuccess(BigDecimal withdrawsuccess) {
        this.withdrawsuccess = withdrawsuccess;
        return this;
    }

    public void setWithdrawsuccess(BigDecimal withdrawsuccess) {
        this.withdrawsuccess = withdrawsuccess;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Statistics statistics = (Statistics) o;
        if (statistics.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), statistics.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Statistics{" +
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
