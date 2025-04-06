package com.example.demo.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.mongo.AutorDocument;

import java.util.List;

public interface AutorMongoRepository extends MongoRepository<AutorDocument, String> {
    List<AutorDocument> findByNombreContainingIgnoreCase(String nombre);
    List<AutorDocument> findByApellidosContainingIgnoreCase(String apellidos);
}