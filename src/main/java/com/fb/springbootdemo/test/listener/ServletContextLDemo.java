package com.fb.springbootdemo.test.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.springframework.stereotype.Component;

@WebListener
@Component
public class ServletContextLDemo implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent sce) {
	}

	public void contextInitialized(ServletContextEvent sce) {

		ServletContext ctx = sce.getServletContext();

		ctx.setAttribute("greet", "hello");
	}

}
