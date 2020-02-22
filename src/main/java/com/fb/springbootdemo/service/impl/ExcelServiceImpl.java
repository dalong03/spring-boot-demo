package com.fb.springbootdemo.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fb.springbootdemo.model.Salary;
import com.fb.springbootdemo.model.Staff;
import com.fb.springbootdemo.repository.SalaryRepository;
import com.fb.springbootdemo.repository.StaffRepository;
import com.fb.springbootdemo.service.ExcelService;
import com.fb.springbootdemo.service.StaffService;
import com.fb.springbootdemo.utils.excel.ExcelReader2;

@Service
public class ExcelServiceImpl implements ExcelService {

	@Autowired
	private StaffService staffService;
	@Autowired
	private StaffRepository staffRepository;
	@Autowired
	private SalaryRepository salaryRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(ExcelServiceImpl.class);

	@Override
	public Map<String, Object> importExcel() {
		Map<String, Object> ret = new HashMap<>();
		List<Map<String, Object>> successList = new ArrayList<>();
		List<Map<String, Object>> failureList = new ArrayList<>();
		String excelFileName = "D:\\develop\\excel\\2018年工资.xlsx";
		// 读取Excel文件内容
		Map<String, Object> excelParam = new HashMap<>();
		excelParam.put("startSheet", 1);
//		excelParam.put("endSheet", 2);
		excelParam.put("startRow", 3);
//		excelParam.put("endRow", 5);
		Integer[] columns = { 1, 2, 8, 25, 29, 31 };
		excelParam.put("columns", columns);
		excelParam.put("numericScale", "0");
		excelParam.put("needSheetName", true);
		Map<String, Object> excelMap = ExcelReader2.readExcel(excelFileName, excelParam);
		List<Map<String, Object>> excelSuccessList = (List<Map<String, Object>>)excelMap.get("successList");
		List<Map<String, Object>> excelFailureList = (List<Map<String, Object>>)excelMap.get("failureList");
		logger.info("导入数据开始");
		Map<String, Object> dataMap = null;
		ExecutorService executorService = null;
		for (int i = 0; i < excelSuccessList.size(); i++) {
			dataMap = excelSuccessList.get(i);
			if (dataMap.get("c2") != null) {
				ImportData importData = new ImportData();
				importData.setSalaryRepository(salaryRepository);
				importData.setStaffRepository(staffRepository);
				importData.setStaffService(staffService);
				importData.setSuccessList(successList);
				importData.setDataMap(dataMap);
				executorService = Executors.newFixedThreadPool(4);
				executorService.submit(importData);
			}
		}
		executorService.shutdown();
		while(!executorService.isTerminated()) {
			try {
				logger.info("导入未完成");
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		logger.info("导入数据结束");
		ret.put("successList", successList);
		ret.put("failureList", failureList);
		return ret;
	}
	
	public static class ImportData implements Runnable{
		private StaffService staffService;
		private StaffRepository staffRepository;
		private SalaryRepository salaryRepository;
		private Map<String, Object> dataMap;
		private List<Map<String, Object>> successList;

		public void setStaffService(StaffService staffService) {
			this.staffService = staffService;
		}

		public void setStaffRepository(StaffRepository staffRepository) {
			this.staffRepository = staffRepository;
		}

		public void setSalaryRepository(SalaryRepository salaryRepository) {
			this.salaryRepository = salaryRepository;
		}

		public void setDataMap(Map<String, Object> dataMap) {
			this.dataMap = dataMap;
		}

		public void setSuccessList(List<Map<String, Object>> successList) {
			this.successList = successList;
		}

		@Override
		public void run() {
			String staffName = (String) dataMap.get("c2");
			List<Staff> staffs = staffRepository.findByStaName(staffName);
			Staff staff = null;
			if (staffs.size() == 0) {
				Staff newStaff = new Staff();
				newStaff.setStaName(staffName);
				newStaff.setDepName((String) dataMap.get("c1"));
				staff = staffService.newStaff(newStaff);
			} else {
				staff = staffs.get(0);
			}
			Salary salary = new Salary();
			salary.setStaCode(staff.getStaCode());
			salary.setStaName(staff.getStaName());
			salary.setIssueYear("2018");
			  
			String regEx="[^0-9]";  
			Pattern pattern = Pattern.compile(regEx);
			Matcher matcher = null;
			String sheetName = (String)dataMap.get("sheetName");
			matcher = pattern.matcher(sheetName);
			String month = matcher.replaceAll("");
			if(month.length() == 1) {
				month = "0" + month;
			}
			salary.setIssueMonth(month);
			for (String key : dataMap.keySet()) {
				if (key != null) {
					switch (key) {
					case "c8":
						salary.setSalary((BigDecimal) dataMap.get("c8"));
						salary.setType("01");
						salary.setTypeDesc("标准");
						break;
					case "c25":
						salary.setSalary((BigDecimal) dataMap.get("c25"));
						salary.setType("02");
						salary.setTypeDesc("应发");
						break;
					case "c29":
						salary.setSalary((BigDecimal) dataMap.get("c29"));
						salary.setType("03");
						salary.setTypeDesc("实发");
						break;
					case "c31":
						salary.setSalary((BigDecimal) dataMap.get("c31"));
						salary.setType("04");
						salary.setTypeDesc("人工成本");
						break;
					default:
						continue;
					}
					Salary exist = salaryRepository.findSalary(salary.getStaCode(), salary.getIssueYear(), salary.getIssueMonth(), salary.getType());
					if(exist == null) {
						salary.setSalId(UUID.randomUUID().toString().replace("-", ""));
						salaryRepository.save(salary);
					}
				}
			}
			
			successList.add(dataMap);
		}
		
	}

}
