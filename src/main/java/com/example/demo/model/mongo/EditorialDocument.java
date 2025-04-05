package com.example.demo.model.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import java.util.List;

@Document(collection = "editoriales")
@Data
public class EditorialDocument {
    
    @Id
    private String id;
    
    private String nombre;
    private String direccion;
    private String ciudad;
    private String pais;
    private String telefono;
    private String email;
    private String sitioWeb;
    
    // Referencias a libros
    private List<String> librosIds;
}