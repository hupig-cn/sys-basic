package com.weisen.www.code.yjf.basic.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Linkuser.
 */
@Entity
@Table(name = "linkuser")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Linkuser implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "userid")
	private String userid;

	@Column(name = "phone")
	private String phone;

	@Column(name = "alipay")
	private String alipay;

	@Column(name = "wechat")
	private String wechat;

	@Column(name = "alipayname")
	private String alipayname;

	@Column(name = "wechatname")
	private String wechatname;

	@Column(name = "name")
	private String name;

	@Column(name = "idcard")
	private String idcard;

	@Column(name = "sex")
	private String sex;

	@Column(name = "paypassword")
	private String paypassword;

	@Column(name = "paycount")
	private Integer paycount;

	@Column(name = "paylasttime")
	private String paylasttime;

	@Column(name = "address")
	private String address;

	@Column(name = "province")
	private String province;

	@Column(name = "city")
	private String city;

	@Column(name = "county")
	private String county;

	@Column(name = "loginnum")
	private Integer loginnum;

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

	@Column(name = "login_total")
	private Integer loginTotal;

	@Column(name = "last_logintime")
	private String lastLogintime;

	@Column(name = "last_logingps")
	private String lastLogingps;

	@Column(name = "state")
	private String state;

	@Column(name = "balance_rights")
	private String balanceRights;

	@Column(name = "integral_rights")
	private String integralRights;

	@Column(name = "withdraw_rights")
	private String withdrawRights;

	// jhipster-needle-entity-add-field - JHipster will add fields here, do not
	// remove
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserid() {
		return userid;
	}

	public Linkuser userid(String userid) {
		this.userid = userid;
		return this;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPhone() {
		return phone;
	}

	public Linkuser phone(String phone) {
		this.phone = phone;
		return this;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAlipay() {
		return alipay;
	}

	public Linkuser alipay(String alipay) {
		this.alipay = alipay;
		return this;
	}

	public void setAlipay(String alipay) {
		this.alipay = alipay;
	}

	public String getWechat() {
		return wechat;
	}

	public Linkuser wechat(String wechat) {
		this.wechat = wechat;
		return this;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

	public String getAlipayname() {
		return alipayname;
	}

	public Linkuser alipayname(String alipayname) {
		this.alipayname = alipayname;
		return this;
	}

	public void setAlipayname(String alipayname) {
		this.alipayname = alipayname;
	}

	public String getWechatname() {
		return wechatname;
	}

	public Linkuser wechatname(String wechatname) {
		this.wechatname = wechatname;
		return this;
	}

	public void setWechatname(String wechatname) {
		this.wechatname = wechatname;
	}

	public String getName() {
		return name;
	}

	public Linkuser name(String name) {
		this.name = name;
		return this;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdcard() {
		return idcard;
	}

	public Linkuser idcard(String idcard) {
		this.idcard = idcard;
		return this;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getSex() {
		return sex;
	}

	public Linkuser sex(String sex) {
		this.sex = sex;
		return this;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPaypassword() {
		return paypassword;
	}

	public Linkuser paypassword(String paypassword) {
		this.paypassword = paypassword;
		return this;
	}

	public void setPaypassword(String paypassword) {
		this.paypassword = paypassword;
	}

	public Integer getPaycount() {
		return paycount;
	}

	public Linkuser paycount(Integer paycount) {
		this.paycount = paycount;
		return this;
	}

	public void setPaycount(Integer paycount) {
		this.paycount = paycount;
	}

	public String getPaylasttime() {
		return paylasttime;
	}

	public Linkuser paylasttime(String paylasttime) {
		this.paylasttime = paylasttime;
		return this;
	}

	public void setPaylasttime(String paylasttime) {
		this.paylasttime = paylasttime;
	}

	public String getAddress() {
		return address;
	}

	public Linkuser address(String address) {
		this.address = address;
		return this;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getProvince() {
		return province;
	}

	public Linkuser province(String province) {
		this.province = province;
		return this;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public Linkuser city(String city) {
		this.city = city;
		return this;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return county;
	}

	public Linkuser county(String county) {
		this.county = county;
		return this;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public Integer getLoginnum() {
		return loginnum;
	}

	public Linkuser loginnum(Integer loginnum) {
		this.loginnum = loginnum;
		return this;
	}

	public void setLoginnum(Integer loginnum) {
		this.loginnum = loginnum;
	}

	public String getCreator() {
		return creator;
	}

	public Linkuser creator(String creator) {
		this.creator = creator;
		return this;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getCreatedate() {
		return createdate;
	}

	public Linkuser createdate(String createdate) {
		this.createdate = createdate;
		return this;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

	public String getModifier() {
		return modifier;
	}

	public Linkuser modifier(String modifier) {
		this.modifier = modifier;
		return this;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public String getModifierdate() {
		return modifierdate;
	}

	public Linkuser modifierdate(String modifierdate) {
		this.modifierdate = modifierdate;
		return this;
	}

	public void setModifierdate(String modifierdate) {
		this.modifierdate = modifierdate;
	}

	public Long getModifiernum() {
		return modifiernum;
	}

	public Linkuser modifiernum(Long modifiernum) {
		this.modifiernum = modifiernum;
		return this;
	}

	public void setModifiernum(Long modifiernum) {
		this.modifiernum = modifiernum;
	}

	public Boolean isLogicdelete() {
		return logicdelete;
	}

	public Linkuser logicdelete(Boolean logicdelete) {
		this.logicdelete = logicdelete;
		return this;
	}

	public void setLogicdelete(Boolean logicdelete) {
		this.logicdelete = logicdelete;
	}

	public String getOther() {
		return other;
	}

	public Linkuser other(String other) {
		this.other = other;
		return this;
	}

	public void setOther(String other) {
		this.other = other;
	}
	// jhipster-needle-entity-add-getters-setters - JHipster will add getters and
	// setters here, do not remove

	public Integer getLoginTotal() {
		return loginTotal;
	}

	public void setLoginTotal(Integer loginTotal) {
		this.loginTotal = loginTotal;
	}

	public String getLastLogintime() {
		return lastLogintime;
	}

	public void setLastLogintime(String lastLogintime) {
		this.lastLogintime = lastLogintime;
	}

	public String getLastLogingps() {
		return lastLogingps;
	}

	public void setLastLogingps(String lastLogingps) {
		this.lastLogingps = lastLogingps;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getBalanceRights() {
		return balanceRights;
	}

	public void setBalanceRights(String balanceRights) {
		this.balanceRights = balanceRights;
	}

	public String getIntegralRights() {
		return integralRights;
	}

	public void setIntegralRights(String integralRights) {
		this.integralRights = integralRights;
	}

	public String getWithdrawRights() {
		return withdrawRights;
	}

	public void setWithdrawRights(String withdrawRights) {
		this.withdrawRights = withdrawRights;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Linkuser)) {
			return false;
		}
		return id != null && id.equals(((Linkuser) o).id);
	}

	@Override
	public int hashCode() {
		return 31;
	}

	@Override
	public String toString() {
		return "Linkuser{" + "id=" + getId() + ", userid='" + getUserid() + "'" + ", phone='" + getPhone() + "'"
				+ ", alipay='" + getAlipay() + "'" + ", wechat='" + getWechat() + "'" + ", alipayname='"
				+ getAlipayname() + "'" + ", wechatname='" + getWechatname() + "'" + ", name='" + getName() + "'"
				+ ", idcard='" + getIdcard() + "'" + ", sex='" + getSex() + "'" + ", paypassword='" + getPaypassword()
				+ "'" + ", paycount=" + getPaycount() + ", paylasttime='" + getPaylasttime() + "'" + ", address='"
				+ getAddress() + "'" + ", province='" + getProvince() + "'" + ", city='" + getCity() + "'"
				+ ", county='" + getCounty() + "'" + ", loginnum=" + getLoginnum() + ", creator='" + getCreator() + "'"
				+ ", createdate='" + getCreatedate() + "'" + ", modifier='" + getModifier() + "'" + ", modifierdate='"
				+ getModifierdate() + "'" + ", modifiernum=" + getModifiernum() + ", logicdelete='" + isLogicdelete()
				+ "'" + ", other='" + getOther() + "'" + "}";
	}
}
