package com.fb.springbootdemo.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fb.springbootdemo.model.PageInfo;
import com.fb.springbootdemo.model.T1;
import com.fb.springbootdemo.repository.T1Repository;
import com.fb.springbootdemo.service.T1Service;
import com.fb.springbootdemo.test.routinginject.HelloService;
import com.fb.springbootdemo.test.routinginject.RoutingInject;

@RestController
@RequestMapping(value = "/t1")
public class T1Controller {
	private static Logger logger = LoggerFactory.getLogger(T1Controller.class);

	@Autowired
//	@Resource
	private T1Service t1Service;

	@Autowired
	private Validator validator;

	public T1Service getT1Service() {
		return t1Service;
	}

	@Autowired
	private T1Repository t1Repository;

	@RoutingInject("helloServiceImpl2")
	private HelloService helloService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Map<String, Object> get(ServletRequest request, ServletResponse response, String name,
			@PathVariable("id") Integer id) {
		Optional<T1> t1 = t1Repository.findById(1);
		System.out.println(t1);
		Map<String, Object> out = new HashMap<>();
		out.put("t1", t1);
		return out;
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public Map<String, Object> findList(ServletRequest request, ServletResponse response, T1 t1, PageInfo pageInfo,
			String order1) {
		return null;
	}

	@RequestMapping(method = RequestMethod.POST)
	public Map<String, Object> post(ServletRequest request, ServletResponse response, @RequestBody /*@Valid*/ T1 t1,
			BindingResult result) {
//        if(result.hasErrors()){
//            for (ObjectError error : result.getAllErrors()) {
//                System.out.println(error.getDefaultMessage());
//            }
//        }
//		Set<ConstraintViolation<T1>> violationSet = validator.validate(t1);
//		for (ConstraintViolation<T1> model : violationSet) {
//			System.out.println(model.getPropertyPath());
//			System.out.println(model.getMessage());
//		}
		Map<String, Object> out = new HashMap<>();
//		System.out.println(request.getParameter("name"));
		out.put("name", "jerry");
//		t1.getContacts().stream().forEach(item -> {item.setT1(t1);});
		t1Repository.save(t1);
		return out;
	}

	@RequestMapping(value = "", method = RequestMethod.PUT)
	public T1 update(@RequestBody T1 t1) {
		return t1Repository.save(t1);
	}

}
