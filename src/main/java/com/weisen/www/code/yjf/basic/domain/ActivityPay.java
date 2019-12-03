package com.weisen.www.code.yjf.basic.domain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * The Employee entity.
 * 活动流水表
 */
@ApiModel(description = "The Employee entity. 活动流水表")
@Entity
@Table(name = "activity_pay")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ActivityPay implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The firstname ClassificationAttr.
     */
    @ApiModelProperty(value = "The firstname ClassificationAttr.")
    @Column(name = "user_id")
    private String userId;

    @Column(name = "type")
    private Integer type;

    @Column(name = "income_amo", precision = 21, scale = 2)
    private BigDecimal incomeAmo;

    @Column(name = "transformation_amo", precision = 21, scale = 2)
    private BigDecimal transformationAmo;

    @Column(name = "interest_rate", precision = 21, scale = 2)
    private BigDecimal interestRate;

    @Column(name = "create_time")
    private String createTime;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public ActivityPay userId(String userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getType() {
        return type;
    }

    public ActivityPay type(Integer type) {
        this.type = type;
        return this;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public BigDecimal getIncomeAmo() {
        return incomeAmo;
    }

    public ActivityPay incomeAmo(BigDecimal incomeAmo) {
        this.incomeAmo = incomeAmo;
        return this;
    }

    public void setIncomeAmo(BigDecimal incomeAmo) {
        this.incomeAmo = incomeAmo;
    }

    public BigDecimal getTransformationAmo() {
        return transformationAmo;
    }

    public ActivityPay transformationAmo(BigDecimal transformationAmo) {
        this.transformationAmo = transformationAmo;
        return this;
    }

    public void setTransformationAmo(BigDecimal transformationAmo) {
        this.transformationAmo = transformationAmo;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public ActivityPay interestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
        return this;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public String getCreateTime() {
        return createTime;
    }

    public ActivityPay createTime(String createTime) {
        this.createTime = createTime;
        return this;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ActivityPay)) {
            return false;
        }
        return id != null && id.equals(((ActivityPay) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ActivityPay{" +
            "id=" + getId() +
            ", userId='" + getUserId() + "'" +
            ", type=" + getType() +
            ", incomeAmo=" + getIncomeAmo() +
            ", transformationAmo=" + getTransformationAmo() +
            ", interestRate=" + getInterestRate() +
            ", createTime='" + getCreateTime() + "'" +
            "}";
    }
}
