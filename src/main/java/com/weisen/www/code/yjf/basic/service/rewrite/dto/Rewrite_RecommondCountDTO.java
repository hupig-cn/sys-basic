package com.weisen.www.code.yjf.basic.service.rewrite.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@SuppressWarnings("serial")
@ApiModel(value = "获取推荐人数量信息DTO")
public class Rewrite_RecommondCountDTO implements Serializable {

	@ApiModelProperty(value = "商家数量", example = "7")
    private Long merchantCount;

	@ApiModelProperty(value = "邀请好友数量", example = "20")
    private Long allCount;
	
	@ApiModelProperty(value = "获取的红利总和", example = "6666.66")
	private BigDecimal amount;
	
	@ApiModelProperty(value = "事业合伙人数量", example = "5")
	private Long partnerCount;

	public Long getMerchantCount() {
		return merchantCount;
	}

	public void setMerchantCount(Long merchantCount) {
		this.merchantCount = merchantCount;
	}

	public Long getAllCount() {
		return allCount;
	}

	public void setAllCount(Long allCount) {
		this.allCount = allCount;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Long getPartnerCount() {
		return partnerCount;
	}

	public void setPartnerCount(Long partnerCount) {
		this.partnerCount = partnerCount;
	}


	
}
