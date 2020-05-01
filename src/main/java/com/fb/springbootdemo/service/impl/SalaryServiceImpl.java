package com.fb.springbootdemo.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.fb.springbootdemo.base.ErrorCodeEnum;
import com.fb.springbootdemo.base.ServiceException;
import com.fb.springbootdemo.base.constant.CommonCodeConstant;
import com.fb.springbootdemo.model.CommonCode;
import com.fb.springbootdemo.model.Salary;
import com.fb.springbootdemo.model.Staff;
import com.fb.springbootdemo.repository.CommonCodeRepository;
import com.fb.springbootdemo.repository.SalaryRepository;
import com.fb.springbootdemo.repository.StaffRepository;
import com.fb.springbootdemo.service.SalaryService;
import com.fb.springbootdemo.utils.ExcelUtils;

@Service
public class SalaryServiceImpl implements SalaryService {

	private static final Logger logger = LoggerFactory.getLogger(SalaryServiceImpl.class);

	@Autowired
	private SalaryRepository salaryRepository;
	@Autowired
	private StaffRepository staffRepository;
	@Autowired
	private CommonCodeRepository commonCodeRepository;

	@Override
	@Transactional
	public Salary save(Salary salary) {
		return salaryRepository.save(salary);
	}

	@Override
	public Map<String, Object> importData(Workbook wb, String fileName, String flag) {
		Map<String, Object> retMap = new HashMap<>();
		List<Map<String, Object>> successList = new ArrayList<>();
		List<Map<String, Object>> failureList = new ArrayList<>();

		// 获取薪水发放年份
		Integer issueYear = null;
		if (fileName.contains("年")) {
			int index = fileName.indexOf("年");
			try {
				issueYear = Integer.valueOf(fileName.substring(0, index));
			} catch (Exception e) {
				throw new ServiceException(ErrorCodeEnum.INVALID_FILE_NAME);
			}
		} else {
			throw new ServiceException(ErrorCodeEnum.INVALID_FILE_NAME);
		}

		// 获取薪水发放月份
		Sheet sheet = wb.getSheetAt(0);
		String issueMonthStr = sheet.getSheetName();
		if (issueMonthStr.contains("月")) {
			issueMonthStr = issueMonthStr.replace("月", "");
		}
		Integer issueMonth = null;
		try {
			issueMonth = Integer.valueOf(issueMonthStr);
		} catch (Exception e) {
			throw new ServiceException(ErrorCodeEnum.INVALID_SHEET_NAME);
		}

		// 序号校验
		int endRow = sheet.getPhysicalNumberOfRows();
		for (int i = 1; i < endRow; i++) {
			Row row = sheet.getRow(i);
			String sequence = (String) ExcelUtils.getCellValue(row.getCell(0));
			if (!StringUtils.hasText(sequence)) {
				throw new ServiceException(ErrorCodeEnum.EXCEL_BLANK_VALUE);
			}
		}

		// 获取薪水的码值
		Map<String, Object> wageMap = new HashMap<>();
		List<CommonCode> commonCodeList = commonCodeRepository.findByCodeField(CommonCodeConstant.WAGE_TYPE);
		if (commonCodeList.size() == 0) {
			throw new ServiceException(ErrorCodeEnum.INVALID_COMMON_CODE, CommonCodeConstant.WAGE);
		}
		for (CommonCode code : commonCodeList) {
			wageMap.put(code.getCodeValue(), code.getCodeKey());
		}

		// 薪水类别
		Row row = sheet.getRow(0);
		int cellNum = row.getPhysicalNumberOfCells();
		String[] wageTypeArray = new String[cellNum - 4];// 薪水类别数组
		for (int i = 4; i < cellNum; i++) {
			String wageType = (String) ExcelUtils.getCellValue(row.getCell(i));// 薪水类别
			if (wageMap.get(wageType) == null) {
				throw new ServiceException(ErrorCodeEnum.INCOMPLETE_CODE, CommonCodeConstant.WAGE);
			}
			wageTypeArray[i - 4] = wageType;
		}

		for (int i = 1; i < endRow; i++) {
			row = sheet.getRow(i);
			if (null == row) {
				continue;
			}
			
			//反参的successList failureList所需对象
			String sequence = (String) ExcelUtils.getCellValue(row.getCell(0));
			Map<String, Object> salaryMap = new HashMap<>();
			salaryMap.put("sequence", sequence);
			
			Cell cell1 = row.getCell(2);
			String staCode = (String) ExcelUtils.getCellValue(cell1);
			Staff staff = null;
			if (StringUtils.hasText(staCode)) {
				staff = staffRepository.findByStaCode(staCode);
			} else {
				Cell cell2 = row.getCell(3);
				String staName = (String) ExcelUtils.getCellValue(cell2);
				if (StringUtils.hasText(staName)) {
					List<Staff> staffList = staffRepository.findByStaName(staName);
					if (staffList.size() == 1) {
						staff = staffList.get(0);
					} else if (staffList.size() > 1) {
						salaryMap.put("staName", staName);
						salaryMap.put("remark", "系统内此姓名存在多个，请输入员工编号加以确认");
						failureList.add(salaryMap);
						continue;
					} else {
						salaryMap.put("staCode", staCode);
						salaryMap.put("staName", staName);
						salaryMap.put("remark", "系统内无此人员，请先录入");
						failureList.add(salaryMap);
						continue;
					}
				} else {
					salaryMap.put("remark", "请录入员工编号或员工姓名");
					failureList.add(salaryMap);
					continue;
				}
			}

			Salary salary = new Salary();
			salary.setStaId(staff.getStaId());
			salary.setStaCode(staff.getStaCode());
			salary.setStaName(staff.getStaName());
			salary.setIssueYear(issueYear);
			salary.setIssueMonth(issueMonth);

			for (int j = 0; j < wageTypeArray.length; j++) {
				Cell cell3 = row.getCell(4 + j);
				Object wage = ExcelUtils.getCellValue(cell3);
				if (wage == null) {
					continue;
				}

				// 成功或失败的map
				salaryMap.put("staCode", staff.getStaCode());
				salaryMap.put("staName", staff.getStaName());

				// 当年当月该类型工资的幂等校验
				String wageType = wageTypeArray[j];
				Salary checkSalary = salaryRepository.findSalary(staff.getStaId(), issueYear, issueMonth,
						(String) wageMap.get(wageType));
				if (!"update".equals(flag) && checkSalary != null) {
					salaryMap.put("remark", "该员工" + issueYear + "年" + issueMonth + "月" + wageType + "已登记");
					failureList.add(salaryMap);
					continue;
				}

				salary.setWage(new BigDecimal(ExcelUtils.getCellValue(cell3).toString()));
				salary.setType((String) wageMap.get(wageType));
				salary.setTypeDesc(wageType);

				try {
					if (!"update".equals(flag)) {
						salaryRepository.save(salary);
					} else if (checkSalary != null) {
						salaryRepository.update(salary.getSalId(), salary.getWage());
					} else {
						salaryMap.put("remark", "该员工" + issueYear + "年" + issueMonth + "月" + wageType + "未登记");
					}
				} catch (Exception e) {
					logger.error("保存员工失败");
					e.printStackTrace();
					salaryMap.put("remark", "保存员工失败");
					failureList.add(salaryMap);
					continue;
				}
				successList.add(salaryMap);
			}

		}
		retMap.put("successList", successList);
		retMap.put("failureList", failureList);
		return retMap;
	}

}
