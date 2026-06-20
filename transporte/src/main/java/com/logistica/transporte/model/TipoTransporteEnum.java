package com.logistica.transporte.model;

import lombok.Getter;

@Getter 
public enum TipoTransporteEnum {

    PARTICULAR("VEHICULO PARTICULAR"),
    STAFF("TRANSPORTE DE STAFF"),
    INSUMOS("TRANSPORTE DE INSUMOS"),
    GENERAL("TRANSPORTE DE STAFF E INSUMOS");

    private final String descripcion;

    TipoTransporteEnum(String descripcion) {
        this.descripcion = descripcion;
    }
}