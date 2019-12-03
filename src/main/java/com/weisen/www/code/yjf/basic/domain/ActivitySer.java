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
 * 活动服务表
 */
@ApiModel(description = "The Employee entity. 活动服务表")
@Entity
@Table(name = "activity_ser")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ActivitySer implements Serializable {

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

    @Column(name = "activity_amo", precision = 21, scale = 2)
    private BigDecimal activityAmo;

    @Column(name = "available_amo", precision = 21, scale = 2)
    private BigDecimal availableAmo;

    @Column(name = "cash_withdrawal", precision = 21, scale = 2)
    private BigDecimal cashWithdrawal;

    @Column(name = "create_time")
    private String createTime;

    @Column(name = "update_time")
    private String updateTime;

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

    public ActivitySer userId(String userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public BigDecimal getActivityAmo() {
        return activityAmo;
    }

    public ActivitySer activityAmo(BigDecimal activityAmo) {
        this.activityAmo = activityAmo;
        return this;
    }

    public void setActivityAmo(BigDecimal activityAmo) {
        this.activityAmo = activityAmo;
    }

    public BigDecimal getAvailableAmo() {
        return availableAmo;
    }

    public ActivitySer availableAmo(BigDecimal availableAmo) {
        this.availableAmo = availableAmo;
        return this;
    }

    public void setAvailableAmo(BigDecimal availableAmo) {
        this.availableAmo = availableAmo;
    }

    public BigDecimal getCashWithdrawal() {
        return cashWithdrawal;
    }

    public ActivitySer cashWithdrawal(BigDecimal cashWithdrawal) {
        this.cashWithdrawal = cashWithdrawal;
        return this;
    }

    public void setCashWithdrawal(BigDecimal cashWithdrawal) {
        this.cashWithdrawal = cashWithdrawal;
    }

    public String getCreateTime() {
        return createTime;
    }

    public ActivitySer createTime(String createTime) {
        this.createTime = createTime;
        return this;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public ActivitySer updateTime(String updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ActivitySer)) {
            return false;
        }
        return id != null && id.equals(((ActivitySer) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ActivitySer{" +
            "id=" + getId() +
            ", userId='" + getUserId() + "'" +
            ", activityAmo=" + getActivityAmo() +
            ", availableAmo=" + getAvailableAmo() +
            ", cashWithdrawal=" + getCashWithdrawal() +
            ", createTime='" + getCreateTime() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            "}";
    }
}
