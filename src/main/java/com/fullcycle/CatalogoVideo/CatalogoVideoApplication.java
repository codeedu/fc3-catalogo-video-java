package com.fullcycle.CatalogoVideo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CatalogoVideoApplication {

	public static void main(String[] args) {
		System.setProperty("spring.profiles.default", "development");
		SpringApplication.run(CatalogoVideoApplication.class, args);
	}
}