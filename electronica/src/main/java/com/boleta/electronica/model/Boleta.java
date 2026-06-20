package com.boleta.electronica.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("boleta")
public class Boleta {
    
    @Id
    private Long id;
    private String nombreArchivo;
    private String tipoContenido;
    private LocalDateTime fechaCarga;
    private byte[] archivoPdf;  // El byte se usa para almacenar el contenido del PDF en la base de datos.
}