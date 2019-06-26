package com.weisen.www.code.yjf.basic.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Profitlog.
 */
@Entity
@Table(name = "profitlog")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Profitlog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userid")
    private String userid;

    @Column(name = "consumer")
    private String consumer;

    @Column(name = "appearamount")
    private String appearamount;

    @Column(name = "appeardate")
    private String appeardate;

    @Column(name = "frozendate")
    private String frozendate;

    @Column(name = "profittype")
    private String profittype;

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

    public Profitlog userid(String userid) {
        this.userid = userid;
        return this;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getConsumer() {
        return consumer;
    }

    public Profitlog consumer(String consumer) {
        this.consumer = consumer;
        return this;
    }

    public void setConsumer(String consumer) {
        this.consumer = consumer;
    }

    public String getAppearamount() {
        return appearamount;
    }

    public Profitlog appearamount(String appearamount) {
        this.appearamount = appearamount;
        return this;
    }

    public void setAppearamount(String appearamount) {
        this.appearamount = appearamount;
    }

    public String getAppeardate() {
        return appeardate;
    }

    public Profitlog appeardate(String appeardate) {
        this.appeardate = appeardate;
        return this;
    }

    public void setAppeardate(String appeardate) {
        this.appeardate = appeardate;
    }

    public String getFrozendate() {
        return frozendate;
    }

    public Profitlog frozendate(String frozendate) {
        this.frozendate = frozendate;
        return this;
    }

    public void setFrozendate(String frozendate) {
        this.frozendate = frozendate;
    }

    public String getProfittype() {
        return profittype;
    }

    public Profitlog profittype(String profittype) {
        this.profittype = profittype;
        return this;
    }

    public void setProfittype(String profittype) {
        this.profittype = profittype;
    }

    public String getCreator() {
        return creator;
    }

    public Profitlog creator(String creator) {
        this.creator = creator;
        return this;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreatedate() {
        return createdate;
    }

    public Profitlog createdate(String createdate) {
        this.createdate = createdate;
        return this;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getModifier() {
        return modifier;
    }

    public Profitlog modifier(String modifier) {
        this.modifier = modifier;
        return this;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public String getModifierdate() {
        return modifierdate;
    }

    public Profitlog modifierdate(String modifierdate) {
        this.modifierdate = modifierdate;
        return this;
    }

    public void setModifierdate(String modifierdate) {
        this.modifierdate = modifierdate;
    }

    public Long getModifiernum() {
        return modifiernum;
    }

    public Profitlog modifiernum(Long modifiernum) {
        this.modifiernum = modifiernum;
        return this;
    }

    public void setModifiernum(Long modifiernum) {
        this.modifiernum = modifiernum;
    }

    public Boolean isLogicdelete() {
        return logicdelete;
    }

    public Profitlog logicdelete(Boolean logicdelete) {
        this.logicdelete = logicdelete;
        return this;
    }

    public void setLogicdelete(Boolean logicdelete) {
        this.logicdelete = logicdelete;
    }

    public String getOther() {
        return other;
    }

    public Profitlog other(String other) {
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
        if (!(o instanceof Profitlog)) {
            return false;
        }
        return id != null && id.equals(((Profitlog) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Profitlog{" +
            "id=" + getId() +
            ", userid='" + getUserid() + "'" +
            ", consumer='" + getConsumer() + "'" +
            ", appearamount='" + getAppearamount() + "'" +
            ", appeardate='" + getAppeardate() + "'" +
            ", frozendate='" + getFrozendate() + "'" +
            ", profittype='" + getProfittype() + "'" +
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
