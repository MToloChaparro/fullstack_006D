package com.ecogarzones.administrador.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ecogarzones.administrador.service.AdminService;
import com.ecogarzones.administrador.repository.AdminRepository;
import com.ecogarzones.administrador.model.Administrador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("api/v1/administrador")
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;

    AdminController(AdminService adminService) {
    }

    @GetMapping
    public ResponseEntity<List<Administrador>> listar(){
        List<Administrador> administradores = adminRepository.findAll();
        if (administradores.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(administradores);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Administrador> buscarAdministrador(@PathVariable Integer id){
        try{
            Administrador administrador = adminRepository.findById(id).orElse(null);
            if (administrador == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(administrador);
        }
        catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("rol")
    public ResponseEntity<List<Administrador>> buscarAdministradoresporRol(@RequestParam String rol){
        List<Administrador> administradores = adminRepository.findByRoladministrador(rol);
        if (administradores.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(administradores);
    }
    
    @PostMapping
    public ResponseEntity<Administrador> crear(@RequestBody Administrador administrador){
        return ResponseEntity.ok(adminRepository.save(administrador));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Administrador> actualizar(@PathVariable Integer id, @RequestBody Administrador
            administradorActualizado) {
        return adminRepository.findById(id)
                .map(administrador -> {
                    administrador.setNombreAdministrador(administradorActualizado.getNombreAdministrador());
                    administrador.setApellidopAdministrador(administradorActualizado.getApellidopAdministrador());
                    administrador.setApellidomAdministrador(administradorActualizado.getApellidomAdministrador());
                    administrador.setRutAdministrador(administradorActualizado.getRutAdministrador());
                    administrador.setDvRut(administradorActualizado.getDvRut());
                    administrador.setCorreoAdministrador(administradorActualizado.getCorreoAdministrador());
                    administrador.setRoladministrador(administradorActualizado.getRoladministrador());
                    return ResponseEntity.ok(adminRepository.save(administrador));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> eliminar(@PathVariable Integer id) {
        return adminRepository.findById(id)
                .map(administrador -> {
                    adminRepository.delete(administrador);
                    return ResponseEntity.noContent().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}

