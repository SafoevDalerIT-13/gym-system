package ru.safoev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("ru.safoev.repositoryinterface")
@EntityScan("ru.safoev.entity")
public class GymSystemApplication {

	public static void main(String[] args) {
    SpringApplication.run(GymSystemApplication.class, args);
	}

}
