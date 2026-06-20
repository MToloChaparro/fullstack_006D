package com.ecogarzones.evento.repository;

import java.time.LocalDate;
import java.util.List;
import com.ecogarzones.evento.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Integer> { // Corregido de Long a Integer


    List<Evento> findByNombreEventoContainingIgnoreCase(String nombreEvento);   

    List<Evento> findByFechaEvento(LocalDate fechaEvento);

    List<Evento> findByIdCliente(Integer idCliente);

    List<Evento> findByFechaEventoBetween(LocalDate fechaInicio, LocalDate fechaFin);

    List<Evento> findByIdEstadoEvento(Integer idEstadoEvento);

    List<Evento> findByIdComuna(Integer idComuna);

    List<Evento> findByIdAdministrador(Integer idAdministrador);

    
}