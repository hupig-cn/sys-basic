package com.weisen.www.code.yjf.basic.service.rewrite.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@SuppressWarnings("serial")
@ApiModel(value = "获取邀请人列表DTO")
public class Rewrite_GetIncomeListDTO implements Serializable {

	@ApiModelProperty(value = "昵称", example = "哈哈")
    private String firstName;

	@ApiModelProperty(value = "创建时间", example = "2018-11-12")
    private String createdate;
	
	@ApiModelProperty(value = "头像url", example = "www.baidu.com")
    private String imageUrl;
	
	@ApiModelProperty(value = "获取的红利总和", example = "6666.66")
	private BigDecimal amount;
	
	@ApiModelProperty(value = "商家", example = "商家")
	private String merchant;
	
	@ApiModelProperty(value = "事业合伙人    数据库表中的字段   ： 1:是事业合伙人", example = "事业合伙人")
	private String partner;

	@ApiModelProperty(value = "会员", example = "会员")
	private String vip;
	
	public String getVip() {
		return vip;
	}

	public void setVip(String vip) {
		this.vip = vip;
	}

	public String getMerchant() {
		return merchant;
	}

	public void setMerchant(String merchant) {
		this.merchant = merchant;
	}

	public String getPartner() {
		return partner;
	}

	public void setPartner(String partner) {
		this.partner = partner;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}


	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	
	
	

}
