package com.weisen.www.code.yjf.basic.service.rewrite.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@SuppressWarnings("serial")
@ApiModel(value = "活动服务提现DTO")
public class Rewrite_ActivityPayDTO implements Serializable {

	@ApiModelProperty(value = "商家用户ID")
	private String userId;

	@ApiModelProperty(value = "未提现的可用资金")
	private BigDecimal transformationAmo;

	@ApiModelProperty(value = "流水类型")
	private Integer type;

	@ApiModelProperty(value = "提现时间")
	private String createDate;

	@ApiModelProperty(value = "单笔提现金额")
	private BigDecimal incomeAmo;

	@ApiModelProperty(value = "用户单笔可用资金提现详细说明")
	private String explain;

	@ApiModelProperty(value = "用户支出、收入积分0=支出,1=收入", example = "0")
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
