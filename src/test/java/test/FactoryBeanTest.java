package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fb.springbootdemo.SpringBootDemoApplication;
import com.fb.springbootdemo.test.factorybean.Fruit;
import com.fb.springbootdemo.test.factorybean.FruitFactoryBean;

@SpringBootTest(classes = SpringBootDemoApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class FactoryBeanTest {

	@Autowired
	private FruitFactoryBean fruitFactoryBean;

	@Autowired
	private ApplicationContext applicationContext;

	@Test
	public void getObject() throws Exception {
		// 直接通过#getObject获取实例
		Fruit apple = fruitFactoryBean.getObject();
		System.out.println(apple.toString());
		// 通过Spring上下文获取实例
		Fruit fruit = (Fruit) applicationContext.getBean("fruitFactoryBean");
		System.out.println(fruit);
		// 获取FruitFactoryBean自身的实例
		FruitFactoryBean bean = (FruitFactoryBean) applicationContext.getBean("&fruitFactoryBean");
		System.out.println(bean);
	}
}
