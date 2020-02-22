package com.fb.springbootdemo.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "salary")
public class Salary {
	@Id
	@Column(name = "sal_id", nullable = false)
	private String salId;

	@Column(name = "sta_code", nullable = false)
	private String staCode;

	@Column(name = "sta_name", nullable = false)
	private String staName;

	@Column(name = "salary", nullable = false)
	private BigDecimal salary;
	
	@Column(name = "type", nullable = false)
	private String type;

	@Column(name = "type_desc", nullable = false)
	private String typeDesc;

	@Column(name = "issue_year", nullable = false)
	private String issueYear;

	@Column(name = "issue_month", nullable = false)
	private String issueMonth;

	@Column(name = "issue_date", nullable = false)
	private String issueDate;

	@Column(name = "create_date")
	private Date createDate = new Date();

	@Column(name = "update_date")
	private Date updateDate = new Date();

	public String getSalId() {
		return salId;
	}

	public void setSalId(String salId) {
		this.salId = salId;
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

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
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

	public String getIssueYear() {
		return issueYear;
	}

	public void setIssueYear(String issueYear) {
		this.issueYear = issueYear;
	}

	public String getIssueMonth() {
		return issueMonth;
	}

	public void setIssueMonth(String issueMonth) {
		this.issueMonth = issueMonth;
	}

	public String getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
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
		return "Salary [salId=" + salId + ", staCode=" + staCode + ", staName=" + staName + ", salary=" + salary
				+ ", type=" + type + ", typeDesc=" + typeDesc + ", issueYear=" + issueYear + ", issueMonth="
				+ issueMonth + ", issueDate=" + issueDate + ", createDate=" + createDate + ", updateDate=" + updateDate
				+ "]";
	}

}
