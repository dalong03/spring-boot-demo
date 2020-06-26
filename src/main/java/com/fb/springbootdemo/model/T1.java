package com.fb.springbootdemo.model;

public class T1 {

	private Integer id;
	private String name;
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
