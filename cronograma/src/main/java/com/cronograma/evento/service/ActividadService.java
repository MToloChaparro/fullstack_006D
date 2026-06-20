package com.cronograma.evento.service;

import com.cronograma.evento.model.Actividad;
import java.util.List;

public interface ActividadService {
    Actividad crearActividad(Actividad actividad);
    List<Actividad> obtenerCronogramaPorEvento(Integer idEvento);
    void eliminarActividad(Integer idActividad);
}