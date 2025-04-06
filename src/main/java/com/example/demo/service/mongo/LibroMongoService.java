package com.example.demo.service.mongo;

import com.example.demo.model.mongo.LibroDocument;
import com.example.demo.repository.mongo.LibroMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class LibroMongoService {

    @Autowired
    private LibroMongoRepository libroMongoRepository;

    // Crear libro
    public LibroDocument crearLibro(LibroDocument libro) {
        libro.setFechaCreacion(LocalDateTime.now());
        libro.setFechaActualizacion(LocalDateTime.now());
        return libroMongoRepository.save(libro);
    }

    // Obtener libro por ID
    public Optional<LibroDocument> obtenerLibroPorId(String id) {
        return libroMongoRepository.findById(id);
    }

    // Listar todos los libros
    public List<LibroDocument> listarLibros() {
        return libroMongoRepository.findAll();
    }

    // Buscar libros por título
    public List<LibroDocument> buscarLibrosPorTitulo(String titulo) {
        return libroMongoRepository.findByTituloContainingIgnoreCase(titulo);
    }

    // Buscar libros por género
    public List<LibroDocument> buscarLibrosPorGenero(String genero) {
        return libroMongoRepository.findByGenero(genero);
    }

    // Buscar libros por año de publicación
    public List<LibroDocument> buscarLibrosPorAnio(Integer anio) {
        return libroMongoRepository.findByAnioPublicacion(anio);
    }

    // Actualizar libro
    public LibroDocument actualizarLibro(String id, LibroDocument libroActualizado) {
        return libroMongoRepository.findById(id)
            .map(libro -> {
                libro.setTitulo(libroActualizado.getTitulo());
                libro.setIsbn(libroActualizado.getIsbn());
                libro.setGenero(libroActualizado.getGenero());
                libro.setSinopsis(libroActualizado.getSinopsis());
                libro.setNumeroPaginas(libroActualizado.getNumeroPaginas());
                libro.setFechaActualizacion(LocalDateTime.now());
                
                // Actualizar referencias
                libro.setEditorial(libroActualizado.getEditorial());
                libro.setAutores(libroActualizado.getAutores());
                
                return libroMongoRepository.save(libro);
            })
            .orElseThrow(() -> new RuntimeException("Libro no encontrado"));
    }

    // Eliminar libro
    public void eliminarLibro(String id) {
        if (!libroMongoRepository.existsById(id)) {
            throw new RuntimeException("Libro no encontrado");
        }
        libroMongoRepository.deleteById(id);
    }

    // Contar libros por género
    public long contarLibrosPorGenero(String genero) {
        return libroMongoRepository.countByGenero(genero);
    }
}