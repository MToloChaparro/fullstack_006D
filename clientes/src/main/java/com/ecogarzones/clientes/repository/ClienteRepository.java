package com.ecogarzones.clientes.repository;

import com.ecogarzones.clientes.model.Cliente;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    boolean existsByRutCliente(Integer rutCliente);

    boolean existsByCorreoCliente(String correoCliente);

    Optional<Cliente> findByCorreoCliente(String correoCliente);

    Optional<Cliente> findByRutCliente(Integer rutCliente);

    List<Cliente> findByIdComuna(Integer idComuna);

    List<Cliente> findByApellidopClienteContainingIgnoreCase(String apellido);


}

