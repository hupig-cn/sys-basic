package com.weisen.www.code.yjf.basic.service.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

/**
 * A DTO for the DataFile entity.
 */
@SuppressWarnings("serial")
public class Rewrite_AppVersionUpdateDTO implements Serializable {

	@ApiModelProperty(value = "客户端类型  1：Android 2：IOS", example = "1")
	private Integer clientType;

	@ApiModelProperty(value = "请求返回值  Yes:成功 ", example = "Yes")
	private String update;
	
	@ApiModelProperty(value = "版本id", example = "3")
	private Integer VersionCode;
	
	@ApiModelProperty(value = "版本号", example = "1.1.1")
	private String VersionName;

	@ApiModelProperty(value = "下载文件地址", example = "http://192.168.1.171:8084/services/basicqq/api/dataFile/public/xx")
	private String DownloadUrl;

	@ApiModelProperty(value = "更新日志", example = "修复按键失效")
	private String ModifyContent;

	@ApiModelProperty(value = "Apk文件大小", example = "2048")
	private String ApkSize;
	
	@ApiModelProperty(value = "更新状态  1：不强制更新    2：强制更新", example = "2")
	private String UpdateStatus;


	@ApiModelProperty(value = "Apk Md5加密信息", example = "5151545153451512345121651545")
	private String ApkMd5;

	@ApiModelProperty(value = "状态   1：正常", example = "1")
	private Integer state;

	@ApiModelProperty(value = "发布时间", example = "2019-05-12")
	private String publishTime;

	public Integer getClientType() {
		return clientType;
	}

	public void setClientType(Integer clientType) {
		this.clientType = clientType;
	}
	
	public String getUpdateStatus() {
		return UpdateStatus;
	}

	public void setUpdateStatus(String updateStatus) {
		UpdateStatus = updateStatus;
	}


	public String getUpdate() {
		return update;
	}

	public void setUpdate(String update) {
		this.update = update;
	}

	public Integer getVersionCode() {
		return VersionCode;
	}

	public void setVersionCode(Integer versionCode) {
		VersionCode = versionCode;
	}

	public String getVersionName() {
		return VersionName;
	}

	public void setVersionName(String versionName) {
		VersionName = versionName;
	}

	public String getDownloadUrl() {
		return DownloadUrl;
	}

	public void setDownloadUrl(String downloadUrl) {
		DownloadUrl = downloadUrl;
	}

	public String getModifyContent() {
		return ModifyContent;
	}

	public void setModifyContent(String modifyContent) {
		ModifyContent = modifyContent;
	}

	public String getApkSize() {
		return ApkSize;
	}

	public void setApkSize(String apkSize) {
		ApkSize = apkSize;
	}

	public String getApkMd5() {
		return ApkMd5;
	}

	public void setApkMd5(String apkMd5) {
		ApkMd5 = apkMd5;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}

}
