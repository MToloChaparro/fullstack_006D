package com.ecogarzones.evento.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@FeignClient(name = "ms-incidencias", url = "http://localhost:9090/api/v1/incidencias")
public interface IncidenciaClient {

    @GetMapping("/buscarPorEvento")
    List<Object> obtenerIncidenciasPorEvento(@RequestParam("idEvento") Integer idEvento);
}