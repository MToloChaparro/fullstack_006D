package com.ecogarzones.administrador.repository;

import com.ecogarzones.administrador.model.Administrador;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Administrador, Integer> {


    List<Administrador> findByNombreAdministrador(String nombre);

    List<Administrador> findByRoladministrador(String rol);

    Optional<Administrador> findByCorreoAdministrador(String correo);

    Optional<Administrador> findByRutAdministrador(Integer rut);

    List<Administrador> findByApellidopAdministradorContainingIgnoreCase(String apellido); //Apellido Paterno

    List<Administrador> findByApellidomAdministradorContainingIgnoreCase(String apellido); //Apellido Materno

    boolean existsByRutAdministrador(Integer rutAdministrador);

    boolean existsByCorreoAdministrador(String correoAdministrador);
}
