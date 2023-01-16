package com.example.masaimailapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@EnableSwagger2
@SpringBootApplication
public class MasaiMailAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(MasaiMailAppApplication.class, args);
    }

}
