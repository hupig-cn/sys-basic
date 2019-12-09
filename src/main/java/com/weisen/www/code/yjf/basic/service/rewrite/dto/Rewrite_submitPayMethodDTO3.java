package com.weisen.www.code.yjf.basic.service.rewrite.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Rewrite_submitPayMethodDTO3 implements Serializable {
    private String title;

    private String filesid;

    private Integer width;

    private Integer height;

    private Integer size;

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFilesid() {
        return filesid;
    }

    public void setFilesid(String filesid) {
        this.filesid = filesid;
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

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
