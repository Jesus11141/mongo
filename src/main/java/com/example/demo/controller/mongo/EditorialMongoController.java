package com.example.demo.controller.mongo;

import com.example.demo.model.mongo.EditorialDocument;
import com.example.demo.repository.mongo.EditorialMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/mongo/editoriales")
public class EditorialMongoController {

    @Autowired
    private EditorialMongoRepository editorialMongoRepository;

    // Crear una nueva editorial
    @PostMapping
    public ResponseEntity<EditorialDocument> crearEditorial(@RequestBody EditorialDocument editorial) {
        EditorialDocument nuevaEditorial = editorialMongoRepository.save(editorial);
        return ResponseEntity.ok(nuevaEditorial);
    }

    // Obtener todas las editoriales
    @GetMapping
    public ResponseEntity<List<EditorialDocument>> obtenerTodasLasEditoriales() {
        List<EditorialDocument> editoriales = editorialMongoRepository.findAll();
        return ResponseEntity.ok(editoriales);
    }

    // Obtener editorial por ID
    @GetMapping("/{id}")
    public ResponseEntity<EditorialDocument> obtenerEditorialPorId(@PathVariable String id) {
        Optional<EditorialDocument> editorial = editorialMongoRepository.findById(id);
        return editorial.map(ResponseEntity::ok)
                        .orElse(ResponseEntity.notFound().build());
    }

    // Actualizar editorial
    @PutMapping("/{id}")
    public ResponseEntity<EditorialDocument> actualizarEditorial(
            @PathVariable String id, 
            @RequestBody EditorialDocument editorialActualizada) {
        return editorialMongoRepository.findById(id)
            .map(editorialExistente -> {
                editorialActualizada.setId(id);
                return ResponseEntity.ok(editorialMongoRepository.save(editorialActualizada));
            })
            .orElse(ResponseEntity.notFound().build());
    }

    // Eliminar editorial
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEditorial(@PathVariable String id) {
        if (editorialMongoRepository.existsById(id)) {
            editorialMongoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}