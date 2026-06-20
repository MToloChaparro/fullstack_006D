package com.inventario.ecogarzones.repository;

import com.inventario.ecogarzones.model.Inventario; // Asegúrate de que coincida con tu modelo
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario, Long> {
}