package com.fb.springbootdemo.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("t2")
public class T2Controller {

	@RequestMapping(method = RequestMethod.POST)
	public String jsonp(HttpServletRequest req, HttpServletResponse res) {
		String callback = req.getParameter("callback");
		String content = callback + "({\"id\":\"00\"})";
//		try {
//			PrintWriter w = res.getWriter();
//			w.write(content);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		return content;
	}
}
