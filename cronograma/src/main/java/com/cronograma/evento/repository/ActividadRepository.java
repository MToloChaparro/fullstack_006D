package com.cronograma.evento.repository;

import com.cronograma.evento.model.Actividad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ActividadRepository extends JpaRepository<Actividad, Integer> {
    
    List<Actividad> findByIdEventoOrderByHoraInicioAsc(Integer idEvento);
}