package com.fb.springbootdemo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fb.springbootdemo.model.Salary;

@Repository
public interface SalaryRepository extends CrudRepository<Salary, String> {
	
	@Query(value = "select * from salary t where t.sta_code = :staCode and t.issue_year = :issueYear and t.issue_month = :issueMonth and t.type = :type", nativeQuery = true)
	Salary findSalary(String staCode, String issueYear, String issueMonth, String type);

}
