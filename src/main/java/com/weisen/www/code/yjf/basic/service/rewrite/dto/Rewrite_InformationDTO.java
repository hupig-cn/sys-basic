package com.weisen.www.code.yjf.basic.service.rewrite.dto;

import com.weisen.www.code.yjf.basic.service.dto.InformationDTO;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.weisen.www.code.yjf.basic.domain.Information} entity.
 */
public class Rewrite_InformationDTO implements Serializable {

    private Long id;

    private String type;

    private String readuserid;

    private String senduserid;

    private String senddate;

    private String title;

    private String content;

    private String state;

    private String creator;

    private String createdate;

    private String modifier;

    private String other;

    public String getSenduserid() {
        return senduserid;
    }

    public void setSenduserid(String senduserid) {
        this.senduserid = senduserid;
    }

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



    public String getReaduserid() {
        return readuserid;
    }

    public void setReaduserid(String readuserid) {
        this.readuserid = readuserid;
    }

    public String getSenddate() {
        return senddate;
    }

    public void setSenddate(String senddate) {
        this.senddate = senddate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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

        InformationDTO informationDTO = (InformationDTO) o;
        if (informationDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), informationDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    public String toString() {
        return "Rewrite_InformationDTO{" +
            "id=" + id +
            ", type='" + type + '\'' +
            ", readuserid='" + readuserid + '\'' +
            ", senddate='" + senddate + '\'' +
            ", title='" + title + '\'' +
            ", content='" + content + '\'' +
            ", state='" + state + '\'' +
            ", creator='" + creator + '\'' +
            ", createdate='" + createdate + '\'' +
            ", modifier='" + modifier + '\'' +
            ", other='" + other + '\'' +
            '}';
    }
}
