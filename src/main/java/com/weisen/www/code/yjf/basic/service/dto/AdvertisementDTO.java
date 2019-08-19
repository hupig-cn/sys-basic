package com.weisen.www.code.yjf.basic.service.dto;
import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.weisen.www.code.yjf.basic.domain.Advertisement} entity.
 */
@SuppressWarnings("serial")
public class AdvertisementDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    private String introduction;

    private String pictureFormat;

    private String pictureLink;

    private Integer sort;

    private String link;

    private Integer linkType;

    private Integer type;

    @NotNull
    @Max(value = 1)
    private Integer state;

    @NotNull
    private Instant createdDate;

    @NotNull
    private Instant lastModifiedDate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getPictureFormat() {
        return pictureFormat;
    }

    public void setPictureFormat(String pictureFormat) {
        this.pictureFormat = pictureFormat;
    }

    public String getPictureLink() {
        return pictureLink;
    }

    public void setPictureLink(String pictureLink) {
        this.pictureLink = pictureLink;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Integer getLinkType() {
        return linkType;
    }

    public void setLinkType(Integer linkType) {
        this.linkType = linkType;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AdvertisementDTO advertisementDTO = (AdvertisementDTO) o;
        if (advertisementDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), advertisementDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AdvertisementDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", introduction='" + getIntroduction() + "'" +
            ", pictureFormat='" + getPictureFormat() + "'" +
            ", pictureLink='" + getPictureLink() + "'" +
            ", sort=" + getSort() +
            ", link='" + getLink() + "'" +
            ", linkType=" + getLinkType() +
            ", type=" + getType() +
            ", state=" + getState() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            "}";
    }
}
