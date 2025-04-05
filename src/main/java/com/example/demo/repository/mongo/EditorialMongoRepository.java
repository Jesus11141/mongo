package com.example.demo.repository.mongo;

import com.example.demo.model.mongo.EditorialDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface EditorialMongoRepository extends MongoRepository<EditorialDocument, String> {
    List<EditorialDocument> findByNombreContainingIgnoreCase(String nombre);
}