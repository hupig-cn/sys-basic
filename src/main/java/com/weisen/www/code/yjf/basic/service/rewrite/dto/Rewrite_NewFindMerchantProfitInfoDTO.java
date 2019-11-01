package com.weisen.www.code.yjf.basic.service.rewrite.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@SuppressWarnings("serial")
@ApiModel(value = "查询用户商家端收益列表倒叙(重写)DTO")
public class Rewrite_NewFindMerchantProfitInfoDTO implements Serializable {

	@ApiModelProperty(value = "交易类型")
	private String dealtype;

	@ApiModelProperty(value = "用户id")
	private String userid;

	@ApiModelProperty(value = "来源")
	private String sourcer;

	@ApiModelProperty(value = "受益")
	private String benefit;

	@ApiModelProperty(value = "支付方式")
	private String payway;

	@ApiModelProperty(value = "交易金额")
	private BigDecimal amount;

	@ApiModelProperty(value = "红利")
	private BigDecimal bonus;

	@ApiModelProperty(value = "产生时间")
	private String happendate;

	@ApiModelProperty(value = "冻结时间")
	private String freezedate;

	@ApiModelProperty(value = "交易状态")
	private String dealstate;

	@ApiModelProperty(value = "创建者")
	private String creator;

	@ApiModelProperty(value = "创建时间")
	private String createdate;

	@ApiModelProperty(value = "修改者")
	private String modifier;

	@ApiModelProperty(value = "修改日期")
	private String modifierdate;

	@ApiModelProperty(value = "修改次数")
	private Long modifiernum;

	@ApiModelProperty(value = "删除")
	private Boolean logicdelete;

	@ApiModelProperty(value = "备注")
	private String other;

	public String getDealtype() {
		return dealtype;
	}

	public void setDealtype(String dealtype) {
		this.dealtype = dealtype;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getSourcer() {
		return sourcer;
	}

	public void setSourcer(String sourcer) {
		this.sourcer = sourcer;
	}

	public String getBenefit() {
		return benefit;
	}

	public void setBenefit(String benefit) {
		this.benefit = benefit;
	}

	public String getPayway() {
		return payway;
	}

	public void setPayway(String payway) {
		this.payway = payway;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getBonus() {
		return bonus;
	}

	public void setBonus(BigDecimal bonus) {
		this.bonus = bonus;
	}

	public String getHappendate() {
		return happendate;
	}

	public void setHappendate(String happendate) {
		this.happendate = happendate;
	}

	public String getFreezedate() {
		return freezedate;
	}

	public void setFreezedate(String freezedate) {
		this.freezedate = freezedate;
	}

	public String getDealstate() {
		return dealstate;
	}

	public void setDealstate(String dealstate) {
		this.dealstate = dealstate;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public String getModifierdate() {
		return modifierdate;
	}

	public void setModifierdate(String modifierdate) {
		this.modifierdate = modifierdate;
	}

	public Long getModifiernum() {
		return modifiernum;
	}

	public void setModifiernum(Long modifiernum) {
		this.modifiernum = modifiernum;
	}

	public Boolean getLogicdelete() {
		return logicdelete;
	}

	public void setLogicdelete(Boolean logicdelete) {
		this.logicdelete = logicdelete;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}



}
