package com.dziadkouskaya.housekeeping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.dziadkouskaya.housekeeping"})
public class HouseKeepingApplication {

	public static void main(String[] args) {
		SpringApplication.run(HouseKeepingApplication.class, args);
	}

}
