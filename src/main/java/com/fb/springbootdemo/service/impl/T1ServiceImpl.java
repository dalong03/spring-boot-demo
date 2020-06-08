package com.fb.springbootdemo.service.impl;

import org.springframework.stereotype.Service;

import com.fb.springbootdemo.service.T1Service;

@Service
public class T1ServiceImpl implements T1Service{

	@Override
	public void say() {
		System.out.println("say");
	}

}
