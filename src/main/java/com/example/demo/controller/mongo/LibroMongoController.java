package com.example.demo.controller.mongo;

import com.example.demo.model.mongo.LibroDocument;
import com.example.demo.repository.mongo.LibroMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/mongo/libros")
public class LibroMongoController {

    @Autowired
    private LibroMongoRepository libroMongoRepository;

    // Crear un nuevo libro
    @PostMapping
    public ResponseEntity<LibroDocument> crearLibro(@RequestBody LibroDocument libro) {
        LibroDocument nuevoLibro = libroMongoRepository.save(libro);
        return ResponseEntity.ok(nuevoLibro);
    }

    // Obtener todos los libros
    @GetMapping
    public ResponseEntity<List<LibroDocument>> obtenerTodosLosLibros() {
        List<LibroDocument> libros = libroMongoRepository.findAll();
        return ResponseEntity.ok(libros);
    }

    // Obtener libro por ID
    @GetMapping("/{id}")
    public ResponseEntity<LibroDocument> obtenerLibroPorId(@PathVariable String id) {
        Optional<LibroDocument> libro = libroMongoRepository.findById(id);
        return libro.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }

    // Actualizar libro
    @PutMapping("/{id}")
    public ResponseEntity<LibroDocument> actualizarLibro(
            @PathVariable String id, 
            @RequestBody LibroDocument libroActualizado) {
        return libroMongoRepository.findById(id)
            .map(libroExistente -> {
                libroActualizado.setId(id);
                return ResponseEntity.ok(libroMongoRepository.save(libroActualizado));
            })
            .orElse(ResponseEntity.notFound().build());
    }

    // Eliminar libro
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarLibro(@PathVariable String id) {
        if (libroMongoRepository.existsById(id)) {
            libroMongoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}