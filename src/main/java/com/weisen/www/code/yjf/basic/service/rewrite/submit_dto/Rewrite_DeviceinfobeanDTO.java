package com.weisen.www.code.yjf.basic.service.rewrite.submit_dto;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@SuppressWarnings("serial")
@ApiModel(value = "保存用户手机型号信息DTO")
public class Rewrite_DeviceinfobeanDTO implements Serializable {

	@ApiModelProperty(value = "安卓版本")
	private String platformandroid;

	@ApiModelProperty(value = "IOS版本")
	private String platformios;

	@ApiModelProperty(value = "用户id")
	private String userid;

	@ApiModelProperty(value = "平台")
	private String platformname;

	@ApiModelProperty(value = "制造商")
	private String devicemanufacturer;

	@ApiModelProperty(value = "型号")
	private String devicemodel;

	@ApiModelProperty(value = "cpu架构")
	private String deviceabis;

	@ApiModelProperty(value = "SDK版本名")
	private String sdkversionname;

	@ApiModelProperty(value = "SDK版本号")
	private String sdkversioncode;

	@ApiModelProperty(value = "系统名")
	private String romname;

	@ApiModelProperty(value = "系统版本")
	private String romversion;

	@ApiModelProperty(value = "屏幕宽度")
	private Long screenwidth;

	@ApiModelProperty(value = "屏幕高度")
	private Long screenheight;

	@ApiModelProperty(value = "app界面宽度")
	private Long appscreenwidth;

	@ApiModelProperty(value = "app界面高度")
	private Long appscreenheight;

	@ApiModelProperty(value = "屏幕分辨率")
	private Long screendensitydpi;

	@ApiModelProperty(value = "屏幕大小")
	private String screendensity;

	@ApiModelProperty(value = "APP版本名")
	private String appversionname;

	@ApiModelProperty(value = "APP版本号")
	private String appversioncode;

	@ApiModelProperty(value = "是否Root")
	private Boolean isroot;

	public String getPlatformandroid() {
		return platformandroid;
	}

	public void setPlatformandroid(String platformandroid) {
		this.platformandroid = platformandroid;
	}

	public String getPlatformios() {
		return platformios;
	}

	public void setPlatformios(String platformios) {
		this.platformios = platformios;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPlatformname() {
		return platformname;
	}

	public void setPlatformname(String platformname) {
		this.platformname = platformname;
	}

	public String getDevicemanufacturer() {
		return devicemanufacturer;
	}

	public void setDevicemanufacturer(String devicemanufacturer) {
		this.devicemanufacturer = devicemanufacturer;
	}

	public String getDevicemodel() {
		return devicemodel;
	}

	public void setDevicemodel(String devicemodel) {
		this.devicemodel = devicemodel;
	}

	public String getDeviceabis() {
		return deviceabis;
	}

	public void setDeviceabis(String deviceabis) {
		this.deviceabis = deviceabis;
	}

	public String getSdkversionname() {
		return sdkversionname;
	}

	public void setSdkversionname(String sdkversionname) {
		this.sdkversionname = sdkversionname;
	}

	public String getSdkversioncode() {
		return sdkversioncode;
	}

	public void setSdkversioncode(String sdkversioncode) {
		this.sdkversioncode = sdkversioncode;
	}

	public String getRomname() {
		return romname;
	}

	public void setRomname(String romname) {
		this.romname = romname;
	}

	public String getRomversion() {
		return romversion;
	}

	public void setRomversion(String romversion) {
		this.romversion = romversion;
	}

	public Long getScreenwidth() {
		return screenwidth;
	}

	public void setScreenwidth(Long screenwidth) {
		this.screenwidth = screenwidth;
	}

	public Long getScreenheight() {
		return screenheight;
	}

	public void setScreenheight(Long screenheight) {
		this.screenheight = screenheight;
	}

	public Long getAppscreenwidth() {
		return appscreenwidth;
	}

	public void setAppscreenwidth(Long appscreenwidth) {
		this.appscreenwidth = appscreenwidth;
	}

	public Long getAppscreenheight() {
		return appscreenheight;
	}

	public void setAppscreenheight(Long appscreenheight) {
		this.appscreenheight = appscreenheight;
	}

	public Long getScreendensitydpi() {
		return screendensitydpi;
	}

	public void setScreendensitydpi(Long screendensitydpi) {
		this.screendensitydpi = screendensitydpi;
	}

	public String getScreendensity() {
		return screendensity;
	}

	public void setScreendensity(String screendensity) {
		this.screendensity = screendensity;
	}

	public String getAppversionname() {
		return appversionname;
	}

	public void setAppversionname(String appversionname) {
		this.appversionname = appversionname;
	}

	public String getAppversioncode() {
		return appversioncode;
	}

	public void setAppversioncode(String appversioncode) {
		this.appversioncode = appversioncode;
	}

	public Boolean getIsroot() {
		return isroot;
	}

	public void setIsroot(Boolean isroot) {
		this.isroot = isroot;
	}

}
