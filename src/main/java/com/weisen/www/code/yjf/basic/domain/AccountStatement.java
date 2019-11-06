package com.weisen.www.code.yjf.basic.domain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * The AccountStatement entity.
 */
@ApiModel(description = "The AccountStatement entity.")
@Entity
@Table(name = "account_statement")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class AccountStatement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The firstname attribute.
     */
    @ApiModelProperty(value = "The firstname attribute.")
    @Column(name = "statement_id")
    private String statementId;

    @Column(name = "userid")
    private String userid;

    @Column(name = "amount", precision = 21, scale = 2)
    private BigDecimal amount;

    @Column(name = "asset", precision = 21, scale = 2)
    private BigDecimal asset;

    @Column(name = "btype")
    private Integer btype;

    @Column(name = "ctype")
    private Integer ctype;

    @Column(name = "direction")
    private Integer direction;

    @Column(name = "payway")
    private Integer payway;

    @Column(name = "ctime")
    private String ctime;

    @Column(name = "jhi_desc")
    private String desc;

    @Column(name = "tracking")
    private String tracking;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatementId() {
        return statementId;
    }

    public AccountStatement statementId(String statementId) {
        this.statementId = statementId;
        return this;
    }

    public void setStatementId(String statementId) {
        this.statementId = statementId;
    }

    public String getUserid() {
        return userid;
    }

    public AccountStatement userid(String userid) {
        this.userid = userid;
        return this;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public AccountStatement amount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAsset() {
        return asset;
    }

    public AccountStatement asset(BigDecimal asset) {
        this.asset = asset;
        return this;
    }

    public void setAsset(BigDecimal asset) {
        this.asset = asset;
    }

    public Integer getBtype() {
        return btype;
    }

    public AccountStatement btype(Integer btype) {
        this.btype = btype;
        return this;
    }

    public void setBtype(Integer btype) {
        this.btype = btype;
    }

    public Integer getCtype() {
        return ctype;
    }

    public AccountStatement ctype(Integer ctype) {
        this.ctype = ctype;
        return this;
    }

    public void setCtype(Integer ctype) {
        this.ctype = ctype;
    }

    public Integer getDirection() {
        return direction;
    }

    public AccountStatement direction(Integer direction) {
        this.direction = direction;
        return this;
    }

    public void setDirection(Integer direction) {
        this.direction = direction;
    }

    public Integer getPayway() {
        return payway;
    }

    public AccountStatement payway(Integer payway) {
        this.payway = payway;
        return this;
    }

    public void setPayway(Integer payway) {
        this.payway = payway;
    }

    public String getCtime() {
        return ctime;
    }

    public AccountStatement ctime(String ctime) {
        this.ctime = ctime;
        return this;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getDesc() {
        return desc;
    }

    public AccountStatement desc(String desc) {
        this.desc = desc;
        return this;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTracking() {
        return tracking;
    }

    public AccountStatement tracking(String tracking) {
        this.tracking = tracking;
        return this;
    }

    public void setTracking(String tracking) {
        this.tracking = tracking;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AccountStatement)) {
            return false;
        }
        return id != null && id.equals(((AccountStatement) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "AccountStatement{" +
            "id=" + getId() +
            ", statementId='" + getStatementId() + "'" +
            ", userid='" + getUserid() + "'" +
            ", amount=" + getAmount() +
            ", asset=" + getAsset() +
            ", btype=" + getBtype() +
            ", ctype=" + getCtype() +
            ", direction=" + getDirection() +
            ", payway=" + getPayway() +
            ", ctime='" + getCtime() + "'" +
            ", desc='" + getDesc() + "'" +
            ", tracking='" + getTracking() + "'" +
            "}";
    }
}
