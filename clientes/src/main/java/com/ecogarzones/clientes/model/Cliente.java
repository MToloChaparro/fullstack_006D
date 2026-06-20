package com.ecogarzones.clientes.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Integer idCliente;

    @Column(name = "nombre_cliente", nullable = false)
    private String nombreCliente;

    @Column(name = "apellidop_cliente", nullable = false)
    private String apellidopCliente;

    @Column(name = "apellidom_cliente")
    private String apellidomCliente;

    @Column(name = "rut_cliente", nullable = false)
    private Integer rutCliente;

    @Column(name = "dv_rut", length = 1, nullable = false)
    private String dvRut;

    @Column(name = "correo_cliente", unique = true)
    private String correoCliente;

    @Column(name = "telefono_cliente")
    private String telefonoCliente;

    @Column(name = "id_comuna")
    private Integer idComuna;
}
