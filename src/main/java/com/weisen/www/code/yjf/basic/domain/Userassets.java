package com.weisen.www.code.yjf.basic.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Userassets.
 */
@Entity
@Table(name = "userassets")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Userassets implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userid")
    private String userid;

    @Column(name = "balance")
    private String balance;

    @Column(name = "usablebalance")
    private String usablebalance;

    @Column(name = "frozenbalance")
    private String frozenbalance;

    @Column(name = "couponsum")
    private String couponsum;

    @Column(name = "integral")
    private String integral;

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

    public Userassets userid(String userid) {
        this.userid = userid;
        return this;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getBalance() {
        return balance;
    }

    public Userassets balance(String balance) {
        this.balance = balance;
        return this;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getUsablebalance() {
        return usablebalance;
    }

    public Userassets usablebalance(String usablebalance) {
        this.usablebalance = usablebalance;
        return this;
    }

    public void setUsablebalance(String usablebalance) {
        this.usablebalance = usablebalance;
    }

    public String getFrozenbalance() {
        return frozenbalance;
    }

    public Userassets frozenbalance(String frozenbalance) {
        this.frozenbalance = frozenbalance;
        return this;
    }

    public void setFrozenbalance(String frozenbalance) {
        this.frozenbalance = frozenbalance;
    }

    public String getCouponsum() {
        return couponsum;
    }

    public Userassets couponsum(String couponsum) {
        this.couponsum = couponsum;
        return this;
    }

    public void setCouponsum(String couponsum) {
        this.couponsum = couponsum;
    }

    public String getIntegral() {
        return integral;
    }

    public Userassets integral(String integral) {
        this.integral = integral;
        return this;
    }

    public void setIntegral(String integral) {
        this.integral = integral;
    }

    public String getCreator() {
        return creator;
    }

    public Userassets creator(String creator) {
        this.creator = creator;
        return this;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreatedate() {
        return createdate;
    }

    public Userassets createdate(String createdate) {
        this.createdate = createdate;
        return this;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getModifier() {
        return modifier;
    }

    public Userassets modifier(String modifier) {
        this.modifier = modifier;
        return this;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public String getModifierdate() {
        return modifierdate;
    }

    public Userassets modifierdate(String modifierdate) {
        this.modifierdate = modifierdate;
        return this;
    }

    public void setModifierdate(String modifierdate) {
        this.modifierdate = modifierdate;
    }

    public Long getModifiernum() {
        return modifiernum;
    }

    public Userassets modifiernum(Long modifiernum) {
        this.modifiernum = modifiernum;
        return this;
    }

    public void setModifiernum(Long modifiernum) {
        this.modifiernum = modifiernum;
    }

    public Boolean isLogicdelete() {
        return logicdelete;
    }

    public Userassets logicdelete(Boolean logicdelete) {
        this.logicdelete = logicdelete;
        return this;
    }

    public void setLogicdelete(Boolean logicdelete) {
        this.logicdelete = logicdelete;
    }

    public String getOther() {
        return other;
    }

    public Userassets other(String other) {
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
        if (!(o instanceof Userassets)) {
            return false;
        }
        return id != null && id.equals(((Userassets) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Userassets{" +
            "id=" + getId() +
            ", userid='" + getUserid() + "'" +
            ", balance='" + getBalance() + "'" +
            ", usablebalance='" + getUsablebalance() + "'" +
            ", frozenbalance='" + getFrozenbalance() + "'" +
            ", couponsum='" + getCouponsum() + "'" +
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
