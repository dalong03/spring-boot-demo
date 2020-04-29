package com.fb.springbootdemo.service;

import java.util.Map;

import org.apache.poi.ss.usermodel.Workbook;

import com.fb.springbootdemo.model.Staff;

public interface StaffService {
	Staff save(Staff staff);
	
	Map<String, Object> importData(Workbook wb);

}
