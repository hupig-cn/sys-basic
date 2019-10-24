package com.weisen.www.code.yjf.basic.service.rewrite.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@SuppressWarnings("serial")
@ApiModel(value = "获取邀请人列表DTO")
public class Rewrite_GetIncomeListDTO implements Serializable {

	@ApiModelProperty(value = "昵称", example = "哈哈")
    private String firstName;

	@ApiModelProperty(value = "创建时间", example = "2018-11-12")
    private String createdate;
	
	@ApiModelProperty(value = "头像url", example = "www.baidu.com")
    private String imageUrl;
	
	@ApiModelProperty(value = "获取的红利总和", example = "6666.66")
	private BigDecimal amount;
	

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}


	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	
	
	

}
