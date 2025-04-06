package com.example.demo.controller.mongo;

import com.example.demo.model.mongo.AutorDocument;
import com.example.demo.repository.mongo.AutorMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/mongo/autores")
public class AutorMongoController {

    @Autowired
    private AutorMongoRepository autorMongoRepository;

    // Crear un nuevo autor
    @PostMapping
    public ResponseEntity<AutorDocument> crearAutor(@RequestBody AutorDocument autor) {
        AutorDocument nuevoAutor = autorMongoRepository.save(autor);
        return ResponseEntity.ok(nuevoAutor);
    }

    // Obtener todos los autores
    @GetMapping
    public ResponseEntity<List<AutorDocument>> obtenerTodosLosAutores() {
        List<AutorDocument> autores = autorMongoRepository.findAll();
        return ResponseEntity.ok(autores);
    }

    // Obtener autor por ID
    @GetMapping("/{id}")
    public ResponseEntity<AutorDocument> obtenerAutorPorId(@PathVariable String id) {
        Optional<AutorDocument> autor = autorMongoRepository.findById(id);
        return autor.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }

    // Actualizar autor
    @PutMapping("/{id}")
    public ResponseEntity<AutorDocument> actualizarAutor(
            @PathVariable String id, 
            @RequestBody AutorDocument autorActualizado) {
        return autorMongoRepository.findById(id)
            .map(autorExistente -> {
                autorActualizado.setId(id);
                return ResponseEntity.ok(autorMongoRepository.save(autorActualizado));
            })
            .orElse(ResponseEntity.notFound().build());
    }

    // Eliminar autor
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAutor(@PathVariable String id) {
        if (autorMongoRepository.existsById(id)) {
            autorMongoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}