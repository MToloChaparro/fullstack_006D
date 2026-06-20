package com.cronograma.evento.service.Impl;

import com.cronograma.evento.dto.MenuDTO;
import com.cronograma.evento.model.Actividad;
import com.cronograma.evento.repository.ActividadRepository;
import com.cronograma.evento.service.ActividadService;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.List;

@Service
public class ActividadServiceImpl implements ActividadService {

    private final ActividadRepository actividadRepository;
    private final WebClient.Builder webClientBuilder;
    public ActividadServiceImpl(ActividadRepository actividadRepository, WebClient.Builder webClientBuilder) {
        this.actividadRepository = actividadRepository;
        this.webClientBuilder = webClientBuilder;
    }

    @Override
    public Actividad crearActividad(Actividad actividad) {
        if (actividad.getHoraFin().isBefore(actividad.getHoraInicio())) {
            throw new IllegalArgumentException("La hora de fin no puede ser anterior a la hora de inicio.");
        }
        return actividadRepository.save(actividad);
    }

    @Override
    public List<Actividad> obtenerCronogramaPorEvento(Integer idEvento) {
        List<Actividad> actividades = actividadRepository.findByIdEventoOrderByHoraInicioAsc(idEvento);
        
        for (Actividad actividad : actividades) {
            String titulo = actividad.getTitulo().toLowerCase();
            String descripcion = actividad.getDescripcionDetallada() != null ? actividad.getDescripcionDetallada().toLowerCase() : "";

            if (titulo.contains("banquete") || titulo.contains("cena") || titulo.contains("almuerzo") || descripcion.contains("id_menu")) {
                try {
                    MenuDTO menu = webClientBuilder.build()
                            .get()
                            .uri("http://localhost:8081/api/v1/menus/10") 
                            .retrieve()
                            .bodyToMono(MenuDTO.class)
                            .block(); 
                    actividad.setDetalleMenu(menu);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return actividades;
    }

    @Override
    public void eliminarActividad(Integer idActividad) {
        if (!actividadRepository.existsById(idActividad)) {
            throw new jakarta.persistence.EntityNotFoundException("No se encontró la actividad con ID: " + idActividad);
        }
        actividadRepository.deleteById(idActividad);
}
}