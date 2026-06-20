package com.ecogarzones.evento.controller;

import com.ecogarzones.evento.service.UbicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/ubicaciones")
public class UbicacionController {

    @Autowired
    private UbicacionService ubicacionService;

    @GetMapping("/comuna/{codigo}")
    public String buscarComuna(@PathVariable String codigo) {
        return ubicacionService.obtenerNombreComuna(codigo);
    }
}