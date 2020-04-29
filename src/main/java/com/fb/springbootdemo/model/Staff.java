package com.fb.springbootdemo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "staff")
@EntityListeners(AuditingEntityListener.class)
public class Staff {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sta_id", nullable = false)
	private String staId;

	@Column(name = "sta_code", nullable = false)
	private String staCode;

	@Column(name = "sta_name", nullable = false)
	private String staName;

	@Column(name = "dep_id")
	private Integer depId;

	@Column(name = "dep_code")
	private String depCode;

	@Column(name = "dep_name")
	private String depName;

	@Column(name = "create_by")
	private Integer createBy;

	@Column(name = "create_date")
	@CreatedDate
	private Date createDate = new Date();

	@Column(name = "update_by")
	private Integer updateBy;

	@Column(name = "update_date")
	@LastModifiedDate
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

	public Integer getDepId() {
		return depId;
	}

	public void setDepId(Integer depId) {
		this.depId = depId;
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

	public Integer getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}

	public Integer getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(Integer updateBy) {
		this.updateBy = updateBy;
	}

	@Override
	public String toString() {
		return "Staff [staId=" + staId + ", staCode=" + staCode + ", staName=" + staName + ", depId=" + depId
				+ ", depCode=" + depCode + ", depName=" + depName + ", createBy=" + createBy + ", createDate="
				+ createDate + ", updateBy=" + updateBy + ", updateDate=" + updateDate + "]";
	}

}
