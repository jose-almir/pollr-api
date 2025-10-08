package com.almirdev.pollr;

import org.springframework.boot.SpringApplication;

public class TestPollrApplication {

	public static void main(String[] args) {
		SpringApplication.from(PollrApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
