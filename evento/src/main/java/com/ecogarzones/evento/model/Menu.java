package com.ecogarzones.evento.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "menu")
@Data
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_menu")
    private Integer idMenu;

    @Column(name = "tipo_menu")
    private String tipoMenu;

    @Column(name = "plato_entrada")
    private String platoEntrada;

    @Column(name = "plato_principal")
    private String platoPrincipal;

    @Column(name = "plato_postre")
    private String platoPostre;

    @Column(name = "tipo_barra")
    private String tipoBarra;

    @Column(name = "bebidas_pedidas")
    private String bebidasPedidas;

    @Column(name = "restricciones_alergias")
    private String restriccionesAlergias;

    @Column(name = "numero_personas")
    private Integer numeroPersonas;
}