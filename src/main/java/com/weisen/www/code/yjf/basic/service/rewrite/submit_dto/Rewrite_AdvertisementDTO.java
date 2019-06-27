package com.weisen.www.code.yjf.basic.service.rewrite.submit_dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Objects;

@ApiModel(value="广告DTO")
public class Rewrite_AdvertisementDTO implements Serializable {

	@ApiModelProperty(value="广告id")
    private Long id;

    @ApiModelProperty(value="广告名称")
    private String name;

    @ApiModelProperty(value="介绍说明")
    private String introduction;

    @ApiModelProperty(value="图片格式")
    private String pictureFormat;

    @ApiModelProperty(value="图片链接")
    private String pictureLink;

    @ApiModelProperty(value="跳转地址")
    private String link;

    @ApiModelProperty(value="跳转类型")
    private Integer linkType;

    @ApiModelProperty(value="广告类型")
    private Integer type;

    @ApiModelProperty(value="显示顺序")
    private Integer sort;

    @ApiModelProperty(value="广告状态")
    private Integer state;

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

	public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        com.weisen.www.code.yjf.basic.service.dto.submit_dto.Rewrite_AdvertisementDTO advertisementDTO = (com.weisen.www.code.yjf.basic.service.dto.submit_dto.Rewrite_AdvertisementDTO) o;
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
            ", state=" + getState() +
            "}";
    }
}
