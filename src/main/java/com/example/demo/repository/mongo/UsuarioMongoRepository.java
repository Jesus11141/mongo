package com.example.demo.repository.mongo;

import com.example.demo.model.mongo.UsuarioDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioMongoRepository extends MongoRepository<UsuarioDocument, String> {
    // Buscar usuario por username
    Optional<UsuarioDocument> findByUsername(String username);
    
    // Buscar usuario por email
    Optional<UsuarioDocument> findByEmail(String email);
    
    // Buscar usuarios por rol
    List<UsuarioDocument> findByRole(UsuarioDocument.Role role);
    
    // Buscar usuarios activos
    List<UsuarioDocument> findByActiveTrue();
    
    // Buscar usuario por username y password (para login)
    Optional<UsuarioDocument> findByUsernameAndPassword(String username, String password);
    
    // Verificar si existe un usuario con un username
    boolean existsByUsername(String username);
    
    // Verificar si existe un usuario con un email
    boolean existsByEmail(String email);
}