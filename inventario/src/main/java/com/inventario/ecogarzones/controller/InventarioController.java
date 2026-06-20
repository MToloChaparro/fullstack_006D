package com.inventario.ecogarzones.controller;

import com.inventario.ecogarzones.model.Inventario;
import com.inventario.ecogarzones.service.InventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/inventario")
@CrossOrigin(origins = "*")
public class InventarioController {

    private final InventarioService inventarioService;

    @Autowired
    public InventarioController(InventarioService inventarioService) {
        this.inventarioService = inventarioService;
    }

    // GET: http://localhost:8087/api/v1/inventario
    @GetMapping
    public ResponseEntity<?> obtenerInventarioComplete() {
        try {
            List<Inventario> insumos = inventarioService.listarTodosLosInsumos();
            if (insumos.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("El inventario está actualmente vacío.");
            }
            return ResponseEntity.ok(insumos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al recuperar los datos del inventario: " + e.getMessage());
        }
    }

    // GET: http://localhost:8087/api/v1/inventario/{id}
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerInsumoPorId(@PathVariable Long id) {
        return inventarioService.buscarInsumoPorId(id)
                .map(insumo -> ResponseEntity.ok().body(insumo))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body((Inventario) Map.of("error", "Insumo no encontrado con el ID: " + id)));
    }

    // POST: http://localhost:8087/api/v1/inventario
    @PostMapping
    public ResponseEntity<?> agregarNuevoInsumo(@RequestBody Inventario nuevoInsumo) {
        try {

            if (nuevoInsumo.getCantidadInsumo() != null && nuevoInsumo.getCantidadInsumo() < 0) {
                return ResponseEntity.badRequest().body("La cantidad del insumo no puede ser un número negativo.");
            }
            Inventario insumoGuardado = inventarioService.registrarInsumo(nuevoInsumo);
            return ResponseEntity.status(HttpStatus.CREATED).body(insumoGuardado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error en la estructura del JSON enviado: " + e.getMessage());
        }
    }

    // GET: http://localhost:8087/api/v1/inventario/valor-total
    @GetMapping("/valor-total")
    public ResponseEntity<?> obtenerCalculoValorTotal() {
        try {
            double valorTotal = inventarioService.calcularValorTotalInventario();
            return ResponseEntity.ok(Map.of(
                    "Descripcion", "--- VALOR TOTAL ACUMULADO DE ISUMOS EN INVENTARIO ---",
                    "Total:",  valorTotal
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocurrió un problema al procesar el cálculo matemático: " + e.getMessage());
        }
    }

// DELETE: http://localhost:8087/api/v1/inventario/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarInsumoPorId(@PathVariable Long id) {
        try {
            inventarioService.eliminarInsumo(id);
            return ResponseEntity.ok(java.util.Map.of("mensaje", "Insumo con ID " + id + " eliminado correctamente."));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(java.util.Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(java.util.Map.of("error", "Error interno: " + e.getMessage()));
        }
    }

    // PUT: http://localhost:8087/api/v1/inventario/{id}
    @PutMapping("/{id}")
    public ResponseEntity<?> modificarInsumo(@PathVariable Long id, @RequestBody Inventario datosNuevos) {
        try {
            Inventario insumoActualizado = inventarioService.actualizarInsumo(id, datosNuevos);
            return ResponseEntity.ok(insumoActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(java.util.Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(java.util.Map.of("error", "Error al actualizar: " + e.getMessage()));
        }
    }

    
}