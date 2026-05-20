package com.proyecto.backend.repository;

import com.proyecto.backend.model.Mantenimientoderecursos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MantenimientoRepository extends JpaRepository<Mantenimientoderecursos, String> {
}
