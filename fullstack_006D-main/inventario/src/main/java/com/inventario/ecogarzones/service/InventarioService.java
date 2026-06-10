package com.inventario.ecogarzones.service;

import com.inventario.ecogarzones.model.Inventario;
import com.inventario.ecogarzones.repository.InventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class InventarioService {

    private final InventarioRepository inventarioRepository;

    @Autowired
    public InventarioService(InventarioRepository inventarioRepository) {
        this.inventarioRepository = inventarioRepository;
    }

    @Transactional(readOnly = true)
    public List<Inventario> listarTodosLosInsumos() {
        return inventarioRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Inventario> buscarInsumoPorId(Long id) {
        return inventarioRepository.findById(id);
    }

    @Transactional
    public Inventario registrarInsumo(Inventario inventario) {
        if (inventario.getNombreInsumo() != null) {
            inventario.setNombreInsumo(inventario.getNombreInsumo().toUpperCase());
        }
        return inventarioRepository.save(inventario);
    }

    @Transactional
    public boolean eliminarInsumo(Long id) {
        if (!inventarioRepository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar: El insumo con ID " + id + " no existe.");
        }
        inventarioRepository.deleteById(id);
        return false;
    }

    @Transactional(readOnly = true)
    public double calcularValorTotalInventario() {
        return inventarioRepository.findAll().stream()
                .filter(insumo -> insumo.getCantidadInsumo() != null && insumo.getPrecioCostoUnitario() != null)
                .mapToDouble(insumo -> insumo.getCantidadInsumo() * insumo.getPrecioCostoUnitario().doubleValue())
                .sum();
    }

    @Transactional
    public Inventario registrarInsumo1(Inventario inventario) {
        if (inventario.getNombreInsumo() != null) {
            inventario.setNombreInsumo(inventario.getNombreInsumo().toUpperCase());
        }
        if (inventario.getProveedor() != null) {
            inventario.setProveedor(inventario.getProveedor().toUpperCase());
        }
        return inventarioRepository.save(inventario);
    }

    @Transactional
    public Inventario actualizarInsumo(Long id, Inventario datosNuevos) {

        Inventario insumoExistente = inventarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("No se encontró el insumo con ID: " + id));

        if (datosNuevos.getNombreInsumo() != null) {
            insumoExistente.setNombreInsumo(datosNuevos.getNombreInsumo().toUpperCase());
        }
        if (datosNuevos.getTipoInsumo() != null) {
            insumoExistente.setTipoInsumo(datosNuevos.getTipoInsumo());
        }
        if (datosNuevos.getCantidadInsumo() != null) {
            insumoExistente.setCantidadInsumo(datosNuevos.getCantidadInsumo());
        }
        if (datosNuevos.getUnidadMedida() != null) {
            insumoExistente.setUnidadMedida(datosNuevos.getUnidadMedida());
        }
        if (datosNuevos.getEstadoInsumo() != null) {
            insumoExistente.setEstadoInsumo(datosNuevos.getEstadoInsumo());
        }
        if (datosNuevos.getPrecioCostoUnitario() != null) {
            insumoExistente.setPrecioCostoUnitario(datosNuevos.getPrecioCostoUnitario());
        }
        if (datosNuevos.getProveedor() != null) {
            insumoExistente.setProveedor(datosNuevos.getProveedor().toUpperCase());
        }

        return inventarioRepository.save(insumoExistente);
    }
}