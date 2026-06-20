package com.ecogarzones.clientes;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ecogarzones.clientes.model.Cliente;
import com.ecogarzones.clientes.repository.ClienteRepository;
import com.ecogarzones.clientes.service.ClienteService;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    @Test
    @DisplayName("GIVEN nuevo cliente con RUT duplicado WHEN crear THEN lanza IllegalArgumentException")
    void testCrearClienteDuplicado() {
        // Arrange
        Cliente cliente = new Cliente();
        cliente.setRutCliente(12345678);
        when(clienteRepository.existsByRutCliente(12345678)).thenReturn(true);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> clienteService.crearCliente(cliente));
    }

    @Test
    @DisplayName("GIVEN ID existente WHEN actualizar completo THEN guarda cambios")
    void testActualizarClienteCompletoExitoso() {
        // Arrange
        Integer id = 1;
        Cliente existente = new Cliente();
        Cliente actualizado = new Cliente();
        actualizado.setNombreCliente("Nuevo Nombre");
        
        when(clienteRepository.findById(id)).thenReturn(Optional.of(existente));
        when(clienteRepository.save(any(Cliente.class))).thenReturn(existente);

        // Act
        Cliente resultado = clienteService.actualizarClienteCompleto(id, actualizado);

        // Assert
        assertEquals("Nuevo Nombre", resultado.getNombreCliente());
        verify(clienteRepository, times(1)).save(existente);
    }

    @Test
    @DisplayName("GIVEN ID inexistente WHEN buscar por ID THEN lanza EntityNotFoundException")
    void testObtenerClientePorIdNoEncontrado() {
        // Arrange
        when(clienteRepository.findById(99)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(jakarta.persistence.EntityNotFoundException.class, () -> 
            clienteService.obtenerClientePorId(99));
    }
}