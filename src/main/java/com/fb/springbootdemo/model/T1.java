package com.fb.springbootdemo.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "t1")
public class T1 {

//	@NotBlank(message = "id为空")
	@Id
	private Integer id;
//	@NotBlank(message = "name为空")
	private String name;
	private Date date;
	private Integer balance;
	private Integer version;
	
	@OneToMany(targetEntity = T2.class, cascade = {CascadeType.PERSIST})
	@JoinColumn(name = "t1_id", referencedColumnName = "id")
	private List<T2> contacts;

	public List<T2> getContacts() {
		return contacts;
	}

	public void setContacts(List<T2> contacts) {
		this.contacts = contacts;
	}

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
		return "T1 [id=" + id + ", name=" + name + ", date=" + date + ", balance=" + balance + ", version=" + version
				+ ", contacts=" + contacts + "]";
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
}
