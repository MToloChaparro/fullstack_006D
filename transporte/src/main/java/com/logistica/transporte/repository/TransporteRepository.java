package com.logistica.transporte.repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.logistica.transporte.model.TipoTransporteEnum;
import com.logistica.transporte.model.Transporte;

@Repository
public interface TransporteRepository extends JpaRepository<Transporte, Integer> {

    List<Transporte> findByIdtransporte(Integer idtransporte);
    
    List<Transporte> findByIdevento(Integer idevento);
    
    List<Transporte> findByRutconductor(String rutconductor);
    
    List<Transporte> findByPatentevehiculo(String patentevehiculo);
    
    List<Transporte> findByComunadestino(String comunadestino);
    
    List<Transporte> findByTipotransporte(TipoTransporteEnum tipo);
    
    List<Transporte> findByEstadovehiculo(String estadovehiculo);
    
    List<Transporte> findByMarcavehiculo(String marcavehiculo);
    
    List<Transporte> findByModelovehiculo(String modelovehiculo);
    
    List<Transporte> findByTipovehiculo(String tipovehiculo);
    
    List<Transporte> findByCostotraslado(BigDecimal costotraslado);
    
    List<Transporte> findByHoradespachoestimada(LocalDateTime horadespachoestimada);
    
    List<Transporte> findByHorasalidaestimada(LocalDateTime horasalidaestimada);
    
    List<Transporte> findByNombreconductor(String nombreconductor);
    
    List<Transporte> findByApellidopconductor(String apellidopconductor);
    
    List<Transporte> findByApellidomconductor(String apellidomconductor);

}