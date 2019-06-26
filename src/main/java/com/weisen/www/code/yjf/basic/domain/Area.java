package com.weisen.www.code.yjf.basic.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Area.
 */
@Entity
@Table(name = "area")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Area implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "pid")
    private Integer pid;

    @Column(name = "status")
    private Integer status;

    @Column(name = "pname")
    private String pname;

    @Column(name = "gid")
    private Integer gid;

    @Column(name = "gname")
    private String gname;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Area name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPid() {
        return pid;
    }

    public Area pid(Integer pid) {
        this.pid = pid;
        return this;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getStatus() {
        return status;
    }

    public Area status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPname() {
        return pname;
    }

    public Area pname(String pname) {
        this.pname = pname;
        return this;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public Integer getGid() {
        return gid;
    }

    public Area gid(Integer gid) {
        this.gid = gid;
        return this;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public String getGname() {
        return gname;
    }

    public Area gname(String gname) {
        this.gname = gname;
        return this;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Area)) {
            return false;
        }
        return id != null && id.equals(((Area) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Area{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", pid=" + getPid() +
            ", status=" + getStatus() +
            ", pname='" + getPname() + "'" +
            ", gid=" + getGid() +
            ", gname='" + getGname() + "'" +
            "}";
    }
}
