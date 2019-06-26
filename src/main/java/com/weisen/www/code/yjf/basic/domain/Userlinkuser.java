package com.weisen.www.code.yjf.basic.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Userlinkuser.
 */
@Entity
@Table(name = "userlinkuser")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Userlinkuser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userid")
    private String userid;

    @Column(name = "recommendid")
    private String recommendid;

    @Column(name = "partner")
    private Boolean partner;

    @Column(name = "province")
    private Boolean province;

    @Column(name = "city")
    private Boolean city;

    @Column(name = "county")
    private Boolean county;

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

    public Userlinkuser userid(String userid) {
        this.userid = userid;
        return this;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getRecommendid() {
        return recommendid;
    }

    public Userlinkuser recommendid(String recommendid) {
        this.recommendid = recommendid;
        return this;
    }

    public void setRecommendid(String recommendid) {
        this.recommendid = recommendid;
    }

    public Boolean isPartner() {
        return partner;
    }

    public Userlinkuser partner(Boolean partner) {
        this.partner = partner;
        return this;
    }

    public void setPartner(Boolean partner) {
        this.partner = partner;
    }

    public Boolean isProvince() {
        return province;
    }

    public Userlinkuser province(Boolean province) {
        this.province = province;
        return this;
    }

    public void setProvince(Boolean province) {
        this.province = province;
    }

    public Boolean isCity() {
        return city;
    }

    public Userlinkuser city(Boolean city) {
        this.city = city;
        return this;
    }

    public void setCity(Boolean city) {
        this.city = city;
    }

    public Boolean isCounty() {
        return county;
    }

    public Userlinkuser county(Boolean county) {
        this.county = county;
        return this;
    }

    public void setCounty(Boolean county) {
        this.county = county;
    }

    public String getCreator() {
        return creator;
    }

    public Userlinkuser creator(String creator) {
        this.creator = creator;
        return this;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreatedate() {
        return createdate;
    }

    public Userlinkuser createdate(String createdate) {
        this.createdate = createdate;
        return this;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getModifier() {
        return modifier;
    }

    public Userlinkuser modifier(String modifier) {
        this.modifier = modifier;
        return this;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public String getModifierdate() {
        return modifierdate;
    }

    public Userlinkuser modifierdate(String modifierdate) {
        this.modifierdate = modifierdate;
        return this;
    }

    public void setModifierdate(String modifierdate) {
        this.modifierdate = modifierdate;
    }

    public Long getModifiernum() {
        return modifiernum;
    }

    public Userlinkuser modifiernum(Long modifiernum) {
        this.modifiernum = modifiernum;
        return this;
    }

    public void setModifiernum(Long modifiernum) {
        this.modifiernum = modifiernum;
    }

    public Boolean isLogicdelete() {
        return logicdelete;
    }

    public Userlinkuser logicdelete(Boolean logicdelete) {
        this.logicdelete = logicdelete;
        return this;
    }

    public void setLogicdelete(Boolean logicdelete) {
        this.logicdelete = logicdelete;
    }

    public String getOther() {
        return other;
    }

    public Userlinkuser other(String other) {
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
        if (!(o instanceof Userlinkuser)) {
            return false;
        }
        return id != null && id.equals(((Userlinkuser) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Userlinkuser{" +
            "id=" + getId() +
            ", userid='" + getUserid() + "'" +
            ", recommendid='" + getRecommendid() + "'" +
            ", partner='" + isPartner() + "'" +
            ", province='" + isProvince() + "'" +
            ", city='" + isCity() + "'" +
            ", county='" + isCounty() + "'" +
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
