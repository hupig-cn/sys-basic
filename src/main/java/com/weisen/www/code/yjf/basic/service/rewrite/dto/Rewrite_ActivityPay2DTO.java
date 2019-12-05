package com.weisen.www.code.yjf.basic.service.rewrite.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

@SuppressWarnings("serial")
@ApiModel(value = "活动服务提现2DTO")
public class Rewrite_ActivityPay2DTO implements Serializable {

	@ApiModelProperty(value = "商家用户ID")
	private String userId;

	@ApiModelProperty(value = "可用金额")
	private BigDecimal transformationAmo;

	@ApiModelProperty(value = "流水类型")
	private Integer type;

	@ApiModelProperty(value = "转化时间")
	private String createDate;

	@ApiModelProperty(value = "活动金额")
	private BigDecimal incomeAmo;

	@ApiModelProperty(value = "用户转换金额详细说明")
	private String explain;

	@ApiModelProperty(value = "用户活动资金转为可用金额", example = "0")
	private Integer status;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public BigDecimal getTransformationAmo() {
		return transformationAmo;
	}

	public void setTransformationAmo(BigDecimal transformationAmo) {
		this.transformationAmo = transformationAmo;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public BigDecimal getIncomeAmo() {
		return incomeAmo;
	}

	public void setIncomeAmo(BigDecimal incomeAmo) {
		this.incomeAmo = incomeAmo;
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
