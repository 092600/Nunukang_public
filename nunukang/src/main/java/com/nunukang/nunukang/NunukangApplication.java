package com.nunukang.nunukang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import com.nunukang.nunukang.domain.comment.CommentRepository;



@EnableJpaAuditing
@SpringBootApplication
public class NunukangApplication {

	public static void main(String[] args) {
		SpringApplication.run(NunukangApplication.class, args);
	}

}
