package com.useb.tester;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@SpringBootApplication
@EnableAsync
public class TesterApplication {

	public static void main(String[] args) {
		SpringApplication.run(TesterApplication.class, args);
	}

	@Bean
	public Executor taskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(50);
		executor.setQueueCapacity(10);
		executor.setMaxPoolSize(50);
		executor.setThreadNamePrefix("UseB-Thread");
		executor.initialize();
		return executor;
	}

}
