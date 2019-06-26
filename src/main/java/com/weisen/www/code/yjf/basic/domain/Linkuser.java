package com.weisen.www.code.yjf.basic.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Linkuser.
 */
@Entity
@Table(name = "linkuser")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Linkuser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userid")
    private String userid;

    @Column(name = "phone")
    private String phone;

    @Column(name = "name")
    private String name;

    @Column(name = "idcard")
    private String idcard;

    @Column(name = "sex")
    private String sex;

    @Column(name = "address")
    private String address;

    @Column(name = "province")
    private String province;

    @Column(name = "city")
    private String city;

    @Column(name = "county")
    private String county;

    @Column(name = "loginnum")
    private Integer loginnum;

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

    public Linkuser userid(String userid) {
        this.userid = userid;
        return this;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPhone() {
        return phone;
    }

    public Linkuser phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public Linkuser name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdcard() {
        return idcard;
    }

    public Linkuser idcard(String idcard) {
        this.idcard = idcard;
        return this;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getSex() {
        return sex;
    }

    public Linkuser sex(String sex) {
        this.sex = sex;
        return this;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public Linkuser address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProvince() {
        return province;
    }

    public Linkuser province(String province) {
        this.province = province;
        return this;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public Linkuser city(String city) {
        this.city = city;
        return this;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public Linkuser county(String county) {
        this.county = county;
        return this;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public Integer getLoginnum() {
        return loginnum;
    }

    public Linkuser loginnum(Integer loginnum) {
        this.loginnum = loginnum;
        return this;
    }

    public void setLoginnum(Integer loginnum) {
        this.loginnum = loginnum;
    }

    public String getCreator() {
        return creator;
    }

    public Linkuser creator(String creator) {
        this.creator = creator;
        return this;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreatedate() {
        return createdate;
    }

    public Linkuser createdate(String createdate) {
        this.createdate = createdate;
        return this;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getModifier() {
        return modifier;
    }

    public Linkuser modifier(String modifier) {
        this.modifier = modifier;
        return this;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public String getModifierdate() {
        return modifierdate;
    }

    public Linkuser modifierdate(String modifierdate) {
        this.modifierdate = modifierdate;
        return this;
    }

    public void setModifierdate(String modifierdate) {
        this.modifierdate = modifierdate;
    }

    public Long getModifiernum() {
        return modifiernum;
    }

    public Linkuser modifiernum(Long modifiernum) {
        this.modifiernum = modifiernum;
        return this;
    }

    public void setModifiernum(Long modifiernum) {
        this.modifiernum = modifiernum;
    }

    public Boolean isLogicdelete() {
        return logicdelete;
    }

    public Linkuser logicdelete(Boolean logicdelete) {
        this.logicdelete = logicdelete;
        return this;
    }

    public void setLogicdelete(Boolean logicdelete) {
        this.logicdelete = logicdelete;
    }

    public String getOther() {
        return other;
    }

    public Linkuser other(String other) {
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
        if (!(o instanceof Linkuser)) {
            return false;
        }
        return id != null && id.equals(((Linkuser) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Linkuser{" +
            "id=" + getId() +
            ", userid='" + getUserid() + "'" +
            ", phone='" + getPhone() + "'" +
            ", name='" + getName() + "'" +
            ", idcard='" + getIdcard() + "'" +
            ", sex='" + getSex() + "'" +
            ", address='" + getAddress() + "'" +
            ", province='" + getProvince() + "'" +
            ", city='" + getCity() + "'" +
            ", county='" + getCounty() + "'" +
            ", loginnum=" + getLoginnum() +
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
