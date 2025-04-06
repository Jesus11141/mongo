package com.example.demo.service.mongo;

import com.example.demo.model.mongo.UsuarioDocument;
import com.example.demo.repository.mongo.UsuarioMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioMongoService {

    @Autowired
    private UsuarioMongoRepository usuarioMongoRepository;

    // Crear usuario
    public UsuarioDocument crearUsuario(UsuarioDocument usuario) {
        // Verificar si el usuario ya existe
        if (usuarioMongoRepository.existsByUsername(usuario.getUsername())) {
            throw new RuntimeException("El nombre de usuario ya existe");
        }

        if (usuarioMongoRepository.existsByEmail(usuario.getEmail())) {
            throw new RuntimeException("El correo electrónico ya está registrado");
        }

        usuario.setFechaRegistro(LocalDateTime.now());
        usuario.setActive(true);
        return usuarioMongoRepository.save(usuario);
    }

    // Iniciar sesión
    public UsuarioDocument login(String username, String password) {
        return usuarioMongoRepository.findByUsernameAndPassword(username, password)
            .map(usuario -> {
                usuario.setUltimoAcceso(LocalDateTime.now());
                return usuarioMongoRepository.save(usuario);
            })
            .orElseThrow(() -> new RuntimeException("Credenciales inválidas"));
    }

    // Obtener usuario por ID
    public Optional<UsuarioDocument> obtenerUsuarioPorId(String id) {
        return usuarioMongoRepository.findById(id);
    }

    // Obtener usuario por username
    public Optional<UsuarioDocument> obtenerUsuarioPorUsername(String username) {
        return usuarioMongoRepository.findByUsername(username);
    }

    // Listar todos los usuarios
    public List<UsuarioDocument> listarUsuarios() {
        return usuarioMongoRepository.findAll();
    }

    // Listar usuarios por rol
    public List<UsuarioDocument> listarUsuariosPorRol(UsuarioDocument.Role role) {
        return usuarioMongoRepository.findByRole(role);
    }

    // Actualizar usuario
    public UsuarioDocument actualizarUsuario(String id, UsuarioDocument usuarioActualizado) {
        return usuarioMongoRepository.findById(id)
            .map(usuario -> {
                usuario.setNombre(usuarioActualizado.getNombre());
                usuario.setApellidos(usuarioActualizado.getApellidos());
                usuario.setEmail(usuarioActualizado.getEmail());
                usuario.setRole(usuarioActualizado.getRole());
                return usuarioMongoRepository.save(usuario);
            })
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    // Eliminar usuario
    public void eliminarUsuario(String id) {
        if (!usuarioMongoRepository.existsById(id)) {
            throw new RuntimeException("Usuario no encontrado");
        }
        usuarioMongoRepository.deleteById(id);
    }

    // Cambiar contraseña
    public void cambiarContrasena(String id, String contrasenaActual, String contrasenaNueva) {
        UsuarioDocument usuario = usuarioMongoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!usuario.getPassword().equals(contrasenaActual)) {
            throw new RuntimeException("Contraseña actual incorrecta");
        }

        usuario.setPassword(contrasenaNueva);
        usuarioMongoRepository.save(usuario);
    }
}