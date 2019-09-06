package com.weisen.www.code.yjf.basic.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A Userorder.
 */
@Entity
@Table(name = "userorder")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Userorder implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ordercode")
    private String ordercode;

    @Column(name = "orderstatus")
    private String orderstatus;

    @Column(name = "sum", precision = 21, scale = 2)
    private BigDecimal sum;

    @Column(name = "userid")
    private String userid;

    @Column(name = "payee")
    private String payee;

    @Column(name = "payway")
    private String payway;

    @Column(name = "payresult")
    private String payresult;

    @Column(name = "paytime")
    private String paytime;

    @Column(name = "concession")
    private Integer concession;

    @Column(name = "rebate")
    private Integer rebate;

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

    @Column(name = "logicdelete")
    private Boolean logicdelete;

    @Column(name = "other")
    private String other;

    @Column(name = "express_company")
    private String expressCompany;

    @Column(name = "express_no")
    private String expressNo;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrdercode() {
        return ordercode;
    }

    public Userorder ordercode(String ordercode) {
        this.ordercode = ordercode;
        return this;
    }

    public void setOrdercode(String ordercode) {
        this.ordercode = ordercode;
    }

    public String getOrderstatus() {
        return orderstatus;
    }

    public Userorder orderstatus(String orderstatus) {
        this.orderstatus = orderstatus;
        return this;
    }

    public void setOrderstatus(String orderstatus) {
        this.orderstatus = orderstatus;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public Userorder sum(BigDecimal sum) {
        this.sum = sum;
        return this;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public String getUserid() {
        return userid;
    }

    public Userorder userid(String userid) {
        this.userid = userid;
        return this;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPayee() {
        return payee;
    }

    public Userorder payee(String payee) {
        this.payee = payee;
        return this;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    public String getPayway() {
        return payway;
    }

    public Userorder payway(String payway) {
        this.payway = payway;
        return this;
    }

    public void setPayway(String payway) {
        this.payway = payway;
    }

    public String getPayresult() {
        return payresult;
    }

    public Userorder payresult(String payresult) {
        this.payresult = payresult;
        return this;
    }

    public void setPayresult(String payresult) {
        this.payresult = payresult;
    }

    public String getPaytime() {
        return paytime;
    }

    public Userorder paytime(String paytime) {
        this.paytime = paytime;
        return this;
    }

    public void setPaytime(String paytime) {
        this.paytime = paytime;
    }

    public Integer getConcession() {
        return concession;
    }

    public Userorder concession(Integer concession) {
        this.concession = concession;
        return this;
    }

    public void setConcession(Integer concession) {
        this.concession = concession;
    }

    public Integer getRebate() {
        return rebate;
    }

    public Userorder rebate(Integer rebate) {
        this.rebate = rebate;
        return this;
    }

    public void setRebate(Integer rebate) {
        this.rebate = rebate;
    }

    public String getCreator() {
        return creator;
    }

    public Userorder creator(String creator) {
        this.creator = creator;
        return this;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreatedate() {
        return createdate;
    }

    public Userorder createdate(String createdate) {
        this.createdate = createdate;
        return this;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getModifier() {
        return modifier;
    }

    public Userorder modifier(String modifier) {
        this.modifier = modifier;
        return this;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public String getModifierdate() {
        return modifierdate;
    }

    public Userorder modifierdate(String modifierdate) {
        this.modifierdate = modifierdate;
        return this;
    }

    public void setModifierdate(String modifierdate) {
        this.modifierdate = modifierdate;
    }

    public Long getModifiernum() {
        return modifiernum;
    }

    public Userorder modifiernum(Long modifiernum) {
        this.modifiernum = modifiernum;
        return this;
    }

    public void setModifiernum(Long modifiernum) {
        this.modifiernum = modifiernum;
    }

    public Boolean isLogicdelete() {
        return logicdelete;
    }

    public Userorder logicdelete(Boolean logicdelete) {
        this.logicdelete = logicdelete;
        return this;
    }

    public void setLogicdelete(Boolean logicdelete) {
        this.logicdelete = logicdelete;
    }

    public String getOther() {
        return other;
    }

    public Userorder other(String other) {
        this.other = other;
        return this;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getExpressCompany() {
        return expressCompany;
    }

    public Userorder expressCompany(String expressCompany) {
        this.expressCompany = expressCompany;
        return this;
    }

    public void setExpressCompany(String expressCompany) {
        this.expressCompany = expressCompany;
    }

    public String getExpressNo() {
        return expressNo;
    }

    public Userorder expressNo(String expressNo) {
        this.expressNo = expressNo;
        return this;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Userorder)) {
            return false;
        }
        return id != null && id.equals(((Userorder) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Userorder{" +
            "id=" + getId() +
            ", ordercode='" + getOrdercode() + "'" +
            ", orderstatus='" + getOrderstatus() + "'" +
            ", sum=" + getSum() +
            ", userid='" + getUserid() + "'" +
            ", payee='" + getPayee() + "'" +
            ", payway='" + getPayway() + "'" +
            ", payresult='" + getPayresult() + "'" +
            ", paytime='" + getPaytime() + "'" +
            ", concession=" + getConcession() +
            ", rebate=" + getRebate() +
            ", creator='" + getCreator() + "'" +
            ", createdate='" + getCreatedate() + "'" +
            ", modifier='" + getModifier() + "'" +
            ", modifierdate='" + getModifierdate() + "'" +
            ", modifiernum=" + getModifiernum() +
            ", logicdelete='" + isLogicdelete() + "'" +
            ", other='" + getOther() + "'" +
            ", expressCompany='" + getExpressCompany() + "'" +
            ", expressNo='" + getExpressNo() + "'" +
            "}";
    }
}
