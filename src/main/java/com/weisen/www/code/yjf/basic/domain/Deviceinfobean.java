package com.weisen.www.code.yjf.basic.domain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * The Deviceinfobean entity.
 * 用户手机信息表
 */
@ApiModel(description = "The Deviceinfobean entity. 用户手机信息表")
@Entity
@Table(name = "deviceinfobean")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Deviceinfobean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The platformAndroid attribute.
     * 安卓版本
     */
    @ApiModelProperty(value = "The platformAndroid attribute. 安卓版本")
    @Column(name = "platformandroid")
    private String platformandroid;

    /**
     * The platformIos attribute.
     * IOS版本
     */
    @ApiModelProperty(value = "The platformIos attribute. IOS版本")
    @Column(name = "platformios")
    private String platformios;

    /**
     * The userid attribute.
     * 用户id
     */
    @ApiModelProperty(value = "The userid attribute. 用户id")
    @Column(name = "userid")
    private String userid;

    /**
     * The platformname attribute.
     * 平台
     */
    @ApiModelProperty(value = "The platformname attribute. 平台")
    @Column(name = "platformname")
    private String platformname;

    /**
     * The deviceManufacturer attribute.
     * 制造商
     */
    @ApiModelProperty(value = "The deviceManufacturer attribute. 制造商")
    @Column(name = "devicemanufacturer")
    private String devicemanufacturer;

    /**
     * The deviceModel attribute.
     * 型号
     */
    @ApiModelProperty(value = "The deviceModel attribute. 型号")
    @Column(name = "devicemodel")
    private String devicemodel;

    /**
     * The deviceAbis attribute.
     * cpu架构
     */
    @ApiModelProperty(value = "The deviceAbis attribute. cpu架构")
    @Column(name = "deviceabis")
    private String deviceabis;

    /**
     * The sdkVersionName attribute.
     * SDK版本名
     */
    @ApiModelProperty(value = "The sdkVersionName attribute. SDK版本名")
    @Column(name = "sdkversionname")
    private String sdkversionname;

    /**
     * The sdkVersionCode attribute.
     * SDK版本号
     */
    @ApiModelProperty(value = "The sdkVersionCode attribute. SDK版本号")
    @Column(name = "sdkversioncode")
    private String sdkversioncode;

    /**
     * The romName attribute.
     * 系统名
     */
    @ApiModelProperty(value = "The romName attribute. 系统名")
    @Column(name = "romname")
    private String romname;

    /**
     * The romVersion attribute.
     * 系统版本
     */
    @ApiModelProperty(value = "The romVersion attribute. 系统版本")
    @Column(name = "romversion")
    private String romversion;

    /**
     * The screenWidth attribute.
     * 屏幕宽度
     */
    @ApiModelProperty(value = "The screenWidth attribute. 屏幕宽度")
    @Column(name = "screenwidth")
    private Long screenwidth;

    /**
     * The screenHeight attribute.
     * 屏幕高度
     */
    @ApiModelProperty(value = "The screenHeight attribute. 屏幕高度")
    @Column(name = "screenheight")
    private Long screenheight;

    /**
     * The appScreenWidth attribute.
     * app界面宽度
     */
    @ApiModelProperty(value = "The appScreenWidth attribute. app界面宽度")
    @Column(name = "appscreenwidth")
    private Long appscreenwidth;

    /**
     * The appScreenHeight attribute.
     * app界面高度
     */
    @ApiModelProperty(value = "The appScreenHeight attribute. app界面高度")
    @Column(name = "appscreenheight")
    private Long appscreenheight;

    /**
     * The screenDensityDpi attribute.
     * 屏幕分辨率
     */
    @ApiModelProperty(value = "The screenDensityDpi attribute. 屏幕分辨率")
    @Column(name = "screendensitydpi")
    private Long screendensitydpi;

    /**
     * The screenDensity attribute.
     * 屏幕大小
     */
    @ApiModelProperty(value = "The screenDensity attribute. 屏幕大小")
    @Column(name = "screendensity")
    private String screendensity;

    /**
     * The appVersionName attribute.
     * APP版本名
     */
    @ApiModelProperty(value = "The appVersionName attribute. APP版本名")
    @Column(name = "appversionname")
    private String appversionname;

    /**
     * The appVersionCode attribute.
     * APP版本号
     */
    @ApiModelProperty(value = "The appVersionCode attribute. APP版本号")
    @Column(name = "appversioncode")
    private String appversioncode;

    /**
     * The isRoot attribute.
     * 是否Root
     */
    @ApiModelProperty(value = "The isRoot attribute. 是否Root")
    @Column(name = "isroot")
    private Boolean isroot;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlatformandroid() {
        return platformandroid;
    }

    public Deviceinfobean platformandroid(String platformandroid) {
        this.platformandroid = platformandroid;
        return this;
    }

    public void setPlatformandroid(String platformandroid) {
        this.platformandroid = platformandroid;
    }

    public String getPlatformios() {
        return platformios;
    }

    public Deviceinfobean platformios(String platformios) {
        this.platformios = platformios;
        return this;
    }

    public void setPlatformios(String platformios) {
        this.platformios = platformios;
    }

    public String getUserid() {
        return userid;
    }

    public Deviceinfobean userid(String userid) {
        this.userid = userid;
        return this;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPlatformname() {
        return platformname;
    }

    public Deviceinfobean platformname(String platformname) {
        this.platformname = platformname;
        return this;
    }

    public void setPlatformname(String platformname) {
        this.platformname = platformname;
    }

    public String getDevicemanufacturer() {
        return devicemanufacturer;
    }

    public Deviceinfobean devicemanufacturer(String devicemanufacturer) {
        this.devicemanufacturer = devicemanufacturer;
        return this;
    }

    public void setDevicemanufacturer(String devicemanufacturer) {
        this.devicemanufacturer = devicemanufacturer;
    }

    public String getDevicemodel() {
        return devicemodel;
    }

    public Deviceinfobean devicemodel(String devicemodel) {
        this.devicemodel = devicemodel;
        return this;
    }

    public void setDevicemodel(String devicemodel) {
        this.devicemodel = devicemodel;
    }

    public String getDeviceabis() {
        return deviceabis;
    }

    public Deviceinfobean deviceabis(String deviceabis) {
        this.deviceabis = deviceabis;
        return this;
    }

    public void setDeviceabis(String deviceabis) {
        this.deviceabis = deviceabis;
    }

    public String getSdkversionname() {
        return sdkversionname;
    }

    public Deviceinfobean sdkversionname(String sdkversionname) {
        this.sdkversionname = sdkversionname;
        return this;
    }

    public void setSdkversionname(String sdkversionname) {
        this.sdkversionname = sdkversionname;
    }

    public String getSdkversioncode() {
        return sdkversioncode;
    }

    public Deviceinfobean sdkversioncode(String sdkversioncode) {
        this.sdkversioncode = sdkversioncode;
        return this;
    }

    public void setSdkversioncode(String sdkversioncode) {
        this.sdkversioncode = sdkversioncode;
    }

    public String getRomname() {
        return romname;
    }

    public Deviceinfobean romname(String romname) {
        this.romname = romname;
        return this;
    }

    public void setRomname(String romname) {
        this.romname = romname;
    }

    public String getRomversion() {
        return romversion;
    }

    public Deviceinfobean romversion(String romversion) {
        this.romversion = romversion;
        return this;
    }

    public void setRomversion(String romversion) {
        this.romversion = romversion;
    }

    public Long getScreenwidth() {
        return screenwidth;
    }

    public Deviceinfobean screenwidth(Long screenwidth) {
        this.screenwidth = screenwidth;
        return this;
    }

    public void setScreenwidth(Long screenwidth) {
        this.screenwidth = screenwidth;
    }

    public Long getScreenheight() {
        return screenheight;
    }

    public Deviceinfobean screenheight(Long screenheight) {
        this.screenheight = screenheight;
        return this;
    }

    public void setScreenheight(Long screenheight) {
        this.screenheight = screenheight;
    }

    public Long getAppscreenwidth() {
        return appscreenwidth;
    }

    public Deviceinfobean appscreenwidth(Long appscreenwidth) {
        this.appscreenwidth = appscreenwidth;
        return this;
    }

    public void setAppscreenwidth(Long appscreenwidth) {
        this.appscreenwidth = appscreenwidth;
    }

    public Long getAppscreenheight() {
        return appscreenheight;
    }

    public Deviceinfobean appscreenheight(Long appscreenheight) {
        this.appscreenheight = appscreenheight;
        return this;
    }

    public void setAppscreenheight(Long appscreenheight) {
        this.appscreenheight = appscreenheight;
    }

    public Long getScreendensitydpi() {
        return screendensitydpi;
    }

    public Deviceinfobean screendensitydpi(Long screendensitydpi) {
        this.screendensitydpi = screendensitydpi;
        return this;
    }

    public void setScreendensitydpi(Long screendensitydpi) {
        this.screendensitydpi = screendensitydpi;
    }

    public String getScreendensity() {
        return screendensity;
    }

    public Deviceinfobean screendensity(String screendensity) {
        this.screendensity = screendensity;
        return this;
    }

    public void setScreendensity(String screendensity) {
        this.screendensity = screendensity;
    }

    public String getAppversionname() {
        return appversionname;
    }

    public Deviceinfobean appversionname(String appversionname) {
        this.appversionname = appversionname;
        return this;
    }

    public void setAppversionname(String appversionname) {
        this.appversionname = appversionname;
    }

    public String getAppversioncode() {
        return appversioncode;
    }

    public Deviceinfobean appversioncode(String appversioncode) {
        this.appversioncode = appversioncode;
        return this;
    }

    public void setAppversioncode(String appversioncode) {
        this.appversioncode = appversioncode;
    }

    public Boolean isIsroot() {
        return isroot;
    }

    public Deviceinfobean isroot(Boolean isroot) {
        this.isroot = isroot;
        return this;
    }

    public void setIsroot(Boolean isroot) {
        this.isroot = isroot;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Deviceinfobean)) {
            return false;
        }
        return id != null && id.equals(((Deviceinfobean) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Deviceinfobean{" +
            "id=" + getId() +
            ", platformandroid='" + getPlatformandroid() + "'" +
            ", platformios='" + getPlatformios() + "'" +
            ", userid='" + getUserid() + "'" +
            ", platformname='" + getPlatformname() + "'" +
            ", devicemanufacturer='" + getDevicemanufacturer() + "'" +
            ", devicemodel='" + getDevicemodel() + "'" +
            ", deviceabis='" + getDeviceabis() + "'" +
            ", sdkversionname='" + getSdkversionname() + "'" +
            ", sdkversioncode='" + getSdkversioncode() + "'" +
            ", romname='" + getRomname() + "'" +
            ", romversion='" + getRomversion() + "'" +
            ", screenwidth=" + getScreenwidth() +
            ", screenheight=" + getScreenheight() +
            ", appscreenwidth=" + getAppscreenwidth() +
            ", appscreenheight=" + getAppscreenheight() +
            ", screendensitydpi=" + getScreendensitydpi() +
            ", screendensity='" + getScreendensity() + "'" +
            ", appversionname='" + getAppversionname() + "'" +
            ", appversioncode='" + getAppversioncode() + "'" +
            ", isroot='" + isIsroot() + "'" +
            "}";
    }
}
