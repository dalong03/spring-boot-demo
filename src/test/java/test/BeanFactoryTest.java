package test;

import org.junit.Test;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;

import com.fb.springbootdemo.controller.T3Controller;
import com.fb.springbootdemo.repository.T3Repository;
import com.fb.springbootdemo.service.T3Service;
import com.fb.springbootdemo.test.conditional.MagicBean;
import com.fb.springbootdemo.test.conditional.MagicConfig;
import com.fb.springbootdemo.test.factorybean.Fruit;
import com.fb.springbootdemo.test.listener.Email;
import com.fb.springbootdemo.test.listener.EmailEvent;

public class BeanFactoryTest {

	@SuppressWarnings({ "deprecation" })
	@Test
	public void test1() {
		ClassPathResource resource = new ClassPathResource("applicationContext.xml");
		XmlBeanFactory factory = new XmlBeanFactory(resource);
		T3Controller t1Controller = (T3Controller) factory.getBean("t1Controller");
//		T1Service t1Service = (T1Service) factory.getBean("t1Service");
		System.out.println(t1Controller);
	}

	@SuppressWarnings("resource")
	@Test
	public void test2() {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		Fruit c = (Fruit)ctx.getBean("fruit");
		System.out.println(c);
	}

	@Test
	public void test3() {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
		System.out.println(ac);
	}

	@Test
	public void test4() {
		DefaultResourceLoader rl = new DefaultResourceLoader();
		System.out.println(rl.getClassLoader());
		System.out.println(rl.getResource("applicationContext.xml"));
	}

	@Configuration
	public static class AppConfig {

		@Bean
		public A a() {
			return new A();
		}

		@Bean
		public B b() {
			return new B();
		}
	}

	public static class A {

		public A() {
			System.out.println("Call A constructor");
		}
	}

	public static class B {

		public B() {
			System.out.println("Call B constructor");
		}
	}
}
