package com.weisen.www.code.yjf.basic.service.dto;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link com.weisen.www.code.yjf.basic.domain.Article} entity.
 */
public class ArticleDTO implements Serializable {

    private Long id;

    private Long userid;

    private String title;

    private String imgUrl;

    @Lob
    private String content;

    private Long author;

    private String other;

    private Long logicDelete;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getAuthor() {
        return author;
    }

    public void setAuthor(Long author) {
        this.author = author;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public Long getLogicDelete() {
        return logicDelete;
    }

    public void setLogicDelete(Long logicDelete) {
        this.logicDelete = logicDelete;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ArticleDTO articleDTO = (ArticleDTO) o;
        if (articleDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), articleDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ArticleDTO{" +
            "id=" + getId() +
            ", userid=" + getUserid() +
            ", title='" + getTitle() + "'" +
            ", imgUrl='" + getImgUrl() + "'" +
            ", content='" + getContent() + "'" +
            ", author=" + getAuthor() +
            ", other='" + getOther() + "'" +
            ", logicDelete=" + getLogicDelete() +
            "}";
    }
}
