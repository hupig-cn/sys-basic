package com.weisen.www.code.yjf.basic.service.dto.submit_dto;
/**
* @author 作者:Carson
* @createDate 创建时间：2019年4月10日 下午3:00:00
*/
public class Rewrite_UserSendGoods {
	private Long orderId;
	private String expressCompany;  // 快递公司
	private String expressNo; // 快递单号
	
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public String getExpressCompany() {
		return expressCompany;
	}
	public void setExpressCompany(String expressCompany) {
		this.expressCompany = expressCompany;
	}
	public String getExpressNo() {
		return expressNo;
	}
	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
	}
	
}
