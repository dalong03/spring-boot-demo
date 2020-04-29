package com.fb.springbootdemo.service;

import java.util.Map;

import org.apache.poi.ss.usermodel.Workbook;

import com.fb.springbootdemo.model.Department;

public interface DepartmentService {
	Department saveDepartment(Department department);

	Map<String, Object> importData(Workbook wb);

}
