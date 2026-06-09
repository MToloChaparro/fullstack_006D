package com.ecogarzones.administrador.service;

import com.ecogarzones.administrador.model.Administrador;
import com.ecogarzones.administrador.repository.AdminRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AdminService {

    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public Administrador crearAdministrador(Administrador administrador) {
        if (adminRepository.existsByRutAdministrador(administrador.getRutAdministrador())) {
            throw new IllegalArgumentException("El RUT del administrador ya está registrado");
        }
        if (adminRepository.existsByCorreoAdministrador(administrador.getCorreoAdministrador())) {
            throw new IllegalArgumentException("El correo del administrador ya está registrado");
        }
        return adminRepository.save(administrador);
    }

    @Transactional(readOnly = true)
    public List<Administrador> obtenerTodosLosAdministradores() {
        return adminRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Administrador obtenerAdministradorPorId(Integer idAdministrador) {
        return adminRepository.findById(idAdministrador)
                .orElseThrow(() -> new EntityNotFoundException("Error: El Administrador no ha sido encontrado con la ID: " + idAdministrador));
    }

    @Transactional(readOnly = true)
    public List<Administrador> obtenerAdministradoresporRol() { 
        return adminRepository.findByRoladministrador("ROL_ADMIN");
    }

    @Transactional(readOnly = true)
    public Administrador obtenerAdministradorPorRut(Integer rut) {
        return adminRepository.findByRutAdministrador(rut)
                .orElseThrow(() -> new EntityNotFoundException("Error: El Administrador no ha sido encontrado con el RUT: " + rut));
    }

    @Transactional(readOnly = true)
    public Administrador obtenerAdministradorPorCorreo(String correo) {
        return adminRepository.findByCorreoAdministrador(correo)
                .orElseThrow(() -> new EntityNotFoundException("Error: El Administrador no ha sido encontrado con el Correo: " + correo));
    }

    public Administrador actualizarAdministrador(Integer idAdministrador, Administrador actualizado) {
        Administrador existente = adminRepository.findById(idAdministrador)
                .orElseThrow(() -> new EntityNotFoundException("Administrador no ha sido encontrado con la ID: " + idAdministrador));

        existente.setNombreAdministrador(actualizado.getNombreAdministrador());
        existente.setApellidopAdministrador(actualizado.getApellidopAdministrador());
        existente.setApellidomAdministrador(actualizado.getApellidomAdministrador());
        existente.setRutAdministrador(actualizado.getRutAdministrador());
        existente.setDvRut(actualizado.getDvRut());
        existente.setCorreoAdministrador(actualizado.getCorreoAdministrador());
        existente.setRoladministrador(actualizado.getRoladministrador());

        return adminRepository.save(existente);
    }
}