package com.ecogarzones.staff;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ecogarzones.staff.model.Staff;
import com.ecogarzones.staff.model.EstadoStaffEnum;
import com.ecogarzones.staff.repository.StaffRepository;
import com.ecogarzones.staff.service.StaffService;

@ExtendWith(MockitoExtension.class)
public class StaffServiceTest {

    @Mock
    private StaffRepository staffRepository;

    @InjectMocks
    private StaffService staffService;

    @Test
    @DisplayName("GIVEN staff desvinculado (estado 4) SIN motivo WHEN crear THEN lanza IllegalArgumentException")
    void testCrearStaffDesvinculadoSinMotivoFalla() {
        // Arrange
        Staff staff = new Staff();
        staff.setIdEstadoStaff(4); // ID 4 = DESVINCULADO
        staff.setMotivoDesvinculacion(""); // Vacío

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> staffService.crearStaff(staff));
    }

    @Test
    @DisplayName("GIVEN nuevo staff SIN estado WHEN crear THEN asigna ACTIVO por defecto")
    void testCrearStaffAsignaActivoPorDefecto() {
        // Arrange
        Staff staff = new Staff();
        
        when(staffRepository.save(any(Staff.class))).thenReturn(staff);

        // Act
        Staff resultado = staffService.crearStaff(staff);

        // Assert
        assertEquals(EstadoStaffEnum.ACTIVO.getId(), resultado.getIdEstadoStaff());
        verify(staffRepository).save(staff);
    }
}