package com.ecogarzones.evento.service;

import com.ecogarzones.evento.dto.RegionDto;
import com.ecogarzones.evento.dto.ComunaDto;
import jakarta.annotation.PostConstruct;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class UbicacionService {

    private List<RegionDto> regionesCache = new ArrayList<>();

    @PostConstruct
    public void init() {
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
        return regionesCache.stream()
            .flatMap(region -> region.getProvincias().stream())
            .flatMap(provincia -> provincia.getComunas().stream())
            .filter(comuna -> comuna.getCodigo().equalsIgnoreCase(codigoComuna))
            .map(ComunaDto::getNombre)
            .findFirst()
            .orElse("Comuna No Encontrada");
    }
}