package com.example.demo.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.postgres.Imagen;

import java.util.List;

@Repository
public interface ImagenRepository extends JpaRepository<Imagen, Long> {
    List<Imagen> findByLibroId(Long libroId);
}