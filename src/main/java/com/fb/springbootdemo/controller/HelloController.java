package com.fb.springbootdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fb.springbootdemo.service.MyService;

@RestController
@RequestMapping(value = "hello")
public class HelloController {
	@Autowired
	private MyService myService;
	

	@RequestMapping( method = RequestMethod.GET)
	public String name() {
		return String.format("{\"message\":\"Hello %s %s\"}");
	}
}
