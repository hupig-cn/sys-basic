package com.weisen.www.code.yjf.basic.service.rewrite.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import io.swagger.annotations.ApiModel;


@SuppressWarnings("serial")
@ApiModel(value = "收益列表DTO")
public class Rewrite_ProfitListDTO implements Serializable {

    private List<BigDecimal> amount;
    
    private BigDecimal allAmount;

	public BigDecimal getAllAmount() {
		return allAmount;
	}

	public void setAllAmount(BigDecimal allAmount) {
		this.allAmount = allAmount;
	}

	public List<BigDecimal> getAmount() {
		return amount;
	}

	public void setAmount(List<BigDecimal> amount) {
		this.amount = amount;
	}


}
