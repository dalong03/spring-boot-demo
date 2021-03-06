package com.fb.springbootdemo.test.routinginject;

import java.lang.reflect.Field;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class RoutingInjectProcessor implements BeanPostProcessor {

	@Autowired
	private ApplicationContext applicationContext;

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		Class<?> targetCls = bean.getClass();
		Field[] targetFld = targetCls.getDeclaredFields();
		for (Field field : targetFld) {
			// 找到制定目标的注解类
			if (field.isAnnotationPresent(RoutingInject.class)) {
				if (!field.getType().isInterface()) {
					throw new BeanCreationException("RoutingInjected field must be declared as an interface:"
							+ field.getName() + " @Class " + targetCls.getName());
				}
				try {
					this.handleRoutingInjected(field, bean, field.getType());
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		return bean;
	}

	/**
	 * @param field
	 * @param bean
	 * @param type
	 * @throws IllegalAccessException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void handleRoutingInjected(Field field, Object bean, Class type) throws IllegalAccessException {
		Map<String, Object> candidates = this.applicationContext.getBeansOfType(type);
		field.setAccessible(true);
		if (candidates.size() == 1) {
			field.set(bean, candidates.values().iterator().next());
		} else if (candidates.size() == 2) {
			String injectVal = field.getAnnotation(RoutingInject.class).value();
			Object proxy = RoutingBeanProxyFactory.createProxy(injectVal, type, candidates);
			field.set(bean, proxy);
		} else {
			throw new IllegalArgumentException("Find more than 2 beans for type: " + type);
		}
	}
}