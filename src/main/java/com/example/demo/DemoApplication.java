package com.example.demo;

import com.example.demo.API.Entities.Game;
import com.example.demo.API.Entities.Player;
import com.example.demo.API.Entities.Users;
import com.example.demo.API.Entities.Words;
import com.example.demo.API.Enum.UserType;
import com.example.demo.API.Services.ServiceGame;
import com.example.demo.API.Services.ServicePlayer;
import com.example.demo.API.Services.ServiceUser;
import com.example.demo.API.Services.ServiceWord;
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
	@Autowired
	ServicePlayer servicePlayer;
	@Autowired
	ServiceUser serviceUser;
	@Autowired
	ServiceWord serviceWord;

	@PostConstruct
	public void after(){
		serviceUser.save(new Users("user","user", UserType.PLAYER));
		serviceUser.save(new Users("admin","user", UserType.MANAGER));
		serviceUser.save(new Users("user2","user", UserType.PLAYER));


		serviceWord.save(new Words("JAVA"));
		serviceWord.save(new Words("ULUK"));
		serviceWord.save(new Words("PYTHON"));
	}

}
