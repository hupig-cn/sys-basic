package com.weisen.www.code.yjf.basic.service.dto.show_dto;

import com.weisen.www.code.yjf.basic.domain.Receiptpay;

import java.util.List;

public class Rewrite_MerchantShow<T> {

    private String time;

    private List<T> list;

    private Receiptpay singleClass;

    public Rewrite_MerchantShow(){

    }

    public Receiptpay getSingleClass() {
        return singleClass;
    }

    public void setSingleClass(Receiptpay singleClass) {
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
