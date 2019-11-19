package com.weisen.www.code.yjf.basic.service.rewrite.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@SuppressWarnings("serial")
@ApiModel(value = "用户优惠券DTO")
public class Rewrite_CouponDTO implements Serializable {

	@ApiModelProperty(value = "用户单笔支出收入优惠券")
	private BigDecimal amount;

	@ApiModelProperty(value = "用户单笔支出收入优惠券时间")
	private String createDate;

	@ApiModelProperty(value = "用户单笔支出收入优惠券详细说明")
	private String explain;

	@ApiModelProperty(value = "用户支出、收入优惠券0=支出,1=收入", example = "1")
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

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
