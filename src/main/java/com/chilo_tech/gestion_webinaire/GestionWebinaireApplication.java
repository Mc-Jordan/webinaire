package com.chilo_tech.gestion_webinaire;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class GestionWebinaireApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionWebinaireApplication.class, args);
	}

}
