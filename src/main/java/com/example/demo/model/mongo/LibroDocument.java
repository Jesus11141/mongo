package com.example.demo.model.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "libros")
@Data
public class LibroDocument {
    
    @Id
    private String id;
    
    private String titulo;
    private String isbn;
    private Integer anioPublicacion;
    private String genero;
    private String sinopsis;
    private Integer numeroPaginas;
    private String filePath;
    private String fileType;
    private Long fileSize;
    
    // En MongoDB podemos anidar documentos directamente
    private EditorialDocument editorial;
    private List<AutorDocument> autores;
    
    // O podemos guardar solo referencias
    private String editorialId;
    private List<String> autoresIds;
    
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;
}