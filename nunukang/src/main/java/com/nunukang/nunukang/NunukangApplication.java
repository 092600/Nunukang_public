package com.nunukang.nunukang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class NunukangApplication {

	public static void main(String[] args) {
		SpringApplication.run(NunukangApplication.class, args);
	}

}
