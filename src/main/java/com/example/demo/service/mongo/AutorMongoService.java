package com.example.demo.service.mongo;


import com.example.demo.model.mongo.AutorDocument;
import com.example.demo.repository.mongo.AutorMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AutorMongoService {

    @Autowired
    private AutorMongoRepository autorRepository;
    
    public List<AutorDocument> getAllAutores() {
        return autorRepository.findAll();
    }
    
    public Optional<AutorDocument> getAutorById(String id) {
        return autorRepository.findById(id);
    }
    
    public AutorDocument saveAutor(AutorDocument autor) {
        return autorRepository.save(autor);
    }
    
    public void deleteAutor(String id) {
        autorRepository.deleteById(id);
    }
    
    public List<AutorDocument> searchByNombre(String nombre) {
        return autorRepository.findByNombreContainingIgnoreCase(nombre);
    }
}