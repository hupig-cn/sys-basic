package com.weisen.www.code.yjf.basic.service.dto.submit_dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@SuppressWarnings("serial")
@ApiModel(value="订单列表查询DTO")
public class Rewrite_UserOrderPage implements Serializable {

	@ApiModelProperty(value="用户ID")
    private String userid;
	
	@ApiModelProperty(value="订单号")
    private String ordercode;
	
	@ApiModelProperty(value="订单状态")
    private String orderstatus;
	
	@ApiModelProperty(value="订单创建时间")
	private String createStartTime;
	
	@ApiModelProperty(value="订单创建时间")
	private String createEndTime;
    
	@ApiModelProperty(value="第几页")
    private Integer pageNum;
    
	@ApiModelProperty(value="每页多少条")
    private Integer pageSize;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getOrdercode() {
		return ordercode;
	}

	public void setOrdercode(String ordercode) {
		this.ordercode = ordercode;
	}

	public String getOrderstatus() {
		return orderstatus;
	}

	public void setOrderstatus(String orderstatus) {
		this.orderstatus = orderstatus;
	}

	public String getCreateStartTime() {
		return createStartTime;
	}

	public void setCreateStartTime(String createStartTime) {
		this.createStartTime = createStartTime;
	}

	public String getCreateEndTime() {
		return createEndTime;
	}

	public void setCreateEndTime(String createEndTime) {
		this.createEndTime = createEndTime;
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
