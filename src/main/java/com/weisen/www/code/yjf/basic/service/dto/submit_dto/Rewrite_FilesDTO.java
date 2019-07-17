package com.weisen.www.code.yjf.basic.service.dto.submit_dto;
import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Lob;

/**
 * A DTO for the {@link com.weisen.www.code.yjf.basic.domain.Files} entity.
 */
public class Rewrite_FilesDTO implements Serializable {

    private Long id;

    private String userid;

    private String name;

    private Integer size;

    private byte[] file;

    private String fileContentType;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public String getFileContentType() {
        return fileContentType;
    }

    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Rewrite_FilesDTO filesDTO = (Rewrite_FilesDTO) o;
        if (filesDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), filesDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FilesDTO{" +
            "id=" + getId() +
            ", userid='" + getUserid() + "'" +
            ", name='" + getName() + "'" +
            ", size=" + getSize() +
            ", file='" + getFile() + "'" +
            ", fileContentType='" + getFileContentType() + "'" +
            "}";
    }
}
