package com.example.demo.controller.mongo;

import com.example.demo.model.mongo.UsuarioDocument;
import com.example.demo.repository.mongo.UsuarioMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/mongo/usuarios")
public class UsuarioMongoController {

    @Autowired
    private UsuarioMongoRepository usuarioMongoRepository;

    // Crear un nuevo usuario
    @PostMapping
    public ResponseEntity<UsuarioDocument> crearUsuario(@RequestBody UsuarioDocument usuario) {
        usuario.setFechaRegistro(LocalDateTime.now());
        usuario.setActive(true);
        UsuarioDocument nuevoUsuario = usuarioMongoRepository.save(usuario);
        return ResponseEntity.ok(nuevoUsuario);
    }

    // Obtener todos los usuarios
    @GetMapping
    public ResponseEntity<List<UsuarioDocument>> obtenerTodosLosUsuarios() {
        List<UsuarioDocument> usuarios = usuarioMongoRepository.findAll();
        return ResponseEntity.ok(usuarios);
    }

    // Obtener usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDocument> obtenerUsuarioPorId(@PathVariable String id) {
        Optional<UsuarioDocument> usuario = usuarioMongoRepository.findById(id);
        return usuario.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }

    // Actualizar usuario
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDocument> actualizarUsuario(
            @PathVariable String id, 
            @RequestBody UsuarioDocument usuarioActualizado) {
        return usuarioMongoRepository.findById(id)
            .map(usuarioExistente -> {
                usuarioActualizado.setId(id);
                usuarioActualizado.setFechaRegistro(usuarioExistente.getFechaRegistro());
                usuarioActualizado.setUltimoAcceso(LocalDateTime.now());
                return ResponseEntity.ok(usuarioMongoRepository.save(usuarioActualizado));
            })
            .orElse(ResponseEntity.notFound().build());
    }

    // Eliminar usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable String id) {
        if (usuarioMongoRepository.existsById(id)) {
            usuarioMongoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Login
    @PostMapping("/login")
    public ResponseEntity<UsuarioDocument> login(@RequestBody LoginRequest loginRequest) {
        Optional<UsuarioDocument> usuario = usuarioMongoRepository
            .findByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());
        
        return usuario.map(u -> {
            u.setUltimoAcceso(LocalDateTime.now());
            return ResponseEntity.ok(usuarioMongoRepository.save(u));
        }).orElse(ResponseEntity.status(401).build());
    }

    // Clase de solicitud de login
    public static class LoginRequest {
        private String username;
        private String password;

        // Getters y setters
        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }
}