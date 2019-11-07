package com.weisen.www.code.yjf.basic.service.rewrite.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@SuppressWarnings("serial")
@ApiModel(value = "后台查找用户列表DTO")
public class Rewrite_findByUserAccountOrSomethingDTO implements Serializable {

	@ApiModelProperty(value = "用户id", example = "5")
    private String userid;
	
	@ApiModelProperty(value = "收入支出金额", example = "12")
	private BigDecimal balance;
	
	@ApiModelProperty(value = "用户账号", example = "12345678910")
    private String phone;
	
	@ApiModelProperty(value = "创建时间", example = "2019-10-29 13:45:37")
    private String createdate;
	
	@ApiModelProperty(value = "昵称", example = "王炸")
    private String firstName;
	
	@ApiModelProperty(value = "真名", example = "王毛炸")
    private String realname;
	
	@ApiModelProperty(value = "最后登录时间", example = "2019-10-30 13:45:37")
    private Instant lastModifiedDate;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public Instant getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Instant lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	
	
}
