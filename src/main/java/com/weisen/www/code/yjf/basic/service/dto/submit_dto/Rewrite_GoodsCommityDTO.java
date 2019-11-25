package com.weisen.www.code.yjf.basic.service.dto.submit_dto;
import java.io.Serializable;

@SuppressWarnings("serial")
public class Rewrite_GoodsCommityDTO implements Serializable {
   private String url;

   private Integer width;

   private Integer height;

   private Integer size;

   private String type;

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
