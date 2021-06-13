package br.com.forum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
@EnableSpringDataWebSupport
@EnableCaching
public class ForumApplication {
	public static Dotenv dotenv = Dotenv.load();
	public static void main(String[] args) {
		SpringApplication.run(ForumApplication.class, args);
	}

}
