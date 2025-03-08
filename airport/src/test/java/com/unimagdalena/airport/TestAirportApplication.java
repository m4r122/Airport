package com.unimagdalena.airport;

import org.springframework.boot.SpringApplication;

public class TestAirportApplication {

	public static void main(String[] args) {
		SpringApplication.from(AirportApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
