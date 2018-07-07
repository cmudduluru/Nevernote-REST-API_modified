package com.NevernoteRESTAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//this annotation tells spring that this is spring application
@SpringBootApplication
public class NevernoteRestApiApplication {

	// this is the starting point of the application
	public static void main(String[] args) {
		// starts the servlet container and hosts the application in it
		SpringApplication.run(NevernoteRestApiApplication.class, args);
	}
}
