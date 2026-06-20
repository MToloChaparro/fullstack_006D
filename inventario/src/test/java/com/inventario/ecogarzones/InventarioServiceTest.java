package com.inventario.ecogarzones;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.inventario.ecogarzones.model.Inventario;
import com.inventario.ecogarzones.repository.InventarioRepository;
import com.inventario.ecogarzones.service.InventarioService;

@ExtendWith(MockitoExtension.class)
public class InventarioServiceTest {

    @Mock
    private InventarioRepository inventarioRepository;

    @InjectMocks
    private InventarioService inventarioService;

    @Test
    @DisplayName("GIVEN insumos en inventario WHEN calcular valor total THEN retorna suma correcta")
    void testCalcularValorTotalInventario() {
        // Arrange
        Inventario i1 = new Inventario();
        i1.setCantidadInsumo(10);
        i1.setPrecioCostoUnitario(BigDecimal.valueOf(100)); // 1000

        Inventario i2 = new Inventario();
        i2.setCantidadInsumo(5);
        i2.setPrecioCostoUnitario(BigDecimal.valueOf(200)); // 1000
        
        when(inventarioRepository.findAll()).thenReturn(Arrays.asList(i1, i2));

        // Act
        double total = inventarioService.calcularValorTotalInventario();

        // Assert
        assertEquals(2000.0, total);
        verify(inventarioRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("GIVEN insumo existente WHEN eliminar THEN se borra correctamente")
    void testEliminarInsumoExitoso() {
        // Arrange
        Long id = 1L;
        when(inventarioRepository.existsById(id)).thenReturn(true);
        doNothing().when(inventarioRepository).deleteById(id);

        // Act
        inventarioService.eliminarInsumo(id);

        // Assert
        verify(inventarioRepository, times(1)).deleteById(id);
    }
}