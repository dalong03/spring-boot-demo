package com.fb.springbootdemo.test.listener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

//@Component
public class EmailNotifier implements ApplicationListener<ApplicationEvent> {

	public void onApplicationEvent(ApplicationEvent event) {
		if (event instanceof EmailEvent) {
			Email email = (Email) event.getSource();
			System.out.println("邮件地址：" + email.getAddress());
			System.out.println("邮件内容：" + email.getText());
		} else {
			System.out.println("容器本身事件：" + event);
		}
	}
}
