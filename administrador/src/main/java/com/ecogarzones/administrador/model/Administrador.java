package com.ecogarzones.administrador.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ms_administrador")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Administrador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_administrador")
    private Integer idAdministrador;

    @Column(name = "nombre_administrador", nullable = false)
    private String nombreAdministrador;

    @Column(name = "apellidop_administrador", nullable = false)
    private String apellidopAdministrador;

    @Column(name = "apellidom_administrador")
    private String apellidomAdministrador;

    @Column(name = "rut_administrador", nullable = false)
    private Integer rutAdministrador;

    @Column(name = "dv_rut", length = 1, nullable = false)
    private String dvRut;

    @Column(name = "correo_administrador", unique = true, nullable = false)
    private String correoAdministrador;

    @Column(name = "rol_administrador", nullable = false)
    private String roladministrador;
}