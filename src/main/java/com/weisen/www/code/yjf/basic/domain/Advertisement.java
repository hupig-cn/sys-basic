package com.weisen.www.code.yjf.basic.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A Advertisement.
 */
@Entity
@Table(name = "advertisement")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Advertisement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "introduction")
    private String introduction;

    @Column(name = "picture_format")
    private String pictureFormat;

    @Column(name = "picture_link")
    private String pictureLink;

    @Column(name = "jhi_sort")
    private Integer sort;

    @Column(name = "jhi_link")
    private String link;

    @Column(name = "link_type")
    private Integer linkType;

    @Column(name = "jhi_type")
    private Integer type;

    @NotNull
    @Max(value = 1)
    @Column(name = "state", nullable = false)
    private Integer state;

    @NotNull
    @Column(name = "created_date", nullable = false)
    private Instant createdDate;

    @NotNull
    @Column(name = "last_modified_date", nullable = false)
    private Instant lastModifiedDate;

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

    public Advertisement name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduction() {
        return introduction;
    }

    public Advertisement introduction(String introduction) {
        this.introduction = introduction;
        return this;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getPictureFormat() {
        return pictureFormat;
    }

    public Advertisement pictureFormat(String pictureFormat) {
        this.pictureFormat = pictureFormat;
        return this;
    }

    public void setPictureFormat(String pictureFormat) {
        this.pictureFormat = pictureFormat;
    }

    public String getPictureLink() {
        return pictureLink;
    }

    public Advertisement pictureLink(String pictureLink) {
        this.pictureLink = pictureLink;
        return this;
    }

    public void setPictureLink(String pictureLink) {
        this.pictureLink = pictureLink;
    }

    public Integer getSort() {
        return sort;
    }

    public Advertisement sort(Integer sort) {
        this.sort = sort;
        return this;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getLink() {
        return link;
    }

    public Advertisement link(String link) {
        this.link = link;
        return this;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Integer getLinkType() {
        return linkType;
    }

    public Advertisement linkType(Integer linkType) {
        this.linkType = linkType;
        return this;
    }

    public void setLinkType(Integer linkType) {
        this.linkType = linkType;
    }

    public Integer getType() {
        return type;
    }

    public Advertisement type(Integer type) {
        this.type = type;
        return this;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getState() {
        return state;
    }

    public Advertisement state(Integer state) {
        this.state = state;
        return this;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public Advertisement createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public Advertisement lastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
        return this;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Advertisement)) {
            return false;
        }
        return id != null && id.equals(((Advertisement) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Advertisement{" +
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
