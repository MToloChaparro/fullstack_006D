package com.ecogarzones.staff.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
            return ResponseEntity.ok(staff);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{idStaff}")
    public ResponseEntity<?> actualizarStaff(@PathVariable Long idStaff, @RequestBody Staff staffActualizado) {
        try {
            Staff staff = staffService.actualizarStaff(idStaff, staffActualizado);
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
            return ResponseEntity.ok(staff);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/buscar-rol")
    public ResponseEntity<List<Staff>> buscarPorRol(@RequestParam Integer idRol) {
        List<Staff> lista = staffService.buscarPorRol(idRol);
        return ResponseEntity.ok(lista);
    }
}