package com.weisen.www.code.yjf.basic.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Coupon.
 */
@Entity
@Table(name = "coupon")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Coupon implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userid")
    private String userid;

    @Column(name = "sum")
    private String sum;

    @Column(name = "coupontype")
    private String coupontype;

    @Column(name = "lineon")
    private Boolean lineon;

    @Column(name = "lineunder")
    private Boolean lineunder;

    @Column(name = "integral")
    private Boolean integral;

    @Column(name = "profit")
    private Boolean profit;

    @Column(name = "creator")
    private String creator;

    @Column(name = "createdate")
    private String createdate;

    @Column(name = "modifier")
    private String modifier;

    @Column(name = "modifierdate")
    private String modifierdate;

    @Column(name = "modifiernum")
    private Long modifiernum;

    @Column(name = "logicdelete")
    private Boolean logicdelete;

    @Column(name = "other")
    private String other;

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

    public Coupon userid(String userid) {
        this.userid = userid;
        return this;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getSum() {
        return sum;
    }

    public Coupon sum(String sum) {
        this.sum = sum;
        return this;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public String getCoupontype() {
        return coupontype;
    }

    public Coupon coupontype(String coupontype) {
        this.coupontype = coupontype;
        return this;
    }

    public void setCoupontype(String coupontype) {
        this.coupontype = coupontype;
    }

    public Boolean isLineon() {
        return lineon;
    }

    public Coupon lineon(Boolean lineon) {
        this.lineon = lineon;
        return this;
    }

    public void setLineon(Boolean lineon) {
        this.lineon = lineon;
    }

    public Boolean isLineunder() {
        return lineunder;
    }

    public Coupon lineunder(Boolean lineunder) {
        this.lineunder = lineunder;
        return this;
    }

    public void setLineunder(Boolean lineunder) {
        this.lineunder = lineunder;
    }

    public Boolean isIntegral() {
        return integral;
    }

    public Coupon integral(Boolean integral) {
        this.integral = integral;
        return this;
    }

    public void setIntegral(Boolean integral) {
        this.integral = integral;
    }

    public Boolean isProfit() {
        return profit;
    }

    public Coupon profit(Boolean profit) {
        this.profit = profit;
        return this;
    }

    public void setProfit(Boolean profit) {
        this.profit = profit;
    }

    public String getCreator() {
        return creator;
    }

    public Coupon creator(String creator) {
        this.creator = creator;
        return this;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreatedate() {
        return createdate;
    }

    public Coupon createdate(String createdate) {
        this.createdate = createdate;
        return this;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getModifier() {
        return modifier;
    }

    public Coupon modifier(String modifier) {
        this.modifier = modifier;
        return this;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public String getModifierdate() {
        return modifierdate;
    }

    public Coupon modifierdate(String modifierdate) {
        this.modifierdate = modifierdate;
        return this;
    }

    public void setModifierdate(String modifierdate) {
        this.modifierdate = modifierdate;
    }

    public Long getModifiernum() {
        return modifiernum;
    }

    public Coupon modifiernum(Long modifiernum) {
        this.modifiernum = modifiernum;
        return this;
    }

    public void setModifiernum(Long modifiernum) {
        this.modifiernum = modifiernum;
    }

    public Boolean isLogicdelete() {
        return logicdelete;
    }

    public Coupon logicdelete(Boolean logicdelete) {
        this.logicdelete = logicdelete;
        return this;
    }

    public void setLogicdelete(Boolean logicdelete) {
        this.logicdelete = logicdelete;
    }

    public String getOther() {
        return other;
    }

    public Coupon other(String other) {
        this.other = other;
        return this;
    }

    public void setOther(String other) {
        this.other = other;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Coupon)) {
            return false;
        }
        return id != null && id.equals(((Coupon) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Coupon{" +
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
