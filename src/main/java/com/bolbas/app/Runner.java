package com.bolbas.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;

@SpringBootApplication
public class Runner {

	public static void main(String[] args) {
		// init api for telegram
		ApiContextInitializer.init();

		SpringApplication.run(Runner.class, args);
	}

}
