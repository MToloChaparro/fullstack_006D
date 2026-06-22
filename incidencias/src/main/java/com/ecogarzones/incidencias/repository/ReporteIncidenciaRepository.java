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
    
    Optional<ReporteIncidencia> findByCodigoTicket(String codigoTicket);

    @Query("SELECT r FROM ReporteIncidencia r WHERE r.estado_incidenciaString = :estado")
    List<ReporteIncidencia> buscarPorEstado(@Param("estado") EstadoIncidencia estado);

    @Query("SELECT r FROM ReporteIncidencia r WHERE r.prioridad_incidencia = :prioridad")
    List<ReporteIncidencia> buscarPorPrioridad(@Param("prioridad") PrioridadIncidencia prioridad);

    List<ReporteIncidencia> findByIdAdministradorAsignado(Long idAdministradorAsignado);

    @Query("SELECT r FROM ReporteIncidencia r WHERE r.categoria_incidencia = :categoria AND r.estado_incidenciaString = :estado")
    List<ReporteIncidencia> buscarPorCategoriaYEstado(
            @Param("categoria") CategoriaIncidencia categoria, 
            @Param("estado") EstadoIncidencia estado
    );


    @Query("SELECT SUM(r.costoEstimado) FROM ReporteIncidencia r WHERE r.costoEstimado IS NOT NULL")
    Double calcularCostoTotalIncidencias();

    @Query("SELECT SUM(r.costoEstimado) FROM ReporteIncidencia r WHERE r.categoria_incidencia = :categoria AND r.costoEstimado IS NOT NULL")
    Double calcularCostoPorCategoria(@Param("categoria") CategoriaIncidencia categoria);

    @Query("SELECT COUNT(r) FROM ReporteIncidencia r WHERE r.idUsuarioReporta = :idUsuario AND r.estado_incidenciaString IN (com.ecogarzones.incidencias.model.EstadoIncidencia.PENDIENTE, com.ecogarzones.incidencias.model.EstadoIncidencia.EN_PROCESO)")
    Long contarIncidenciasActivasDeUsuario(@Param("idUsuario") Long idUsuario);
}