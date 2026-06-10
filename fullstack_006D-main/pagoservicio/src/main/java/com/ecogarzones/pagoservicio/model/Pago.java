package com.ecogarzones.pagoservicio.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "ms_pago_servicio")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pago_servicio")
    private Integer idPagoServicio;

    @Column(name = "id_estado_pago", nullable = false)
    private Integer idEstadoPago;

    @Column(name = "id_metodo_pago", nullable = false)
    private Integer idMetodoPago;

    @Column(name = "monto_pago", nullable = false)
    private Integer montoPago;

    @Column(name = "fecha_pago", nullable = false)
    private LocalDate fechaPago;

    @Column(name = "hora_pago", nullable = false)
    private LocalTime horaPago;
}