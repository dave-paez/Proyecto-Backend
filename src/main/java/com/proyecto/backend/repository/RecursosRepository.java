package com.proyecto.backend.repository;

import com.proyecto.backend.model.Recursos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecursosRepository extends JpaRepository<Recursos, String> {
}
