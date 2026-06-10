package com.ecogarzones.clientes.service;

import org.springframework.stereotype.Service;
import com.ecogarzones.clientes.model.Cliente;
import com.ecogarzones.clientes.repository.ClienteRepository;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional
public class ClienteService {

    private final ClienteRepository clienteRepository;


    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente crearCliente(Cliente cliente) {
        if (clienteRepository.existsByRutCliente(cliente.getRutCliente())) {
            throw new IllegalArgumentException("El RUT ya está registrado");
        }
        if (clienteRepository.existsByCorreoCliente(cliente.getCorreoCliente())) {
            throw new IllegalArgumentException("El correo electrónico ya está registrado");
        }
        return clienteRepository.save(cliente);
    }

    public List<Cliente> obtenerTodosLosClientes() {
        return clienteRepository.findAll();
    }

    public Cliente obtenerClientePorId(Integer idCliente) {
        return clienteRepository.findById(idCliente)
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado con ID: " + idCliente));
    }


    public Cliente actualizarCliente(Integer idCliente, Cliente clienteActualizado) {
        Cliente clienteExistente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado con ID: " + idCliente));
        
        clienteExistente.setNombreCliente(clienteActualizado.getNombreCliente());
        clienteExistente.setCorreoCliente(clienteActualizado.getCorreoCliente());
        
        return clienteRepository.save(clienteExistente);
    }

    public Cliente actualizarClienteCompleto(Integer idCliente, Cliente clienteActualizado) {
        Cliente clienteExistente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado con ID: " + idCliente));
        
        clienteExistente.setNombreCliente(clienteActualizado.getNombreCliente());
        clienteExistente.setApellidopCliente(clienteActualizado.getApellidopCliente());
        clienteExistente.setApellidomCliente(clienteActualizado.getApellidomCliente());
        clienteExistente.setRutCliente(clienteActualizado.getRutCliente());
        clienteExistente.setDvRut(clienteActualizado.getDvRut());
        clienteExistente.setCorreoCliente(clienteActualizado.getCorreoCliente());
        clienteExistente.setTelefonoCliente(clienteActualizado.getTelefonoCliente());
        clienteExistente.setIdComuna(clienteActualizado.getIdComuna());
        
        return clienteRepository.save(clienteExistente);
    }

}