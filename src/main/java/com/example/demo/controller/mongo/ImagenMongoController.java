package com.example.demo.controller.mongo;

import com.example.demo.model.mongo.ImagenDocument;
import com.example.demo.repository.mongo.ImagenMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/mongo/imagenes")
public class ImagenMongoController {

    @Autowired
    private ImagenMongoRepository imagenMongoRepository;

    // Crear una nueva imagen
    @PostMapping
    public ResponseEntity<ImagenDocument> crearImagen(@RequestBody ImagenDocument imagen) {
        ImagenDocument nuevaImagen = imagenMongoRepository.save(imagen);
        return ResponseEntity.ok(nuevaImagen);
    }

    // Obtener todas las imágenes
    @GetMapping
    public ResponseEntity<List<ImagenDocument>> obtenerTodasLasImagenes() {
        List<ImagenDocument> imagenes = imagenMongoRepository.findAll();
        return ResponseEntity.ok(imagenes);
    }

    // Obtener imagen por ID
    @GetMapping("/{id}")
    public ResponseEntity<ImagenDocument> obtenerImagenPorId(@PathVariable String id) {
        Optional<ImagenDocument> imagen = imagenMongoRepository.findById(id);
        return imagen.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }

    // Actualizar imagen
    @PutMapping("/{id}")
    public ResponseEntity<ImagenDocument> actualizarImagen(
            @PathVariable String id, 
            @RequestBody ImagenDocument imagenActualizada) {
        return imagenMongoRepository.findById(id)
            .map(imagenExistente -> {
                imagenActualizada.setId(id);
                return ResponseEntity.ok(imagenMongoRepository.save(imagenActualizada));
            })
            .orElse(ResponseEntity.notFound().build());
    }

    // Eliminar imagen
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarImagen(@PathVariable String id) {
        if (imagenMongoRepository.existsById(id)) {
            imagenMongoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}