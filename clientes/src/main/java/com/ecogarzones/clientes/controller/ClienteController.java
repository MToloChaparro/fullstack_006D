package com.ecogarzones.clientes.controller;

import com.ecogarzones.clientes.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ecogarzones.clientes.repository.ClienteRepository;
import com.ecogarzones.clientes.model.Cliente;
import java.util.List;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    ClienteController(ClienteService clienteService) {
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listar(){
        List<Cliente> clientes = clienteRepository.findAll();
        if (clientes.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(clientes);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarCliente(@PathVariable Integer id){
        try{
            Cliente cliente = clienteRepository.findById(id).orElse(null);
            if (cliente == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(cliente);
        }
        catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Cliente> crear(@RequestBody Cliente cliente){
        if (clienteRepository.existsByRutCliente(cliente.getRutCliente())) {
            return ResponseEntity.badRequest().build();
        }
        if (clienteRepository.existsByCorreoCliente(cliente.getCorreoCliente())) {
            return ResponseEntity.badRequest().build();
        }
        Cliente nuevoCliente = clienteRepository.save(cliente);
        return ResponseEntity.ok(nuevoCliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizar(@PathVariable Integer id, @RequestBody Cliente cliente){
        try{
            Cliente clienteExistente = clienteRepository.findById(id).orElse(null);
            if (clienteExistente == null) {
                return ResponseEntity.notFound().build();
            }
            clienteExistente.setNombreCliente(cliente.getNombreCliente());
            clienteExistente.setApellidopCliente(cliente.getApellidopCliente()); // Apellido paterno
            clienteExistente.setApellidomCliente(cliente.getApellidomCliente()); // Apellido materno
            clienteExistente.setRutCliente(cliente.getRutCliente());
            clienteExistente.setDvRut(cliente.getDvRut());
            clienteExistente.setTelefonoCliente(cliente.getTelefonoCliente());
            clienteExistente.setCorreoCliente(cliente.getCorreoCliente());
            clienteExistente.setIdComuna(cliente.getIdComuna());
            Cliente clienteActualizado = clienteRepository.save(clienteExistente);
            return ResponseEntity.ok(clienteActualizado);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id){
        try{
            if (!clienteRepository.existsById(id)) {
                return ResponseEntity.notFound().build();
            }
            clienteRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
    

}
