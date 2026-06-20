package com.boleta.electronica.controller;

import com.boleta.electronica.model.Boleta;
import com.boleta.electronica.service.BoletaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/boletas")
public class BoletaController {

    private final BoletaService boletaService;

    public BoletaController(BoletaService boletaService) {
        this.boletaService = boletaService;
    }

    @GetMapping("/{id}/download")
public ResponseEntity<byte[]> descargarBoleta(@PathVariable Long id) {
    Boleta boleta = boletaService.obtenerBoletaPorId(id);

    return ResponseEntity.ok()
            .header(org.springframework.http.HttpHeaders.CONTENT_DISPOSITION, 
                    "attachment; filename=\"" + boleta.getNombreArchivo() + "\"")
            .contentType(org.springframework.http.MediaType.APPLICATION_PDF)
            .body(boleta.getArchivoPdf());
}

    @PostMapping("/upload")
    public ResponseEntity<String> subirBoleta(@RequestParam("file") MultipartFile file) {
        
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El archivo está vacío.");
        }

        if (!"application/pdf".equals(file.getContentType())) {
            return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body("Solo se permiten archivos PDF.");
        }

        try {
            Boleta boletaGuardada = boletaService.guardarBoleta(file);
            
            return ResponseEntity.ok("Boleta procesada por el servicio con éxito. ID: " + boletaGuardada.getId());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno en el servicio: " + e.getMessage());
        }
    }
}