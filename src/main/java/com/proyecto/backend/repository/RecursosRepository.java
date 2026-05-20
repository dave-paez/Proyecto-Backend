package com.proyecto.backend.repository;

import com.proyecto.backend.model.Funcionescrud;
import com.proyecto.backend.model.Recursos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecursosRepository extends JpaRepository<Recursos, String>, Funcionescrud<Recursos> {

    @Override
    default void guardar(Recursos entidad) {
        save(entidad);
    }

    @Override
    default Recursos buscar(String id) {
        return findById(id).orElse(null);
    }

    @Override
    default void eliminar(String id) {
        deleteById(id);
    }
}
