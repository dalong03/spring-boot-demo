package com.fb.springbootdemo.test.postprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)
@Component
public class ConcreteBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
//        if (beanName.contains("postBean"))
//        System.out.println(String.format("Bean初始化之前,bean:%s,beanName:%s", bean.toString(), beanName));
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//        if (beanName.contains("postBean"))
//        System.out.println(String.format("Bean初始化之后,bean:%s,beanName:%s", bean.toString(), beanName));
        return bean;
    }
}
