package com.weisen.www.code.yjf.basic.service.rewrite.dto;

import com.weisen.www.code.yjf.basic.service.dto.OsversionDTO;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Osversion entity.
 */
public class Rewrite_OsversionDTO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

    private String version;

    private String loginfo;

    private String os;

    private String osversion;

    private String apkurl;

    private Integer type;

    private String picture;

    private String createdate;

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getLoginfo() {
        return loginfo;
    }

    public void setLoginfo(String loginfo) {
        this.loginfo = loginfo;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getOsversion() {
        return osversion;
    }

    public void setOsversion(String osversion) {
        this.osversion = osversion;
    }

    public String getApkurl() {
        return apkurl;
    }

    public void setApkurl(String apkurl) {
        this.apkurl = apkurl;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OsversionDTO osversionDTO = (OsversionDTO) o;
        if (osversionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), osversionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OsversionDTO{" +
            "id=" + getId() +
            ", version='" + getVersion() + "'" +
            ", loginfo='" + getLoginfo() + "'" +
            ", os='" + getOs() + "'" +
            ", osversion='" + getOsversion() + "'" +
            ", apkurl='" + getApkurl() + "'" +
            ", type=" + getType() +
            ", picture='" + getPicture() + "'" +
            "}";
    }
}
