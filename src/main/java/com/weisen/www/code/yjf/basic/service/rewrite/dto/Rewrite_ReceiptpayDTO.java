package com.weisen.www.code.yjf.basic.service.rewrite.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * @Author: 阮铭辉
 * @Date: 2019/10/23 15:01
 */
public class Rewrite_ReceiptpayDTO implements Serializable {

    private BigDecimal amount;//交易金额

    private String sourcer;//来源id

    private String dealtype;//交易类型

    private String happendate;//产生时间

    private String userid;//我方账户id

    private String sourcername;//对方姓名

    private String payway;//支付方式

    private String Id;
    
    private Long stats;// 0 为减少 1 为增加
   

    public Long getStats() {
		return stats;
	}

	public void setStats(Long stats) {
		this.stats = stats;
	}

	public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getSourcer() {
        return sourcer;
    }

    public void setSourcer(String sourcer) {
        this.sourcer = sourcer;
    }

    public String getDealtype() {
        return dealtype;
    }

    public void setDealtype(String dealtype) {
        this.dealtype = dealtype;
    }

    public String getHappendate() {
        return happendate;
    }

    public void setHappendate(String happendate) {
        this.happendate = happendate;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getSourcername() {
        return sourcername;
    }

    public void setSourcername(String sourcername) {
        this.sourcername = sourcername;
    }

    public String getPayway() {
        return payway;
    }

    public void setPayway(String payway) {
        this.payway = payway;
    }

    @Override
    public String toString() {
        return "Rewrite_ReceiptpayDTO{" +
            "happendate='" + happendate + '\'' +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rewrite_ReceiptpayDTO that = (Rewrite_ReceiptpayDTO) o;
        return Objects.equals(Id, that.Id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(Id);
    }
}
