package com.fb.springbootdemo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fb.springbootdemo.base.CommonModel;

@Entity
@Table(name = "staff")
@EntityListeners(AuditingEntityListener.class)
public class Staff extends CommonModel{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sta_id", nullable = false)
	private Integer staId;

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
	

	public Integer getStaId() {
		return staId;
	}

	public void setStaId(Integer staId) {
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

	@Override
	public String toString() {
		return "Staff [staId=" + staId + ", staCode=" + staCode + ", staName=" + staName + ", depId=" + depId
				+ ", depCode=" + depCode + ", depName=" + depName + "]";
	}

}
