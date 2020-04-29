package com.fb.springbootdemo.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.transaction.Transactional;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.fb.springbootdemo.model.Department;
import com.fb.springbootdemo.model.Staff;
import com.fb.springbootdemo.repository.DepartmentRepository;
import com.fb.springbootdemo.repository.StaffRepository;
import com.fb.springbootdemo.service.StaffService;
import com.fb.springbootdemo.utils.ExcelUtils;

@Service
public class StaffServiceImpl implements StaffService {

	private static final Logger logger = LoggerFactory.getLogger(StaffServiceImpl.class);

	@Autowired
	private StaffRepository staffRepository;
	@Autowired
	private DepartmentRepository departmentRepository;

	@Override
	@Transactional
	public Staff save(Staff staff) {
		String maxCode = staffRepository.findMaxStaCode();
		Integer code = Integer.valueOf(maxCode);
		code += 1;
		String staCode = null;
		if (code < 100) {
			staCode = "0" + code;
		} else {
			staCode = code.toString();
		}
		staff.setStaCode(staCode);

		Department department = departmentRepository.findByDepName(staff.getDepName());
		staff.setDepCode(department.getDepCode());
		staff.setStaId(UUID.randomUUID().toString().replace("-", ""));
		return staffRepository.save(staff);
	}

	@Override
	public Map<String, Object> importData(Workbook wb) {
		Map<String, Object> retMap = new HashMap<>();
		List<Map<String, Object>> successList = new ArrayList<>();
		List<Map<String, Object>> failureList = new ArrayList<>();

		Sheet sheet = wb.getSheetAt(0);
		int endRow = sheet.getPhysicalNumberOfRows();
		Iterable<Department> deps = departmentRepository.findAll();
		Set<String> depCodes = new HashSet<>();
		Set<String> depNames = new HashSet<>();
		Map<String, Object> depMaps = new HashMap<>();
		Map<String, Object> depMaps2 = new HashMap<>();
		for (Department d : deps) {
			depCodes.add(d.getDepCode());
			depNames.add(d.getDepName());
			depMaps.put(d.getDepCode(), d.getDepName());
			depMaps2.put(d.getDepCode(), d.getDepId());
		}
		for (int i = 1; i < endRow; i++) {
			Row row = sheet.getRow(i);
			if (null == row) {
				continue;
			}
			Cell cell1 = row.getCell(1);
			String depCode = (String) ExcelUtils.getCellValue(cell1);
			if (!depCodes.contains(depCode)) {
				Map<String, Object> failureMap = new HashMap<>();
				failureMap.put("depCode", depCode);
				failureMap.put("remark", "无此部门编号");
				failureList.add(failureMap);
				continue;
			}
			Cell cell2 = row.getCell(2);
			String depName = (String) ExcelUtils.getCellValue(cell2);
			if (!depNames.contains(depName)) {
				Map<String, Object> failureMap = new HashMap<>();
				failureMap.put("depName", depName);
				failureMap.put("remark", "无此部门名称");
				failureList.add(failureMap);
				continue;
			}
			if (!depMaps.get(depCode).equals(depName)) {
				Map<String, Object> failureMap = new HashMap<>();
				failureMap.put("depCode", depCode);
				failureMap.put("depName", depName);
				failureMap.put("remark", "部门编号和部门名称不匹配");
				failureList.add(failureMap);
				continue;
			}

			Staff staff = new Staff();
			staff.setDepId((Integer) depMaps2.get(depCode));
			staff.setDepCode(depCode);
			staff.setDepName(depName);

			Cell cell3 = row.getCell(3);
			String staCode = (String) ExcelUtils.getCellValue(cell3);
			Cell cell4 = row.getCell(4);
			String staName = (String) ExcelUtils.getCellValue(cell4);
			staff.setStaCode(staCode);
			staff.setStaName(staName);

			Map<String, Object> staMap = new HashMap<>();
			staMap.put("depCode", depCode);
			staMap.put("depName", depName);
			staMap.put("staCode", staCode);
			staMap.put("staName", staName);

			try {
				staffRepository.save(staff);
			} catch (DataIntegrityViolationException e) {
				e.printStackTrace();
				staMap.put("remark", "重复的员工编号");
				failureList.add(staMap);
				continue;
			} catch (Exception e) {
				logger.error("保存员工失败");
				e.printStackTrace();
				staMap.put("remark", "保存员工失败");
				failureList.add(staMap);
				continue;
			}
			successList.add(staMap);
		}
		retMap.put("successList", successList);
		retMap.put("failureList", failureList);
		return retMap;
	}

}
