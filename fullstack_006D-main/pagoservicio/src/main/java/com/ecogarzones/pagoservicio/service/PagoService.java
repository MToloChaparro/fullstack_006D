package com.ecogarzones.pagoservicio.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ecogarzones.pagoservicio.model.Pago;
import com.ecogarzones.pagoservicio.model.EstadoPagoEnum;
import com.ecogarzones.pagoservicio.repository.PagoRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional
public class PagoService {

    private final PagoRepository pagoRepository;

    public PagoService(PagoRepository pagoRepository) {
        this.pagoRepository = pagoRepository;
    }

    public Pago crearPago(Pago pago) {
        if (pago.getIdPagoServicio() != null && pagoRepository.existsById(pago.getIdPagoServicio())) {
            throw new IllegalArgumentException("El pago con ID " + pago.getIdPagoServicio() + " ya existe");
        }
        
        if (pago.getIdEstadoPago() == null) {
            pago.setIdEstadoPago(EstadoPagoEnum.PENDIENTE.getId()); // Guarda un 1
        }
        
        return pagoRepository.save(pago);
    }

    @Transactional(readOnly = true)
    public List<Pago> obtenerTodosLosPagos() {
        return pagoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Pago obtenerPagoPorId(Integer idPagoServicio) {
        return pagoRepository.findById(idPagoServicio)
                .orElseThrow(() -> new EntityNotFoundException("Pago no encontrado con ID: " + idPagoServicio));
    }

    public Pago actualizarPago(Integer idPagoServicio, Pago pagoActualizado) {
        Pago pagoExistente = pagoRepository.findById(idPagoServicio)
                .orElseThrow(() -> new EntityNotFoundException("Pago no encontrado con ID: " + idPagoServicio));
        
        pagoExistente.setMontoPago(pagoActualizado.getMontoPago());
        pagoExistente.setFechaPago(pagoActualizado.getFechaPago());
        pagoExistente.setIdEstadoPago(pagoActualizado.getIdEstadoPago());
        
        return pagoRepository.save(pagoExistente);
    }

    public Pago estadoPago(Integer idPagoServicio, Integer idEstadoPago) { 
        Pago pagoExistente = pagoRepository.findById(idPagoServicio)
                .orElseThrow(() -> new EntityNotFoundException("Pago no encontrado con ID: " + idPagoServicio));
        

        EstadoPagoEnum.desdeId(idEstadoPago); 
        
        pagoExistente.setIdEstadoPago(idEstadoPago);
        return pagoRepository.save(pagoExistente);
    }

    public void eliminarPago(Integer idPagoServicio) {
        if (!pagoRepository.existsById(idPagoServicio)) {
            throw new EntityNotFoundException("Pago no encontrado con ID: " + idPagoServicio);
        }
        pagoRepository.deleteById(idPagoServicio);
    }

    public Pago fechPago(Integer idPagoServicio, java.time.LocalDate fechaPago) {
        Pago pagoExistente = pagoRepository.findById(idPagoServicio)
                .orElseThrow(() -> new EntityNotFoundException("Pago no encontrado con ID: " + idPagoServicio));
        
        pagoExistente.setFechaPago(fechaPago);
        return pagoRepository.save(pagoExistente);
    }
}