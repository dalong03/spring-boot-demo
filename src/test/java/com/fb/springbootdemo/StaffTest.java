package com.fb.springbootdemo;

import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fb.springbootdemo.model.Staff;
import com.fb.springbootdemo.repository.StaffRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
public class StaffTest {

	@Autowired
	private StaffRepository staffRepository;

	@Test
	public void findMaxStaCode() throws Exception {
		Staff staff = new Staff();
		staff.setStaId("1");
		staff.setStaCode("1");
		staff.setStaName("1");
		Staff s1 = staffRepository.save(staff);
		System.out.println(s1.toString());
		staff.setStaId("2");
		Staff s2 = staffRepository.save(staff);
		System.out.println(s2.toString());
	}
}
