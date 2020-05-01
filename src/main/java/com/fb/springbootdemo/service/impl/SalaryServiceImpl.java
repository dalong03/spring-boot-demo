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
	public Map<String, Object> importData(Workbook wb, String fileName) {
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

		// 获取薪水的码值
		Map<String, Object> wageMap = new HashMap<>();
		List<CommonCode> commonCodeList = commonCodeRepository.findByCodeField(CommonCodeConstant.WAGE_TYPE);
		for (CommonCode code : commonCodeList) {
			wageMap.put(code.getCodeValue(), code.getCodeKey());
		}

		Sheet sheet = wb.getSheetAt(0);

		// 获取薪水发放月份
		String issueMonthStr = sheet.getSheetName();
		Integer issueMonth = null;
		try {
			issueMonth = Integer.valueOf(issueMonthStr);
		} catch (Exception e) {
			throw new ServiceException(ErrorCodeEnum.INVALID_SHEET_NAME);
		}

		Row row = sheet.getRow(0);
		int cellNum = row.getPhysicalNumberOfCells();

		// 薪水类别
		String[] wageTypeArray = new String[cellNum - 4];// 薪水类别数组
		for (int i = 4; i < cellNum; i++) {
			String wageType = (String) ExcelUtils.getCellValue(row.getCell(4));// 薪水类别
			if (wageMap.get(wageType) == null) {
				throw new ServiceException(ErrorCodeEnum.INVALID_COMMON_CODE);
			}
			wageTypeArray[i - 4] = wageType;
		}

		int endRow = sheet.getPhysicalNumberOfRows();
		for (int i = 1; i < endRow; i++) {
			row = sheet.getRow(i);
			if (null == row) {
				continue;
			}
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
						Map<String, Object> failureMap = new HashMap<>();
						failureMap.put("staName", staName);
						failureMap.put("remark", "系统内此姓名存在多个，请输入员工编号加以确认");
						failureList.add(failureMap);
						continue;
					} else {
						Map<String, Object> failureMap = new HashMap<>();
						failureMap.put("staCode", staCode);
						failureMap.put("staName", staName);
						failureMap.put("remark", "系统内无此人员，请先录入");
						failureList.add(failureMap);
						continue;
					}
				} else {
					Map<String, Object> failureMap = new HashMap<>();
					failureMap.put("remark", "请录入员工编号或员工姓名");
					failureList.add(failureMap);
					continue;
				}
			}

			Salary salary = new Salary();
			salary.setStaId(staff.getStaId());
			salary.setStaCode(staff.getStaCode());
			salary.setStaName(staff.getStaName());
			salary.setIssueYear(issueYear);
			salary.setIssueMonth(issueMonth);

			for (String wageType : wageTypeArray) {
				Map<String, Object> staMap = new HashMap<>();
				staMap.put("staCode", staff.getStaCode());
				staMap.put("staName", staff.getStaName());

				Cell cell3 = row.getCell(4);
				BigDecimal wage = (BigDecimal) ExcelUtils.getCellValue(cell3);
				if (wage == null) {
					continue;
				} else {
					salary.setWage(wage);
					salary.setType((String) wageMap.get(wageType));
					salary.setTypeDesc(wageType);

					try {
						salaryRepository.save(salary);
					} catch (Exception e) {
						logger.error("保存员工失败");
						e.printStackTrace();
						staMap.put("remark", "保存员工失败");
						failureList.add(staMap);
						continue;
					}
				}
				successList.add(staMap);
			}

		}
		retMap.put("successList", successList);
		retMap.put("failureList", failureList);
		return retMap;
	}

}
