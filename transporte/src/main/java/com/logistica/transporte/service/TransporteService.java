package com.logistica.transporte.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.logistica.transporte.model.TipoTransporteEnum;
import com.logistica.transporte.model.Transporte;
import com.logistica.transporte.repository.TransporteRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TransporteService {

    private final TransporteRepository transporteRepository;

    public Transporte guardarTransporte(Transporte transporte) {
        return transporteRepository.save(transporte);
    }

    public List<Transporte> obtenerTodosLosTransportes() {
        return transporteRepository.findAll();
    }

    public Optional<Transporte> obtenerPorId(Integer id) {
        return transporteRepository.findById(id);
    }

    public void eliminarTransporte(Integer id) {
        transporteRepository.deleteById(id);
    }

    public List<Transporte> buscarPorTipo(TipoTransporteEnum tipo) {
        return transporteRepository.findByTipotransporte(tipo);
    }

    public List<Transporte> buscarPorEstado(String estado) {
        return transporteRepository.findByEstadovehiculo(estado);
    }

    public List<Transporte> buscarPorIdEvento(Integer idEvento) {
        return transporteRepository.findByIdevento(idEvento);
    }

    public List<Transporte> buscarPorRutConductor(String rut) {
        return transporteRepository.findByRutconductor(rut);
    }

    public List<Transporte> buscarPorPatente(String patente) {
        return transporteRepository.findByPatentevehiculo(patente);
    }

    public List<Transporte> buscarPorComunaDestino(String comuna) {
        return transporteRepository.findByComunadestino(comuna);
    }
}