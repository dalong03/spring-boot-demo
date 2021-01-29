package com.fb.springbootdemo.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class T1 {

//	@NotBlank(message = "id为空")
	private Integer id;
//	@NotBlank(message = "name为空")
	private String name;
	@NotBlank(message = "date为空")
	@NotNull(message = "日期格式不正确")
	private Date date;
	private Integer balance;
	private Integer version;

	public T1() {
	}

	public T1(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
        this.date = date;
	}

	public void setDate(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        try {
        	this.date = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            this.date = null;
        }
	}

	public Integer getBalance() {
		return balance;
	}

	public void setBalance(Integer balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "T1 [id=" + id + ", name=" + name + ", balance=" + balance + ", version=" + version + "]";
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
}
