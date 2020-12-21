package com.fb.springbootdemo.test.listener;

public class Email {
	private String address;
	private String text;

	public Email() {
		super();
	}

	public Email(String address, String text) {
		super();
		this.address = address;
		this.text = text;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
