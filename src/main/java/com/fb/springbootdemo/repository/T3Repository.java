package com.fb.springbootdemo.repository;

import org.springframework.stereotype.Repository;

import com.fb.springbootdemo.model.T3;

@Repository
public interface T3Repository {
	T3 findById(Integer id);
}
