package com.weisen.www.code.yjf.basic.service.rewrite.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@SuppressWarnings("serial")
@ApiModel(value = "根据类型查找用户资料DTO")
public class Rewrite_UserInformationListDTO implements Serializable {

	@ApiModelProperty(value = "用户类型", example = "支付宝")
	private String accounttype;
	
	@ApiModelProperty(value = "用户编号", example = "1111")
	private String login;

	@ApiModelProperty(value = "创建时间", example = "2015-10-10")
	private String createdate;

	public String getAccounttype() {
		return accounttype;
	}

	public void setAccounttype(String accounttype) {
		this.accounttype = accounttype;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}


}
