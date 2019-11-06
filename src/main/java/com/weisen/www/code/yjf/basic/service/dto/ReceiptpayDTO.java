package com.weisen.www.code.yjf.basic.service.dto;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the Receiptpay entity.
 */
public class ReceiptpayDTO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

    private String dealtype;

    private String userid;

    private String sourcer;

    private String benefit;

    private String payway;

    private BigDecimal amount;

    private BigDecimal bonus;

    private String happendate;

    private String freezedate;

    private String dealstate;

    private String creator;

    private String createdate;

    private String modifier;

    private String modifierdate;

    private Long modifiernum;

    private Boolean logicdelete;

    private String other;

    private BigDecimal balance;

    private BigDecimal useablebalance;

    private BigDecimal freezebalance;

    private BigDecimal coupon;

    private BigDecimal integral;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDealtype() {
        return dealtype;
    }

    public void setDealtype(String dealtype) {
        this.dealtype = dealtype;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getSourcer() {
        return sourcer;
    }

    public void setSourcer(String sourcer) {
        this.sourcer = sourcer;
    }

    public String getBenefit() {
        return benefit;
    }

    public void setBenefit(String benefit) {
        this.benefit = benefit;
    }

    public String getPayway() {
        return payway;
    }

    public void setPayway(String payway) {
        this.payway = payway;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getBonus() {
        return bonus;
    }

    public void setBonus(BigDecimal bonus) {
        this.bonus = bonus;
    }

    public String getHappendate() {
        return happendate;
    }

    public void setHappendate(String happendate) {
        this.happendate = happendate;
    }

    public String getFreezedate() {
        return freezedate;
    }

    public void setFreezedate(String freezedate) {
        this.freezedate = freezedate;
    }

    public String getDealstate() {
        return dealstate;
    }

    public void setDealstate(String dealstate) {
        this.dealstate = dealstate;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public String getModifierdate() {
        return modifierdate;
    }

    public void setModifierdate(String modifierdate) {
        this.modifierdate = modifierdate;
    }

    public Long getModifiernum() {
        return modifiernum;
    }

    public void setModifiernum(Long modifiernum) {
        this.modifiernum = modifiernum;
    }

    public Boolean isLogicdelete() {
        return logicdelete;
    }

    public void setLogicdelete(Boolean logicdelete) {
        this.logicdelete = logicdelete;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
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

    public BigDecimal getCoupon() {
        return coupon;
    }

    public void setCoupon(BigDecimal coupon) {
        this.coupon = coupon;
    }

    public BigDecimal getIntegral() {
        return integral;
    }

    public void setIntegral(BigDecimal integral) {
        this.integral = integral;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ReceiptpayDTO receiptpayDTO = (ReceiptpayDTO) o;
        if (receiptpayDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), receiptpayDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ReceiptpayDTO{" +
            "id=" + getId() +
            ", dealtype='" + getDealtype() + "'" +
            ", userid='" + getUserid() + "'" +
            ", sourcer='" + getSourcer() + "'" +
            ", benefit='" + getBenefit() + "'" +
            ", payway='" + getPayway() + "'" +
            ", amount=" + getAmount() +
            ", bonus=" + getBonus() +
            ", happendate='" + getHappendate() + "'" +
            ", freezedate='" + getFreezedate() + "'" +
            ", dealstate='" + getDealstate() + "'" +
            ", creator='" + getCreator() + "'" +
            ", createdate='" + getCreatedate() + "'" +
            ", modifier='" + getModifier() + "'" +
            ", modifierdate='" + getModifierdate() + "'" +
            ", modifiernum=" + getModifiernum() +
            ", logicdelete='" + isLogicdelete() + "'" +
            ", other='" + getOther() + "'" +
            ", balance=" + getBalance() +
            ", useablebalance=" + getUseablebalance() +
            ", freezebalance=" + getFreezebalance() +
            ", coupon=" + getCoupon() +
            ", integral=" + getIntegral() +
            "}";
    }
}
