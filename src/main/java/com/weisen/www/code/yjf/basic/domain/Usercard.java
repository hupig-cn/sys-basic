package com.weisen.www.code.yjf.basic.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Usercard.
 */
@Entity
@Table(name = "usercard")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Usercard implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userid")
    private String userid;

    @Column(name = "cardtype")
    private String cardtype;

    @Column(name = "belong")
    private String belong;

    @Column(name = "bank")
    private String bank;

    @Column(name = "cardnum")
    private String cardnum;

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

    public Usercard userid(String userid) {
        this.userid = userid;
        return this;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getCardtype() {
        return cardtype;
    }

    public Usercard cardtype(String cardtype) {
        this.cardtype = cardtype;
        return this;
    }

    public void setCardtype(String cardtype) {
        this.cardtype = cardtype;
    }

    public String getBelong() {
        return belong;
    }

    public Usercard belong(String belong) {
        this.belong = belong;
        return this;
    }

    public void setBelong(String belong) {
        this.belong = belong;
    }

    public String getBank() {
        return bank;
    }

    public Usercard bank(String bank) {
        this.bank = bank;
        return this;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getCardnum() {
        return cardnum;
    }

    public Usercard cardnum(String cardnum) {
        this.cardnum = cardnum;
        return this;
    }

    public void setCardnum(String cardnum) {
        this.cardnum = cardnum;
    }

    public String getCreator() {
        return creator;
    }

    public Usercard creator(String creator) {
        this.creator = creator;
        return this;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreatedate() {
        return createdate;
    }

    public Usercard createdate(String createdate) {
        this.createdate = createdate;
        return this;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getModifier() {
        return modifier;
    }

    public Usercard modifier(String modifier) {
        this.modifier = modifier;
        return this;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public String getModifierdate() {
        return modifierdate;
    }

    public Usercard modifierdate(String modifierdate) {
        this.modifierdate = modifierdate;
        return this;
    }

    public void setModifierdate(String modifierdate) {
        this.modifierdate = modifierdate;
    }

    public Long getModifiernum() {
        return modifiernum;
    }

    public Usercard modifiernum(Long modifiernum) {
        this.modifiernum = modifiernum;
        return this;
    }

    public void setModifiernum(Long modifiernum) {
        this.modifiernum = modifiernum;
    }

    public Boolean isLogicdelete() {
        return logicdelete;
    }

    public Usercard logicdelete(Boolean logicdelete) {
        this.logicdelete = logicdelete;
        return this;
    }

    public void setLogicdelete(Boolean logicdelete) {
        this.logicdelete = logicdelete;
    }

    public String getOther() {
        return other;
    }

    public Usercard other(String other) {
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
        if (!(o instanceof Usercard)) {
            return false;
        }
        return id != null && id.equals(((Usercard) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Usercard{" +
            "id=" + getId() +
            ", userid='" + getUserid() + "'" +
            ", cardtype='" + getCardtype() + "'" +
            ", belong='" + getBelong() + "'" +
            ", bank='" + getBank() + "'" +
            ", cardnum='" + getCardnum() + "'" +
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
