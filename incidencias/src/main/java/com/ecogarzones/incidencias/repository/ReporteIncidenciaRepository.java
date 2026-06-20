package com.ecogarzones.incidencias.repository;

import com.ecogarzones.incidencias.model.ReporteIncidencia;
import com.ecogarzones.incidencias.model.CategoriaIncidencia;
import com.ecogarzones.incidencias.model.PrioridadIncidencia;
import com.ecogarzones.incidencias.model.EstadoIncidencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReporteIncidenciaRepository extends JpaRepository<ReporteIncidencia, Long> {
    
    // 1. Buscar por el código de ticket único
    Optional<ReporteIncidencia> findByCodigoTicket(String codigoTicket);

    // 2. CORREGIDO CON @QUERY: Filtrar por estado de forma segura sin que el guion bajo rompa JPA
    @Query("SELECT r FROM ReporteIncidencia r WHERE r.estado_incidenciaString = :estado")
    List<ReporteIncidencia> buscarPorEstado(@Param("estado") EstadoIncidencia estado);

    // 3. CORREGIDO CON @QUERY: Filtrar por prioridad de forma segura
    @Query("SELECT r FROM ReporteIncidencia r WHERE r.prioridad_incidencia = :prioridad")
    List<ReporteIncidencia> buscarPorPrioridad(@Param("prioridad") PrioridadIncidencia prioridad);

    // 4. VER TICKETS ASIGNADOS A UN ADMINISTRADOR ESPECÍFICO
    List<ReporteIncidencia> findByIdAdministradorAsignado(Long idAdministradorAsignado);

    // 5. CORREGIDO CON @QUERY: Combinación avanzada (Categoría y Estado)
    @Query("SELECT r FROM ReporteIncidencia r WHERE r.categoria_incidencia = :categoria AND r.estado_incidenciaString = :estado")
    List<ReporteIncidencia> buscarPorCategoriaYEstado(
            @Param("categoria") CategoriaIncidencia categoria, 
            @Param("estado") EstadoIncidencia estado
    );

    // ==========================================
    // CONSULTAS CON OPERACIONES MATEMÁTICAS
    // ==========================================

    // 6. CALCULAR EL COSTO TOTAL EN PÉRDIDAS POR INCIDENCIAS
    @Query("SELECT SUM(r.costoEstimado) FROM ReporteIncidencia r WHERE r.costoEstimado IS NOT NULL")
    Double calcularCostoTotalIncidencias();

    // 7. CALCULAR EL COSTO TOTAL DE INCIDENCIAS DE UNA SOLA CATEGORÍA
    @Query("SELECT SUM(r.costoEstimado) FROM ReporteIncidencia r WHERE r.categoria_incidencia = :categoria AND r.costoEstimado IS NOT NULL")
    Double calcularCostoPorCategoria(@Param("categoria") CategoriaIncidencia categoria);

    // 8. CONTAR CUÁNTAS INCIDENCIAS ACTIVAS TIENE UN USUARIO DE LA APP
    @Query("SELECT COUNT(r) FROM ReporteIncidencia r WHERE r.idUsuarioReporta = :idUsuario AND r.estado_incidenciaString IN (com.ecogarzones.incidencias.model.EstadoIncidencia.PENDIENTE, com.ecogarzones.incidencias.model.EstadoIncidencia.EN_PROCESO)")
    Long contarIncidenciasActivasDeUsuario(@Param("idUsuario") Long idUsuario);
}