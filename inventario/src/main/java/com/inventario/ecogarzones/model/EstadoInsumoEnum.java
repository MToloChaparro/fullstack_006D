package com.inventario.ecogarzones.model;

import lombok.Getter;

@Getter // Genera automáticamente los métodos getDescripcion() y getColor()
public enum EstadoInsumoEnum {
    
    DISPONIBLE("Disponible para uso", "green"),
    INCOMPLETO("Faltan unidades / Stock bajo", "orange"),
    DEFECTUOSO("Dañado o Quebrado", "red"),
    VENCIDO("Alimento/Bebida Vencida", "red"),
    NO_DISPONIBLE("Bloqueado / En mantención", "gray");

    private final String descripcion;
    private final String color;

    // Constructor del Enum
    EstadoInsumo(String descripcion, String color) {
        this.descripcion = descripcion;
        this.color = color;
    }
}