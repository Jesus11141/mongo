package com.example.demo.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.postgres.Editorial;

import java.util.List;

@Repository
public interface EditorialRepository extends JpaRepository<Editorial, Long> {
    List<Editorial> findByNombreContainingIgnoreCase(String nombre);
}