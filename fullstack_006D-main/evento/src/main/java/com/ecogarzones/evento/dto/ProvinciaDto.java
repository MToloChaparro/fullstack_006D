package com.ecogarzones.evento.dto;

import java.util.List;
import lombok.Data;

@Data
public class ProvinciaDto {
    private String nombre;
    private String codigo;
    private String capital_provincial;
    private List<ComunaDto> comunas;
}