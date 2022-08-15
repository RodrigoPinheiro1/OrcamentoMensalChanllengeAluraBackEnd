package com.example.orcamentofamiliar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc //spring security
public class OrcamentoFamiliarApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrcamentoFamiliarApplication.class, args);
    }

}
