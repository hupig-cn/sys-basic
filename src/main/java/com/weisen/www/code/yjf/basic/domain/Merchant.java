package com.weisen.www.code.yjf.basic.domain;


import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Merchant.
 */
@Entity
@Table(name = "merchant")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Merchant implements Serializable {

//    private static final long serialVersionUID = 1L;

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userid")
    private String userid;

    @Column(name = "merchantphoto")
    private String merchantphoto;

    @Column(name = "name")
    private String name;

    @Column(name = "businessid")
    private String businessid;

    @Column(name = "state")
    private String state;

    @Column(name = "address")
    private String address;

    @Column(name = "province")
    private String province;

    @Column(name = "city")
    private String city;

    @Column(name = "county")
    private String county;

    @Column(name = "longitude", precision = 21, scale = 2)
    private BigDecimal longitude;

    @Column(name = "latitude", precision = 21, scale = 2)
    private BigDecimal latitude;

    @Column(name = "concession")
    private Integer concession;

    @Column(name = "rebate")
    private Integer rebate;

    @Column(name = "buslicenseimage")
    private String buslicenseimage;

    @Column(name = "jhi_show")
    private String show;

    @Column(name = "creditcode")
    private String creditcode;

    @Column(name = "weight")
    private String weight;

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

    public Merchant userid(String userid) {
        this.userid = userid;
        return this;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getMerchantphoto() {
        return merchantphoto;
    }

    public Merchant merchantphoto(String merchantphoto) {
        this.merchantphoto = merchantphoto;
        return this;
    }

    public void setMerchantphoto(String merchantphoto) {
        this.merchantphoto = merchantphoto;
    }

    public String getName() {
        return name;
    }

    public Merchant name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBusinessid() {
        return businessid;
    }

    public Merchant businessid(String businessid) {
        this.businessid = businessid;
        return this;
    }

    public void setBusinessid(String businessid) {
        this.businessid = businessid;
    }

    public String getState() {
        return state;
    }

    public Merchant state(String state) {
        this.state = state;
        return this;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAddress() {
        return address;
    }

    public Merchant address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProvince() {
        return province;
    }

    public Merchant province(String province) {
        this.province = province;
        return this;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public Merchant city(String city) {
        this.city = city;
        return this;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public Merchant county(String county) {
        this.county = county;
        return this;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public Merchant longitude(BigDecimal longitude) {
        this.longitude = longitude;
        return this;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public Merchant latitude(BigDecimal latitude) {
        this.latitude = latitude;
        return this;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public Integer getConcession() {
        return concession;
    }

    public Merchant concession(Integer concession) {
        this.concession = concession;
        return this;
    }

    public void setConcession(Integer concession) {
        this.concession = concession;
    }

    public Integer getRebate() {
        return rebate;
    }

    public Merchant rebate(Integer rebate) {
        this.rebate = rebate;
        return this;
    }

    public void setRebate(Integer rebate) {
        this.rebate = rebate;
    }

    public String getBuslicenseimage() {
        return buslicenseimage;
    }

    public Merchant buslicenseimage(String buslicenseimage) {
        this.buslicenseimage = buslicenseimage;
        return this;
    }

    public void setBuslicenseimage(String buslicenseimage) {
        this.buslicenseimage = buslicenseimage;
    }

    public String getShow() {
        return show;
    }

    public Merchant show(String show) {
        this.show = show;
        return this;
    }

    public void setShow(String show) {
        this.show = show;
    }

    public String getCreditcode() {
        return creditcode;
    }

    public Merchant creditcode(String creditcode) {
        this.creditcode = creditcode;
        return this;
    }

    public void setCreditcode(String creditcode) {
        this.creditcode = creditcode;
    }

    public String getWeight() {
        return weight;
    }

    public Merchant weight(String weight) {
        this.weight = weight;
        return this;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getCreator() {
        return creator;
    }

    public Merchant creator(String creator) {
        this.creator = creator;
        return this;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreatedate() {
        return createdate;
    }

    public Merchant createdate(String createdate) {
        this.createdate = createdate;
        return this;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getModifier() {
        return modifier;
    }

    public Merchant modifier(String modifier) {
        this.modifier = modifier;
        return this;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public String getModifierdate() {
        return modifierdate;
    }

    public Merchant modifierdate(String modifierdate) {
        this.modifierdate = modifierdate;
        return this;
    }

    public void setModifierdate(String modifierdate) {
        this.modifierdate = modifierdate;
    }

    public Long getModifiernum() {
        return modifiernum;
    }

    public Merchant modifiernum(Long modifiernum) {
        this.modifiernum = modifiernum;
        return this;
    }

    public void setModifiernum(Long modifiernum) {
        this.modifiernum = modifiernum;
    }

    public Boolean isLogicdelete() {
        return logicdelete;
    }

    public Merchant logicdelete(Boolean logicdelete) {
        this.logicdelete = logicdelete;
        return this;
    }

    public void setLogicdelete(Boolean logicdelete) {
        this.logicdelete = logicdelete;
    }

    public String getOther() {
        return other;
    }

    public Merchant other(String other) {
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
        if (!(o instanceof Merchant)) {
            return false;
        }
        return id != null && id.equals(((Merchant) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Merchant{" +
            "id=" + getId() +
            ", userid='" + getUserid() + "'" +
            ", merchantphoto='" + getMerchantphoto() + "'" +
            ", name='" + getName() + "'" +
            ", businessid='" + getBusinessid() + "'" +
            ", state='" + getState() + "'" +
            ", address='" + getAddress() + "'" +
            ", province='" + getProvince() + "'" +
            ", city='" + getCity() + "'" +
            ", county='" + getCounty() + "'" +
            ", longitude=" + getLongitude() +
            ", latitude=" + getLatitude() +
            ", concession=" + getConcession() +
            ", rebate=" + getRebate() +
            ", buslicenseimage='" + getBuslicenseimage() + "'" +
            ", show='" + getShow() + "'" +
            ", creditcode='" + getCreditcode() + "'" +
            ", weight='" + getWeight() + "'" +
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
