package com.fb.springbootdemo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fb.springbootdemo.service.ExcelService;

@RestController
@RequestMapping("/excel")
public class ExcelController {

	@Autowired
	private ExcelService excelService;

	@RequestMapping(value="/upload",method = RequestMethod.POST)
	public Map<String, Object> uploadImage() {
		Map<String, Object> ret = excelService.importExcel();
		return ret;
	}
}
