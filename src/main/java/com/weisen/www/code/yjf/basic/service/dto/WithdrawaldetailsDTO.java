package com.weisen.www.code.yjf.basic.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.weisen.www.code.yjf.basic.domain.Withdrawaldetails} entity.
 */
public class WithdrawaldetailsDTO implements Serializable {

    private Long id;

    private String userid;

    private String withdrawalway;

    private String title;

    private String withdrawalid;

    private String type;

    private String amount;

    private String afteramount;

    private String createdate;

    private String modifierdate;

    private String state;

    private String other;


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

    public String getWithdrawalway() {
        return withdrawalway;
    }

    public void setWithdrawalway(String withdrawalway) {
        this.withdrawalway = withdrawalway;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWithdrawalid() {
        return withdrawalid;
    }

    public void setWithdrawalid(String withdrawalid) {
        this.withdrawalid = withdrawalid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getAfteramount() {
        return afteramount;
    }

    public void setAfteramount(String afteramount) {
        this.afteramount = afteramount;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getModifierdate() {
        return modifierdate;
    }

    public void setModifierdate(String modifierdate) {
        this.modifierdate = modifierdate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        WithdrawaldetailsDTO withdrawaldetailsDTO = (WithdrawaldetailsDTO) o;
        if (withdrawaldetailsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), withdrawaldetailsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "WithdrawaldetailsDTO{" +
            "id=" + getId() +
            ", userid='" + getUserid() + "'" +
            ", withdrawalway='" + getWithdrawalway() + "'" +
            ", title='" + getTitle() + "'" +
            ", withdrawalid='" + getWithdrawalid() + "'" +
            ", type='" + getType() + "'" +
            ", amount='" + getAmount() + "'" +
            ", afteramount='" + getAfteramount() + "'" +
            ", createdate='" + getCreatedate() + "'" +
            ", modifierdate='" + getModifierdate() + "'" +
            ", state='" + getState() + "'" +
            ", other='" + getOther() + "'" +
            "}";
    }
}
