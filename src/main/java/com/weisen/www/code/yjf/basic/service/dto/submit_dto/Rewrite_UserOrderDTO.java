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
