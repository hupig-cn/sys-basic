package com.weisen.www.code.yjf.basic.service.dto;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @author shenxiaoxin
 * @date 2019-10-15 14:56:30
 *
 */
@SuppressWarnings("serial")
@ApiModel(value = "传入版本信息和客户端类型信息")
public class AppControlUpdateDTO implements Serializable {

	@ApiModelProperty(value = "客户端类型   1:Android 2:IOS ", example = "1")
	private Integer clientType;

	@ApiModelProperty(value = "客户端版本", example = "1.1.5")
	private String clientVersion;
	
	
	public Integer getClientType() {
		return clientType;
	}
	
	public void setClientType(Integer clientType) {
		this.clientType = clientType;
	}
	
	public String getClientVersion() {
		return clientVersion;
	}
	
	public void setClientVersion(String clientVersion) {
		this.clientVersion = clientVersion;
	}
}
