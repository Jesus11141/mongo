package com.example.demo.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.postgres.Autor;

import java.util.List;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {
    List<Autor> findByNombreContainingIgnoreCaseOrApellidosContainingIgnoreCase(String nombre, String apellidos);
}