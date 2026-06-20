package com.boleta.electronica.repository;

import com.boleta.electronica.model.Boleta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoletaRepository extends CrudRepository<Boleta, Long> {
}