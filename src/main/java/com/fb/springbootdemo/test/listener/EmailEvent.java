package com.fb.springbootdemo.test.listener;

import org.springframework.context.ApplicationEvent;

public class EmailEvent extends ApplicationEvent {
	private static final long serialVersionUID = -184867819363420088L;

	public EmailEvent(Email source) {
		super(source);
	}
}