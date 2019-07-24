package com.weisen.www.code.yjf.basic.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.weisen.www.code.yjf.basic.domain.Receiving} entity.
 */
public class ReceivingDTO implements Serializable {

    private Long id;

    private String userid;

    private String consignee;

    private String mobile;

    private String areaid;

    private String address;

    private Boolean isdefault;

    private String createtime;

    private String updatetime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
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

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
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
            ", createtime='" + getCreatetime() + "'" +
            ", updatetime='" + getUpdatetime() + "'" +
            "}";
    }
}
