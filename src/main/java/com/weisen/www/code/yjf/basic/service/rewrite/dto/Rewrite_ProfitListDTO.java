package com.weisen.www.code.yjf.basic.service.rewrite.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;


@SuppressWarnings("serial")
@ApiModel(value = "收益列表DTO")
public class Rewrite_ProfitListDTO implements Serializable {
	
    public Rewrite_ProfitListDTO() {
    }

    private String earn;

    private String date;

	public String getEarn() {
		return earn;
	}

	public void setEarn(String earn) {
		this.earn = earn;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}



}
