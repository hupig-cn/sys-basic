package com.weisen.www.code.yjf.basic.service.dto.submit_dto;
import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class Rewrite_GoodsCommity2DTO implements Serializable {
   private String specifications;

   private String title;

   private String price;

   private List<Rewrite_GoodsCommityDTO> hplist;

   private List<Rewrite_GoodsCommityDTO> splist;

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
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
