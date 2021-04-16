package com.fb.springbootdemo.test;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaProducerTest {
	
	@Autowired
	private KafkaTemplate<Integer, String> kafkaTemplate;
	
	@PostMapping(value = "/sent/{topic}")
	public Map<String, Object> sent(HttpServletRequest req, HttpServletResponse resp, @PathVariable("topic") String topic, @RequestBody JSONObject in){
		Map<String, Object> map = new HashMap<>();
		System.out.println(in.toString());
		
		ListenableFuture<SendResult<Integer, String>> send = kafkaTemplate.send(topic, 1, (String)in.get("id"));
		send.addCallback(new ListenableFutureCallback<SendResult<Integer, String>>() {
			public void onFailure(Throwable throwable) {
				System.out.println(throwable);
			}

			public void onSuccess(SendResult<Integer, String> integerStringSendResult) {
				System.out.println(integerStringSendResult);
			}
		});
		
		map.put("ret", "success");
		return map;
	}

}
