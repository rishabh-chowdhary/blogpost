package com.blog.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@EnableMongoRepositories(basePackages= {"com.blog.repository"})
@ComponentScan(basePackages= {"com.blog.*"})
public class MainApp extends SpringBootServletInitializer {
   public static void main(String[] args) {
      SpringApplication.run(MainApp.class, args);
   }
}
