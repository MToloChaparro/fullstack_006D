package com.ecogarzones.pagoservicio;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ecogarzones.pagoservicio.model.Pago;
import com.ecogarzones.pagoservicio.model.EstadoPagoEnum;
import com.ecogarzones.pagoservicio.repository.PagoRepository;
import com.ecogarzones.pagoservicio.service.PagoService;
import jakarta.persistence.EntityNotFoundException;

@ExtendWith(MockitoExtension.class)
public class PagoServiceTest {

    @Mock
    private PagoRepository pagoRepository;

    @InjectMocks
    private PagoService pagoService;

    @Test
    @DisplayName("GIVEN un nuevo pago SIN estado WHEN crear THEN asigna estado PENDIENTE por defecto")
    void testCrearPagoAsignaEstadoPendiente() {
        // Arrange
        Pago pago = new Pago();
        when(pagoRepository.save(any(Pago.class))).thenReturn(pago);

        // Act
        Pago resultado = pagoService.crearPago(pago);

        // Assert
        assertEquals(EstadoPagoEnum.PENDIENTE.getId(), resultado.getIdEstadoPago());
        verify(pagoRepository, times(1)).save(pago);
    }

    @Test
    @DisplayName("GIVEN un ID existente WHEN cambiar estado THEN actualiza correctamente")
    void testEstadoPagoExitoso() {
        // Arrange
        Integer id = 1;
        Pago pagoExistente = new Pago();
        when(pagoRepository.findById(id)).thenReturn(Optional.of(pagoExistente));
        when(pagoRepository.save(any(Pago.class))).thenReturn(pagoExistente);

        // Act
        pagoService.estadoPago(id, EstadoPagoEnum.COMPLETADO.getId());

        // Assert
        assertEquals(EstadoPagoEnum.COMPLETADO.getId(), pagoExistente.getIdEstadoPago());
        verify(pagoRepository).save(pagoExistente);
    }

    @Test
    @DisplayName("GIVEN ID inexistente WHEN eliminar THEN lanza EntityNotFoundException")
    void testEliminarPagoFallido() {
        // Arrange
        Integer id = 99;
        when(pagoRepository.existsById(id)).thenReturn(false);

        // Act & Assert
        assertThrows(EntityNotFoundException.class, () -> pagoService.eliminarPago(id));
    }
}