package com.Productores;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.Productores.repositories.FicherosRepository;

@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = FicherosRepository.class)
public class ProductoresApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductoresApplication.class, args);
	}

}
