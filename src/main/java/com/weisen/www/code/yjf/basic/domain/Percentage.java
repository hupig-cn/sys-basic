package com.weisen.www.code.yjf.basic.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A Percentage.
 */
@Entity
@Table(name = "percentage")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Percentage implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "type")
	private String type;

	@Column(name = "value")
	private String value;

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

	@Column(name = "cevent")
	private char cevent;

	@Column(name = "discount")
	private BigDecimal discount;

	// jhipster-needle-entity-add-field - JHipster will add fields here, do not
	// remove
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public Percentage name(String name) {
		this.name = name;
		return this;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public Percentage type(String type) {
		this.type = type;
		return this;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public Percentage value(String value) {
		this.value = value;
		return this;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getCreator() {
		return creator;
	}

	public Percentage creator(String creator) {
		this.creator = creator;
		return this;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getCreatedate() {
		return createdate;
	}

	public Percentage createdate(String createdate) {
		this.createdate = createdate;
		return this;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

	public String getModifier() {
		return modifier;
	}

	public Percentage modifier(String modifier) {
		this.modifier = modifier;
		return this;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public String getModifierdate() {
		return modifierdate;
	}

	public Percentage modifierdate(String modifierdate) {
		this.modifierdate = modifierdate;
		return this;
	}

	public void setModifierdate(String modifierdate) {
		this.modifierdate = modifierdate;
	}

	public Long getModifiernum() {
		return modifiernum;
	}

	public Percentage modifiernum(Long modifiernum) {
		this.modifiernum = modifiernum;
		return this;
	}

	public void setModifiernum(Long modifiernum) {
		this.modifiernum = modifiernum;
	}

	public Boolean isLogicdelete() {
		return logicdelete;
	}

	public Percentage logicdelete(Boolean logicdelete) {
		this.logicdelete = logicdelete;
		return this;
	}

	public void setLogicdelete(Boolean logicdelete) {
		this.logicdelete = logicdelete;
	}

	public String getOther() {
		return other;
	}

	public Percentage other(String other) {
		this.other = other;
		return this;
	}

	public void setOther(String other) {
		this.other = other;
	}
	// jhipster-needle-entity-add-getters-setters - JHipster will add getters and
	// setters here, do not remove

	public char getCevent() {
		return cevent;
	}

	public void setCevent(char cevent) {
		this.cevent = cevent;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Percentage)) {
			return false;
		}
		return id != null && id.equals(((Percentage) o).id);
	}

	@Override
	public int hashCode() {
		return 31;
	}

	@Override
	public String toString() {
		return "Percentage{" + "id=" + getId() + ", name='" + getName() + "'" + ", type='" + getType() + "'"
				+ ", value='" + getValue() + "'" + ", creator='" + getCreator() + "'" + ", createdate='"
				+ getCreatedate() + "'" + ", modifier='" + getModifier() + "'" + ", modifierdate='" + getModifierdate()
				+ "'" + ", modifiernum=" + getModifiernum() + ", logicdelete='" + isLogicdelete() + "'" + ", other='"
				+ getOther() + "'" + "}";
	}
}
