package com.logistica.transporte.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "transporte")
@Data
public class Transporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idtransporte;

    private Integer idevento; 
    private String rutconductor;
    private String nombreconductor;
    private String apellidopconductor;
    private String apellidomconductor;
    private String patentevehiculo;
    private String estadovehiculo;
    private String modelovehiculo;
    private String marcavehiculo;
    private String tipovehiculo;

    @Enumerated(EnumType.STRING)
    private TipoTransporteEnum tipotransporte;

    private Double costotraslado;
    private String comunadestino;
    private LocalDateTime horadespachoestimada;
    private LocalDateTime horasalidaestimada;
}