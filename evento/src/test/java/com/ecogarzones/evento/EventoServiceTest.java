package com.ecogarzones.evento; // Asegúrate que el paquete coincida con tu estructura

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import jakarta.persistence.EntityNotFoundException;

import com.ecogarzones.evento.repository.EventoRepository;
import com.ecogarzones.evento.service.EventoService;

@ExtendWith(MockitoExtension.class)
public class EventoServiceTest {

    @Mock
    private EventoRepository eventoRepository;

    @InjectMocks
    private EventoService eventoService;

    @Test
    @DisplayName("Debería ejecutar la eliminación del evento correctamente si existe")
    void testEliminarEventoExitoso() {
        // 1. Arrange
        Integer id = 1;
        when(eventoRepository.existsById(id)).thenReturn(true);
        
        // 2. Act
        eventoService.eliminarEvento(id);

        // 3. Assert
        verify(eventoRepository, times(1)).deleteById(id);
    }

    @Test
    @DisplayName("Debería lanzar EntityNotFoundException si el evento no existe")
    void testEliminarEventoFallido() {
        Integer id = 99;
        when(eventoRepository.existsById(id)).thenReturn(false);
        assertThrows(EntityNotFoundException.class, () -> {
        eventoService.eliminarEvento(id); 
});
        verify(eventoRepository, never()).deleteById(id);
    }
}