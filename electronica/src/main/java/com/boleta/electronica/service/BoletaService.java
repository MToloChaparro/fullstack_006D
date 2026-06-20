package com.boleta.electronica.service;

import com.boleta.electronica.model.Boleta;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

public interface BoletaService {
    Boleta guardarBoleta(MultipartFile file) throws IOException;
    Boleta obtenerBoletaPorId(Long id);
}