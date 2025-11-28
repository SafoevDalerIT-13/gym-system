package ru.safoev.maingym;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.safoev.entity.ClientEntity;
import ru.safoev.entity.GymEntity;
import ru.safoev.repositoryinterface.GymRepository;

@SpringBootApplication
public class GymSystemApplication {

	public static void main(String[] args) {
    SpringApplication.run(GymSystemApplication.class, args);
	}

}
