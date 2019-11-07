package com.weisen.www.code.yjf.basic.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A PaymethodConst.
 */
@Entity
@Table(name = "paymethod_const")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PaymethodConst implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "state")
    private Integer state;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public PaymethodConst title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getState() {
        return state;
    }

    public PaymethodConst state(Integer state) {
        this.state = state;
        return this;
    }

    public void setState(Integer state) {
        this.state = state;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PaymethodConst)) {
            return false;
        }
        return id != null && id.equals(((PaymethodConst) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "PaymethodConst{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", state=" + getState() +
            "}";
    }
}
