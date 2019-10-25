package com.weisen.www.code.yjf.basic.service.dto.submit_dto;

import java.math.BigDecimal;

/**
 * @Author: 阮铭辉
 * @Date: 2019/10/25 14:49
 */
public class operatingIncomeDTO {

    public operatingIncomeDTO() {
    }

    private BigDecimal earn;

    private String date;

    public BigDecimal getEarn() {
        return earn;
    }

    public void setEarn(BigDecimal earn) {
        this.earn = earn;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
