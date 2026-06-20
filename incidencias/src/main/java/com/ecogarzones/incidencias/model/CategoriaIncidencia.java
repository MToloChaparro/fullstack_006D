package com.ecogarzones.incidencias.model;

public enum CategoriaIncidencia {
    LOGISTICA("Problemas con camiones, rutas o recolección", "ADMINISTRADOR"), //MS TRANSPORTE
    CLIENTE("Reclamos de clientela, quejas o sugerencias", "ADMINISTRADOR"), //MS EVENTO
    SEGURIDAD("Accidentes laborales o problemas de EPPS", "ADMINISTRADOR"), //MS STAFF
    PERSONAL("Conflictos entre empleados, ausencias o problemas de clima laboral", "ADMINISTRADOR"), //MS STAFF
    INVENTARIO("Falta de herramientas o insumos", "ADMINISTRADOR");  //MS INVENTARIO

    private final String descripcion;
    private final String areaEncargada;

    CategoriaIncidencia(String descripcion, String areaEncargada) {
        this.descripcion = descripcion;
        this.areaEncargada = areaEncargada;
    }

    public String getDescripcion() { return descripcion; }
    public String getAreaEncargada() { return areaEncargada; }
}