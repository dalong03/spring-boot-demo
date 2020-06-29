package com.fb.springbootdemo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fb.springbootdemo.model.PageInfo;
import com.fb.springbootdemo.model.T1;
import com.fb.springbootdemo.repository.T1Repository;
import com.fb.springbootdemo.service.T1Service;

@RestController
@RequestMapping(value = "/t1")
public class T1Controller {
	private Logger logger = LoggerFactory.getLogger(T1Controller.class);

	@Autowired
	private T1Service t1Service;
	@Autowired
	private T1Repository t1Repository;

	@RequestMapping(value = "/html", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView view = new ModelAndView("t1");
		return view;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Map<String, Object> get(ServletRequest request, ServletResponse response, String name,
			@PathVariable("id") Integer id) {
		T1 t1 = t1Repository.find(id);
		Map<String, Object> out = new HashMap<>();
		out.put("name", "tom");
//		out.put("t1", t1);
		return out;
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public Map<String, Object> findList(ServletRequest request, ServletResponse response, T1 t1, PageInfo pageInfo,
			String order1) {
		T1 order = null;
		if (order1 != null && order1 != "") {
			order = new T1();
			order.setName("");
		}
		List<T1> t1s = t1Service.findPage(t1, order, pageInfo);
		Map<String, Object> out = new HashMap<>();
		out.put("name", "tom");
		out.put("t1s", t1s);
		return out;
	}

	@RequestMapping(method = RequestMethod.POST)
	public Map<String, Object> post(@RequestBody Map<String, Object> in) throws InterruptedException {
		Map<String, Object> out = new HashMap<>();
		out.put("name", "jerry");
		return out;
	}

	@RequestMapping(value = "", method = RequestMethod.PUT)
	public T1 update(@RequestBody T1 t1) {
		return t1Service.update(t1);
	}

	@RequestMapping(value = "/locklevel", method = RequestMethod.PUT)
	public Map<String, Object> optimisticLockTest() {
		return t1Service.lockLevelTest();
	}

}
