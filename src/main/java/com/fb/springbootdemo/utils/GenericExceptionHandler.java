package com.fb.springbootdemo.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Component
@ControllerAdvice
public class GenericExceptionHandler {

	@ExceptionHandler(value = RuntimeException.class)
	@ResponseBody
	public Map<String, Object> handleException(RuntimeException e) {
		Map<String, Object> m = new HashMap<>();
		m.put("code", "99999");
		m.put("message", e.getMessage());
		return m;
	}

}
