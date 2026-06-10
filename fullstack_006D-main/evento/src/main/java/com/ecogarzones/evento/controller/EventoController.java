package com.ecogarzones.evento.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecogarzones.evento.model.EstadoEvento_Enum;
import com.ecogarzones.evento.model.Evento;
import com.ecogarzones.evento.service.EventoService;
import com.ecogarzones.evento.service.UbicacionService;

import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/v1/eventos")
public class EventoController {
    
    @Autowired
    private EventoService eventoService;

    @Autowired
    private UbicacionService ubicacionService;

    @PostMapping
    public ResponseEntity<Evento> crearEvento(@RequestBody Evento evento) {
        Evento nuevoEvento = eventoService.crearEvento(evento);
        
        if (nuevoEvento.getIdComuna() != null) {
            nuevoEvento.setNombreComuna(ubicacionService.obtenerNombreComuna(nuevoEvento.getIdComuna()));
        }
        if (nuevoEvento.getIdEstadoEvento() != null) {
            nuevoEvento.setNombreEstadoEvento(EstadoEvento_Enum.obtenerNombreEstado(nuevoEvento.getIdEstadoEvento()));
        }
        
        return ResponseEntity.ok(nuevoEvento);
    }

    @GetMapping
    public ResponseEntity<List<Evento>> obtenerTodosLosEventos() {
        List<Evento> eventos = eventoService.obtenerTodosLosEventos();
        if (eventos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        
        for (Evento e : eventos) {
            if (e.getIdComuna() != null) {
                e.setNombreComuna(ubicacionService.obtenerNombreComuna(e.getIdComuna()));
            }
            if (e.getIdEstadoEvento() != null) {
                e.setNombreEstadoEvento(EstadoEvento_Enum.obtenerNombreEstado(e.getIdEstadoEvento()));
            }
        }
        
        return ResponseEntity.ok(eventos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evento> obtenerEventoPorId(@PathVariable Integer id) {
        try {
            Evento evento = eventoService.obtenerEventoPorId(id);
            
            if (evento.getIdComuna() != null) {
                evento.setNombreComuna(ubicacionService.obtenerNombreComuna(evento.getIdComuna()));
            }
            if (evento.getIdEstadoEvento() != null) {
                evento.setNombreEstadoEvento(EstadoEvento_Enum.obtenerNombreEstado(evento.getIdEstadoEvento()));
            }
            
            return ResponseEntity.ok(evento);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Evento> actualizarEvento(@PathVariable Integer id, @RequestBody Evento eventoActualizado) {
        try {
            Evento evento = eventoService.actualizarEvento(id, eventoActualizado);
            
            if (evento.getIdComuna() != null) {
                evento.setNombreComuna(ubicacionService.obtenerNombreComuna(evento.getIdComuna()));
            }
            if (evento.getIdEstadoEvento() != null) {
                evento.setNombreEstadoEvento(EstadoEvento_Enum.obtenerNombreEstado(evento.getIdEstadoEvento()));
            }
            
            return ResponseEntity.ok(evento);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEvento(@PathVariable Integer id) {
        try {
            eventoService.eliminarEvento(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Evento>> buscarEventos(@RequestParam String nombre) {
        List<Evento> eventos = eventoService.buscarEventosPorNombre(nombre);
        if (eventos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        
        for (Evento e : eventos) {
            if (e.getIdComuna() != null) {
                e.setNombreComuna(ubicacionService.obtenerNombreComuna(e.getIdComuna()));
            }
            if (e.getIdEstadoEvento() != null) {
                e.setNombreEstadoEvento(EstadoEvento_Enum.obtenerNombreEstado(e.getIdEstadoEvento()));
            }
        }
        
        return ResponseEntity.ok(eventos);
    }
}