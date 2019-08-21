package com.weisen.www.code.yjf.basic.service.dto.submit_dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@SuppressWarnings("serial")
@ApiModel(value="广告分页查询DTO")
public class Rewrite_AdvertisementPageDTO implements Serializable {

	@ApiModelProperty(value="广告名称")
    private String name;
	
	@ApiModelProperty(value="广告位置")
    private Integer type;
    
	@ApiModelProperty(value="第几页")
    private Integer pageNum;
    
	@ApiModelProperty(value="每页多少条")
    private Integer pageSize;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

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
    
}
