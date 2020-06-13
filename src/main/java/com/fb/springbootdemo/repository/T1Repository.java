package com.fb.springbootdemo.repository;

import org.springframework.stereotype.Repository;

import com.fb.springbootdemo.model.T1;

@Repository
public interface T1Repository {
	T1 find(Integer id);

}
