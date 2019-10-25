package com.weisen.www.code.yjf.basic.service.rewrite.dto;

import java.io.Serializable;
import java.math.BigDecimal;


import io.swagger.annotations.ApiModel;


@SuppressWarnings("serial")
@ApiModel(value = "收益列表DTO")
public class Rewrite_ProfitListDTO implements Serializable {

    private BigDecimal amount;

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
}
