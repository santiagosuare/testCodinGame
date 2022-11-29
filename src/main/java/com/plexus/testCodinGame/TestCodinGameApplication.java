package com.plexus.testCodinGame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class TestCodinGameApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestCodinGameApplication.class, args);
	}

}
