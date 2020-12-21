package com.fb.springbootdemo.test;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
//@EnableScheduling // 可以在启动类上注解也可以在当前文件
public class ScheduledTest {

	@Scheduled(cron = "0/5 * * * * ?")
	public void runfirst() {
		System.out.println("********first job is ok******");
	}
	
//	@Scheduled(cron = "0 23 20 28 11 ?")
	public void runfourth() {
		System.out.println("********fourth job is ok******");
	}

//	@Scheduled(fixedRate = 1000 * 10)
	public void runsecend() {
		System.out.println("********second job is ok******");
	}

//	@Scheduled(fixedDelay = 1000)
	public void runThird() {
		System.out.println("********third job is ok*******");
	}
}
