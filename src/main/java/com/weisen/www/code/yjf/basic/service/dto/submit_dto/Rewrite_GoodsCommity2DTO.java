package com.weisen.www.code.yjf.basic.service.dto.submit_dto;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class Rewrite_GoodsCommity2DTO implements Serializable {

    private Long hWidth;

    private Long hHeigh;

    private Long sWidth;

    private Long sHeigh;

    private String commodityId;

    private String title;

    private String price;

    private String model;

    private List<Rewrite_GoodsCommityDTO> hplist;

    private List<Rewrite_GoodsCommityDTO> splist;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Long gethWidth() {
        return hWidth;
    }

    public void sethWidth(Long hWidth) {
        this.hWidth = hWidth;
    }

    public Long gethHeigh() {
        return hHeigh;
    }

    public void sethHeigh(Long hHeigh) {
        this.hHeigh = hHeigh;
    }

    public Long getsWidth() {
        return sWidth;
    }

    public void setsWidth(Long sWidth) {
        this.sWidth = sWidth;
    }

    public Long getsHeigh() {
        return sHeigh;
    }

    public void setsHeigh(Long sHeigh) {
        this.sHeigh = sHeigh;
    }

    public String getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<Rewrite_GoodsCommityDTO> getHplist() {
        return hplist;
    }

    public void setHplist(List<Rewrite_GoodsCommityDTO> hplist) {
        this.hplist = hplist;
    }

    public List<Rewrite_GoodsCommityDTO> getSplist() {
        return splist;
    }

    public void setSplist(List<Rewrite_GoodsCommityDTO> splist) {
        this.splist = splist;
    }
}
