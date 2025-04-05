package com.example.demo.repository.mongo;

import com.example.demo.model.mongo.LibroDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface LibroMongoRepository extends MongoRepository<LibroDocument, String> {
    List<LibroDocument> findByTituloContainingIgnoreCase(String titulo);
    List<LibroDocument> findByGeneroContainingIgnoreCase(String genero);
}