package com.ecogarzones.evento.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ecogarzones.evento.model.Evento;
import com.ecogarzones.evento.repository.EventoRepository;
import jakarta.persistence.EntityNotFoundException;
import com.ecogarzones.evento.model.EstadoEvento_Enum;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class EventoService {

    private final EventoRepository eventoRepository;

    public EventoService(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
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
                .orElseThrow(() -> new EntityNotFoundException("Evento no encontrado con ID: " + id));
        
        if (eventoActualizado.getIdEstadoEvento() != null) {
            EstadoEvento_Enum.obtenerNombreEstado(eventoActualizado.getIdEstadoEvento());
            eventoExistente.setIdEstadoEvento(eventoActualizado.getIdEstadoEvento());
        }

        eventoExistente.setNombreEvento(eventoActualizado.getNombreEvento());
        eventoExistente.setDescripcionEvento(eventoActualizado.getDescripcionEvento());
        eventoExistente.setFechaEvento(eventoActualizado.getFechaEvento());
        eventoExistente.setHoraEvento(eventoActualizado.getHoraEvento());
        eventoExistente.setDireccionEvento(eventoActualizado.getDireccionEvento());
        eventoExistente.setIdCliente(eventoActualizado.getIdCliente());
        eventoExistente.setIdPagoServicio(eventoActualizado.getIdPagoServicio());
        eventoExistente.setIdAdministrador(eventoActualizado.getIdAdministrador());
        eventoExistente.setIdComuna(eventoActualizado.getIdComuna());
        
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