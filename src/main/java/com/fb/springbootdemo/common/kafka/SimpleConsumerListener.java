package com.fb.springbootdemo.common.kafka;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class SimpleConsumerListener {
	private final static Logger logger = LoggerFactory.getLogger(SimpleConsumerListener.class);
	private final CountDownLatch latch1 = new CountDownLatch(1);

	@KafkaListener(id = "foo", topics = "topic-test")
	public void listen(byte[] records) throws UnsupportedEncodingException {
		System.out.println("=====================================================");
		System.out.println("=====================================================");
		logger.info(new String(records, "ASCII"));
		this.latch1.countDown();
	}
}
