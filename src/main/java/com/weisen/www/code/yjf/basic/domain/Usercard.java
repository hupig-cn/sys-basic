package com.weisen.www.code.yjf.basic.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

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

    @Column(name = "bankname")
    private String bankname;

    @Column(name = "logo")
    private String logo;

    @Column(name = "bank")
    private String bank;

    @Column(name = "cardnum")
    private String cardnum;
    
    @Column(name = "code")
    private String code;

    @Column(name = "state")
    private Integer state;

    @Column(name = "numprefix")
    private String numprefix;

    @Column(name = "csort")
    private Integer csort;

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
    
    @Column(name = "bank_background")
    private String bankBackground;
    
    

    public String getBankBackground() {
		return bankBackground;
	}

	public void setBankBackground(String bankBackground) {
		this.bankBackground = bankBackground;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBankname() {
        return bankname;
    }

    public Usercard bankname(String bankname) {
        this.bankname = bankname;
        return this;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

    public String getLogo() {
        return logo;
    }

    public Usercard logo(String logo) {
        this.logo = logo;
        return this;
    }

    public void setLogo(String logo) {
        this.logo = logo;
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

    public String getCode() {
        return code;
    }

    public Usercard code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getState() {
        return state;
    }

    public Usercard state(Integer state) {
        this.state = state;
        return this;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getNumprefix() {
        return numprefix;
    }

    public Usercard numprefix(String numprefix) {
        this.numprefix = numprefix;
        return this;
    }

    public void setNumprefix(String numprefix) {
        this.numprefix = numprefix;
    }

    public Integer getCsort() {
        return csort;
    }

    public Usercard csort(Integer csort) {
        this.csort = csort;
        return this;
    }

    public void setCsort(Integer csort) {
        this.csort = csort;
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
            ", bankname='" + getBankname() + "'" +
            ", logo='" + getLogo() + "'" +
            ", bank='" + getBank() + "'" +
            ", cardnum='" + getCardnum() + "'" +
            ", creator='" + getCreator() + "'" +
            ", code='" + getCode() + "'" +
            ", state=" + getState() +
            ", numprefix='" + getNumprefix() + "'" +
            ", csort=" + getCsort() +
            ", createdate='" + getCreatedate() + "'" +
            ", modifier='" + getModifier() + "'" +
            ", modifierdate='" + getModifierdate() + "'" +
            ", modifiernum=" + getModifiernum() +
            ", logicdelete='" + isLogicdelete() + "'" +
            ", other='" + getOther() + "'" +
            "}";
    }
}
