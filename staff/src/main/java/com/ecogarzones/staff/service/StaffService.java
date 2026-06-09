package com.ecogarzones.staff.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ecogarzones.staff.model.Staff;
import com.ecogarzones.staff.model.EstadoStaffEnum;
import com.ecogarzones.staff.repository.StaffRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional
public class StaffService {

    private final StaffRepository staffRepository;

    public StaffService(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    public Staff crearStaff(Staff staff) {
        if (staff.getIdStaff() != null && staffRepository.existsById(staff.getIdStaff().longValue())) {
            throw new IllegalArgumentException("El miembro del staff con ID " + staff.getIdStaff() + " ya existe");
        }

        if (staff.getIdEstadoStaff() == null) {
            staff.setIdEstadoStaff(EstadoStaffEnum.ACTIVO.getId());
        } else {
            EstadoStaffEnum.desdeId(staff.getIdEstadoStaff());
        }

        return staffRepository.save(staff);
    }

    @Transactional(readOnly = true)
    public List<Staff> obtenerTodosLosStaff() {
        return staffRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Staff obtenerStaffPorId(Long idStaff) {
        return staffRepository.findById(idStaff)
                .orElseThrow(() -> new EntityNotFoundException("Miembro del staff no encontrado con ID: " + idStaff));
    }

    public Staff actualizarStaff(Long idStaff, Staff staffActualizado) {
        Staff staffExistente = staffRepository.findById(idStaff)
                .orElseThrow(() -> new EntityNotFoundException("Miembro del staff no encontrado con ID: " + idStaff));
        
        if (staffActualizado.getIdEstadoStaff() != null) {
            EstadoStaffEnum.desdeId(staffActualizado.getIdEstadoStaff());
            staffExistente.setIdEstadoStaff(staffActualizado.getIdEstadoStaff());
        }
        
        staffExistente.setNombreStaff(staffActualizado.getNombreStaff());
        staffExistente.setApellidopStaff(staffActualizado.getApellidopStaff());
        staffExistente.setApellidomStaff(staffActualizado.getApellidomStaff());
        staffExistente.setRutStaff(staffActualizado.getRutStaff());
        staffExistente.setDvRut(staffActualizado.getDvRut());
        staffExistente.setCorreoStaff(staffActualizado.getCorreoStaff());
        staffExistente.setIdRol(staffActualizado.getIdRol());
        staffExistente.setValorHora(staffActualizado.getValorHora());
        staffExistente.setIdEstadoStaff(staffActualizado.getIdEstadoStaff());

        
        return staffRepository.save(staffExistente);
    }

    public void eliminarStaff(Long idStaff) {
        if (!staffRepository.existsById(idStaff)) {
            throw new EntityNotFoundException("Miembro del staff no encontrado con ID: " + idStaff);
        }
        staffRepository.deleteById(idStaff);
    }

    @Transactional(readOnly = true)
    public List<Staff> buscarPorRol(Integer idRol) {
        return staffRepository.findByIdRol(idRol);
    }

    @Transactional(readOnly = true)
    public Staff buscarPorRut(Integer rut) {
        return staffRepository.findByRutStaff(rut)
                .orElseThrow(() -> new EntityNotFoundException("Miembro del staff no encontrado con el RUT: " + rut));
    }
}