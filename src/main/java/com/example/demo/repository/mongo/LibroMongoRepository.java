package com.example.demo.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.mongo.LibroDocument;

import java.util.List;

public interface LibroMongoRepository extends MongoRepository<LibroDocument, String> {
    List<LibroDocument> findByTituloContainingIgnoreCase(String titulo);
    List<LibroDocument> findByGeneroContainingIgnoreCase(String genero);
    List<LibroDocument> findByGenero(String genero);
    List<LibroDocument> findByAnioPublicacion(Integer anio);
    long countByGenero(String genero);
}