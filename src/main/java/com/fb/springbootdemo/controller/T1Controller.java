package com.fb.springbootdemo.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fb.springbootdemo.entity.PageInfo;
import com.fb.springbootdemo.entity.T1;
import com.fb.springbootdemo.repository.T1Repository;

@RestController
@RequestMapping("/t1")
public class T1Controller {
	@Autowired
	private T1Repository t1Repository;

	@RequestMapping(value = "/{id}")
	public Map<String, Object> find(HttpServletRequest req, HttpServletResponse res, @PathVariable("id") Integer id) {
		Map<String, Object> ret = new HashMap<>();
		T1 t1 = t1Repository.selectById(id);
		ret.put("t1", t1);
		return ret;
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public Map<String, Object> page(ServletRequest request, ServletResponse response, T1 t1, PageInfo pageInfo) {
		Map<String, Object> ret = new HashMap<>();
		QueryWrapper<T1> wrapper = new QueryWrapper<T1>();
		Page<T1> page = t1Repository.selectPage(new Page<T1>(), wrapper);
		ret.put("page", page);
		return ret;
	}

	@RequestMapping(method = RequestMethod.POST)
	public Map<String, Object> post(ServletRequest request, ServletResponse response) {
		Map<String, Object> ret = new HashMap<>();
		T1 t1 = new T1();
		t1.setName(UUID.randomUUID().toString());
		t1Repository.insert(t1);
		ret.put("flag", "1");
		return ret;
	}
}
