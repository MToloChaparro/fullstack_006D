package com.ecogarzones.evento.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ecogarzones.evento.model.Evento;
import com.ecogarzones.evento.model.Menu;
import com.ecogarzones.evento.repository.EventoRepository;
import com.ecogarzones.evento.repository.MenuRepository;
import com.ecogarzones.evento.client.IncidenciaClient; 


import jakarta.persistence.EntityNotFoundException;
import com.ecogarzones.evento.model.EstadoEvento_Enum;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class EventoService {

    private final EventoRepository eventoRepository;
    private final MenuRepository menuRepository;
    private final IncidenciaClient incidenciaClient; 

    @Autowired
    public EventoService(EventoRepository eventoRepository, MenuRepository menuRepository, IncidenciaClient incidenciaClient) {
        this.eventoRepository = eventoRepository;
        this.menuRepository = menuRepository;
        this.incidenciaClient = incidenciaClient;
    }

    public Evento crearEvento(Evento evento) {
        if (evento.getIdEvento() != null && eventoRepository.existsById(evento.getIdEvento())) {
            throw new IllegalArgumentException("El evento con ID " + evento.getIdEvento() + " ya existe");
        }

        if (evento.getIdEstadoEvento() == null) {
            evento.setIdEstadoEvento(EstadoEvento_Enum.CONFIRMADO); 
        } else {
            EstadoEvento_Enum.obtenerNombreEstado(evento.getIdEstadoEvento());
        }

        if (evento.getMenu() != null && evento.getMenu().getIdMenu() != null) {
            Menu menuCompleto = menuRepository.findById(evento.getMenu().getIdMenu())
                    .orElseThrow(() -> new EntityNotFoundException("Menú no encontrado con ID: " + evento.getMenu().getIdMenu()));
            evento.setMenu(menuCompleto);
        }

        return eventoRepository.save(evento);
    }


    @Transactional(readOnly = true)
    public List<Evento> obtenerTodosLosEventos() {
        List<Evento> eventos = eventoRepository.findAll();
        for (Evento evento : eventos) {
            inyectarIncidenciasAEvento(evento);
        }
        return eventos;
    }


    @Transactional(readOnly = true)
    public Evento obtenerEventoPorId(Integer id) {  
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Evento no encontrado con ID: " + id));
        

        inyectarIncidenciasAEvento(evento);
        
        return evento;
    }

    public Evento actualizarEvento(Integer id, Evento eventoActualizado) {
        Evento eventoExistente = eventoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado con ID: " + id));

        eventoExistente.setNombreEvento(eventoActualizado.getNombreEvento());
        eventoExistente.setIdCliente(eventoActualizado.getIdCliente());
        eventoExistente.setIdComuna(eventoActualizado.getIdComuna());
        eventoExistente.setIdEstadoEvento(eventoActualizado.getIdEstadoEvento());
        eventoExistente.setFechaEvento(eventoActualizado.getFechaEvento());
        eventoExistente.setHoraEvento(eventoActualizado.getHoraEvento());
        eventoExistente.setDireccionEvento(eventoActualizado.getDireccionEvento());
        eventoExistente.setDescripcionEvento(eventoActualizado.getDescripcionEvento());
        eventoExistente.setIdAdministrador(eventoActualizado.getIdAdministrador());
        eventoExistente.setIdPagoServicio(eventoActualizado.getIdPagoServicio());

        if (eventoActualizado.getMenu() != null && eventoActualizado.getMenu().getIdMenu() != null) {
            Menu menuCompleto = menuRepository.findById(eventoActualizado.getMenu().getIdMenu())
                    .orElseThrow(() -> new EntityNotFoundException("Menú no encontrado con ID: " + eventoActualizado.getMenu().getIdMenu()));
            eventoExistente.setMenu(menuCompleto);
        } else {
            eventoExistente.setMenu(null); 
        }

        return eventoRepository.save(eventoExistente);
    }

    public void eliminarEvento(Integer id) { 
        if (!eventoRepository.existsById(id)) {
            throw new EntityNotFoundException("Evento no encontrado con ID: " + id);
        }
        eventoRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<Evento> buscarEventosPorNombre(String nombre) { 
        List<Evento> eventos = eventoRepository.findByNombreEventoContainingIgnoreCase(nombre);
        eventos.forEach(this::inyectarIncidenciasAEvento);
        return eventos;
    }

    @Transactional(readOnly = true)
    public List<Evento> buscarEventosPorFecha(LocalDate fecha) { 
        List<Evento> eventos = eventoRepository.findByFechaEvento(fecha);
        eventos.forEach(this::inyectarIncidenciasAEvento);
        return eventos;
    }

    private void inyectarIncidenciasAEvento(Evento evento) {
        try {
            List<Object> incidenciasAsociadas = incidenciaClient.obtenerIncidenciasPorEvento(evento.getIdEvento());
            if (incidenciasAsociadas != null) {
                evento.setReporteIncidencia(incidenciasAsociadas);
            } else {
                evento.setReporteIncidencia(new ArrayList<>());
            }
        } catch (Exception e) {
            System.err.println("Error al conectar con ms-incidencias usando Feign: " + e.getMessage());
            evento.setReporteIncidencia(new ArrayList<>());
        }
    }
}