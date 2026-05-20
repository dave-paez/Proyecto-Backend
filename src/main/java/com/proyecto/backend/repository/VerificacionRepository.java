package com.proyecto.backend.repository;

import com.proyecto.backend.model.Verificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificacionRepository extends JpaRepository<Verificacion, String> {
}
