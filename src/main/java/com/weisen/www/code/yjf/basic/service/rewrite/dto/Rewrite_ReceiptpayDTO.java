package com.weisen.www.code.yjf.basic.service.rewrite.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author: 阮铭辉
 * @Date: 2019/10/23 15:01
 */
public class Rewrite_ReceiptpayDTO implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String ordercode;//订单编号

    private String orderstatus;//订单状态

    private BigDecimal sum;//总价

    private String userid;//下单用户

    private String username;//下单用户姓名

    private String payee;//收款人

    private String payeeName;//收款人姓名

    private String payway;//支付方式

    private String payresult;//支付结果

    private String paytime;//支付时间

    private Long stats;// 0 为减少 1 为增加

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

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    public String getPayeeName() {
        return payeeName;
    }

    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName;
    }

    public String getPayway() {
        return payway;
    }

    public void setPayway(String payway) {
        this.payway = payway;
    }

    public String getPayresult() {
        return payresult;
    }

    public void setPayresult(String payresult) {
        this.payresult = payresult;
    }

    public String getPaytime() {
        return paytime;
    }

    public void setPaytime(String paytime) {
        this.paytime = paytime;
    }

    public Long getStats() {
        return stats;
    }

    public void setStats(Long stats) {
        this.stats = stats;
    }
}
