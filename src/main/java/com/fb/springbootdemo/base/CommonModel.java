package com.fb.springbootdemo.base;

import java.util.Date;

import javax.persistence.Column;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

public class CommonModel {
	@Column(name = "is_valid")
	private Integer isValid;
	
	@Column(name = "create_by")
	private Integer createBy;

	@Column(name = "create_date")
	@CreatedDate
	private Date createDate;

	@Column(name = "update_by")
	private Integer updateBy;

	@Column(name = "update_date")
	@LastModifiedDate
	private Date updateDate;

	public Integer getIsValid() {
		return isValid;
	}

	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}

	public Integer getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(Integer updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
}
