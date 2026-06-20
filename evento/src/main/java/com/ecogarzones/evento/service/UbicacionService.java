package com.ecogarzones.evento.service;

import com.ecogarzones.evento.dto.RegionDto;
import com.ecogarzones.evento.dto.ProvinciaDto;
import com.ecogarzones.evento.dto.ComunaDto;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class UbicacionService {

    private List<RegionDto> regionesCache = new ArrayList<>();

    @PostConstruct
    public void init() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/chiles.json");
        
        if (inputStream == null) {
            System.out.println("❌ ERROR: No se encontró el archivo chiles.json");
            return;
        }

        this.regionesCache = mapper.readValue(inputStream, new TypeReference<List<RegionDto>>(){});
        System.out.println("✅ ÉXITO: Se cargaron " + regionesCache.size() + " regiones en memoria.");
    }

    public List<RegionDto> obtenerTodasLasRegiones() {
        return this.regionesCache;
    }

    public String obtenerNombreComuna(String codigoComuna) {
        if (codigoComuna == null || regionesCache == null) {
            return "Comuna No Encontrada";
        }
        
        return regionesCache.stream()
            .filter(region -> region.getProvincias() != null)
            .flatMap((RegionDto region) -> region.getProvincias().stream())
            .filter(provincia -> provincia.getComunas() != null)
            .flatMap((ProvinciaDto provincia) -> provincia.getComunas().stream())
            .filter(comuna -> comuna.getCodigo() != null && comuna.getCodigo().equalsIgnoreCase(codigoComuna))
            .map((ComunaDto comuna) -> comuna.getNombre()) // <-- Cambiado de método de referencia a Lambda explícita
            .findFirst()
            .orElse("Comuna No Encontrada");
    }
}