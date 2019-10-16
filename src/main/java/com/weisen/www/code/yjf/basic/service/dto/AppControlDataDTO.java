package com.weisen.www.code.yjf.basic.service.dto;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @author shenxiaoxin
 * @date 2019-10-15 14:56:30
 *
 */
@SuppressWarnings("serial")
@ApiModel(value = "增加app版本信息")
public class AppControlDataDTO implements Serializable {

	@ApiModelProperty(value = "客户端类型   1:Android 2:IOS ", example = "1")
	private Integer clientType;

	@ApiModelProperty(value = "客户端版本", example = "1.1.5")
	private String clientVersion;
	
	@ApiModelProperty(value = "下载地址url", example = "www.baidu.com")
	private String downloadUrl;

	@ApiModelProperty(value = "更新日志", example = "修复按钮失灵")
	private String updateLog;

	@ApiModelProperty(value = "是否强制更新   0：强制更新   1：不强制更新",example = "0")
	private String forceUpdate;

	@ApiModelProperty(value = "状态  1：正常  0：不正常", example = "1")
	private Integer state;

	@ApiModelProperty(value = "创建人", example = "1422")
	private Long creater;

	@ApiModelProperty(value = "发布时间", example = "2019-10-15 19:55:48")
	private String publishTime;
	
	@ApiModelProperty(value = "apk文件大小", example = "14  (单位为 ：MB)")
	private Integer apkSize;
	
	@ApiModelProperty(value = "apkMD5", example = "414645665346853")
	private String apkMd5;
	
	@ApiModelProperty(value = "更新版本次数", example = "1")
	private Integer updateNum;
	
	public Integer getApkSize() {
		return apkSize;
	}

	public void setApkSize(Integer apkSize) {
		this.apkSize = apkSize;
	}

	public String getApkMd5() {
		return apkMd5;
	}

	public void setApkMd5(String apkMd5) {
		this.apkMd5 = apkMd5;
	}

	public String getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}

	public Integer getClientType() {
		return clientType;
	}

	public void setClientType(Integer clientType) {
		this.clientType = clientType;
	}

	public String getClientVersion() {
		return clientVersion;
	}

	public void setClientVersion(String clientVersion) {
		this.clientVersion = clientVersion;
	}

	public String getDownloadUrl() {
		return downloadUrl;
	}

	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}

	public String getUpdateLog() {
		return updateLog;
	}

	public void setUpdateLog(String updateLog) {
		this.updateLog = updateLog;
	}

	public String getForceUpdate() {
		return forceUpdate;
	}

	public void setForceUpdate(String forceUpdate) {
		this.forceUpdate = forceUpdate;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Long getCreater() {
		return creater;
	}

	public void setCreater(Long creater) {
		this.creater = creater;
	}
	

}
