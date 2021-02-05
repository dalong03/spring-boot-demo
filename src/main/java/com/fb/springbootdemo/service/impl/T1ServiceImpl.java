package com.fb.springbootdemo.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fb.springbootdemo.common.multidatasource.DataSource;
import com.fb.springbootdemo.model.T1;
import com.fb.springbootdemo.repository.T1Repository;
import com.fb.springbootdemo.service.T1Service;

@Service
public class T1ServiceImpl implements T1Service {
	private Logger log = LoggerFactory.getLogger(T1ServiceImpl.class.getName());

	@Autowired
	private T1Repository t1Repository;

	@Override
	@Transactional
	public void say() {
		System.out.println("say");
	}

	@Override
	@DataSource
	public T1 find(Integer id) {
		System.out.println("=====");
		return null;
	}

}
