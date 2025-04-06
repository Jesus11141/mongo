package com.example.demo.repository.mongo;

import com.example.demo.model.mongo.ImagenDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImagenMongoRepository extends MongoRepository<ImagenDocument, String> {
    // Buscar imágenes por tipo
    List<ImagenDocument> findByTipoImagen(String tipoImagen);
    
    // Buscar imágenes por libro
    List<ImagenDocument> findByLibroId(String libroId);
    
    // Buscar imagen por nombre de archivo
    Optional<ImagenDocument> findByNombreArchivo(String nombreArchivo);
    
    // Contar imágenes por tipo
    long countByTipoImagen(String tipoImagen);
}