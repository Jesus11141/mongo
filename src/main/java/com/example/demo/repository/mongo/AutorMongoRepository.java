package com.example.demo.repository.mongo;

import com.example.demo.model.mongo.AutorDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface AutorMongoRepository extends MongoRepository<AutorDocument, String> {
    List<AutorDocument> findByNombreContainingIgnoreCase(String nombre);
    List<AutorDocument> findByApellidosContainingIgnoreCase(String apellidos);
}