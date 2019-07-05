package com.weisen.www.code.yjf.basic.service.dto.show_dto;

import com.weisen.www.code.yjf.basic.service.dto.CouponDTO;

import java.util.List;

public class Rewrite_PriceDTO {

    private String price;

    private String balance;

    private String integral;

    private List<CouponDTO> Coupon;

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getIntegral() {
        return integral;
    }

    public void setIntegral(String integral) {
        this.integral = integral;
    }

    public List<CouponDTO> getCoupon() {
        return Coupon;
    }

    public void setCoupon(List<CouponDTO> coupon) {
        Coupon = coupon;
    }

    public Rewrite_PriceDTO(String price) {
    	this.price = price;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
