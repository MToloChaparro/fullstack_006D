package com.inventario.ecogarzones.model;

import lombok.Getter;

@Getter
public enum TipoInsumo {
    
    ALIMENTOS_PERECIBLES("Alimentos Perecibles"),
    ALIMENTOS_NO_PERECIBLES("Alimentos No Perecibles"),
    LACTEOS("Lácteos"),
    PASTELERIA("Pastelería y Panadería"),
    CARNES("Carnes"),
    PESCADOS("Pescados y Mariscos"),
    FRUTAS("Frutas"),
    VERDURAS("Verduras"),
    BEBIDAS_NO_ALCOHOLICAS("Bebidas No Alcohólicas (Jugos/Agua)"),
    BEBIDAS_GASEOSAS("Bebidas Gaseosas"),
    BEBIDAS_ALCOHOLICAS("Bebidas Alcohólicas / Tragos"),
    HIGIENE_PERSONAL("Higiene Personal"),
    HIGIENE_GENERAL("Higiene y Limpieza General"),
    UTENSILIOS("Utensilios y Vajilla"),
    MUEBLERIA("Mueblería y Mantelería"),
    OTROS("Otros Insumos"),
    DESECHABLES("Insumos Desechables y Packagings"),
    AMBIENTACION_Y_DECO("Decoración, Flores y Ambientación"),
    UNIFORMES_Y_EPP("Uniformes y Elementos de Protección"),
    MAQUINARIA_Y_EQUIPOS("Maquinaria y Equipamientos"),
    ABARROTES_Y_ESPECIAS("Abarrotes, Aceites y Condimentos"),
    CONGELADOS("Helado, hielo y congelados");

    private final String label;

    TipoInsumo(String label) {
        this.label = label;
    }
}