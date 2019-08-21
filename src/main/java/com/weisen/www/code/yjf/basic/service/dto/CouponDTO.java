package com.weisen.www.code.yjf.basic.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.weisen.www.code.yjf.basic.domain.Coupon} entity.
 */
@SuppressWarnings("serial")
public class CouponDTO implements Serializable {

    private Long id;

    private String userid;

    private String sum;

    private String coupontype;

    private Boolean lineon;

    private Boolean lineunder;

    private Boolean integral;

    private Boolean profit;

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

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public String getCoupontype() {
        return coupontype;
    }

    public void setCoupontype(String coupontype) {
        this.coupontype = coupontype;
    }

    public Boolean isLineon() {
        return lineon;
    }

    public void setLineon(Boolean lineon) {
        this.lineon = lineon;
    }

    public Boolean isLineunder() {
        return lineunder;
    }

    public void setLineunder(Boolean lineunder) {
        this.lineunder = lineunder;
    }

    public Boolean isIntegral() {
        return integral;
    }

    public void setIntegral(Boolean integral) {
        this.integral = integral;
    }

    public Boolean isProfit() {
        return profit;
    }

    public void setProfit(Boolean profit) {
        this.profit = profit;
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

        CouponDTO couponDTO = (CouponDTO) o;
        if (couponDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), couponDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CouponDTO{" +
            "id=" + getId() +
            ", userid='" + getUserid() + "'" +
            ", sum='" + getSum() + "'" +
            ", coupontype='" + getCoupontype() + "'" +
            ", lineon='" + isLineon() + "'" +
            ", lineunder='" + isLineunder() + "'" +
            ", integral='" + isIntegral() + "'" +
            ", profit='" + isProfit() + "'" +
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
