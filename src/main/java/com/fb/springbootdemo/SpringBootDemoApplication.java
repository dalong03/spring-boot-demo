package com.fb.springbootdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@EnableJpaAuditing
@MapperScan("com.fb.springbootdemo.repository")
@SpringBootApplication
public class SpringBootDemoApplication {

	static Logger logger = LoggerFactory.getLogger(SpringBootDemoApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(SpringBootDemoApplication.class, args);
		logger.info("======");
	}

}
