package com.weisen.www.code.yjf.basic.service.dto.submit_dto;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@SuppressWarnings("serial")
public class Rewrite_Commity2DTO implements Serializable {

   private Long commodityId;

   private String text;

   private String price;

   private String url;

   private Integer width;

   private Integer height;

    public Long getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Long commodityId) {
        this.commodityId = commodityId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

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
}
