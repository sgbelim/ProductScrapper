package com.sainsburys.productscrapper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.sainsburys")
@EnableAutoConfiguration
@SpringBootApplication
public class Application {

    /**
     * Bootstrap the Spring Boot Application (Product Scrapper)
     * @param args
     */
    public static void main(String... args) {
        SpringApplication.run(Application.class, args);
    }

}