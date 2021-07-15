package com.fb.springbootdemo.controller;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/template")
public class Z_ExcelController {
	private Logger log = LoggerFactory.getLogger(Z_ExcelController.class);

	@RequestMapping("/resume")
	public void downloadExcelTemplate(HttpServletResponse response) throws Exception {
		log.info("履历模板下载");
		ClassPathResource classPathResource = new ClassPathResource("template/resume.xlsx");
		Workbook workbook = null;
		try {
			InputStream inputStream = classPathResource.getInputStream();
			workbook = new XSSFWorkbook(inputStream);
			
			Sheet sheet = workbook.getSheetAt(0);
			Row row = sheet.getRow(2);
			Cell cell  = row.getCell(5);
			cell.setCellValue("2000 年");
			
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("resume.xlsx", "utf-8"));
			response.setHeader("Access-Control-Expose-Headers", "content-Disposition");
			OutputStream outputStream = response.getOutputStream();
			workbook.write(outputStream);
			outputStream.flush();
			outputStream.close();
			log.info("履历模板下载完成");
		} finally {
			if (workbook != null) {
				workbook.close();
			}
		}
	}

}
