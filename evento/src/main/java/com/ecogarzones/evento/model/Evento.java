package com.ecogarzones.evento.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "ms_evento")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_evento")
    private Integer idEvento;
    
    @Column(name = "id_cliente", nullable = false)
    private Integer idCliente;

    @ManyToOne
    @JoinColumn(name = "id_menu", referencedColumnName = "id_menu")
    private Menu menu;

    @Column(name = "id_pago_servicio")
    private Integer idPagoServicio;

    @Column(name = "id_administrador")
    private Integer idAdministrador;

    @Column(name = "id_comuna", nullable = false)
    private Integer idComuna;

    @Column(name = "id_estado_evento", nullable = false)
    private Integer idEstadoEvento;

    @Column(name = "nombre_evento", length = 100, nullable = false)
    private String nombreEvento;

    @Column(name = "fecha_evento", nullable = false)
    private LocalDate fechaEvento;

    @Column(name = "hora_evento", nullable = false)
    private LocalTime horaEvento;

    @Column(name = "direccion_evento", length = 200, nullable = false)
    private String direccionEvento;

    @Column(name = "descripcion_evento", length = 500)
    private String descripcionEvento;
}