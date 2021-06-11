package br.com.alura.forum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class ForumApplication {
	Dotenv dotenv = Dotenv.load();
	public static void main(String[] args) {
		SpringApplication.run(ForumApplication.class, args);
	}

}
