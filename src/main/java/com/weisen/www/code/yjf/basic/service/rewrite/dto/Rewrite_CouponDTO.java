package com.weisen.www.code.yjf.basic.service.rewrite.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@SuppressWarnings("serial")
@ApiModel(value = "用户优惠券DTO")
public class Rewrite_CouponDTO implements Serializable {

	@ApiModelProperty(value = "优惠券数值", example = "1")
	private String sum;

	@ApiModelProperty(value = "优惠券类型", example = "1")
	private String couponType;

	@ApiModelProperty(value = "可以线上支付", example = "1")
	private Boolean lineon;

	@ApiModelProperty(value = "可以线下支付", example = "1")
	private Boolean lineunder;

	@ApiModelProperty(value = "可以产生积分", example = "1")
	private Boolean integral;

	@ApiModelProperty(value = "可以产生收益", example = "1")
	private Boolean profit;

	@ApiModelProperty(value = "创建者", example = "王老吉")
	private String creator;

	@ApiModelProperty(value = "创建时间", example = "2019-10-10")
	private String createDate;

	@ApiModelProperty(value = "修改者", example = "王老八")
	private String modifier;

	@ApiModelProperty(value = "修改时间", example = "2019-10-11")
	private String modifierDate;

	@ApiModelProperty(value = "修改次数", example = "1")
	private Long modifierNum;

	@ApiModelProperty(value = "逻辑删除", example = "1")
	private Boolean logicDelete;

	@ApiModelProperty(value = "备注", example = "嗯哼")
	private String other;

	public String getSum() {
		return sum;
	}

	public void setSum(String sum) {
		this.sum = sum;
	}

	public String getCouponType() {
		return couponType;
	}

	public void setCouponType(String couponType) {
		this.couponType = couponType;
	}

	public Boolean getLineon() {
		return lineon;
	}

	public void setLineon(Boolean lineon) {
		this.lineon = lineon;
	}

	public Boolean getLineunder() {
		return lineunder;
	}

	public void setLineunder(Boolean lineunder) {
		this.lineunder = lineunder;
	}

	public Boolean getIntegral() {
		return integral;
	}

	public void setIntegral(Boolean integral) {
		this.integral = integral;
	}

	public Boolean getProfit() {
		return profit;
	}

	public void setProfit(Boolean profit) {
		this.profit = profit;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public String getModifierDate() {
		return modifierDate;
	}

	public void setModifierDate(String modifierDate) {
		this.modifierDate = modifierDate;
	}

	public Long getModifierNum() {
		return modifierNum;
	}

	public void setModifierNum(Long modifierNum) {
		this.modifierNum = modifierNum;
	}

	public Boolean getLogicDelete() {
		return logicDelete;
	}

	public void setLogicDelete(Boolean logicDelete) {
		this.logicDelete = logicDelete;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}
}
