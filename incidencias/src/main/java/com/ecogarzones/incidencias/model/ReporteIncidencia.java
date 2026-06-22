package com.ecogarzones.incidencias.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "reportes_incidencias")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReporteIncidencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String codigoTicket;

    @Column(nullable = false, length = 100)
    private String titulo_incidencia;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String descripcion_incidencia;

    @Column(nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private CategoriaIncidencia categoria_incidencia; 

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private PrioridadIncidencia prioridad_incidencia; 

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private EstadoIncidencia estado_incidenciaString; 

    @Column(nullable = false)
    private Long idUsuarioReporta;

    private Long idAdministradorAsignado;

    private Double costoEstimado;

    @Column(columnDefinition = "TEXT")
    private String comentariosResolucion; 

    private LocalDateTime fecha_Incidencia;
    
    private LocalDateTime fecha_Resolucion;

    @PrePersist
    protected void onCreate() {
        this.fecha_Incidencia = LocalDateTime.now();
        
        if (this.estado_incidenciaString == null) {
            this.estado_incidenciaString = EstadoIncidencia.PENDIENTE;
        }
        if (this.prioridad_incidencia == null) {
            this.prioridad_incidencia = PrioridadIncidencia.MEDIA;
        }
        
        if (this.codigoTicket == null) {
            this.codigoTicket = "INC-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        }
    }

    public EstadoIncidencia getEstado_incidenciaString() {
        return this.estado_incidenciaString;
    }

    public void setEstado_incidenciaString(EstadoIncidencia estado) {
        this.estado_incidenciaString = estado;
    }

    public PrioridadIncidencia getPrioridad_incidencia() {
        return this.prioridad_incidencia;
    }

    public void setPrioridad_incidencia(PrioridadIncidencia prioridad) {
        this.prioridad_incidencia = prioridad;
    }

    @Column(name = "id_evento", nullable = true)
private Long idEvento;
}