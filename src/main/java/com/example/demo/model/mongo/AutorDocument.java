package com.example.demo.model.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import java.util.List;

@Document(collection = "autores")
@Data
public class AutorDocument {
    
    @Id
    private String id;
    
    private String nombre;
    private String apellidos;
    private String biografia;
    private String nacionalidad;
    private String fechaNacimiento;
    
    private List<String> librosIds;
}