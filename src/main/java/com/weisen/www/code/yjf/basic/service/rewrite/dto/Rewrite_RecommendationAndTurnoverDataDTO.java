package com.weisen.www.code.yjf.basic.service.rewrite.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@SuppressWarnings("serial")
@ApiModel(value = "获取推荐收益和营业额数据DTO")
public class Rewrite_RecommendationAndTurnoverDataDTO implements Serializable {


	@ApiModelProperty(value = "昨天收益", example = "51.000")
	private BigDecimal yesterdayRecommendationAmountSum;

	@ApiModelProperty(value = "昨天营业额", example = "51.000")
    private BigDecimal yesterdayTurnoverAmountSum;

	@ApiModelProperty(value = "昨天收入", example = "102.000")
    private BigDecimal yesterdayAmountSum;
	
	@ApiModelProperty(value = "总资产", example = "204.000")
	private BigDecimal allAmountSum;
	
	@ApiModelProperty(value = "总营业额", example = "102.000")
	private BigDecimal allTurnoverAmountSum;
	

	public BigDecimal getYesterdayRecommendationAmountSum() {
		return yesterdayRecommendationAmountSum;
	}

	public void setYesterdayRecommendationAmountSum(BigDecimal yesterdayRecommendationAmountSum) {
		this.yesterdayRecommendationAmountSum = yesterdayRecommendationAmountSum;
	}

	public BigDecimal getYesterdayTurnoverAmountSum() {
		return yesterdayTurnoverAmountSum;
	}

	public void setYesterdayTurnoverAmountSum(BigDecimal yesterdayTurnoverAmountSum) {
		this.yesterdayTurnoverAmountSum = yesterdayTurnoverAmountSum;
	}

	public BigDecimal getYesterdayAmountSum() {
		return yesterdayAmountSum;
	}

	public void setYesterdayAmountSum(BigDecimal yesterdayAmountSum) {
		this.yesterdayAmountSum = yesterdayAmountSum;
	}

	public BigDecimal getAllAmountSum() {
		return allAmountSum;
	}

	public void setAllAmountSum(BigDecimal allAmountSum) {
		this.allAmountSum = allAmountSum;
	}

	public BigDecimal getAllTurnoverAmountSum() {
		return allTurnoverAmountSum;
	}

	public void setAllTurnoverAmountSum(BigDecimal allTurnoverAmountSum) {
		this.allTurnoverAmountSum = allTurnoverAmountSum;
	}

}
