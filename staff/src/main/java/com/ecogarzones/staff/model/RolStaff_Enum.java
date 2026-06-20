package com.ecogarzones.staff.model;

public class RolStaff_Enum {
    public static final int GARZON = 1;
    public static final int SOMMELIER = 2;
    public static final int BARTENDER = 3;
    public static final int SUPERVISOR = 4;
    public static final int CHEF = 5;
    public static final int ADMINISTRADOR = 6;
    public static final int COPERO = 7;

    public static String obtenerNombreRol(int idRol) {
        switch (idRol) {
            case GARZON: return "Garzón";
            case SOMMELIER: return "Sommelier";
            case BARTENDER: return "Bartender / Mixólogo";
            case SUPERVISOR: return "Supervisor / Maître";
            case CHEF: return "Chef / Cocineria";
            case ADMINISTRADOR: return "Administrador / Coordinador";
            case COPERO: return "Copero / Limpieza de Vajillas";
            default: return "Rol No Definido";
        }
    }
}