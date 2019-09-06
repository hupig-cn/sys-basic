package com.weisen.www.code.yjf.basic.service.dto.show_dto;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@SuppressWarnings("serial")
@ApiModel(value="订单列表返回DTO")
public class Rewrite_UserOrderListDTO implements Serializable {

	@ApiModelProperty(value="订单ID")
    private Long id;
	
	@ApiModelProperty(value="订单号")
    private String ordercode;
	
	@ApiModelProperty(value="订单状态")
    private String orderstatus;
    
	@ApiModelProperty(value="交易金额")
    private BigDecimal sum;

    @ApiModelProperty(value="用户ID")
    private String userid;

    @ApiModelProperty(value="支付方式")
    private String payway;

    @ApiModelProperty(value="支付时间")
    private String paytime;

    @ApiModelProperty(value="创建时间")
    private String createdate;

    @ApiModelProperty(value="商品ID")
    private String other;
    
    @ApiModelProperty(value="收货人")
    private String consignee;
    
    @ApiModelProperty(value="收货人手机号")
    private String mobile;
    
    @ApiModelProperty(value="收货人地址")
    private String address;
    
}
