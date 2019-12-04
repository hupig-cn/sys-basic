package com.weisen.www.code.yjf.basic.service.dto.submit_dto;

import com.weisen.www.code.yjf.basic.domain.Advertisement;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;

/**
 * @Author: 阮铭辉
 * @Date: 2019/12/4 17:12
 */
@SuppressWarnings("serial")
@ApiModel(value="广告轮播DTO")
public class Rewrite_AdvertisingDTO implements Serializable {

    private Integer width;

    private Integer height;

    private Advertisement advertisement;

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Advertisement getAdvertisement() {
        return advertisement;
    }

    public void setAdvertisement(Advertisement advertisement) {
        this.advertisement = advertisement;
    }
}
