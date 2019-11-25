package com.weisen.www.code.yjf.basic.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;

/**
 * A Prodcutimage.
 */
@Entity
@Table(name = "prodcutimage")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Prodcutimage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "specificationsid")
    private String specificationsid;

    @Column(name = "fileid")
    private String fileid;

    @Column(name = "other")
    private String other;

    @Column(name = "jhi_type")
    private String type;

    @Column(name = "state")
    private String state;

    @Column(name = "creator")
    private String creator;

    @Column(name = "createdate")
    private String createdate;

    @Column(name = "modifier")
    private String modifier;

    @Column(name = "modifierdate")
    private String modifierdate;

    @Column(name = "modifiernum")
    private Long modifiernum;

    @Column(name = "viewplace")
    private String viewplace;

    @Column(name = "path")
    private String path;

    @Column(name = "logicdelete")
    private Boolean logicdelete;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpecificationsid() {
        return specificationsid;
    }

    public Prodcutimage specificationsid(String specificationsid) {
        this.specificationsid = specificationsid;
        return this;
    }

    public void setSpecificationsid(String specificationsid) {
        this.specificationsid = specificationsid;
    }

    public String getFileid() {
        return fileid;
    }

    public Prodcutimage fileid(String fileid) {
        this.fileid = fileid;
        return this;
    }

    public void setFileid(String fileid) {
        this.fileid = fileid;
    }

    public String getOther() {
        return other;
    }

    public Prodcutimage other(String other) {
        this.other = other;
        return this;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getType() {
        return type;
    }

    public Prodcutimage type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getState() {
        return state;
    }

    public Prodcutimage state(String state) {
        this.state = state;
        return this;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCreator() {
        return creator;
    }

    public Prodcutimage creator(String creator) {
        this.creator = creator;
        return this;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreatedate() {
        return createdate;
    }

    public Prodcutimage createdate(String createdate) {
        this.createdate = createdate;
        return this;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getModifier() {
        return modifier;
    }

    public Prodcutimage modifier(String modifier) {
        this.modifier = modifier;
        return this;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public String getModifierdate() {
        return modifierdate;
    }

    public Prodcutimage modifierdate(String modifierdate) {
        this.modifierdate = modifierdate;
        return this;
    }

    public void setModifierdate(String modifierdate) {
        this.modifierdate = modifierdate;
    }

    public Long getModifiernum() {
        return modifiernum;
    }

    public Prodcutimage modifiernum(Long modifiernum) {
        this.modifiernum = modifiernum;
        return this;
    }

    public void setModifiernum(Long modifiernum) {
        this.modifiernum = modifiernum;
    }

    public String getViewplace() {
        return viewplace;
    }

    public Prodcutimage viewplace(String viewplace) {
        this.viewplace = viewplace;
        return this;
    }

    public void setViewplace(String viewplace) {
        this.viewplace = viewplace;
    }

    public String getPath() {
        return path;
    }

    public Prodcutimage path(String path) {
        this.path = path;
        return this;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Boolean isLogicdelete() {
        return logicdelete;
    }

    public Prodcutimage logicdelete(Boolean logicdelete) {
        this.logicdelete = logicdelete;
        return this;
    }

    public void setLogicdelete(Boolean logicdelete) {
        this.logicdelete = logicdelete;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Prodcutimage)) {
            return false;
        }
        return id != null && id.equals(((Prodcutimage) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Prodcutimage{" +
            "id=" + getId() +
            ", specificationsid='" + getSpecificationsid() + "'" +
            ", fileid='" + getFileid() + "'" +
            ", other='" + getOther() + "'" +
            ", type='" + getType() + "'" +
            ", state='" + getState() + "'" +
            ", creator='" + getCreator() + "'" +
            ", createdate='" + getCreatedate() + "'" +
            ", modifier='" + getModifier() + "'" +
            ", modifierdate='" + getModifierdate() + "'" +
            ", modifiernum=" + getModifiernum() +
            ", viewplace='" + getViewplace() + "'" +
            ", path='" + getPath() + "'" +
            ", logicdelete='" + isLogicdelete() + "'" +
            "}";
    }
}
