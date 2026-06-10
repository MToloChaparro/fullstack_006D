package com.ecogarzones.evento.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ecogarzones.evento.model.Evento;
import com.ecogarzones.evento.model.Menu;
import com.ecogarzones.evento.repository.EventoRepository;
import com.ecogarzones.evento.repository.MenuRepository;

import jakarta.persistence.EntityNotFoundException;
import com.ecogarzones.evento.model.EstadoEvento_Enum;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class EventoService {

    private final EventoRepository eventoRepository;
    private final MenuRepository menuRepository;

    @Autowired
    public EventoService(EventoRepository eventoRepository, MenuRepository menuRepository) {
        this.eventoRepository = eventoRepository;
        this.menuRepository = menuRepository;
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
        return eventoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Evento obtenerEventoPorId(Integer id) {  
        return eventoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Evento no encontrado con ID: " + id));
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
        return eventoRepository.findByNombreEventoContainingIgnoreCase(nombre);
    }

    @Transactional(readOnly = true)
    public List<Evento> buscarEventosPorFecha(LocalDate fecha) { 
        return eventoRepository.findByFechaEvento(fecha);
    }
}