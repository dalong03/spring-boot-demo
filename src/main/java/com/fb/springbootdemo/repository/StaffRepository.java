package com.fb.springbootdemo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fb.springbootdemo.model.Staff;

@Repository
public interface StaffRepository extends CrudRepository<Staff, String> {
	Staff findByStaCode(String staCode);
	
	List<Staff> findByStaName(String staName);
	
	@Query(value = "select max(sta_code) from staff", nativeQuery = true)
	String findMaxStaCode();
}
