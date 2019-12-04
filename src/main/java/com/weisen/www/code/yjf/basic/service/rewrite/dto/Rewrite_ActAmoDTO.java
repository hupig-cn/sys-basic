package com.weisen.www.code.yjf.basic.service.rewrite.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@SuppressWarnings("serial")
@ApiModel(value = "查询活动流水DTO")
public class Rewrite_ActAmoDTO implements Serializable {

	@ApiModelProperty(value = "用户昵称")
	private String userNickName;
	
	@ApiModelProperty(value = "用户头像url")
	private String userUrl;

	@ApiModelProperty(value = "付款金额")
	private BigDecimal paymentAmount;
	
	@ApiModelProperty(value = "转化金额")
	private BigDecimal transFormAmount;

	@ApiModelProperty(value = "流水时间")
	private String Time;
	
	public String getUserNickName() {
		return userNickName;
	}

	public void setUserNickName(String userNickName) {
		this.userNickName = userNickName;
	}


	public String getUserUrl() {
		return userUrl;
	}

	public void setUserUrl(String userUrl) {
		this.userUrl = userUrl;
	}

	public BigDecimal getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(BigDecimal paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public BigDecimal getTransFormAmount() {
		return transFormAmount;
	}

	public void setTransFormAmount(BigDecimal transFormAmount) {
		this.transFormAmount = transFormAmount;
	}


	public String getTime() {
		return Time;
	}

	public void setTime(String time) {
		Time = time;
	}

}
