package com.fb.springbootdemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fb.springbootdemo.model.Salary;
import com.fb.springbootdemo.repository.SalaryRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SalaryTest {

	@Autowired
	private SalaryRepository salaryRepository;

	@Test
	public void findSalary() throws Exception {
		Salary staCode = salaryRepository.findSalary("001", "2018", "01", "01");
		System.out.println(staCode);
	}
}
