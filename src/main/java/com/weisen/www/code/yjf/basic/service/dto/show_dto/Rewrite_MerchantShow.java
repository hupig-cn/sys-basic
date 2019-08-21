package com.weisen.www.code.yjf.basic.service.dto.show_dto;

import java.util.List;

import com.weisen.www.code.yjf.basic.service.dto.ReceiptpayDTO;

public class Rewrite_MerchantShow<T> {

    private String time;

    private List<T> list;

    private ReceiptpayDTO singleClass;

    public Rewrite_MerchantShow(){

    }

    public ReceiptpayDTO getSingleClass() {
        return singleClass;
    }

    public void setSingleClass(ReceiptpayDTO singleClass) {
        this.singleClass = singleClass;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }


}
