package com.fb.springbootdemo.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fb.springbootdemo.model.CommonCode;

@Repository
public interface CommonCodeRepository extends CrudRepository<CommonCode, String> {
	
	List<CommonCode> findByCodeField(String codeField);
	
}
