package com.example.demo.repository.mongo;

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
    private String apellidos;  // Cambié 'apellido' a 'apellidos' para consistencia
    private String biografia;
    private String nacionalidad;
    private String fechaNacimiento;  // Cambié el nombre del campo a camelCase

    private List<String> librosIds; // Lista de IDs de libros relacionados con el autor
}