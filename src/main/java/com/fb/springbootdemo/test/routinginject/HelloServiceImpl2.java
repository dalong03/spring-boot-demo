package com.fb.springbootdemo.test.routinginject;

import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl2 implements HelloService {

	@Override
	public String say() {
		return "HelloServiceImpl2";
	}

}
