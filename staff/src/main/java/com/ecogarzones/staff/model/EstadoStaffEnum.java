package com.ecogarzones.staff.model;

public enum EstadoStaffEnum {
    INACTIVO(0),
    ACTIVO(1),
    VACACIONES(2),
    LICENCIA_MEDICA(3),
    DESVINCULADO(4);

    private final int id;

    EstadoStaffEnum(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static EstadoStaffEnum desdeId(int id) {
        for (EstadoStaffEnum estado : values()) {
            if (estado.getId() == id) {
                return estado;
            }
        }
        throw new IllegalArgumentException("ERROR: El ID de estado de staff no es válido: " + id);
    }
}