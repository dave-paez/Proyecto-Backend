package com.proyecto.backend.repository;

import com.proyecto.backend.model.Funcionescrud;
import com.proyecto.backend.model.Patrocinios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatrociniosRepository extends JpaRepository<Patrocinios, String>, Funcionescrud<Patrocinios> {

    @Override
    default void guardar(Patrocinios entidad) {
        save(entidad);
    }

    @Override
    default Patrocinios buscar(String id) {
        return findById(id).orElse(null);
    }

    @Override
    default void eliminar(String id) {
        deleteById(id);
    }
}
