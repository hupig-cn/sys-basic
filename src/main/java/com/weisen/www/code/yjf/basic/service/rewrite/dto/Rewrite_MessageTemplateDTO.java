package com.weisen.www.code.yjf.basic.service.rewrite.dto;

import com.weisen.www.code.yjf.basic.service.dto.MessagetemplateDTO;

import java.io.Serializable;
import java.util.Objects;

public class Rewrite_MessageTemplateDTO implements Serializable {

    private Long id;

    private String type;

    private String content;

    private String createDate;

    private String creator;

    private String modifier;

    private String modifierdate;

    private Long modifiernum;

    private String title;

    private String status;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MessagetemplateDTO messageTemplateDTO = (MessagetemplateDTO) o;
        if (messageTemplateDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), messageTemplateDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MessageTemplateDTO{" +
            "id=" + getId() +
            ", type='" + getType() + "'" +
            ", content='" + getContent() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", creator='" + getCreator() + "'" +
            ", modifier='" + getModifier() + "'" +
            ", modifierdate='" + getModifierdate() + "'" +
            ", modifiernum=" + getModifiernum() +
            ", title='" + getTitle() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
