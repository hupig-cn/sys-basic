package com.weisen.www.code.yjf.basic.service.dto.submit_dto;

import java.util.Objects;

/**
 * @Author: 阮铭辉
 * @Date: 2019/10/24 10:39
 */
public class TouchBalanceDTO {

    private String Userorderid;

    private String payway;//支付方式

    private String payeeTitle;//简单内容

    private String paytime;//支付时间


    public String getPayway() {
        return payway;
    }

    public void setPayway(String payway) {
        this.payway = payway;
    }

    public String getUserorderid() {
        return Userorderid;
    }

    public void setUserorderid(String userorderid) {
        Userorderid = userorderid;
    }



    public String getPayeeTitle() {
        return payeeTitle;
    }

    public void setPayeeTitle(String payeeTitle) {
        this.payeeTitle = payeeTitle;
    }

    public String getPaytime() {
        return paytime;
    }

    public void setPaytime(String paytime) {
        this.paytime = paytime;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TouchBalanceDTO that = (TouchBalanceDTO) o;
        return Objects.equals(paytime, that.paytime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(paytime);
    }
}
