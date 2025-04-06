package com.example.demo.service.mongo;

import com.example.demo.model.mongo.EditorialDocument;
import com.example.demo.repository.mongo.EditorialMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EditorialMongoService {

    @Autowired
    private EditorialMongoRepository editorialMongoRepository;

    // Crear editorial
    public EditorialDocument crearEditorial(EditorialDocument editorial) {
        // Verificar si ya existe una editorial con el mismo nombre
        if (editorialMongoRepository.existsByNombre(editorial.getNombre())) {
            throw new RuntimeException("Ya existe una editorial con este nombre");
        }
        return editorialMongoRepository.save(editorial);
    }

    // Obtener editorial por ID
    public Optional<EditorialDocument> obtenerEditorialPorId(String id) {
        return editorialMongoRepository.findById(id);
    }

    // Listar todas las editoriales
    public List<EditorialDocument> listarEditoriales() {
        return editorialMongoRepository.findAll();
    }

    // Buscar editoriales por nombre
    public List<EditorialDocument> buscarEditorialesPorNombre(String nombre) {
        return editorialMongoRepository.findByNombreContainingIgnoreCase(nombre);
    }

    // Buscar editoriales por país
    public List<EditorialDocument> buscarEditorialesPorPais(String pais) {
        return editorialMongoRepository.findByPais(pais);
    }

    // Actualizar editorial
    public EditorialDocument actualizarEditorial(String id, EditorialDocument editorialActualizada) {
        return editorialMongoRepository.findById(id)
            .map(editorial -> {
                editorial.setNombre(editorialActualizada.getNombre());
                editorial.setDireccion(editorialActualizada.getDireccion());
                editorial.setCiudad(editorialActualizada.getCiudad());
                editorial.setPais(editorialActualizada.getPais());
                editorial.setTelefono(editorialActualizada.getTelefono());
                editorial.setEmail(editorialActualizada.getEmail());
                editorial.setSitioWeb(editorialActualizada.getSitioWeb());
                
                return editorialMongoRepository.save(editorial);
            })
            .orElseThrow(() -> new RuntimeException("Editorial no encontrada"));
    }

    // Eliminar editorial
    public void eliminarEditorial(String id) {
        if (!editorialMongoRepository.existsById(id)) {
            throw new RuntimeException("Editorial no encontrada");
        }
        editorialMongoRepository.deleteById(id);
    }

    // Obtener editorial por email
    public Optional<EditorialDocument> obtenerEditorialPorEmail(String email) {
        return editorialMongoRepository.findByEmail(email);
    }

    // Contar editoriales por país
    public long contarEditorialesPorPais(String pais) {
        return editorialMongoRepository.countByPais(pais);
    }
}