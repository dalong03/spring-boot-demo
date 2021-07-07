package com.fb.springbootdemo.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fb.springbootdemo.entity.PageInfo;
import com.fb.springbootdemo.entity.T1;
import com.fb.springbootdemo.repository.T1Repository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "T1", tags = "T1-")
@RestController
@RequestMapping("/t1")
public class T1Controller {
	@Autowired
	private T1Repository t1Repository;

	@ApiOperation(value = "获取t1信息", notes = "获取t1信息-")
	@GetMapping(value = "/{id}")
	public Map<String, Object> find(HttpServletRequest req, HttpServletResponse res, @PathVariable("id") Integer id) {
		Map<String, Object> ret = new HashMap<>();
		T1 t1 = t1Repository.selectById(id);
		ret.put("t1", t1);
		return ret;
	}

	@GetMapping(value = "")
	public Map<String, Object> page(ServletRequest request, ServletResponse response, T1 t1, PageInfo pageInfo) {
		Map<String, Object> ret = new HashMap<>();
		QueryWrapper<T1> wrapper = new QueryWrapper<T1>();
		Page<T1> page = t1Repository.selectPage(new Page<T1>(), wrapper);
		ret.put("page", page);
		return ret;
	}

	@PostMapping()
	public Map<String, Object> post(ServletRequest request, ServletResponse response) {
		Map<String, Object> ret = new HashMap<>();
		T1 t1 = new T1();
		t1.setName(UUID.randomUUID().toString());
		t1Repository.insert(t1);
		ret.put("flag", "1");
		return ret;
	}
}
