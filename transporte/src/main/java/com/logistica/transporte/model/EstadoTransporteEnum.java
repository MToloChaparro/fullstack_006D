package com.logistica.transporte.model;

import lombok.Getter;

@Getter 
public enum EstadoTransporteEnum {

    PENDIENTE(1, "PENDIENTE"),
    CONFIRMADO(2, "CONFIRMADO"),
    CANCELADO(3, "CANCELADO"),
    FINALIZADO(4, "FINALIZADO"),
    EN_TRAYECTO(5, "EN TRAYECTO");

    private final int id;
    private final String nombre;

    EstadoTransporteEnum(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public static EstadoTransporteEnum obtenerPorId(int idEstado) {
        for (EstadoTransporteEnum estado : values()) {
            if (estado.getId() == idEstado) {
                return estado;
            }
        }
        throw new IllegalArgumentException("Error: El ID de estado de transporte no es válido: " + idEstado);
    }
}