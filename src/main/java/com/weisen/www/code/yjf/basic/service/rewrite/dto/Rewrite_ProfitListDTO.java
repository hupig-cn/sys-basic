package com.weisen.www.code.yjf.basic.service.rewrite.dto;

import java.io.Serializable;
import java.math.BigDecimal;


import io.swagger.annotations.ApiModel;


@SuppressWarnings("serial")
@ApiModel(value = "收益列表DTO")
public class Rewrite_ProfitListDTO implements Serializable {

    private BigDecimal amount;
    
    private BigDecimal allAmount;

	public BigDecimal getAllAmount() {
		return allAmount;
	}

	public void setAllAmount(BigDecimal allAmount) {
		this.allAmount = allAmount;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
}
