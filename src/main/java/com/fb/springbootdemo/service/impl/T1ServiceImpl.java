package com.fb.springbootdemo.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fb.springbootdemo.common.AbstractCrudService;
import com.fb.springbootdemo.common.multidatasource.DataSource;
import com.fb.springbootdemo.model.PageInfo;
import com.fb.springbootdemo.model.T1;
import com.fb.springbootdemo.repository.T1Repository;
import com.fb.springbootdemo.service.T1Service;

@Service
public class T1ServiceImpl extends AbstractCrudService<T1> implements T1Service {
	private Logger log = LoggerFactory.getLogger(T1ServiceImpl.class.getName());

	@Autowired
	private T1Repository t1Repository;

	@Override
	@Transactional
	public void say() {
		System.out.println("say");
	}

	@Override
//	@DataSource
	public T1 find(Integer id) {
		return null; //t1Repository.find(id);
	}
	
	@Override
	@DataSource
	public List<T1> findPage(T1 t1, T1 order, PageInfo pageInfo) {
		return null; //t1Repository.findPage(t1, order, pageInfo);
	}

	@Override
	@Transactional
	public T1 update(T1 t1) {
//		T1 t0 = t1Repository.find(t1.getId());
//		t1.setVersion(t0.getVersion());
//		t1Repository.update(t1);
		if(true) {
//			throw new RuntimeException();
		}
//		T1 t2 = t1Repository.find(t1.getId());
		return t1;
	}

}
