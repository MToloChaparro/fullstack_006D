package com.ecogarzones.staff.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ecogarzones.staff.model.Staff;
import java.util.Optional;
import java.util.List;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> 
{

Optional<Staff> findByCorreoStaff(String correoStaff);

    Optional<Staff> findByRutStaff(Integer rutStaff);

    List<Staff> findByIdRol(Integer idRol);

    List<Staff> findByIdEstadoStaff(Integer idEstadoStaff);

    List<Staff> findByValorHora(Double valorHora);

    List<Staff> findByValorHoraLessThanEqual(Double valorHora);

    List<Staff> findByValorHoraGreaterThanEqual(Double valorHora);



}
