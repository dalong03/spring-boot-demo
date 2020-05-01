package com.fb.springbootdemo.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fb.springbootdemo.model.Salary;

@Repository
public interface SalaryRepository extends CrudRepository<Salary, String> {
	
	@Query(value = "select * from salary t where t.sta_id = :staId and t.issue_year = :issueYear and t.issue_month = :issueMonth and t.type = :type", nativeQuery = true)
	Salary findSalary(Integer staId, Integer issueYear, Integer issueMonth, String type);
	
	@Query(value = "update salary set wage = :wage where sal_id = :salId", nativeQuery = true)
	Salary update(Integer salId, BigDecimal wage);

}
