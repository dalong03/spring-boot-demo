package com.fb.springbootdemo.test.conditional;

public class MagicBean {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "MagicBean [name=" + name + "]";
	}

}
