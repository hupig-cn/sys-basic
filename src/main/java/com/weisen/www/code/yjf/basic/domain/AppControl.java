package com.weisen.www.code.yjf.basic.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A AppControl.
 */
@Entity
@Table(name = "app_control")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class AppControl implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "client_type")
	private Integer clientType;

	@Column(name = "client_version")
	private String clientVersion;

	@Column(name = "download_url")
	private String downloadUrl;

	@Column(name = "qr_code_fileid")
	private Long qrCodeFileid;

	@Column(name = "update_log")
	private String updateLog;

	@Column(name = "force_update")
	private String forceUpdate;

	@Column(name = "publish_time")
	private String publishTime;

	@Column(name = "state")
	private Integer state;

	@Column(name = "creater")
	private Long creater;

	@Column(name = "create_time")
	private String createTime;

	@Column(name = "updater")
	private Long updater;

	@Column(name = "update_time")
	private String updateTime;

	@Column(name = "logic_delete")
	private Integer logicDelete;

	@Column(name = "update_num")
	private Integer updateNum;

	@Column(name = "other")
	private String other;
	
	@Column(name = "apk_size")
	private Integer apkSize;

	@Column(name = "apk_md5")
	private String apkMd5;



	// jhipster-needle-entity-add-field - JHipster will add fields here, do not
	// remove
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getClientType() {
		return clientType;
	}

	public AppControl clientType(Integer clientType) {
		this.clientType = clientType;
		return this;
	}

	public void setClientType(Integer clientType) {
		this.clientType = clientType;
	}

	public String getClientVersion() {
		return clientVersion;
	}

	public AppControl clientVersion(String clientVersion) {
		this.clientVersion = clientVersion;
		return this;
	}

	public void setClientVersion(String clientVersion) {
		this.clientVersion = clientVersion;
	}

	public String getDownloadUrl() {
		return downloadUrl;
	}

	public AppControl downloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
		return this;
	}
	public Integer getApkSize() {
		return apkSize;
	}
	
	public AppControl apkSize(Integer apkSize) {
		this.apkSize = apkSize;
		return this;
	}

	public void setApkSize(Integer apkSize) {
		this.apkSize = apkSize;
	}

	public String getApkMd5() {
		return apkMd5;
	}
	
	public AppControl apkMd5(String apkMd5) {
		this.apkMd5 = apkMd5;
		return this;
	}

	public void setApkMd5(String apkMd5) {
		this.apkMd5 = apkMd5;
	}

	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}

	public Long getQrCodeFileid() {
		return qrCodeFileid;
	}

	public AppControl qrCodeFileid(Long qrCodeFileid) {
		this.qrCodeFileid = qrCodeFileid;
		return this;
	}

	public void setQrCodeFileid(Long qrCodeFileid) {
		this.qrCodeFileid = qrCodeFileid;
	}

	public String getUpdateLog() {
		return updateLog;
	}

	public AppControl updateLog(String updateLog) {
		this.updateLog = updateLog;
		return this;
	}

	public void setUpdateLog(String updateLog) {
		this.updateLog = updateLog;
	}

	public String getForceUpdate() {
		return forceUpdate;
	}

	public AppControl forceUpdate(String forceUpdate) {
		this.forceUpdate = forceUpdate;
		return this;
	}

	public void setForceUpdate(String forceUpdate) {
		this.forceUpdate = forceUpdate;
	}

	public String getPublishTime() {
		return publishTime;
	}

	public AppControl publishTime(String publishTime) {
		this.publishTime = publishTime;
		return this;
	}

	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}

	public Integer getState() {
		return state;
	}

	public AppControl state(Integer state) {
		this.state = state;
		return this;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Long getCreater() {
		return creater;
	}

	public AppControl creater(Long creater) {
		this.creater = creater;
		return this;
	}

	public void setCreater(Long creater) {
		this.creater = creater;
	}

	public String getCreateTime() {
		return createTime;
	}

	public AppControl createTime(String createTime) {
		this.createTime = createTime;
		return this;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Long getUpdater() {
		return updater;
	}

	public AppControl updater(Long updater) {
		this.updater = updater;
		return this;
	}

	public void setUpdater(Long updater) {
		this.updater = updater;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public AppControl updateTime(String updateTime) {
		this.updateTime = updateTime;
		return this;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getLogicDelete() {
		return logicDelete;
	}

	public AppControl logicDelete(Integer logicDelete) {
		this.logicDelete = logicDelete;
		return this;
	}

	public void setLogicDelete(Integer logicDelete) {
		this.logicDelete = logicDelete;
	}

	public Integer getUpdateNum() {
		return updateNum;
	}

	public AppControl updateNum(Integer updateNum) {
		this.updateNum = updateNum;
		return this;
	}

	public void setUpdateNum(Integer updateNum) {
		this.updateNum = updateNum;
	}

	public String getOther() {
		return other;
	}

	public AppControl other(String other) {
		this.other = other;
		return this;
	}

	public void setOther(String other) {
		this.other = other;
	}
	// jhipster-needle-entity-add-getters-setters - JHipster will add getters and
	// setters here, do not remove

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof AppControl)) {
			return false;
		}
		return id != null && id.equals(((AppControl) o).id);
	}

	@Override
	public int hashCode() {
		return 31;
	}

	@Override
	public String toString() {
		return "AppControl{" + "id=" + getId() + ", clientType=" + getClientType() + ", clientVersion='"
				+ getClientVersion() + "'" + ", downloadUrl='" + getDownloadUrl() + "'" + ", qrCodeFileid="
				+ getQrCodeFileid() + ", updateLog='" + getUpdateLog() + "'" + ", forceUpdate='" + getForceUpdate()
				+ "'" + ", publishTime='" + getPublishTime() + "'" + ", state=" + getState() + ", creater="
				+ getCreater() + ", createTime='" + getCreateTime() + "'" + ", updater=" + getUpdater()
				+ ", updateTime='" + getUpdateTime() + "'" + ", logicDelete=" + getLogicDelete() + ", updateNum="
				+ getUpdateNum() + ", other='" + getOther() + "'" + "}";
	}
}
