package com.cronograma.evento.controller;

import com.cronograma.evento.model.Actividad;
import com.cronograma.evento.service.ActividadService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cronogramas")
@CrossOrigin(origins = "*")
public class ActividadController {

    private final ActividadService actividadService;

    public ActividadController(ActividadService actividadService) {
        this.actividadService = actividadService;
    }

    @PostMapping
    public ResponseEntity<?> agregarActividad(@RequestBody Actividad actividad) {
        try {
            Actividad nuevaActividad = actividadService.crearActividad(actividad);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaActividad);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar la actividad: " + e.getMessage());
        }
    }

    @GetMapping("/evento/{idEvento}")
    public ResponseEntity<List<Actividad>> obtenerCronograma(@PathVariable Integer idEvento) {
        List<Actividad> cronograma = actividadService.obtenerCronogramaPorEvento(idEvento);
        return ResponseEntity.ok(cronograma);
    }

    @DeleteMapping("/{idActividad}")
    public ResponseEntity<Void> eliminarActividad(@PathVariable Integer idActividad) {
        actividadService.eliminarActividad(idActividad);
        return ResponseEntity.noContent().build();
    }
}