package com.proyecto.backend.repository;

import com.proyecto.backend.model.Patrocinios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatrociniosRepository extends JpaRepository<Patrocinios, String> {
}
