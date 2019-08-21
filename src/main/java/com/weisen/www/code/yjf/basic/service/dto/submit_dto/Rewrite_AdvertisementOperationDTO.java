package com.weisen.www.code.yjf.basic.service.dto.submit_dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@SuppressWarnings("serial")
@ApiModel(value="广告操作DTO")
public class Rewrite_AdvertisementOperationDTO implements Serializable {
	
	@ApiModelProperty(value="广告id")
    private Long id;
	
	@ApiModelProperty(value="广告批量id")
    private String ids;
    
	@ApiModelProperty(value="操作")
    private Integer operation;
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public Integer getOperation() {
		return operation;
	}

	public void setOperation(Integer operation) {
		this.operation = operation;
	}
    
}
