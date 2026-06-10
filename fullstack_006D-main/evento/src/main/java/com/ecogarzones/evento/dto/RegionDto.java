package com.ecogarzones.evento.dto;

import java.util.List;
import lombok.Data;

@Data
public class RegionDto {
    private String nombre;
    private String region_iso_3166_2;
    private String capital_regional;
    private List<ProvinciaDto> provincias;
}