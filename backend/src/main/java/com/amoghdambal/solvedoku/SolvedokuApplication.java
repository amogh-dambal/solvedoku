package com.amoghdambal.solvedoku;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class SolvedokuApplication {
	public static void main(String[] args) {
		SpringApplication.run(SolvedokuApplication.class, args);
	}
}
