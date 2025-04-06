package com.example.demo.model.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import java.time.LocalDateTime;

@Document(collection = "imagenes")
@Data
public class ImagenDocument {
    
    @Id
    private String id;
    
    private String nombreArchivo;
    private String contentType;
    private String filePath;
    private Long fileSize;
    private String tipoImagen;
    
    private LocalDateTime fechaSubida;
    
    // Referencia opcional al libro si está relacionado
    private String libroId;
}