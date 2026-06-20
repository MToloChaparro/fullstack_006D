package com.cronograma.evento.model;

import java.time.LocalDateTime;
import com.cronograma.evento.dto.MenuDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ms_cronograma_actividades")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Actividad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_actividad")
    private Integer idActividad;

    @Column(name = "id_evento", nullable = false)
    private Integer idEvento;

    @Column(nullable = false, length = 100)
    private String titulo;

    @Column(name = "descripcion_detallada", length = 1000)
    private String descripcionDetallada; 

    @Column(name = "hora_inicio", nullable = false)
    private LocalDateTime horaInicio;

    @Column(name = "hora_fin", nullable = false)
    private LocalDateTime horaFin;

    @Column(name = "responsable_bloque", length = 100)
    private String responsableBloque; 

    @Column(name = "area_recinto", length = 100)
    private String areaRecinto; 

    @Transient
    private MenuDTO detalleMenu;
}