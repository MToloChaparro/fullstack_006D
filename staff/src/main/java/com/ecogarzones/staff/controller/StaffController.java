package com.ecogarzones.staff.controller;

import com.ecogarzones.staff.repository.StaffRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecogarzones.staff.model.EstadoStaffEnum;
import com.ecogarzones.staff.model.RolStaff_Enum;
import com.ecogarzones.staff.model.Staff;
import com.ecogarzones.staff.service.StaffService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/api/staff")
public class StaffController {

    private final StaffService staffService;

    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @GetMapping("/{idStaff}")
    public ResponseEntity<Staff> obtenerStaffPorId(@PathVariable Long idStaff) {
        try {
            Staff staff = staffService.obtenerStaffPorId(idStaff);
            
            // Mapeo de Rol y Estado
            if (staff.getIdRol() != null) {
                staff.setNombreRol(RolStaff_Enum.obtenerNombreRol(staff.getIdRol()));
            }
            if (staff.getIdEstadoStaff() != null) {
                staff.setNombreEstadoStaff(EstadoStaffEnum.desdeId(staff.getIdEstadoStaff()).name());
            }
            
            return ResponseEntity.ok(staff);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{idStaff}")
    public ResponseEntity<?> actualizarStaff(@PathVariable Long idStaff, @RequestBody Staff staffActualizado) {
        try {
            Staff staff = staffService.actualizarStaff(idStaff, staffActualizado);
            
            // Mapeo de Rol y Estado para la respuesta de actualización
            if (staff.getIdRol() != null) {
                staff.setNombreRol(RolStaff_Enum.obtenerNombreRol(staff.getIdRol()));
            }
            if (staff.getIdEstadoStaff() != null) {
                staff.setNombreEstadoStaff(EstadoStaffEnum.desdeId(staff.getIdEstadoStaff()).name());
            }
            
            return ResponseEntity.ok(staff);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{idStaff}")
    public ResponseEntity<Void> eliminarStaff(@PathVariable Long idStaff) {
        try {
            staffService.eliminarStaff(idStaff);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/buscar-rut")
    public ResponseEntity<?> buscarPorRut(@RequestParam Integer rut) {
        try {
            Staff staff = staffService.buscarPorRut(rut);
            
            if (staff.getIdRol() != null) {
                staff.setNombreRol(RolStaff_Enum.obtenerNombreRol(staff.getIdRol()));
            }
            if (staff.getIdEstadoStaff() != null) {
                staff.setNombreEstadoStaff(EstadoStaffEnum.desdeId(staff.getIdEstadoStaff()).name());
            }
            
            return ResponseEntity.ok(staff);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/buscar-rol")
    public ResponseEntity<List<Staff>> buscarPorRol(@RequestParam Integer idRol) {
        List<Staff> lista = staffService.buscarPorRol(idRol);
        
        for (Staff s : lista) {
            if (s.getIdRol() != null) {
                s.setNombreRol(RolStaff_Enum.obtenerNombreRol(s.getIdRol()));
            }
            if (s.getIdEstadoStaff() != null) {
                s.setNombreEstadoStaff(EstadoStaffEnum.desdeId(s.getIdEstadoStaff()).name());
            }
        }
        
        return ResponseEntity.ok(lista);
    }

    @PostMapping
    public ResponseEntity<?> crearStaff(@RequestBody Staff nuevoStaff) {
        try {
            Staff staffGuardado = staffService.crearStaff(nuevoStaff); 
            
            if (staffGuardado.getIdRol() != null) {
                staffGuardado.setNombreRol(RolStaff_Enum.obtenerNombreRol(staffGuardado.getIdRol()));
            }
            if (staffGuardado.getIdEstadoStaff() != null) {
                staffGuardado.setNombreEstadoStaff(EstadoStaffEnum.desdeId(staffGuardado.getIdEstadoStaff()).name());
            }
            
            return ResponseEntity.status(HttpStatus.CREATED).body(staffGuardado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el staff: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Staff>> listarTodo() {
        List<Staff> personal = staffService.obtenerTodosLosStaff();
        
        if (personal.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        
        for (Staff s : personal) {
            if (s.getIdRol() != null) {
                s.setNombreRol(RolStaff_Enum.obtenerNombreRol(s.getIdRol()));
            }
            if (s.getIdEstadoStaff() != null) {
                s.setNombreEstadoStaff(EstadoStaffEnum.desdeId(s.getIdEstadoStaff()).name());
            }
        }
        
        return ResponseEntity.ok(personal);
    }
}