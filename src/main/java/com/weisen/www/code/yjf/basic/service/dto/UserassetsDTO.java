package com.weisen.www.code.yjf.basic.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.weisen.www.code.yjf.basic.domain.Userassets} entity.
 */
public class UserassetsDTO implements Serializable {

    private Long id;

    private String userid;

    private String couponid;

    private String couponnum;

    private String balance;

    private String usablebalance;

    private String frozenbalance;

    private String integral;

    private String creator;

    private String createdate;

    private String modifier;

    private String modifierdate;

    private Long modifiernum;

    private Boolean logicdelete;

    private String other;


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

    public String getCouponid() {
        return couponid;
    }

    public void setCouponid(String couponid) {
        this.couponid = couponid;
    }

    public String getCouponnum() {
        return couponnum;
    }

    public void setCouponnum(String couponnum) {
        this.couponnum = couponnum;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getUsablebalance() {
        return usablebalance;
    }

    public void setUsablebalance(String usablebalance) {
        this.usablebalance = usablebalance;
    }

    public String getFrozenbalance() {
        return frozenbalance;
    }

    public void setFrozenbalance(String frozenbalance) {
        this.frozenbalance = frozenbalance;
    }

    public String getIntegral() {
        return integral;
    }

    public void setIntegral(String integral) {
        this.integral = integral;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UserassetsDTO userassetsDTO = (UserassetsDTO) o;
        if (userassetsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), userassetsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UserassetsDTO{" +
            "id=" + getId() +
            ", userid='" + getUserid() + "'" +
            ", couponid='" + getCouponid() + "'" +
            ", couponnum='" + getCouponnum() + "'" +
            ", balance='" + getBalance() + "'" +
            ", usablebalance='" + getUsablebalance() + "'" +
            ", frozenbalance='" + getFrozenbalance() + "'" +
            ", integral='" + getIntegral() + "'" +
            ", creator='" + getCreator() + "'" +
            ", createdate='" + getCreatedate() + "'" +
            ", modifier='" + getModifier() + "'" +
            ", modifierdate='" + getModifierdate() + "'" +
            ", modifiernum=" + getModifiernum() +
            ", logicdelete='" + isLogicdelete() + "'" +
            ", other='" + getOther() + "'" +
            "}";
    }
}
