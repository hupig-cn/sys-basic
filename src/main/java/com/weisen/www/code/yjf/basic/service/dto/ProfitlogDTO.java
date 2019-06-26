package com.weisen.www.code.yjf.basic.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.weisen.www.code.yjf.basic.domain.Profitlog} entity.
 */
public class ProfitlogDTO implements Serializable {

    private Long id;

    private String userid;

    private String consumer;

    private String appearamount;

    private String appeardate;

    private String frozendate;

    private String profittype;

    private String creator;

    private String createdate;

    private String modifier;

    private String modifierdate;

    private Long modifiernum;

    private Boolean logicdelete;

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

    public String getConsumer() {
        return consumer;
    }

    public void setConsumer(String consumer) {
        this.consumer = consumer;
    }

    public String getAppearamount() {
        return appearamount;
    }

    public void setAppearamount(String appearamount) {
        this.appearamount = appearamount;
    }

    public String getAppeardate() {
        return appeardate;
    }

    public void setAppeardate(String appeardate) {
        this.appeardate = appeardate;
    }

    public String getFrozendate() {
        return frozendate;
    }

    public void setFrozendate(String frozendate) {
        this.frozendate = frozendate;
    }

    public String getProfittype() {
        return profittype;
    }

    public void setProfittype(String profittype) {
        this.profittype = profittype;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProfitlogDTO profitlogDTO = (ProfitlogDTO) o;
        if (profitlogDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), profitlogDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProfitlogDTO{" +
            "id=" + getId() +
            ", userid='" + getUserid() + "'" +
            ", consumer='" + getConsumer() + "'" +
            ", appearamount='" + getAppearamount() + "'" +
            ", appeardate='" + getAppeardate() + "'" +
            ", frozendate='" + getFrozendate() + "'" +
            ", profittype='" + getProfittype() + "'" +
            ", creator='" + getCreator() + "'" +
            ", createdate='" + getCreatedate() + "'" +
            ", modifier='" + getModifier() + "'" +
            ", modifierdate='" + getModifierdate() + "'" +
            ", modifiernum=" + getModifiernum() +
            ", logicdelete='" + isLogicdelete() + "'" +
            ", other='" + getOther() + "'" +
            "}";
    }
}
