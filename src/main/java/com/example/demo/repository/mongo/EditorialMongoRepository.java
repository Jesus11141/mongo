package com.example.demo.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.mongo.EditorialDocument;

import java.util.List;
import java.util.Optional;

public interface EditorialMongoRepository extends MongoRepository<EditorialDocument, String> {
    List<EditorialDocument> findByNombreContainingIgnoreCase(String nombre);

    boolean existsByNombre(String nombre);

    List<EditorialDocument> findByPais(String pais);

    Optional<EditorialDocument> findByEmail(String email);

    long countByPais(String pais);
}