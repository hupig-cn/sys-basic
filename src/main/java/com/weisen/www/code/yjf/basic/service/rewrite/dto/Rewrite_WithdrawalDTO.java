package com.weisen.www.code.yjf.basic.service.rewrite.dto;

import com.weisen.www.code.yjf.basic.service.dto.WithdrawalDTO;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.weisen.www.code.yjf.basic.domain.Withdrawal} entity.
 */
@SuppressWarnings("serial")
public class Rewrite_WithdrawalDTO implements Serializable {

    private Long id;

    private String userid;

    private String withdrawalamount;

    private String withdrawaltype;

    private String creator;

    private String createdate;

    private String modifier;

    private String modifierdate;

    private Long modifiernum;

    private Boolean logicdelete;

    private String other;

    private String gatheringway;


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

    public String getWithdrawalamount() {
        return withdrawalamount;
    }

    public void setWithdrawalamount(String withdrawalamount) {
        this.withdrawalamount = withdrawalamount;
    }

    public String getWithdrawaltype() {
        return withdrawaltype;
    }

    public void setWithdrawaltype(String withdrawaltype) {
        this.withdrawaltype = withdrawaltype;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
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

    public String getGatheringway() {
        return gatheringway;
    }

    public void setGatheringway(String gatheringway) {
        this.gatheringway = gatheringway;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        WithdrawalDTO withdrawalDTO = (WithdrawalDTO) o;
        if (withdrawalDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), withdrawalDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "WithdrawalDTO{" +
            "id=" + getId() +
            ", userid='" + getUserid() + "'" +
            ", withdrawalamount='" + getWithdrawalamount() + "'" +
            ", withdrawaltype='" + getWithdrawaltype() + "'" +
            ", creator='" + getCreator() + "'" +
            ", createdate='" + getCreatedate() + "'" +
            ", modifier='" + getModifier() + "'" +
            ", modifierdate='" + getModifierdate() + "'" +
            ", modifiernum=" + getModifiernum() +
            ", logicdelete='" + isLogicdelete() + "'" +
            ", other='" + getOther() + "'" +
            ", gatheringway='" + getGatheringway() + "'" +
            "}";
    }
}
