package com.fb.springbootdemo.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.fb.springbootdemo.service.SalaryService;

@RestController
@RequestMapping("/salary")
public class SalaryController {

	@Autowired
	private SalaryService salaryService;

	@RequestMapping(value = "/import", method = RequestMethod.POST)
	public Map<String, Object> uploadImage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String flag = (String)req.getAttribute("flag");//保存或更新
		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) req;
		Iterator<String> iter = multiRequest.getFileNames();
		if (iter.hasNext()) {
			String fileName = iter.next();
			InputStream is = multiRequest.getFile(fileName).getInputStream();
			Workbook wb = new XSSFWorkbook(is);
			return salaryService.importData(wb, fileName, flag);
		}
		return null;
	}
}
