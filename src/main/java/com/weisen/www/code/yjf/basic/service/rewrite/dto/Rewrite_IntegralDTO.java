package com.weisen.www.code.yjf.basic.service.rewrite.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@SuppressWarnings("serial")
@ApiModel(value = "查询用户积分收支出DTO")
public class Rewrite_IntegralDTO implements Serializable {

	@ApiModelProperty(value = "用户单笔支出积分")
	private BigDecimal amount;

	@ApiModelProperty(value = "用户单笔支出积分时间")
	private String createDate;

	@ApiModelProperty(value = "用户单笔支出积分详细说明")
	private String other;

	@ApiModelProperty(value = "用户支出、收入积分0=支出,1=收入", example = "1")
	private Integer status;

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
