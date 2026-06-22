package com.inventario.ecogarzones.model;

import lombok.Getter;

@Getter
public enum UnidadMedidaEnum {
    
    UNIDADES("Unidades", "un"),
    KILOGRAMOS("Kilogramos", "kg"),
    GRAMOS("Gramos", "g"),
    LITROS("Litros", "L"),
    CC("Centímetros Cúbicos", "cc"),
    PACK_CAJA("Pack / Caja", "pack");

    private final String descripcion;
    private final String abreviacion;

    // Constructor del Enum
    UnidadMedidaEnum(String descripcion, String abreviacion) {
        this.descripcion = descripcion;
        this.abreviacion = abreviacion;
    }
}