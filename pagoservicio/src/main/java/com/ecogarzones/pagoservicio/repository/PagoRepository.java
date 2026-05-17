package com.ecogarzones.pagoservicio.repository;

import com.ecogarzones.pagoservicio.model.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Integer> {


    List<Pago> findByMontoPagoGreaterThan(Integer monto);

    List<Pago> findByIdEstadoPago(Integer idEstadoPago);

    List<Pago> findByFechaPago(LocalDate fechaPago);

    List<Pago> findByFechaPagoBetween(LocalDate fechaInicio, LocalDate fechaFin);

    List<Pago> findByIdMetodoPago(Integer idMetodoPago);

    List<Pago> findByIdMetodoPagoAndIdEstadoPago(Integer idMetodoPago, Integer idEstadoPago);
}