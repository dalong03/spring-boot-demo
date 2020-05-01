package com.fb.springbootdemo.service;

import java.util.Map;

import org.apache.poi.ss.usermodel.Workbook;

import com.fb.springbootdemo.model.Salary;

public interface SalaryService {
	Salary save(Salary salary);

	Map<String, Object> importData(Workbook wb, String fileName, String flag);

}
