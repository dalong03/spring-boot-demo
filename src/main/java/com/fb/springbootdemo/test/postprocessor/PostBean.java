package com.fb.springbootdemo.test.postprocessor;

import org.springframework.stereotype.Component;

@Component
public class PostBean {

	private String author;

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void sayhello() {
		System.out.println(String.format("author %s say hello!", author));
	}
}