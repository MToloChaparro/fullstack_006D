package com.ecogarzones.incidencias.controller;

import com.ecogarzones.incidencias.model.ReporteIncidencia;
import com.ecogarzones.incidencias.model.CategoriaIncidencia;
import com.ecogarzones.incidencias.model.PrioridadIncidencia;
import com.ecogarzones.incidencias.model.EstadoIncidencia;
import com.ecogarzones.incidencias.service.ReporteIncidenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/v1/incidencias")
public class ReporteIncidenciaController {

    @Autowired
    private ReporteIncidenciaService service;

    @GetMapping
    public List<ReporteIncidencia> listarTodas() {
        return service.obtenerTodos();
    }

    @PostMapping
    public ResponseEntity<?> crearIncidencia(@RequestBody ReporteIncidencia reporte) {
        try {
            ReporteIncidencia nuevoReporte = service.guardar(reporte);
            return ResponseEntity.ok(nuevoReporte);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("status", "error", "error", e.getMessage()));
        } catch (IllegalStateException e) {
            if (e.getMessage().contains("disponible")) {
                return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(Map.of("status", "failed", "error", e.getMessage()));
            }
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(Map.of("status", "error", "error", e.getMessage()));
        }
    }

    @GetMapping("/ticket/{codigo}")
    public ResponseEntity<ReporteIncidencia> buscarPorTicket(@PathVariable String codigo) {
        return service.obtenerPorTicket(codigo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/resolver")
    public ResponseEntity<ReporteIncidencia> resolverIncidencia(
            @PathVariable Long id,
            @RequestParam String comentarios,
            @RequestParam Long idAdmin) {
        
        return service.resolver(id, comentarios, idAdmin)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/filtro/estado")
    public List<ReporteIncidencia> filtrarPorEstado(@RequestParam EstadoIncidencia valor) {
        return service.listarPorEstado(valor);
    }

    @GetMapping("/filtro/prioridad")
    public List<ReporteIncidencia> filtrarPorPrioridad(@RequestParam PrioridadIncidencia valor) {
        return service.listarPorPrioridad(valor);
    }

    @GetMapping("/administrador/{idAdmin}")
    public ResponseEntity<List<ReporteIncidencia>> listarPorAdministrador(@PathVariable Long idAdmin) {
        try {
            return ResponseEntity.ok(service.obtenerTodos().stream()
                    .filter(r -> idAdmin.equals(r.getIdAdministradorAsignado())).toList());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
        }
    }

    @GetMapping("/filtro/avanzado")
    public List<ReporteIncidencia> filtrarPorCategoriaYEstado(
            @RequestParam CategoriaIncidencia categoria,
            @RequestParam EstadoIncidencia estado) {
        return service.listarPorCategoriaYEstado(categoria, estado);
    }

    @GetMapping("/costo-financiero")
    public ResponseEntity<Map<String, Object>> obtenerCostosFinancieros(
            @RequestParam(required = false) CategoriaIncidencia categoria) {
        
        Map<String, Object> reporteFinanciero = new HashMap<>();
        
        if (categoria != null) {
            Double costoCat = service.obtenerCostoPorCategoria(categoria);
            reporteFinanciero.put("categoria", categoria);
            reporteFinanciero.put("costoTotalCategoria", costoCat);
        } else {
            Double costoGlobal = service.obtenerCostoTotalGlobal();
            reporteFinanciero.put("costoTotalGlobal", costoGlobal);
        }
        
        return ResponseEntity.ok(reporteFinanciero);
    }

    @GetMapping("/usuario/{idUsuario}/alerta-sobrecarga")
    public ResponseEntity<Map<String, Object>> verificarSobrecargaUsuario(@PathVariable Long idUsuario) {
        boolean sobrecargado = service.usuarioTieneExcesoDeTickets(idUsuario);
        
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("idUsuario", idUsuario);
        respuesta.put("tieneExcesoDeTicketsActivos", sobrecargado);
        respuesta.put("mensaje", sobrecargado 
            ? "Atención: El usuario posee 4 o más incidencias activas. Evitar asignarle más tareas de riesgo." 
            : "Nivel de incidencias bajo control para este usuario.");
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/configuracion-sla")
    public ResponseEntity<Map<String, Object>> obtenerInformacionSLA() {
        Map<String, Object> respuesta = new HashMap<>();
        
        Map<String, String> tiemposPrioridad = new HashMap<>();
        for (PrioridadIncidencia p : PrioridadIncidencia.values()) {
            tiemposPrioridad.put(p.name(), p.getTiempoLimiteResolucion() + " (Color: " + p.getColorCodigo() + ")");
        }

        Map<String, String> areasCategoria = new HashMap<>();
        for (CategoriaIncidencia c : CategoriaIncidencia.values()) {
            areasCategoria.put(c.name(), c.getAreaEncargada() + " - " + c.getDescripcion());
        }

        respuesta.put("SLA_Tiempos_Prioridad", tiemposPrioridad);
        respuesta.put("Departamentos_Responsables", areasCategoria);
        
        return ResponseEntity.ok(respuesta);
    }
}