package com.weisen.www.code.yjf.basic.service.dto.submit_dto;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: 阮铭辉
 * @Date: 2019/10/30 10:11
 */
public class Rewrite_UserOrderDTO implements Serializable {

    private String userId;

    private List<Long> others;

    private List<Long> nums;

    private List<Long> ids;

    private String consignee;

    private String mobile;

    private String address;

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<Long> getOthers() {
        return others;
    }

    public void setOthers(List<Long> others) {
        this.others = others;
    }

    public List<Long> getNums() {
        return nums;
    }

    public void setNums(List<Long> nums) {
        this.nums = nums;
    }

    @Override
    public String toString() {
        return "Rewrite_UserOrderDTO{" +
            "userId='" + userId + '\'' +
            ", others=" + others +
            ", nums=" + nums +
            '}';
    }
}
