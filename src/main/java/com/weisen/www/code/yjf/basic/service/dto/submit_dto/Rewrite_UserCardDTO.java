package com.weisen.www.code.yjf.basic.service.dto.submit_dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@SuppressWarnings("serial")
@ApiModel(value = "重写用户添加银行卡DTO")
public class Rewrite_UserCardDTO implements Serializable {

	@ApiModelProperty(value = "银行卡列表ID")
	private Long id;

	@ApiModelProperty(value = "用户userid")
	private String userid;

	@ApiModelProperty(value = "持卡人姓名")
	private String realname;

	@ApiModelProperty(value = "银行卡号")
	private String bankcard;

	@ApiModelProperty(value = "银行预留手机号")
	private String bankphone;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getBankcard() {
		return bankcard;
	}

	public void setBankcard(String bankcard) {
		this.bankcard = bankcard;
	}

	public String getBankphone() {
		return bankphone;
	}

	public void setBankphone(String bankphone) {
		this.bankphone = bankphone;
	}

}
