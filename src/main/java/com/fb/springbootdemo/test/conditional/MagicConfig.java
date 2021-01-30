package com.fb.springbootdemo.test.conditional;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
//@Conditional(MagicExistsCondition.class)
@ConditionalOnClass(MagicExistsCondition2.class)
public class MagicConfig {

	@Bean
//	@Conditional(MagicExistsCondition.class)
	public MagicBean magicBean() {
		return new MagicBean();
	}

	@Bean
	@ConditionalOnClass(MagicExistsCondition.class)
	public MagicBean2 magicBean2() {
		return new MagicBean2();
	}

}
