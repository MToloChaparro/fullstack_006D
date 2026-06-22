package com.logistica.transporte.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.logistica.transporte.model.TipoTransporteEnum;
import com.logistica.transporte.model.Transporte;
import com.logistica.transporte.service.TransporteService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/transportes")
@AllArgsConstructor
public class TransporteController {

    private final TransporteService transporteService;

    @PostMapping
    public ResponseEntity<Transporte> crear(@RequestBody Transporte transporte) {
        return new ResponseEntity<>(transporteService.guardarTransporte(transporte), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Transporte>> obtenerTodos() {
        return ResponseEntity.ok(transporteService.obtenerTodosLosTransportes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transporte> obtenerPorId(@PathVariable Integer id) {
        return transporteService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        transporteService.eliminarTransporte(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/buscar/tipo")
    public ResponseEntity<List<Transporte>> buscarPorTipo(@RequestParam TipoTransporteEnum tipo) {
        return ResponseEntity.ok(transporteService.buscarPorTipo(tipo));
    }

    @GetMapping("/buscar/estado")
    public ResponseEntity<List<Transporte>> buscarPorEstado(@RequestParam String estado) {
        return ResponseEntity.ok(transporteService.buscarPorEstado(estado));
    }

    @GetMapping("/buscar/evento/{idEvento}")
    public ResponseEntity<List<Transporte>> buscarPorEvento(@PathVariable Integer idEvento) {
        return ResponseEntity.ok(transporteService.buscarPorIdEvento(idEvento));
    }

    @GetMapping("/buscar/conductor/rut/{rut}")
    public ResponseEntity<List<Transporte>> buscarPorRutConductor(@PathVariable String rut) {
        return ResponseEntity.ok(transporteService.buscarPorRutConductor(rut));
    }

    @GetMapping("/buscar/vehiculo/patente/{patente}")
    public ResponseEntity<List<Transporte>> buscarPorPatente(@PathVariable String patente) {
        return ResponseEntity.ok(transporteService.buscarPorPatente(patente));
    }

    @GetMapping("/buscar/comuna/{comuna}")
    public ResponseEntity<List<Transporte>> buscarPorComuna(@PathVariable String comuna) {
        return ResponseEntity.ok(transporteService.buscarPorComunaDestino(comuna));
    }
}