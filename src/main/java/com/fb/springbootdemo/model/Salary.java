package com.fb.springbootdemo.model;

import java.math.BigDecimal;
import java.util.Date;

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
@Table(name = "salary")
@EntityListeners(AuditingEntityListener.class)
public class Salary extends CommonModel{
	@Id
	@Column(name = "sal_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String salId;

	@Column(name = "sta_id", nullable = false)
	private Integer staId;
	
	@Column(name = "sta_code", nullable = false)
	private String staCode;

	@Column(name = "sta_name", nullable = false)
	private String staName;

	@Column(name = "wage", nullable = false)
	private BigDecimal wage;
	
	@Column(name = "type", nullable = false)
	private String type;

	@Column(name = "type_desc", nullable = false)
	private String typeDesc;

	@Column(name = "issue_year", nullable = false)
	private Integer issueYear;

	@Column(name = "issue_month", nullable = false)
	private Integer issueMonth;

	@Column(name = "issue_date", nullable = false)
	private Date issueDate;

	public String getSalId() {
		return salId;
	}

	public void setSalId(String salId) {
		this.salId = salId;
	}

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

	public BigDecimal getWage() {
		return wage;
	}

	public void setWage(BigDecimal wage) {
		this.wage = wage;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTypeDesc() {
		return typeDesc;
	}

	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}

	public Integer getIssueYear() {
		return issueYear;
	}

	public void setIssueYear(Integer issueYear) {
		this.issueYear = issueYear;
	}

	public Integer getIssueMonth() {
		return issueMonth;
	}

	public void setIssueMonth(Integer issueMonth) {
		this.issueMonth = issueMonth;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	@Override
	public String toString() {
		return "Salary [salId=" + salId + ", staId=" + staId + ", staCode=" + staCode + ", staName=" + staName
				+ ", wage=" + wage + ", type=" + type + ", typeDesc=" + typeDesc + ", issueYear=" + issueYear
				+ ", issueMonth=" + issueMonth + ", issueDate=" + issueDate + "]";
	}
}
