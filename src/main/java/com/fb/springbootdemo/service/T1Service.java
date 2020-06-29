package com.fb.springbootdemo.service;

import java.util.Map;

import com.fb.springbootdemo.common.CrudService;
import com.fb.springbootdemo.model.T1;

public interface T1Service extends CrudService<T1> {

	void say();
	
	Map<String, Object> lockLevelTest();
	

}
