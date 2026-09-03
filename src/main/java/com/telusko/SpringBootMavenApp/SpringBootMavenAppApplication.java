package com.telusko.SpringBootMavenApp.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SpringBootMavenAppApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringBootMavenAppApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMavenAppApplication.class, args);
    }
}
