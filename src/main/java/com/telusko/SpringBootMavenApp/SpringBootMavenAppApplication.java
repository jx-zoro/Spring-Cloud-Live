package com.telusko.SpringBootMavenApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
    "com.telusko.SpringBootMavenApp",
    "com.telusko.SpringBootMavenApp.service.IGreetingService",
    "com.telusko.SpringBootMavenApp.web"
})
public class SpringBootMavenAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMavenAppApplication.class, args);
    }
}