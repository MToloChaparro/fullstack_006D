package com.ecogarzones.incidencias;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class IncidenciasApplication {
    public static void main(String[] args) {
        SpringApplication.run(IncidenciasApplication.class, args);
    }
}