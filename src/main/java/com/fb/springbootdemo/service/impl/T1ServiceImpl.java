package com.fb.springbootdemo.service.impl;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fb.springbootdemo.base.AbstractCrudService;
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
	public List<T1> findPage(T1 t1, T1 order, PageInfo pageInfo) {
		if (pageInfo.getStartIndex() == null || pageInfo.getEndIndex() == null) {
			pageInfo.setStartIndex((pageInfo.getPageNo() - 1) * pageInfo.getCount());
			pageInfo.setEndIndex(pageInfo.getPageNo() * pageInfo.getCount() - 1);
		}
		return t1Repository.findPage(t1, order, pageInfo);
	}

	@Override
	@Transactional
	public T1 update(T1 t1) {
		T1 t0 = t1Repository.find(t1.getId());
		t1.setVersion(t0.getVersion());
		t1Repository.update(t1);
		T1 t2 = t1Repository.find(t1.getId());
		return t2;
	}

	@Override
	public Map<String, Object> lockLevelTest() {
		ExecutorService pool = Executors.newFixedThreadPool(10);
		long s1 = System.currentTimeMillis();
		for (int i = 0; i < 10; i++) {
			pool.execute(new FindT1(t1Repository, new T1(i, "jerry")));
		}
		pool.shutdown();
		while (true) {
			if (pool.isTerminated()) {
				long s2 = System.currentTimeMillis();
				log.info(String.format("============%d", s2 - s1));
				break;
			} else {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	static class FindT1 implements Runnable {
		public FindT1(T1Repository t1Repository, T1 t1) {
			this.t1Repository = t1Repository;
			this.t1 = t1;
		}

		private T1Repository t1Repository;
		private T1 t1;

		@Override
		public void run() {
			t1Repository.update(t1);
		}
	}

}
