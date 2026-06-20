package com.ecogarzones.incidencias.model;

public enum PrioridadIncidencia {
    BAJA(1, "No requiere acción inmediata", "Verde"),
    MEDIA(2, "Se solicita supervisión preventiva", "Amarillo"),
    ALTA(3, "Se solicita resolución lo antes posible", "Naranja"),
    CRITICA(4, "Se solicita resolución Inmediata", "Rojo");

    private final int nivelSeveridad;
    private final String tiempoLimiteResolucion;
    private final String colorCodigo;

    PrioridadIncidencia(int nivelSeveridad, String tiempoLimiteResolucion, String colorCodigo) {
        this.nivelSeveridad = nivelSeveridad;
        this.tiempoLimiteResolucion = tiempoLimiteResolucion;
        this.colorCodigo = colorCodigo;
    }

    public int getNivelSeveridad() { return nivelSeveridad; }
    public String getTiempoLimiteResolucion() { return tiempoLimiteResolucion; }
    public String getColorCodigo() { return colorCodigo; }
}