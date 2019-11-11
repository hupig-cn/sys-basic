package com.weisen.www.code.yjf.basic.service.rewrite.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@SuppressWarnings("serial")
@ApiModel(value = "根据前端传来的用户id查找邀请人列表DTO")
public class Rewrite_GetIncomeAfferentDTO implements Serializable {



	@ApiModelProperty(value = "用户id", example = "51")
	private String recommendId;

	@ApiModelProperty(value = "开始时间", example = "1564329600000")
    private Long firstTime;

	@ApiModelProperty(value = "结束时间", example = "1572599940000")
    private Long lastTime;
	
	@ApiModelProperty(value = "页数", example = "0")
	private Integer pageNum;
	
	@ApiModelProperty(value = "条数", example = "10")
	private Integer pageSize;
	
	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getRecommendId() {
		return recommendId;
	}

	public void setRecommendId(String recommendId) {
		this.recommendId = recommendId;
	}

	public Long getFirstTime() {
		return firstTime;
	}

	public void setFirstTime(Long firstTime) {
		this.firstTime = firstTime;
	}

	public Long getLastTime() {
		return lastTime;
	}

	public void setLastTime(Long lastTime) {
		this.lastTime = lastTime;
	}


}
