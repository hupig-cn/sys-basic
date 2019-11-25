package com.weisen.www.code.yjf.basic.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Commodity.
 */
@Entity
@Table(name = "commodity")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Commodity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "brandid")
    private String brandid;

    @Column(name = "classificationid")
    private String classificationid;

    @Column(name = "commoditystate")
    private String commoditystate;

    @Column(name = "postage")
    private String postage;

    @Column(name = "salevalue")
    private String salevalue;

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

    @Column(name = "sub_title")
    private String subTitle;

    @Column(name = "store_alert")
    private String storeAlert;

    @Column(name = "quota")
    private Integer quota;

    @Column(name = "weight2")
    private Boolean weight2;

    @Column(name = "weight3")
    private Boolean weight3;

    @Column(name = "feature")
    private String feature;

    @Column(name = "services")
    private String services;

    @Column(name = "remark")
    private String remark;

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getStoreAlert() {
        return storeAlert;
    }

    public void setStoreAlert(String storeAlert) {
        this.storeAlert = storeAlert;
    }

    public Integer getQuota() {
        return quota;
    }

    public void setQuota(Integer quota) {
        this.quota = quota;
    }

    public Boolean getWeight2() {
        return weight2;
    }

    public void setWeight2(Boolean weight2) {
        this.weight2 = weight2;
    }

    public Boolean getWeight3() {
        return weight3;
    }

    public void setWeight3(Boolean weight3) {
        this.weight3 = weight3;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Commodity name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrandid() {
        return brandid;
    }

    public Commodity brandid(String brandid) {
        this.brandid = brandid;
        return this;
    }

    public void setBrandid(String brandid) {
        this.brandid = brandid;
    }

    public String getClassificationid() {
        return classificationid;
    }

    public Commodity classificationid(String classificationid) {
        this.classificationid = classificationid;
        return this;
    }

    public void setClassificationid(String classificationid) {
        this.classificationid = classificationid;
    }

    public String getCommoditystate() {
        return commoditystate;
    }

    public Commodity commoditystate(String commoditystate) {
        this.commoditystate = commoditystate;
        return this;
    }

    public void setCommoditystate(String commoditystate) {
        this.commoditystate = commoditystate;
    }

    public String getPostage() {
        return postage;
    }

    public Commodity postage(String postage) {
        this.postage = postage;
        return this;
    }

    public void setPostage(String postage) {
        this.postage = postage;
    }

    public String getSalevalue() {
        return salevalue;
    }

    public Commodity salevalue(String salevalue) {
        this.salevalue = salevalue;
        return this;
    }

    public void setSalevalue(String salevalue) {
        this.salevalue = salevalue;
    }

    public String getWeight() {
        return weight;
    }

    public Commodity weight(String weight) {
        this.weight = weight;
        return this;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getCreator() {
        return creator;
    }

    public Commodity creator(String creator) {
        this.creator = creator;
        return this;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreatedate() {
        return createdate;
    }

    public Commodity createdate(String createdate) {
        this.createdate = createdate;
        return this;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getModifier() {
        return modifier;
    }

    public Commodity modifier(String modifier) {
        this.modifier = modifier;
        return this;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public String getModifierdate() {
        return modifierdate;
    }

    public Commodity modifierdate(String modifierdate) {
        this.modifierdate = modifierdate;
        return this;
    }

    public void setModifierdate(String modifierdate) {
        this.modifierdate = modifierdate;
    }

    public Long getModifiernum() {
        return modifiernum;
    }

    public Commodity modifiernum(Long modifiernum) {
        this.modifiernum = modifiernum;
        return this;
    }

    public void setModifiernum(Long modifiernum) {
        this.modifiernum = modifiernum;
    }

    public Boolean isLogicdelete() {
        return logicdelete;
    }

    public Commodity logicdelete(Boolean logicdelete) {
        this.logicdelete = logicdelete;
        return this;
    }

    public void setLogicdelete(Boolean logicdelete) {
        this.logicdelete = logicdelete;
    }

    public String getOther() {
        return other;
    }

    public Commodity other(String other) {
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
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Commodity commodity = (Commodity) o;
        if (commodity.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), commodity.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Commodity{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", brandid='" + getBrandid() + "'" +
            ", classificationid='" + getClassificationid() + "'" +
            ", commoditystate='" + getCommoditystate() + "'" +
            ", postage='" + getPostage() + "'" +
            ", salevalue='" + getSalevalue() + "'" +
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
