package com.weisen.www.code.yjf.basic.service.rewrite.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@SuppressWarnings("serial")
@ApiModel(value = "查询商家用户可用资金和活动资金DTO")
public class Rewrite_ActivitySerDTO implements Serializable {

	@ApiModelProperty(value = "商家用户ID")
	private String userId;

	@ApiModelProperty(value = "商家活动资金")
	private BigDecimal activityAmo;
	
	@ApiModelProperty(value = "商家可用资金")
	private BigDecimal availableAmo;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public BigDecimal getAvailableAmo() {
		return availableAmo;
	}

	public void setAvailableAmo(BigDecimal availableAmo) {
		this.availableAmo = availableAmo;
	}

	public BigDecimal getActivityAmo() {
		return activityAmo;
	}

	public void setActivityAmo(BigDecimal activityAmo) {
		this.activityAmo = activityAmo;
	}
}
