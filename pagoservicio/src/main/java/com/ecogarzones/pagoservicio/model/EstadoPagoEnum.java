package com.ecogarzones.pagoservicio.model;

public enum EstadoPagoEnum {

    PENDIENTE(1),
    COMPLETADO(2),
    FALLIDO(3),
    CANCELADO(4),
    REEMBOLSADO(5),
    EN_PROCESO(6),
    RECHAZADO(7);

    private final int id;

    EstadoPagoEnum(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static EstadoPagoEnum desdeId(int id) {
        for (EstadoPagoEnum estado : values()) {
            if (estado.getId() == id) {
                return estado;
            }
        }
        throw new IllegalArgumentException("Error: El ID de estado de pago no es válido: " + id);
    }
}