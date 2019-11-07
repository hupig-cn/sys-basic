package com.weisen.www.code.yjf.basic.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Options.
 */
@Entity
@Table(name = "options")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Options implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "stype")
    private String stype;

    @Column(name = "sk")
    private String sk;

    @Column(name = "sv")
    private String sv;

    @Column(name = "state")
    private String state;

    @Column(name = "csort")
    private Long csort;

    @Column(name = "info")
    private String info;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStype() {
        return stype;
    }

    public Options stype(String stype) {
        this.stype = stype;
        return this;
    }

    public void setStype(String stype) {
        this.stype = stype;
    }

    public String getSk() {
        return sk;
    }

    public Options sk(String sk) {
        this.sk = sk;
        return this;
    }

    public void setSk(String sk) {
        this.sk = sk;
    }

    public String getSv() {
        return sv;
    }

    public Options sv(String sv) {
        this.sv = sv;
        return this;
    }

    public void setSv(String sv) {
        this.sv = sv;
    }

    public String getState() {
        return state;
    }

    public Options state(String state) {
        this.state = state;
        return this;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Long getCsort() {
        return csort;
    }

    public Options csort(Long csort) {
        this.csort = csort;
        return this;
    }

    public void setCsort(Long csort) {
        this.csort = csort;
    }

    public String getInfo() {
        return info;
    }

    public Options info(String info) {
        this.info = info;
        return this;
    }

    public void setInfo(String info) {
        this.info = info;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Options)) {
            return false;
        }
        return id != null && id.equals(((Options) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Options{" +
            "id=" + getId() +
            ", stype='" + getStype() + "'" +
            ", sk='" + getSk() + "'" +
            ", sv='" + getSv() + "'" +
            ", state='" + getState() + "'" +
            ", csort=" + getCsort() +
            ", info='" + getInfo() + "'" +
            "}";
    }
}
