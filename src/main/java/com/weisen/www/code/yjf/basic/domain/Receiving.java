package com.weisen.www.code.yjf.basic.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Receiving.
 */
@Entity
@Table(name = "receiving")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Receiving implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userid")
    private String userid;

    @Column(name = "consignee")
    private String consignee;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "areaid")
    private String areaid;

    @Column(name = "address")
    private String address;

    @Column(name = "isdefault")
    private Boolean isdefault;

    @Column(name = "createtime")
    private String createtime;

    @Column(name = "updatetime")
    private String updatetime;

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

    public Receiving userid(String userid) {
        this.userid = userid;
        return this;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getConsignee() {
        return consignee;
    }

    public Receiving consignee(String consignee) {
        this.consignee = consignee;
        return this;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getMobile() {
        return mobile;
    }

    public Receiving mobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAreaid() {
        return areaid;
    }

    public Receiving areaid(String areaid) {
        this.areaid = areaid;
        return this;
    }

    public void setAreaid(String areaid) {
        this.areaid = areaid;
    }

    public String getAddress() {
        return address;
    }

    public Receiving address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean isIsdefault() {
        return isdefault;
    }

    public Receiving isdefault(Boolean isdefault) {
        this.isdefault = isdefault;
        return this;
    }

    public void setIsdefault(Boolean isdefault) {
        this.isdefault = isdefault;
    }

    public String getCreatetime() {
        return createtime;
    }

    public Receiving createtime(String createtime) {
        this.createtime = createtime;
        return this;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public Receiving updatetime(String updatetime) {
        this.updatetime = updatetime;
        return this;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Receiving)) {
            return false;
        }
        return id != null && id.equals(((Receiving) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Receiving{" +
            "id=" + getId() +
            ", userid='" + getUserid() + "'" +
            ", consignee='" + getConsignee() + "'" +
            ", mobile='" + getMobile() + "'" +
            ", areaid='" + getAreaid() + "'" +
            ", address='" + getAddress() + "'" +
            ", isdefault='" + isIsdefault() + "'" +
            ", createtime='" + getCreatetime() + "'" +
            ", updatetime='" + getUpdatetime() + "'" +
            "}";
    }
}
