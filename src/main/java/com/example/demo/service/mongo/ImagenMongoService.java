package com.example.demo.service.mongo;

import com.example.demo.model.mongo.ImagenDocument;
import com.example.demo.repository.mongo.ImagenMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ImagenMongoService {

    @Autowired
    private ImagenMongoRepository imagenMongoRepository;

    // Directorio de almacenamiento de archivos
    private final String uploadDir = "./uploads/";

    // Guardar imagen
    public ImagenDocument guardarImagen(MultipartFile file, String tipoImagen, String libroId) throws IOException {
        // Crear directorio si no existe
        Path uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize();
        Files.createDirectories(uploadPath);

        // Generar nombre de archivo único
        String nombreArchivo = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        Path targetLocation = uploadPath.resolve(nombreArchivo);

        // Copiar archivo
        Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

        // Crear documento de imagen
        ImagenDocument imagen = new ImagenDocument();
        imagen.setNombreArchivo(nombreArchivo);
        imagen.setContentType(file.getContentType());
        imagen.setFilePath(targetLocation.toString());
        imagen.setFileSize(file.getSize());
        imagen.setTipoImagen(tipoImagen);
        imagen.setFechaSubida(LocalDateTime.now());
        imagen.setLibroId(libroId);

        return imagenMongoRepository.save(imagen);
    }

    // Obtener imagen por ID
    public Optional<ImagenDocument> obtenerImagenPorId(String id) {
        return imagenMongoRepository.findById(id);
    }

    // Listar todas las imágenes
    public List<ImagenDocument> listarImagenes() {
        return imagenMongoRepository.findAll();
    }

    // Listar imágenes por tipo
    public List<ImagenDocument> listarImagenesPorTipo(String tipoImagen) {
        return imagenMongoRepository.findByTipoImagen(tipoImagen);
    }

    // Listar imágenes por libro
    public List<ImagenDocument> listarImagenesPorLibro(String libroId) {
        return imagenMongoRepository.findByLibroId(libroId);
    }

    // Eliminar imagen
    public void eliminarImagen(String id) throws IOException {
        Optional<ImagenDocument> imagen = imagenMongoRepository.findById(id);
        
        if (imagen.isPresent()) {
            // Eliminar archivo físico
            Path filePath = Paths.get(imagen.get().getFilePath());
            Files.deleteIfExists(filePath);

            // Eliminar registro en base de datos
            imagenMongoRepository.deleteById(id);
        }
    }

    // Actualizar imagen
    public ImagenDocument actualizarImagen(String id, ImagenDocument imagenActualizada) {
        return imagenMongoRepository.findById(id)
            .map(imagen -> {
                imagen.setTipoImagen(imagenActualizada.getTipoImagen());
                imagen.setLibroId(imagenActualizada.getLibroId());
                return imagenMongoRepository.save(imagen);
            })
            .orElseThrow(() -> new RuntimeException("Imagen no encontrada"));
    }
}