package com.ecogarzones.evento;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ecogarzones.evento.model.Menu;
import com.ecogarzones.evento.repository.MenuRepository;
import com.ecogarzones.evento.service.MenuService;

@ExtendWith(MockitoExtension.class)
public class MenuServiceTest {

    @Mock
    private MenuRepository menuRepository;

    @InjectMocks
    private MenuService menuService;

    @Test
    @DisplayName("Debería guardar y retornar el menú creado")
    void testCrearMenu() {
        // Arrange
        Menu menu = new Menu();
        menu.setTipoMenu("Menu Ejecutivo");
        when(menuRepository.save(any(Menu.class))).thenReturn(menu);

        // Act
        Menu resultado = menuService.crearMenu(menu);

        // Assert
        assertNotNull(resultado);
        assertEquals("Menu Ejecutivo", resultado.getTipoMenu());
        verify(menuRepository, times(1)).save(menu);
    }

    @Test
    @DisplayName("Debería retornar todos los menús existentes")
    void testObtenerTodosLosMenus() {
        // Arrange
        when(menuRepository.findAll()).thenReturn(new ArrayList<>());

        // Act
        List<Menu> lista = menuService.obtenerTodosLosMenus();

        // Assert
        assertNotNull(lista);
        verify(menuRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Debería lanzar excepción si el menú no existe al buscar por ID")
    void testObtenerMenuPorIdFallido() {
        // Arrange
        Integer id = 99;
        when(menuRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            menuService.obtenerMenuPorId(id);
        });
        verify(menuRepository, times(1)).findById(id);
    }
}