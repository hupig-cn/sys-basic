package com.weisen.www.code.yjf.basic.service.dto.submit_dto;
import java.io.Serializable;
import java.util.Objects;

import com.weisen.www.code.yjf.basic.service.dto.FeedbackDTO;

import io.swagger.annotations.ApiModelProperty;

/**
 * A DTO for the {@link com.weisen.www.code.yjf.basic.domain.Feedback} entity.
 */
@SuppressWarnings("serial")
public class Rewrite_FeedbackDTO implements Serializable {

	@ApiModelProperty(value="反馈人id")
    private Long id;

	@ApiModelProperty(value="反馈人姓名")
    private String name;

	@ApiModelProperty(value="反馈时间")
    private String feedbackdate;

	@ApiModelProperty(value="反馈标题")
    private String title;

	@ApiModelProperty(value="反馈内容")
    private String content;

	@ApiModelProperty(value="反馈状态")
    private String state;

	@ApiModelProperty(value="反馈截图")
    private String imageurl;

	@ApiModelProperty(value="创建者")
    private String creator;

	@ApiModelProperty(value="创建日期")
    private String createdate;

	@ApiModelProperty(value="修改者")
    private String modifier;

	@ApiModelProperty(value="修改日期")
    private String modifierdate;

	@ApiModelProperty(value="修改次数")
    private Long modifiernum;

	@ApiModelProperty(value="删除")
    private Boolean logicdelete;

	@ApiModelProperty(value="备注")
    private String other;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFeedbackdate() {
        return feedbackdate;
    }

    public void setFeedbackdate(String feedbackdate) {
        this.feedbackdate = feedbackdate;
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

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
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

        FeedbackDTO feedbackDTO = (FeedbackDTO) o;
        if (feedbackDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), feedbackDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FeedbackDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", feedbackdate='" + getFeedbackdate() + "'" +
            ", title='" + getTitle() + "'" +
            ", content='" + getContent() + "'" +
            ", state='" + getState() + "'" +
            ", imageurl='" + getImageurl() + "'" +
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
