package com.fb.springbootdemo.common;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Component
@RestControllerAdvice
public class GenericExceptionHandler {

	@ExceptionHandler({ ServiceException.class })
	public Map<String, Object> serviceExceptionHandle(ServiceException e) {
		Map<String, Object> m = new HashMap<>();
		m.put("code", e.getErrorCode().getCode());
		m.put("message", e.getErrorCode().getMsg());
		return m;
	}

}
