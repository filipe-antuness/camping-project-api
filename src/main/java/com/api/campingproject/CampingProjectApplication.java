package com.api.campingproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class CampingProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(CampingProjectApplication.class, args);
    }

}
