package com.fb.springbootdemo.service.impl;

import org.springframework.stereotype.Service;

import com.fb.springbootdemo.service.MyService;

@Service
public class MyServiceImpl implements MyService{

	@Override
	public void say() {
		System.out.println("say");
	}

}
