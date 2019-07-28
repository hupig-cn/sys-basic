package com.weisen.www.code.yjf.basic.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Withdrawaldetails.
 */
@Entity
@Table(name = "withdrawaldetails")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Withdrawaldetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userid")
    private String userid;

    @Column(name = "withdrawalway")
    private String withdrawalway;

    @Column(name = "title")
    private String title;

    @Column(name = "withdrawalid")
    private String withdrawalid;

    @Column(name = "type")
    private String type;

    @Column(name = "amount")
    private String amount;

    @Column(name = "afteramount")
    private String afteramount;

    @Column(name = "createdate")
    private String createdate;

    @Column(name = "modifierdate")
    private String modifierdate;

    @Column(name = "state")
    private String state;

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

    public Withdrawaldetails userid(String userid) {
        this.userid = userid;
        return this;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getWithdrawalway() {
        return withdrawalway;
    }

    public Withdrawaldetails withdrawalway(String withdrawalway) {
        this.withdrawalway = withdrawalway;
        return this;
    }

    public void setWithdrawalway(String withdrawalway) {
        this.withdrawalway = withdrawalway;
    }

    public String getTitle() {
        return title;
    }

    public Withdrawaldetails title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWithdrawalid() {
        return withdrawalid;
    }

    public Withdrawaldetails withdrawalid(String withdrawalid) {
        this.withdrawalid = withdrawalid;
        return this;
    }

    public void setWithdrawalid(String withdrawalid) {
        this.withdrawalid = withdrawalid;
    }

    public String getType() {
        return type;
    }

    public Withdrawaldetails type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAmount() {
        return amount;
    }

    public Withdrawaldetails amount(String amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getAfteramount() {
        return afteramount;
    }

    public Withdrawaldetails afteramount(String afteramount) {
        this.afteramount = afteramount;
        return this;
    }

    public void setAfteramount(String afteramount) {
        this.afteramount = afteramount;
    }

    public String getCreatedate() {
        return createdate;
    }

    public Withdrawaldetails createdate(String createdate) {
        this.createdate = createdate;
        return this;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getModifierdate() {
        return modifierdate;
    }

    public Withdrawaldetails modifierdate(String modifierdate) {
        this.modifierdate = modifierdate;
        return this;
    }

    public void setModifierdate(String modifierdate) {
        this.modifierdate = modifierdate;
    }

    public String getState() {
        return state;
    }

    public Withdrawaldetails state(String state) {
        this.state = state;
        return this;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getOther() {
        return other;
    }

    public Withdrawaldetails other(String other) {
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
        if (!(o instanceof Withdrawaldetails)) {
            return false;
        }
        return id != null && id.equals(((Withdrawaldetails) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Withdrawaldetails{" +
            "id=" + getId() +
            ", userid='" + getUserid() + "'" +
            ", withdrawalway='" + getWithdrawalway() + "'" +
            ", title='" + getTitle() + "'" +
            ", withdrawalid='" + getWithdrawalid() + "'" +
            ", type='" + getType() + "'" +
            ", amount='" + getAmount() + "'" +
            ", afteramount='" + getAfteramount() + "'" +
            ", createdate='" + getCreatedate() + "'" +
            ", modifierdate='" + getModifierdate() + "'" +
            ", state='" + getState() + "'" +
            ", other='" + getOther() + "'" +
            "}";
    }
}
