package com.ecogarzones.incidencias.model;

public enum EstadoIncidencia {
    PENDIENTE("Reporte recibido, esperando asignación de administrador"),
    EN_PROCESO("El administrador está trabajando en la solución"),
    RESUELTO("El problema fue solucionado con éxito"),
    ANULADO("El reporte fue descartado por información incorrecta o duplicada");

    private final String significado;

    EstadoIncidencia(String significado) {
        this.significado = significado;
    }

    public String getSignificado() { return significado; }
}