package com.ecogarzones.staff.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ms_staff")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_staff")
    private Integer idStaff;

    @Column(name = "nombre_staff", nullable = false)
    private String nombreStaff;

    @Column(name = "apellidop_staff", nullable = false)
    private String apellidopStaff;

    @Column(name = "apellidom_staff")
    private String apellidomStaff;

    @Column(name = "rut_staff", nullable = false)
    private Integer rutStaff;

    @Column(name = "dv_rut", length = 1, nullable = true)
    private String dvRut;

    @Column(name = "correo_staff", unique = true)
    private String correoStaff;

    @Column(name = "telefono_staff")
    private String telefonoStaff;

    @Column(name = "id_rol", nullable = false)
    private Integer idRol;

    @Column(name = "id_estado_staff", nullable = false)
    private Integer idEstadoStaff;

    @Column(name = "valor_hora", nullable = false)
    private Double valorHora;

    @Column(name = "motivo_desvinculacion", length = 255)
    private String motivoDesvinculacion;

    @Transient
    private String nombreRol;

    @Transient
    private String nombreEstadoStaff;

    
}