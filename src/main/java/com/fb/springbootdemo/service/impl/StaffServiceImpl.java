package com.fb.springbootdemo.service.impl;

import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fb.springbootdemo.model.Department;
import com.fb.springbootdemo.model.Staff;
import com.fb.springbootdemo.repository.DepartmentRepository;
import com.fb.springbootdemo.repository.StaffRepository;
import com.fb.springbootdemo.service.StaffService;

@Service
public class StaffServiceImpl implements StaffService {

	@Autowired
	private StaffRepository staffRepository;
	@Autowired
	private DepartmentRepository departmentRepository;

	@Override
	@Transactional
	public Staff newStaff(Staff newStaff) {
		String maxCode = staffRepository.findMaxStaCode();
		Integer code = Integer.valueOf(maxCode);
		code += 1;
		String staCode = null;
		if (code < 100) {
			staCode = "0" + code;
		} else {
			staCode = code.toString();
		}
		newStaff.setStaCode(staCode);

		Department department = departmentRepository.findByDepName(newStaff.getDepName());
		newStaff.setDepCode(department.getDepCode());
		newStaff.setStaId(UUID.randomUUID().toString().replace("-", ""));
		return staffRepository.save(newStaff);
	}

}
