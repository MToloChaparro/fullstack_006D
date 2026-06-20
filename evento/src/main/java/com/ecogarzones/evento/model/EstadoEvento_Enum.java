package com.ecogarzones.evento.model;

public class EstadoEvento_Enum {

    public static final int PENDIENTE = 1;
    public static final int CONFIRMADO = 2;
    public static final int CANCELADO = 3;
    public static final int FINALIZADO = 4;
    public static final int EN_PROCESO = 5;

    public static String obtenerNombreEstado(int idEstado) {
        switch (idEstado) {
            case PENDIENTE:
                return "PENDIENTE";
            case CONFIRMADO:
                return "CONFIRMADO";
            case CANCELADO:
                return "CANCELADO";
            case FINALIZADO:
                return "FINALIZADO";
            case EN_PROCESO:
                return "EN PROCESO";
            default:
                throw new IllegalArgumentException("Error: El ID de estado de evento no es válido: " + idEstado);
        }
    }


}
