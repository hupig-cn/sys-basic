package com.weisen.www.code.yjf.basic.service.rewrite.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import io.swagger.annotations.ApiModel;


@SuppressWarnings("serial")
@ApiModel(value = "收益列表DTO")
public class Rewrite_ProfitListDTO implements Serializable {
	
    public Rewrite_ProfitListDTO() {
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
