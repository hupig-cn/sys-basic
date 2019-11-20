package com.weisen.www.code.yjf.basic.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Boss.
 */
@Entity
@Table(name = "boss")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Boss implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "region")
    private String region;

    @Column(name = "turnover")
    private String turnover;

    @Column(name = "userid")
    private Long userid;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegion() {
        return region;
    }

    public Boss region(String region) {
        this.region = region;
        return this;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getTurnover() {
        return turnover;
    }

    public Boss turnover(String turnover) {
        this.turnover = turnover;
        return this;
    }

    public void setTurnover(String turnover) {
        this.turnover = turnover;
    }

    public Long getUserid() {
        return userid;
    }

    public Boss userid(Long userid) {
        this.userid = userid;
        return this;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Boss)) {
            return false;
        }
        return id != null && id.equals(((Boss) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Boss{" +
            "id=" + getId() +
            ", region='" + getRegion() + "'" +
            ", turnover='" + getTurnover() + "'" +
            ", userid=" + getUserid() +
            "}";
    }
}
