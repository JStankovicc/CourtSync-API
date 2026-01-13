package com.example.MQService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class MqServiceApplication {

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(MqServiceApplication.class, args);
		System.out.println("MQService running... (external TCP ActiveMQ broker)");

		Thread.currentThread().join();
	}

}
