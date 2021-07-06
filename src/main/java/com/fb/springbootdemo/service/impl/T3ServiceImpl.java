package com.fb.springbootdemo.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fb.springbootdemo.common.multidatasource.DataSource;
import com.fb.springbootdemo.entity.T3;
import com.fb.springbootdemo.repository.T3Repository;
import com.fb.springbootdemo.service.T3Service;

@Service
public class T3ServiceImpl implements T3Service {
	private Logger log = LoggerFactory.getLogger(T3ServiceImpl.class.getName());

	@Autowired
	private T3Repository t1Repository;

	@Override
	@Transactional
	public void say() {
		System.out.println("say");
	}

	@Override
	@DataSource
	public T3 find(Integer id) {
		System.out.println("=====");
		return null;
	}

}
