package com.weisen.www.code.yjf.basic.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A Receiptpay.
 */
@Entity
@Table(name = "receiptpay")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Receiptpay implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dealtype")
    private String dealtype;

    @Column(name = "userid")
    private String userid;

    @Column(name = "sourcer")
    private String sourcer;

    @Column(name = "benefit")
    private String benefit;

    @Column(name = "amount", precision = 21, scale = 2)
    private BigDecimal amount;

    @Column(name = "bonus", precision = 21, scale = 2)
    private BigDecimal bonus;

    @Column(name = "happendate")
    private String happendate;

    @Column(name = "freezedate")
    private String freezedate;

    @Column(name = "dealstate")
    private String dealstate;

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

    public String getDealtype() {
        return dealtype;
    }

    public Receiptpay dealtype(String dealtype) {
        this.dealtype = dealtype;
        return this;
    }

    public void setDealtype(String dealtype) {
        this.dealtype = dealtype;
    }

    public String getUserid() {
        return userid;
    }

    public Receiptpay userid(String userid) {
        this.userid = userid;
        return this;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getSourcer() {
        return sourcer;
    }

    public Receiptpay sourcer(String sourcer) {
        this.sourcer = sourcer;
        return this;
    }

    public void setSourcer(String sourcer) {
        this.sourcer = sourcer;
    }

    public String getBenefit() {
        return benefit;
    }

    public Receiptpay benefit(String benefit) {
        this.benefit = benefit;
        return this;
    }

    public void setBenefit(String benefit) {
        this.benefit = benefit;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Receiptpay amount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getBonus() {
        return bonus;
    }

    public Receiptpay bonus(BigDecimal bonus) {
        this.bonus = bonus;
        return this;
    }

    public void setBonus(BigDecimal bonus) {
        this.bonus = bonus;
    }

    public String getHappendate() {
        return happendate;
    }

    public Receiptpay happendate(String happendate) {
        this.happendate = happendate;
        return this;
    }

    public void setHappendate(String happendate) {
        this.happendate = happendate;
    }

    public String getFreezedate() {
        return freezedate;
    }

    public Receiptpay freezedate(String freezedate) {
        this.freezedate = freezedate;
        return this;
    }

    public void setFreezedate(String freezedate) {
        this.freezedate = freezedate;
    }

    public String getDealstate() {
        return dealstate;
    }

    public Receiptpay dealstate(String dealstate) {
        this.dealstate = dealstate;
        return this;
    }

    public void setDealstate(String dealstate) {
        this.dealstate = dealstate;
    }

    public String getCreator() {
        return creator;
    }

    public Receiptpay creator(String creator) {
        this.creator = creator;
        return this;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreatedate() {
        return createdate;
    }

    public Receiptpay createdate(String createdate) {
        this.createdate = createdate;
        return this;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getModifier() {
        return modifier;
    }

    public Receiptpay modifier(String modifier) {
        this.modifier = modifier;
        return this;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public String getModifierdate() {
        return modifierdate;
    }

    public Receiptpay modifierdate(String modifierdate) {
        this.modifierdate = modifierdate;
        return this;
    }

    public void setModifierdate(String modifierdate) {
        this.modifierdate = modifierdate;
    }

    public Long getModifiernum() {
        return modifiernum;
    }

    public Receiptpay modifiernum(Long modifiernum) {
        this.modifiernum = modifiernum;
        return this;
    }

    public void setModifiernum(Long modifiernum) {
        this.modifiernum = modifiernum;
    }

    public Boolean isLogicdelete() {
        return logicdelete;
    }

    public Receiptpay logicdelete(Boolean logicdelete) {
        this.logicdelete = logicdelete;
        return this;
    }

    public void setLogicdelete(Boolean logicdelete) {
        this.logicdelete = logicdelete;
    }

    public String getOther() {
        return other;
    }

    public Receiptpay other(String other) {
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
        if (!(o instanceof Receiptpay)) {
            return false;
        }
        return id != null && id.equals(((Receiptpay) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Receiptpay{" +
            "id=" + getId() +
            ", dealtype='" + getDealtype() + "'" +
            ", userid='" + getUserid() + "'" +
            ", sourcer='" + getSourcer() + "'" +
            ", benefit='" + getBenefit() + "'" +
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
            "}";
    }
}
