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
 * 活动配置表
 */
@ApiModel(description = "The Employee entity. 活动配置表")
@Entity
@Table(name = "activity_con")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ActivityCon implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The firstname ClassificationAttr.
     */
    @ApiModelProperty(value = "The firstname ClassificationAttr.")
    @Column(name = "activity_name")
    private String activityName;

    @Column(name = "interest_rate", precision = 21, scale = 4)
    private BigDecimal interestRate;

    @Column(name = "create_time")
    private String createTime;

    @Column(name = "logical_del")
    private Integer logicalDel;
    
    public Integer getLogicalDel() {
		return logicalDel;
	}

	public void setLogicalDel(Integer logicalDel) {
		this.logicalDel = logicalDel;
	}

	// jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getActivityName() {
        return activityName;
    }

    public ActivityCon activityName(String activityName) {
        this.activityName = activityName;
        return this;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public ActivityCon interestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
        return this;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public String getCreateTime() {
        return createTime;
    }

    public ActivityCon createTime(String createTime) {
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
        if (!(o instanceof ActivityCon)) {
            return false;
        }
        return id != null && id.equals(((ActivityCon) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ActivityCon{" +
            "id=" + getId() +
            ", activityName='" + getActivityName() + "'" +
            ", interestRate=" + getInterestRate() +
            ", createTime='" + getCreateTime() + "'" +
            "}";
    }
}
