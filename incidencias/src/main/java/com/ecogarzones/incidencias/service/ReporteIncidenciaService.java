package com.ecogarzones.incidencias.service;

import com.ecogarzones.incidencias.model.ReporteIncidencia;
import com.ecogarzones.incidencias.model.CategoriaIncidencia;
import com.ecogarzones.incidencias.model.PrioridadIncidencia;
import com.ecogarzones.incidencias.model.EstadoIncidencia;
import com.ecogarzones.incidencias.repository.ReporteIncidenciaRepository;
import com.ecogarzones.incidencias.client.EventoClient; // <- Importamos el cliente Feign
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
    private EventoClient eventoClient; // <- Inyectamos el puente HTTP inter-servicio

    // 1. OBTENER TODOS LOS REPORTES
    public List<ReporteIncidencia> obtenerTodos() {
        return repository.findAll();
    }

// 2. CREAR UN NUEVO REPORTE CON VALIDACIÓN INTER-SERVICIO Y REGLAS DE NEGOCIO
    public ReporteIncidencia guardar(ReporteIncidencia reporte) {
        // Regla de negocio preexistente: El costo estimado no puede ser negativo
        if (reporte.getCostoEstimado() != null && reporte.getCostoEstimado() < 0) {
            throw new IllegalArgumentException("El costo estimado de reparación no puede ser menor a cero.");
        }

        // CONTROL: Verificar si el usuario ya llegó al límite permitido de reportes activos
        if (usuarioTieneExcesoDeTickets(reporte.getIdUsuarioReporta())) {
            throw new IllegalStateException("El usuario ya cuenta con 4 o más incidencias activas. No se pueden procesar más reportes hasta solucionar los anteriores.");
        }

        // VALIDACIÓN DE MICROSERVICIOS: Si se incluye un idEvento, consultamos al otro MS si existe
        if (reporte.getIdEvento() != null) {
            try {
                // CORRECCIÓN AQUÍ: Convertimos el Long de idEvento a Integer usando .intValue()
                Integer idEventoInteger = reporte.getIdEvento().intValue();
                
                boolean existeElEvento = eventoClient.verificarSiEventoExiste(idEventoInteger);
                if (!existeElEvento) {
                    throw new IllegalArgumentException("Error de Integración: El id_Evento " + reporte.getIdEvento() + " no existe en el sistema de Eventos.");
                }
            } catch (IllegalArgumentException e) {
                // Dejamos pasar nuestro propio error de "El id_Evento no existe"
                throw e;
            } catch (Exception e) {
                // Plan de respaldo si el microservicio de Eventos está caído o fuera de línea
                throw new IllegalStateException("No se pudo validar el evento porque el microservicio de Eventos no se encuentra disponible.");
            }
        }

        // AUTOMATIZACIONES DE CREACIÓN (Para que no queden nulas en la BD)
        reporte.setCodigoTicket("INC-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        reporte.setEstado_incidenciaString(EstadoIncidencia.PENDIENTE);
        reporte.setFecha_Incidencia(LocalDateTime.now());

        return repository.save(reporte);
    }
    // 3. BUSCAR POR CÓDIGO DE TICKET ÚNICO
    public Optional<ReporteIncidencia> obtenerPorTicket(String codigoTicket) {
        return repository.findByCodigoTicket(codigoTicket);
    }

    // 4. LÓGICA PARA RESOLVER EL PROBLEMA
    public Optional<ReporteIncidencia> resolver(Long id, String comentarios, Long idAdmin) {
        return repository.findById(id).map(reporte -> {
            reporte.setEstado_incidenciaString(EstadoIncidencia.RESUELTO); 
            reporte.setComentariosResolucion(comentarios);
            reporte.setIdAdministradorAsignado(idAdmin);
            reporte.setFecha_Resolucion(LocalDateTime.now());
            return repository.save(reporte);
        });
    }

    // 5. OBTENER INCIDENCIAS FILTRADAS POR ESTADO
    public List<ReporteIncidencia> listarPorEstado(EstadoIncidencia estado) {
        return repository.buscarPorEstado(estado);
    }

    // 6. OBTENER INCIDENCIAS FILTRADAS POR PRIORIDAD
    public List<ReporteIncidencia> listarPorPrioridad(PrioridadIncidencia prioridad) {
        return repository.buscarPorPrioridad(prioridad);
    }

    // 8. OBTENER INCIDENCIAS POR CATEGORÍA Y ESTADO
    public List<ReporteIncidencia> listarPorCategoriaYEstado(CategoriaIncidencia categoria, EstadoIncidencia estado) {
        return repository.buscarPorCategoriaYEstado(categoria, estado);
    }

    // 9. OBTENER EL GASTO TOTAL EN INCIDENCIAS DE ECOGARZONES
    public Double obtenerCostoTotalGlobal() {
        Double total = repository.calcularCostoTotalIncidencias();
        return (total != null) ? total : 0.0;
    }

    // 10. OBTENER EL GASTO DE UNA CATEGORÍA ESPECÍFICA (Ej: LOGISTICA)
    public Double obtenerCostoPorCategoria(CategoriaIncidencia categoria) {
        Double total = repository.calcularCostoPorCategoria(categoria);
        return (total != null) ? total : 0.0;
    }

    // 11. VERIFICAR SI UN USUARIO TIENE DEMASIADOS TICKETS ACTIVOS (Regla de Control)
    public boolean usuarioTieneExcesoDeTickets(Long idUsuario) {
        if (idUsuario == null) return false;
        Long activos = repository.contarIncidenciasActivasDeUsuario(idUsuario);
        return activos >= 4;
    }
}