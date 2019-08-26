package com.weisen.www.code.yjf.basic.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Userbankcard entity.
 */
public class UserbankcardDTO implements Serializable {

    private Long id;

    private String userid;

    private String realname;

    private String bankcard;

    private String banktype;

    private String cardtype;

    private String bankicon;

    private String bankphone;

    private String createdate;

    private String modifier;

    private String modifierdate;

    private Long modifiernum;

    private String state;

    private Boolean logicdelete;

    private String other;

    private String bankcity;

    @Size(max = 255)
    private String banksubbranch;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getBankcard() {
        return bankcard;
    }

    public void setBankcard(String bankcard) {
        this.bankcard = bankcard;
    }

    public String getBanktype() {
        return banktype;
    }

    public void setBanktype(String banktype) {
        this.banktype = banktype;
    }

    public String getCardtype() {
        return cardtype;
    }

    public void setCardtype(String cardtype) {
        this.cardtype = cardtype;
    }

    public String getBankicon() {
        return bankicon;
    }

    public void setBankicon(String bankicon) {
        this.bankicon = bankicon;
    }

    public String getBankphone() {
        return bankphone;
    }

    public void setBankphone(String bankphone) {
        this.bankphone = bankphone;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public String getModifierdate() {
        return modifierdate;
    }

    public void setModifierdate(String modifierdate) {
        this.modifierdate = modifierdate;
    }

    public Long getModifiernum() {
        return modifiernum;
    }

    public void setModifiernum(Long modifiernum) {
        this.modifiernum = modifiernum;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Boolean isLogicdelete() {
        return logicdelete;
    }

    public void setLogicdelete(Boolean logicdelete) {
        this.logicdelete = logicdelete;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getBankcity() {
        return bankcity;
    }

    public void setBankcity(String bankcity) {
        this.bankcity = bankcity;
    }

    public String getBanksubbranch() {
        return banksubbranch;
    }

    public void setBanksubbranch(String banksubbranch) {
        this.banksubbranch = banksubbranch;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UserbankcardDTO userbankcardDTO = (UserbankcardDTO) o;
        if (userbankcardDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), userbankcardDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UserbankcardDTO{" +
            "id=" + getId() +
            ", userid='" + getUserid() + "'" +
            ", realname='" + getRealname() + "'" +
            ", bankcard='" + getBankcard() + "'" +
            ", banktype='" + getBanktype() + "'" +
            ", cardtype='" + getCardtype() + "'" +
            ", bankicon='" + getBankicon() + "'" +
            ", bankphone='" + getBankphone() + "'" +
            ", createdate='" + getCreatedate() + "'" +
            ", modifier='" + getModifier() + "'" +
            ", modifierdate='" + getModifierdate() + "'" +
            ", modifiernum=" + getModifiernum() +
            ", state='" + getState() + "'" +
            ", logicdelete='" + isLogicdelete() + "'" +
            ", other='" + getOther() + "'" +
            ", bankcity='" + getBankcity() + "'" +
            ", banksubbranch='" + getBanksubbranch() + "'" +
            "}";
    }
}
