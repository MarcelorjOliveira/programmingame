package org.b2w.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication	
@ComponentScan(basePackages = {"br.com.assessmentsystem.assessmentsystem.controller"})
public class ClientApplication {


	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);
	}
	/*
		repository.deleteAll();

		repository.save(new Planeta("nome", "clima", "terreno"));
		repository.save(new Planeta("Marte", "frio", "seco"));

		System.out.println("Planetas found with findAll():");
		System.out.println("-------------------------------");
		for (Planeta planeta : repository.findAll()) {
			System.out.println(planeta);
		}
	*/

}
