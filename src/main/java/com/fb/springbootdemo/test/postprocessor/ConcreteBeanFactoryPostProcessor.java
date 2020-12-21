package com.fb.springbootdemo.test.postprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

@Component
public class ConcreteBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
    	BeanDefinition beanDefinition = beanFactory.getBeanDefinition("postBean");
    	System.out.println("beanDefinition = " + beanDefinition);
        MutablePropertyValues propertyValues = beanDefinition.getPropertyValues();
        propertyValues.addPropertyValue("author", "throwable");
    }
}
