package com.fb.springbootdemo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fb.springbootdemo.model.T1;

@Repository
public interface T1Repository extends CrudRepository<T1, Integer>{

}
