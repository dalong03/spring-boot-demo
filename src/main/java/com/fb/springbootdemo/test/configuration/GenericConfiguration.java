package com.fb.springbootdemo.test.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fb.springbootdemo.test.factorybean.Fruit;

@Configuration
public class GenericConfiguration {

	@Bean
	public Fruit fruit(Variety var) {
		System.out.println(var);
		return new Fruit();
	}
}
