package com.weisen.www.code.yjf.basic.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Paymethod.
 */
@Entity
@Table(name = "paymethod")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Paymethod implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "os")
    private String os;

    @Column(name = "jhi_online")
    private Boolean online;

    @Column(name = "switchs")
    private Boolean switchs;

    @Column(name = "payname")
    private String payname;

    @Column(name = "jhi_order")
    private String order;

    @Column(name = "messages")
    private String messages;

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

    public String getOs() {
        return os;
    }

    public Paymethod os(String os) {
        this.os = os;
        return this;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public Boolean isOnline() {
        return online;
    }

    public Paymethod online(Boolean online) {
        this.online = online;
        return this;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }

    public Boolean isSwitchs() {
        return switchs;
    }

    public Paymethod switchs(Boolean switchs) {
        this.switchs = switchs;
        return this;
    }

    public void setSwitchs(Boolean switchs) {
        this.switchs = switchs;
    }

    public String getPayname() {
        return payname;
    }

    public Paymethod payname(String payname) {
        this.payname = payname;
        return this;
    }

    public void setPayname(String payname) {
        this.payname = payname;
    }

    public String getOrder() {
        return order;
    }

    public Paymethod order(String order) {
        this.order = order;
        return this;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getMessages() {
        return messages;
    }

    public Paymethod messages(String messages) {
        this.messages = messages;
        return this;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    public String getCreator() {
        return creator;
    }

    public Paymethod creator(String creator) {
        this.creator = creator;
        return this;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreatedate() {
        return createdate;
    }

    public Paymethod createdate(String createdate) {
        this.createdate = createdate;
        return this;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getModifier() {
        return modifier;
    }

    public Paymethod modifier(String modifier) {
        this.modifier = modifier;
        return this;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public String getModifierdate() {
        return modifierdate;
    }

    public Paymethod modifierdate(String modifierdate) {
        this.modifierdate = modifierdate;
        return this;
    }

    public void setModifierdate(String modifierdate) {
        this.modifierdate = modifierdate;
    }

    public Long getModifiernum() {
        return modifiernum;
    }

    public Paymethod modifiernum(Long modifiernum) {
        this.modifiernum = modifiernum;
        return this;
    }

    public void setModifiernum(Long modifiernum) {
        this.modifiernum = modifiernum;
    }

    public Boolean isLogicdelete() {
        return logicdelete;
    }

    public Paymethod logicdelete(Boolean logicdelete) {
        this.logicdelete = logicdelete;
        return this;
    }

    public void setLogicdelete(Boolean logicdelete) {
        this.logicdelete = logicdelete;
    }

    public String getOther() {
        return other;
    }

    public Paymethod other(String other) {
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
        if (!(o instanceof Paymethod)) {
            return false;
        }
        return id != null && id.equals(((Paymethod) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Paymethod{" +
            "id=" + getId() +
            ", os='" + getOs() + "'" +
            ", online='" + isOnline() + "'" +
            ", switchs='" + isSwitchs() + "'" +
            ", payname='" + getPayname() + "'" +
            ", order='" + getOrder() + "'" +
            ", messages='" + getMessages() + "'" +
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
