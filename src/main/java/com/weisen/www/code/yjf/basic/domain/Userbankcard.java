package com.weisen.www.code.yjf.basic.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Userbankcard.
 */
@Entity
@Table(name = "userbankcard")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Userbankcard implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userid")
    private String userid;

    @Column(name = "realname")
    private String realname;

    @Column(name = "bankcard")
    private String bankcard;

    @Column(name = "banktype")
    private String banktype;

    @Column(name = "cardtype")
    private String cardtype;

    @Column(name = "bankicon")
    private String bankicon;

    @Column(name = "bankphone")
    private String bankphone;

    @Column(name = "createdate")
    private String createdate;

    @Column(name = "modifier")
    private String modifier;

    @Column(name = "modifierdate")
    private String modifierdate;

    @Column(name = "modifiernum")
    private Long modifiernum;

    @Column(name = "state")
    private String state;

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

    public Userbankcard userid(String userid) {
        this.userid = userid;
        return this;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getRealname() {
        return realname;
    }

    public Userbankcard realname(String realname) {
        this.realname = realname;
        return this;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getBankcard() {
        return bankcard;
    }

    public Userbankcard bankcard(String bankcard) {
        this.bankcard = bankcard;
        return this;
    }

    public void setBankcard(String bankcard) {
        this.bankcard = bankcard;
    }

    public String getBanktype() {
        return banktype;
    }

    public Userbankcard banktype(String banktype) {
        this.banktype = banktype;
        return this;
    }

    public void setBanktype(String banktype) {
        this.banktype = banktype;
    }

    public String getCardtype() {
        return cardtype;
    }

    public Userbankcard cardtype(String cardtype) {
        this.cardtype = cardtype;
        return this;
    }

    public void setCardtype(String cardtype) {
        this.cardtype = cardtype;
    }

    public String getBankicon() {
        return bankicon;
    }

    public Userbankcard bankicon(String bankicon) {
        this.bankicon = bankicon;
        return this;
    }

    public void setBankicon(String bankicon) {
        this.bankicon = bankicon;
    }

    public String getBankphone() {
        return bankphone;
    }

    public Userbankcard bankphone(String bankphone) {
        this.bankphone = bankphone;
        return this;
    }

    public void setBankphone(String bankphone) {
        this.bankphone = bankphone;
    }

    public String getCreatedate() {
        return createdate;
    }

    public Userbankcard createdate(String createdate) {
        this.createdate = createdate;
        return this;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getModifier() {
        return modifier;
    }

    public Userbankcard modifier(String modifier) {
        this.modifier = modifier;
        return this;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public String getModifierdate() {
        return modifierdate;
    }

    public Userbankcard modifierdate(String modifierdate) {
        this.modifierdate = modifierdate;
        return this;
    }

    public void setModifierdate(String modifierdate) {
        this.modifierdate = modifierdate;
    }

    public Long getModifiernum() {
        return modifiernum;
    }

    public Userbankcard modifiernum(Long modifiernum) {
        this.modifiernum = modifiernum;
        return this;
    }

    public void setModifiernum(Long modifiernum) {
        this.modifiernum = modifiernum;
    }

    public String getState() {
        return state;
    }

    public Userbankcard state(String state) {
        this.state = state;
        return this;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Boolean isLogicdelete() {
        return logicdelete;
    }

    public Userbankcard logicdelete(Boolean logicdelete) {
        this.logicdelete = logicdelete;
        return this;
    }

    public void setLogicdelete(Boolean logicdelete) {
        this.logicdelete = logicdelete;
    }

    public String getOther() {
        return other;
    }

    public Userbankcard other(String other) {
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
        if (!(o instanceof Userbankcard)) {
            return false;
        }
        return id != null && id.equals(((Userbankcard) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Userbankcard{" +
            "id=" + getId() +
            ", userid='" + getUserid() + "'" +
            ", realname='" + getRealname() + "'" +
            ", bankcard='" + getBankcard() + "'" +
            ", banktype='" + getBanktype() + "'" +
            ", cardtype='" + getCardtype() + "'" +
            ", bankicon='" + getBankicon() + "'" +
            ", bankphone='" + getBankphone() + "'" +
            ", createdate='" + getCreatedate() + "'" +
            ", modifier='" + getModifier() + "'" +
            ", modifierdate='" + getModifierdate() + "'" +
            ", modifiernum=" + getModifiernum() +
            ", state='" + getState() + "'" +
            ", logicdelete='" + isLogicdelete() + "'" +
            ", other='" + getOther() + "'" +
            "}";
    }
}
