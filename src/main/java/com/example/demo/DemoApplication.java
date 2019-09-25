package com.example.demo;

import com.example.demo.Entities.Game;
import com.example.demo.Services.ServiceGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableJpaAuditing
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Autowired
	ServiceGame serviceGame;

	@PostConstruct
	public void after(){
		serviceGame.save(new Game("ABBA"));

	}

}
