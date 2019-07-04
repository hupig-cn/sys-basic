package com.weisen.www.code.yjf.basic.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Coderecord entity.
 */
public class CoderecordDTO implements Serializable {

    private Long id;

    private String code;

    private String type;

    private String phone;

    private String createDate;

    private String updateDate;

    private Integer num;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CoderecordDTO coderecordDTO = (CoderecordDTO) o;
        if (coderecordDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), coderecordDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CoderecordDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", type='" + getType() + "'" +
            ", phone='" + getPhone() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", updateDate='" + getUpdateDate() + "'" +
            ", num=" + getNum() +
            "}";
    }
}
