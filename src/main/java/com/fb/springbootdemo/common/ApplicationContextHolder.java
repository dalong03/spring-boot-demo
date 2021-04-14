package com.fb.springbootdemo.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextHolder implements ApplicationContextAware, DisposableBean {
	private static ApplicationContext applicationContext = null;
	private static Logger logger = LoggerFactory.getLogger(ApplicationContextHolder.class);

	/**
	 * 注入spring context
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		ApplicationContextHolder.applicationContext = applicationContext;
	}

	/**
	 * 获取 spring context
	 * 
	 * @return
	 */
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/**
	 * 获取 bean
	 * 
	 * @param name
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) {
		return (T) applicationContext.getBean(name);
	}

	/**
	 * 获取 bean
	 */
	public static <T> T getBean(Class<T> clazz) {
		return (T) applicationContext.getBean(clazz);
	}

	/**
	 * spring context关闭时，清理静态变量
	 */
	@Override
	public void destroy() throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("清除SpringContextHolder中的ApplicationContext:" + applicationContext);
		}
		applicationContext = null;
	}

}
