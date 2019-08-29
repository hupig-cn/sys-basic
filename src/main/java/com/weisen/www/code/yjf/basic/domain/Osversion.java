package com.weisen.www.code.yjf.basic.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Osversion.
 */
@Entity
@Table(name = "osversion")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Osversion implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "version")
    private String version;

    @Column(name = "loginfo")
    private String loginfo;

    @Column(name = "os")
    private String os;

    @Column(name = "osversion")
    private String osversion;

    @Column(name = "apkurl")
    private String apkurl;

    @Column(name = "jhi_type")
    private Integer type;

    @Column(name = "picture")
    private String picture;

    @Size(max = 255)
    @Column(name = "createdate", length = 255)
    private String createdate;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public Osversion version(String version) {
        this.version = version;
        return this;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getLoginfo() {
        return loginfo;
    }

    public Osversion loginfo(String loginfo) {
        this.loginfo = loginfo;
        return this;
    }

    public void setLoginfo(String loginfo) {
        this.loginfo = loginfo;
    }

    public String getOs() {
        return os;
    }

    public Osversion os(String os) {
        this.os = os;
        return this;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getOsversion() {
        return osversion;
    }

    public Osversion osversion(String osversion) {
        this.osversion = osversion;
        return this;
    }

    public void setOsversion(String osversion) {
        this.osversion = osversion;
    }

    public String getApkurl() {
        return apkurl;
    }

    public Osversion apkurl(String apkurl) {
        this.apkurl = apkurl;
        return this;
    }

    public void setApkurl(String apkurl) {
        this.apkurl = apkurl;
    }

    public Integer getType() {
        return type;
    }

    public Osversion type(Integer type) {
        this.type = type;
        return this;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPicture() {
        return picture;
    }

    public Osversion picture(String picture) {
        this.picture = picture;
        return this;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getCreatedate() {
        return createdate;
    }

    public Osversion createdate(String createdate) {
        this.createdate = createdate;
        return this;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
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
        Osversion osversion = (Osversion) o;
        if (osversion.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), osversion.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Osversion{" +
            "id=" + getId() +
            ", version='" + getVersion() + "'" +
            ", loginfo='" + getLoginfo() + "'" +
            ", os='" + getOs() + "'" +
            ", osversion='" + getOsversion() + "'" +
            ", apkurl='" + getApkurl() + "'" +
            ", type=" + getType() +
            ", picture='" + getPicture() + "'" +
            ", createdate='" + getCreatedate() + "'" +
            "}";
    }
}
