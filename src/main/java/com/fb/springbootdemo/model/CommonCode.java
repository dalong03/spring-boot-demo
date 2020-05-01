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

/**
 * 码值
 * 
 * @author Administrator
 *
 */
@Entity
@Table(name = "common_code")
@EntityListeners(AuditingEntityListener.class)
public class CommonCode extends CommonModel{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "code_id", nullable = true)
	private Integer codeId;

	@Column(name = "code_field", nullable = false)
	private String codeField;

	@Column(name = "code_key")
	private String codeKey;

	@Column(name = "code_value")
	private String codeValue;

	@Column(name = "code_desc")
	private String codeDesc;


	public Integer getCodeId() {
		return codeId;
	}

	public void setCodeId(Integer codeId) {
		this.codeId = codeId;
	}

	public String getCodeField() {
		return codeField;
	}

	public void setCodeField(String codeField) {
		this.codeField = codeField;
	}

	public String getCodeKey() {
		return codeKey;
	}

	public void setCodeKey(String codeKey) {
		this.codeKey = codeKey;
	}

	public String getCodeValue() {
		return codeValue;
	}

	public void setCodeValue(String codeValue) {
		this.codeValue = codeValue;
	}

	public String getCodeDesc() {
		return codeDesc;
	}

	public void setCodeDesc(String codeDesc) {
		this.codeDesc = codeDesc;
	}

	@Override
	public String toString() {
		return "CommonCode [codeId=" + codeId + ", codeField=" + codeField + ", codeKey=" + codeKey + ", codeValue="
				+ codeValue + ", codeDesc=" + codeDesc + "]";
	}

}
