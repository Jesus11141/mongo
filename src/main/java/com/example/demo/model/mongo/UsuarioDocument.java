package com.example.demo.model.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import java.time.LocalDateTime;

@Document(collection = "usuarios")
@Data
public class UsuarioDocument {
    
    @Id
    private String id;
    
    private String username;
    private String password;
    private String nombre;
    private String apellidos;
    private String email;
    
    // Enum para roles
    private Role role;
    private Boolean active;
    
    private LocalDateTime fechaRegistro;
    private LocalDateTime ultimoAcceso;

    // Enum para definir roles
    public enum Role {
        ADMIN,
        USER
    }
}