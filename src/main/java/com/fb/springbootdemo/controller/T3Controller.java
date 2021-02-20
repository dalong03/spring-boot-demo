package com.fb.springbootdemo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fb.springbootdemo.model.T3;
import com.fb.springbootdemo.repository.T3Repository;

@RestController
@RequestMapping("/t3")
public class T3Controller {
	@Autowired
	private T3Repository t3Repository;

	@RequestMapping(value = "/{id}")
	public Map<String, Object> find(HttpServletRequest req, HttpServletResponse res, @PathVariable("id") Integer id) {
		Map<String, Object> ret = new HashMap<>();
		T3 t3 = t3Repository.findById(1);
		ret.put("t3", t3);
		return ret;
	}
}
