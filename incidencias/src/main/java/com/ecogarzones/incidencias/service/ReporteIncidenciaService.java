package com.ecogarzones.incidencias.service;

import com.ecogarzones.incidencias.model.ReporteIncidencia;
import com.ecogarzones.incidencias.model.CategoriaIncidencia;
import com.ecogarzones.incidencias.model.PrioridadIncidencia;
import com.ecogarzones.incidencias.model.EstadoIncidencia;
import com.ecogarzones.incidencias.repository.ReporteIncidenciaRepository;
import com.ecogarzones.incidencias.client.EventoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ReporteIncidenciaService {

    @Autowired
    private ReporteIncidenciaRepository repository;

    @Autowired
    private EventoClient eventoClient;

    // 1. OBTENER TODOS LOS REPORTES
    public List<ReporteIncidencia> obtenerTodos() {
        return repository.findAll();
    }

    public ReporteIncidencia guardar(ReporteIncidencia reporte) {
        if (reporte.getCostoEstimado() != null && reporte.getCostoEstimado() < 0) {
            throw new IllegalArgumentException("El costo estimado de reparación no puede ser menor a cero.");
        }

        if (usuarioTieneExcesoDeTickets(reporte.getIdUsuarioReporta())) {
            throw new IllegalStateException("El usuario ya cuenta con 4 o más incidencias activas. No se pueden procesar más reportes hasta solucionar los anteriores.");
        }

        if (reporte.getIdEvento() != null) {
            try {
                Integer idEventoInteger = reporte.getIdEvento().intValue();
                
                boolean existeElEvento = eventoClient.verificarSiEventoExiste(idEventoInteger);
                if (!existeElEvento) {
                    throw new IllegalArgumentException("Error de Integración: El id_Evento " + reporte.getIdEvento() + " no existe en el sistema de Eventos.");
                }
            } catch (IllegalArgumentException e) {
                throw e;
            } catch (Exception e) {
                throw new IllegalStateException("No se pudo validar el evento porque el microservicio de Eventos no se encuentra disponible.");
            }
        }

        reporte.setCodigoTicket("INC-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        reporte.setEstado_incidenciaString(EstadoIncidencia.PENDIENTE);
        reporte.setFecha_Incidencia(LocalDateTime.now());

        return repository.save(reporte);
    }
    public Optional<ReporteIncidencia> obtenerPorTicket(String codigoTicket) {
        return repository.findByCodigoTicket(codigoTicket);
    }

    public Optional<ReporteIncidencia> resolver(Long id, String comentarios, Long idAdmin) {
        return repository.findById(id).map(reporte -> {
            reporte.setEstado_incidenciaString(EstadoIncidencia.RESUELTO); 
            reporte.setComentariosResolucion(comentarios);
            reporte.setIdAdministradorAsignado(idAdmin);
            reporte.setFecha_Resolucion(LocalDateTime.now());
            return repository.save(reporte);
        });
    }

    public List<ReporteIncidencia> listarPorEstado(EstadoIncidencia estado) {
        return repository.buscarPorEstado(estado);
    }

    public List<ReporteIncidencia> listarPorPrioridad(PrioridadIncidencia prioridad) {
        return repository.buscarPorPrioridad(prioridad);
    }

    public List<ReporteIncidencia> listarPorCategoriaYEstado(CategoriaIncidencia categoria, EstadoIncidencia estado) {
        return repository.buscarPorCategoriaYEstado(categoria, estado);
    }

    public Double obtenerCostoTotalGlobal() {
        Double total = repository.calcularCostoTotalIncidencias();
        return (total != null) ? total : 0.0;
    }

    public Double obtenerCostoPorCategoria(CategoriaIncidencia categoria) {
        Double total = repository.calcularCostoPorCategoria(categoria);
        return (total != null) ? total : 0.0;
    }

    public boolean usuarioTieneExcesoDeTickets(Long idUsuario) {
        if (idUsuario == null) return false;
        Long activos = repository.contarIncidenciasActivasDeUsuario(idUsuario);
        return activos >= 4;
    }
}