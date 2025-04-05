package com.example.demo.util;

import com.example.demo.model.Autor;
import com.example.demo.model.Editorial;
import com.example.demo.model.Libro;
import com.example.demo.model.mongo.AutorDocument;
import com.example.demo.model.mongo.EditorialDocument;
import com.example.demo.model.mongo.LibroDocument;

import java.util.stream.Collectors;
import java.util.ArrayList;

public class DocumentConverter {
    
    // Convertir de JPA a MongoDB
    public static AutorDocument toDocument(Autor autor) {
        AutorDocument doc = new AutorDocument();
        if (autor.getId() != null) {
            doc.setId(autor.getId().toString());
        }
        doc.setNombre(autor.getNombre());
        doc.setApellidos(autor.getApellidos());
        doc.setBiografia(autor.getBiografia());
        doc.setNacionalidad(autor.getNacionalidad());
        doc.setFechaNacimiento(autor.getFechaNacimiento());
        
        // Si hay libros, convertir IDs
        if (autor.getLibros() != null && !autor.getLibros().isEmpty()) {
            doc.setLibrosIds(autor.getLibros().stream()
                .map(libro -> libro.getId().toString())
                .collect(Collectors.toList()));
        }
        
        return doc;
    }
    
    public static EditorialDocument toDocument(Editorial editorial) {
        EditorialDocument doc = new EditorialDocument();
        if (editorial.getId() != null) {
            doc.setId(editorial.getId().toString());
        }
        doc.setNombre(editorial.getNombre());
        doc.setDireccion(editorial.getDireccion());
        doc.setCiudad(editorial.getCiudad());
        doc.setPais(editorial.getPais());
        doc.setTelefono(editorial.getTelefono());
        doc.setEmail(editorial.getEmail());
        doc.setSitioWeb(editorial.getSitioWeb());
        
        // Si hay libros, convertir IDs
        if (editorial.getLibros() != null && !editorial.getLibros().isEmpty()) {
            doc.setLibrosIds(editorial.getLibros().stream()
                .map(libro -> libro.getId().toString())
                .collect(Collectors.toList()));
        }
        
        return doc;
    }
    
    public static LibroDocument toDocument(Libro libro) {
        LibroDocument doc = new LibroDocument();
        if (libro.getId() != null) {
            doc.setId(libro.getId().toString());
        }
        doc.setTitulo(libro.getTitulo());
        doc.setIsbn(libro.getIsbn());
        doc.setAnioPublicacion(libro.getAnioPublicacion());
        doc.setGenero(libro.getGenero());
        doc.setSinopsis(libro.getSinopsis());
        doc.setNumeroPaginas(libro.getNumeroPaginas());
        doc.setFilePath(libro.getFilePath());
        doc.setFileType(libro.getFileType());
        doc.setFileSize(libro.getFileSize());
        doc.setFechaCreacion(libro.getFechaCreacion());
        doc.setFechaActualizacion(libro.getFechaActualizacion());
        
        // Convertir editorial
        if (libro.getEditorial() != null) {
            doc.setEditorialId(libro.getEditorial().getId().toString());
            doc.setEditorial(toDocument(libro.getEditorial()));
        }
        
        // Convertir autores
        if (libro.getAutores() != null && !libro.getAutores().isEmpty()) {
            doc.setAutoresIds(libro.getAutores().stream()
                .map(autor -> autor.getId().toString())
                .collect(Collectors.toList()));
            
            doc.setAutores(libro.getAutores().stream()
                .map(DocumentConverter::toDocument)
                .collect(Collectors.toList()));
        }
        
        return doc;
    }
    
    // Convertir de MongoDB a JPA (métodos simplificados)
    public static Autor toEntity(AutorDocument doc) {
        Autor autor = new Autor();
        if (doc.getId() != null) {
            autor.setId(Long.valueOf(doc.getId()));
        }
        autor.setNombre(doc.getNombre());
        autor.setApellidos(doc.getApellidos());
        autor.setBiografia(doc.getBiografia());
        autor.setNacionalidad(doc.getNacionalidad());
        autor.setFechaNacimiento(doc.getFechaNacimiento());
        
        return autor;
    }
    
    public static Editorial toEntity(EditorialDocument doc) {
        Editorial editorial = new Editorial();
        if (doc.getId() != null) {
            editorial.setId(Long.valueOf(doc.getId()));
        }
        editorial.setNombre(doc.getNombre());
        editorial.setDireccion(doc.getDireccion());
        editorial.setCiudad(doc.getCiudad());
        editorial.setPais(doc.getPais());
        editorial.setTelefono(doc.getTelefono());
        editorial.setEmail(doc.getEmail());
        editorial.setSitioWeb(doc.getSitioWeb());
        
        return editorial;
    }
    
    public static Libro toEntity(LibroDocument doc) {
        Libro libro = new Libro();
        if (doc.getId() != null) {
            libro.setId(Long.valueOf(doc.getId()));
        }
        libro.setTitulo(doc.getTitulo());
        libro.setIsbn(doc.getIsbn());
        libro.setAnioPublicacion(doc.getAnioPublicacion());
        libro.setGenero(doc.getGenero());
        libro.setSinopsis(doc.getSinopsis());
        libro.setNumeroPaginas(doc.getNumeroPaginas());
        libro.setFilePath(doc.getFilePath());
        libro.setFileType(doc.getFileType());
        libro.setFileSize(doc.getFileSize());
        libro.setFechaCreacion(doc.getFechaCreacion());
        libro.setFechaActualizacion(doc.getFechaActualizacion());
        
        return libro;
    }
}