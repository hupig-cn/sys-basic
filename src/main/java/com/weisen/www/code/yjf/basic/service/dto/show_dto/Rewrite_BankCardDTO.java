package com.weisen.www.code.yjf.basic.service.dto.show_dto;

public class Rewrite_BankCardDTO {

    private Long id;

    private String bankname;

    private String banknum;

    private String bankuser;

    private String logo;

    public Rewrite_BankCardDTO(){}


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

    public String getBanknum() {
        return banknum;
    }

    public void setBanknum(String banknum) {
        this.banknum = banknum;
    }

    public String getBankuser() {
        return bankuser;
    }

    public void setBankuser(String bankuser) {
        this.bankuser = bankuser;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
