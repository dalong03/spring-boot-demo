package com.fb.springbootdemo.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.transaction.Transactional;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fb.springbootdemo.model.Department;
import com.fb.springbootdemo.repository.DepartmentRepository;
import com.fb.springbootdemo.service.DepartmentService;
import com.fb.springbootdemo.utils.ExcelUtils;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	private static final Logger logger = LoggerFactory.getLogger(DepartmentServiceImpl.class);

	@Autowired
	private DepartmentRepository DepartmentRepository;
	@Autowired
	private DepartmentRepository departmentRepository;

	@Override
	@Transactional
	public Department save(Department department) {
		return DepartmentRepository.save(department);
	}

	@Override
	public Map<String, Object> importData(Workbook wb) {
		Map<String, Object> retMap = new HashMap<>();
		List<Map<String, Object>> successList = new ArrayList<>();
		List<Map<String, Object>> failureList = new ArrayList<>();

		Sheet sheet = wb.getSheetAt(0);
		int endRow = sheet.getPhysicalNumberOfRows();
		Iterable<Department> deps = departmentRepository.findAll();
		Set<String> depSets = new HashSet<>();
		for (Department d : deps) {
			depSets.add(d.getDepCode());
		}
		for (int i = 1; i < endRow; i++) {
			Row row = sheet.getRow(i);
			if (null == row) {
				continue;
			}
			Cell cell1 = row.getCell(1);
			String depCode = (String) ExcelUtils.getCellValue(cell1);
			Cell cell2 = row.getCell(2);
			String depName = (String) ExcelUtils.getCellValue(cell2);
			if (depSets.contains(depCode)) {
				Map<String, Object> failureMap = new HashMap<String, Object>();
				failureMap.put("depCode", depCode);
				failureMap.put("depName", depName);
				failureMap.put("remark", "重复的部门编号");
				failureList.add(failureMap);
				continue;
			}
			Department department = new Department();
			department.setDepName(depName);
			department.setDepCode(depCode);

			Cell cell3 = row.getCell(3);
			String depShortName = (String) ExcelUtils.getCellValue(cell3);
			department.setDepShortName(depShortName);
			Cell cell4 = row.getCell(4);
			String leader = (String) ExcelUtils.getCellValue(cell4);
			department.setLeader(leader);
			
			Map<String, Object> depMap = new HashMap<String, Object>();
			depMap.put("depCode", depCode);
			depMap.put("depName", depName);
			
			try {
				departmentRepository.save(department);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("部门保存失败");
				depMap.put("remark", "数据保存失败");
				failureList.add(depMap);
				continue;
			}
			successList.add(depMap);
		}

		retMap.put("successList", successList);
		retMap.put("failureList", failureList);
		return retMap;
	}

}
