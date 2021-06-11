package br.com.forum.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.cdimascio.dotenv.Dotenv;

@RestController
public class HelloController {
    Dotenv dotenv = Dotenv.load();
    String name = dotenv.get("EXAMPLE_NAME");

    @RequestMapping("/")
    public String hello() {
        return "Hello, " + name;
    }
    
}
