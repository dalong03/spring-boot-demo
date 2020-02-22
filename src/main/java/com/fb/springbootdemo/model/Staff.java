package com.fb.springbootdemo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "staff")
public class Staff {
	@Id
	@Column(name = "sta_id", nullable = false)
	private String staId;

	@Column(name = "sta_code", nullable = false)
	private String staCode;

	@Column(name = "sta_name", nullable = false)
	private String staName;

	@Column(name = "dep_code")
	private String depCode;

	@Column(name = "dep_name")
	private String depName;

	@Column(name = "create_date")
	private Date createDate = new Date();

	@Column(name = "update_date")
	private Date updateDate = new Date();

	public String getStaId() {
		return staId;
	}

	public void setStaId(String staId) {
		this.staId = staId;
	}

	public String getStaCode() {
		return staCode;
	}

	public void setStaCode(String staCode) {
		this.staCode = staCode;
	}

	public String getStaName() {
		return staName;
	}

	public void setStaName(String staName) {
		this.staName = staName;
	}

	public String getDepCode() {
		return depCode;
	}

	public void setDepCode(String depCode) {
		this.depCode = depCode;
	}

	public String getDepName() {
		return depName;
	}

	public void setDepName(String depName) {
		this.depName = depName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Override
	public String toString() {
		return "Staff [staId=" + staId + ", staCode=" + staCode + ", staName=" + staName + ", depCode=" + depCode
				+ ", depName=" + depName + ", createDate=" + createDate + ", updateDate=" + updateDate + "]";
	}

}
