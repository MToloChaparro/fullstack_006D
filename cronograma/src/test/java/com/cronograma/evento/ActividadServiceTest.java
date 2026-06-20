package com.cronograma.evento;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;

import com.cronograma.evento.model.Actividad;
import com.cronograma.evento.repository.ActividadRepository;
import com.cronograma.evento.service.Impl.ActividadServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ActividadServiceTest {

    @Mock private ActividadRepository actividadRepository;
    @Mock private WebClient.Builder webClientBuilder;
    @Mock private WebClient webClient; // Necesario para mockear la llamada al menú

    @InjectMocks
    private ActividadServiceImpl actividadService;

    @Test
    @DisplayName("GIVEN evento con actividades WHEN obtener cronograma THEN retorna lista")
    void testObtenerCronogramaPorEvento() {
        // Arrange
        Integer idEvento = 1;
        List<Actividad> listaSimulada = new ArrayList<>();
        // Agregamos una actividad para que entre en el loop del WebClient si es necesario
        Actividad act = new Actividad();
        act.setTitulo("Cena");
        listaSimulada.add(act);

        when(actividadRepository.findByIdEventoOrderByHoraInicioAsc(idEvento)).thenReturn(listaSimulada);
        // Mockeamos la cadena de llamadas del WebClient para evitar NullPointerException
        when(webClientBuilder.build()).thenReturn(webClient);
        when(webClient.get()).thenReturn(mock(WebClient.RequestHeadersUriSpec.class));

        // Act
        List<Actividad> resultado = actividadService.obtenerCronogramaPorEvento(idEvento);

        // Assert
        assertNotNull(resultado);
        verify(actividadRepository, times(1)).findByIdEventoOrderByHoraInicioAsc(idEvento);
    }

    @Test
    @DisplayName("GIVEN actividad existente WHEN eliminar THEN ejecuta delete del repositorio")
    void testEliminarActividad() {
        // Arrange
        Integer idActividad = 1;
        when(actividadRepository.existsById(idActividad)).thenReturn(true);
        doNothing().when(actividadRepository).deleteById(idActividad);

        // Act
        actividadService.eliminarActividad(idActividad);

        // Assert
        verify(actividadRepository, times(1)).deleteById(idActividad);
    }
}