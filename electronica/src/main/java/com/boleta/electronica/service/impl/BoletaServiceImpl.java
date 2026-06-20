package com.boleta.electronica.service.impl;

import com.boleta.electronica.model.Boleta;
import com.boleta.electronica.repository.BoletaRepository;
import com.boleta.electronica.service.BoletaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.time.LocalDateTime;

@Service
public class BoletaServiceImpl implements BoletaService {

    private final BoletaRepository boletaRepository;

    public BoletaServiceImpl(BoletaRepository boletaRepository) {
        this.boletaRepository = boletaRepository;
    }

    @Override
    @Transactional
    public Boleta guardarBoleta(MultipartFile file) throws IOException {
        
        // 1. Aquí podrías meter lógica extra en el futuro (ej. Leer el PDF con PDFBox)
        
        // 2. Mapeamos los datos al objeto de negocio
        Boleta boleta = new Boleta();
        boleta.setNombreArchivo(file.getOriginalFilename());
        boleta.setTipoContenido(file.getContentType());
        boleta.setFechaCarga(LocalDateTime.now());
        boleta.setArchivoPdf(file.getBytes()); // Convierte el PDF a binario

        // 3. Guardamos usando el repositorio de Spring Data JDBC
        return boletaRepository.save(boleta);
    }

    @Override
    public Boleta obtenerBoletaPorId(Long id) {
        return boletaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("La boleta con ID " + id + " no existe."));
    }
}