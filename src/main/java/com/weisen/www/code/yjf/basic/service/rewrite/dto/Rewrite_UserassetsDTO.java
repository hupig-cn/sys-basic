package com.weisen.www.code.yjf.basic.service.rewrite.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@SuppressWarnings("serial")
@ApiModel(value = "查询用户总积分、总优惠券DTO")
public class Rewrite_UserassetsDTO implements Serializable {

	@ApiModelProperty(value = "用户优惠券coupon")
	private String coupon;

	@ApiModelProperty(value = "用户总积分integral")
	private String integral;

	public String getCoupon() {
		return coupon;
	}

	public void setCoupon(String coupon) {
		this.coupon = coupon;
	}

	public String getIntegral() {
		return integral;
	}

	public void setIntegral(String integral) {
		this.integral = integral;
	}

}
