package com.weisen.www.code.yjf.basic.service.rewrite.dto;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@SuppressWarnings("serial")
@ApiModel(value = "获取邀请人列表DTO")
public class Rewrite_GetIncomeListDTO implements Serializable {

	@ApiModelProperty(value = "推荐人总数", example = "50")
	private Long recommendIdCount;

	@ApiModelProperty(value = "昵称", example = "好友：136****1111")
	private String nickName;

	@ApiModelProperty(value = "创建时间", example = "2018-11-12")
    private String createdate;

	public Long getRecommendIdCount() {
		return recommendIdCount;
	}

	public void setRecommendIdCount(Long recommendIdCount) {
		this.recommendIdCount = recommendIdCount;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	
	
	

}
