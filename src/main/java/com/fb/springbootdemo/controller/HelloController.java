package com.fb.springbootdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fb.springbootdemo.service.MyService;

@RestController
@RequestMapping(value = "hello")
public class HelloController {
	@Value("${example.property}")
	private String exampleProperty="";
	@Autowired
	private MyService myService;
	

	@RequestMapping(value = "/{firstName}/{lastName}", method = RequestMethod.GET)
	public String name(@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName) {
		myService.say();
		return String.format("{\"message\":\"Hello %s %s\"}", firstName, lastName);
	}
}
