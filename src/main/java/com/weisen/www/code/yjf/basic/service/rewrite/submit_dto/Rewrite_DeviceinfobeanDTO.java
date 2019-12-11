package com.weisen.www.code.yjf.basic.service.rewrite.submit_dto;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@SuppressWarnings("serial")
@ApiModel(value = "保存用户手机型号信息DTO")
public class Rewrite_DeviceinfobeanDTO implements Serializable {

	@ApiModelProperty(value = "用户id")
	private int userId;

	@ApiModelProperty(value = "平台")
	private String platform_name;

	@ApiModelProperty(value = "制造商")
	private String deviceManufacturer;

	@ApiModelProperty(value = "型号")
	private String deviceModel;

	@ApiModelProperty(value = "cpu架构")
	private String deviceAbis;

	@ApiModelProperty(value = "SDK版本名")
	private String sdkVersionName;

	@ApiModelProperty(value = "SDK版本号")
	private int sdkVersionCode;

	@ApiModelProperty(value = "系统名")
	private String romName;

	@ApiModelProperty(value = "系统版本")
	private String romVersion;

	@ApiModelProperty(value = "屏幕宽度")
	private int screenWidth;

	@ApiModelProperty(value = "屏幕高度")
	private int screenHeight;

	@ApiModelProperty(value = "app界面宽度")
	private int appScreenWidth;

	@ApiModelProperty(value = "app界面高度")
	private int appScreenHeight;

	@ApiModelProperty(value = "屏幕分辨率")
	private int screenDensityDpi;

	@ApiModelProperty(value = "屏幕大小")
	private float screenDensity;

	@ApiModelProperty(value = "APP版本名")
	private String appVersionName;

	@ApiModelProperty(value = "APP版本号")
	private int appVersionCode;

	@ApiModelProperty(value = "是否Root")
	private Boolean isRoot;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getPlatform_name() {
		return platform_name;
	}

	public void setPlatform_name(String platform_name) {
		this.platform_name = platform_name;
	}

	public String getDeviceManufacturer() {
		return deviceManufacturer;
	}

	public void setDeviceManufacturer(String deviceManufacturer) {
		this.deviceManufacturer = deviceManufacturer;
	}

	public String getDeviceModel() {
		return deviceModel;
	}

	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}

	public String getDeviceAbis() {
		return deviceAbis;
	}

	public void setDeviceAbis(String deviceAbis) {
		this.deviceAbis = deviceAbis;
	}

	public String getSdkVersionName() {
		return sdkVersionName;
	}

	public int getSdkVersionCode() {
		return sdkVersionCode;
	}

	public void setSdkVersionCode(int sdkVersionCode) {
		this.sdkVersionCode = sdkVersionCode;
	}

	public void setSdkVersionName(String sdkVersionName) {
		this.sdkVersionName = sdkVersionName;
	}

	public void setSdkversioncode(int sdkVersionCode) {
		this.sdkVersionCode = sdkVersionCode;
	}

	public String getRomName() {
		return romName;
	}

	public void setRomName(String romName) {
		this.romName = romName;
	}

	public String getRomVersion() {
		return romVersion;
	}

	public void setRomVersion(String romVersion) {
		this.romVersion = romVersion;
	}

	public int getScreenWidth() {
		return screenWidth;
	}

	public void setScreenWidth(int screenWidth) {
		this.screenWidth = screenWidth;
	}

	public int getScreenHeight() {
		return screenHeight;
	}

	public void setScreenHeight(int screenHeight) {
		this.screenHeight = screenHeight;
	}

	public int getAppScreenWidth() {
		return appScreenWidth;
	}

	public void setAppScreenWidth(int appScreenWidth) {
		this.appScreenWidth = appScreenWidth;
	}

	public int getAppScreenHeight() {
		return appScreenHeight;
	}

	public void setAppScreenHeight(int appScreenHeight) {
		this.appScreenHeight = appScreenHeight;
	}

	public int getScreenDensityDpi() {
		return screenDensityDpi;
	}

	public void setScreenDensityDpi(int screenDensityDpi) {
		this.screenDensityDpi = screenDensityDpi;
	}

	public float getScreenDensity() {
		return screenDensity;
	}

	public void setScreenDensity(float screenDensity) {
		this.screenDensity = screenDensity;
	}

	public String getAppVersionName() {
		return appVersionName;
	}

	public void setAppVersionName(String appVersionName) {
		this.appVersionName = appVersionName;
	}

	public int getAppVersionCode() {
		return appVersionCode;
	}

	public void setAppVersionCode(int appVersionCode) {
		this.appVersionCode = appVersionCode;
	}

	public Boolean getIsRoot() {
		return isRoot;
	}

	public void setIsRoot(Boolean isRoot) {
		this.isRoot = isRoot;
	}

}
