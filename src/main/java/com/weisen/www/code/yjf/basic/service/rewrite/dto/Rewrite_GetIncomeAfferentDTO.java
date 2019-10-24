package com.weisen.www.code.yjf.basic.service.rewrite.dto;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@SuppressWarnings("serial")
@ApiModel(value = "根据前端传来的用户id查找邀请人列表DTO")
public class Rewrite_GetIncomeAfferentDTO implements Serializable {

	@ApiModelProperty(value = "用户id", example = "50")
	private String recommendId;

	@ApiModelProperty(value = "开始时间", example = "2018-11-12")
    private String firstTime;

	@ApiModelProperty(value = "结束时间", example = "2018-11-12")
    private String lastTime;

	public String getRecommendId() {
		return recommendId;
	}

	public void setRecommendId(String recommendId) {
		this.recommendId = recommendId;
	}

	public String getFirstTime() {
		return firstTime;
	}

	public void setFirstTime(String firstTime) {
		this.firstTime = firstTime;
	}

	public String getLastTime() {
		return lastTime;
	}

	public void setLastTime(String lastTime) {
		this.lastTime = lastTime;
	}


}
