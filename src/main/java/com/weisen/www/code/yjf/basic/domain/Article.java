package com.weisen.www.code.yjf.basic.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Article.
 */
@Entity
@Table(name = "article")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userid")
    private Long userid;

    @Column(name = "title")
    private String title;

    @Column(name = "img_url")
    private String imgUrl;

    @Lob
    @Column(name = "content")
    private String content;

    @Column(name = "author")
    private Long author;

    @Column(name = "other")
    private String other;

    @Column(name = "logic_delete")
    private Long logicDelete;
    
    @Column(name = "createtime")
    private String createtime;
    
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserid() {
        return userid;
    }

    public Article userid(Long userid) {
        this.userid = userid;
        return this;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getTitle() {
        return title;
    }

    public Article title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public Article imgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getContent() {
        return content;
    }

    public Article content(String content) {
        this.content = content;
        return this;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getAuthor() {
        return author;
    }

    public Article author(Long author) {
        this.author = author;
        return this;
    }

    public void setAuthor(Long author) {
        this.author = author;
    }

    public String getOther() {
        return other;
    }

    public Article other(String other) {
        this.other = other;
        return this;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public Long getLogicDelete() {
        return logicDelete;
    }

    public Article logicDelete(Long logicDelete) {
        this.logicDelete = logicDelete;
        return this;
    }

    public void setLogicDelete(Long logicDelete) {
        this.logicDelete = logicDelete;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Article)) {
            return false;
        }
        return id != null && id.equals(((Article) o).id);
    }

    public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	@Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Article{" +
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
