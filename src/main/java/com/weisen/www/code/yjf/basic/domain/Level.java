package com.weisen.www.code.yjf.basic.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Level.
 */
@Entity
@Table(name = "level")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Level implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "region")
    private String region;

    @Column(name = "boss")
    private String boss;

    @Column(name = "turnover")
    private String turnover;

    @Column(name = "who")
    private Integer who;

    public Integer getWho() {
        return who;
    }

    public void setWho(Integer who) {
        this.who = who;
    }

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

    public Level region(String region) {
        this.region = region;
        return this;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getBoss() {
        return boss;
    }

    public Level boss(String boss) {
        this.boss = boss;
        return this;
    }

    public void setBoss(String boss) {
        this.boss = boss;
    }

    public String getTurnover() {
        return turnover;
    }

    public Level turnover(String turnover) {
        this.turnover = turnover;
        return this;
    }

    public void setTurnover(String turnover) {
        this.turnover = turnover;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Level)) {
            return false;
        }
        return id != null && id.equals(((Level) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Level{" +
            "id=" + getId() +
            ", region='" + getRegion() + "'" +
            ", boss='" + getBoss() + "'" +
            ", turnover='" + getTurnover() + "'" +
            "}";
    }
}
