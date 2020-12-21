package com.fb.springbootdemo.test.routinginject;

import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService {

	@Override
	public String say() {
		return "HelloServiceImpl";
	}

}
