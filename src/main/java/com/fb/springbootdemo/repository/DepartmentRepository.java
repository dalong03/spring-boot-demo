package com.fb.springbootdemo.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fb.springbootdemo.model.Department;

@Repository
public interface DepartmentRepository extends CrudRepository<Department, String> {
	
	Department findByDepName(String depName);
	
}
