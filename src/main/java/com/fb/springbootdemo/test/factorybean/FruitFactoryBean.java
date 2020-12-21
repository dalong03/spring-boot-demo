package com.fb.springbootdemo.test.factorybean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@Component
public class FruitFactoryBean implements FactoryBean<Fruit> {

	@Override
	public Fruit getObject() throws Exception {
		Fruit fruit = new Fruit();
		fruit.setColor("red");
		fruit.setName("apple");
		return fruit;
	}

	@Override
	public Class<?> getObjectType() {
		return Fruit.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}
}
