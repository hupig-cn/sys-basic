package com.weisen.www.code.yjf.basic.service.rewrite.dto;

import com.weisen.www.code.yjf.basic.service.dto.ReceivingDTO;

import java.io.Serializable;
import java.util.Objects;

@SuppressWarnings("serial")
public class Rewrite_ReceivingDTO implements Serializable {

    private Long id;

    private Long userid;

    private String consignee;

    private String mobile;

    private String address;

    private Boolean isdefault;

    private String areaid;

    private String areaName;

    private String cityId;

    private String city;

    private String provinceId;

    private String province;

    public Boolean getIsdefault() {
        return isdefault;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAreaid() {
        return areaid;
    }

    public void setAreaid(String areaid) {
        this.areaid = areaid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean isIsdefault() {
        return isdefault;
    }

    public void setIsdefault(Boolean isdefault) {
        this.isdefault = isdefault;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ReceivingDTO receivingDTO = (ReceivingDTO) o;
        if (receivingDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), receivingDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ReceivingDTO{" +
            "id=" + getId() +
            ", userid='" + getUserid() + "'" +
            ", consignee='" + getConsignee() + "'" +
            ", mobile='" + getMobile() + "'" +
            ", areaid='" + getAreaid() + "'" +
            ", address='" + getAddress() + "'" +
            ", isdefault='" + isIsdefault() + "'" +
            "}";
    }
}
