package com.ecogarzones.incidencias.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "microservicio-eventos", url = "http://localhost:8082") 
public interface EventoClient {

    @GetMapping("/api/v1/eventos/verificar/{id}")
    boolean verificarSiEventoExiste(@PathVariable("id") Integer id); // <-- Cambiado Long por Integer
}