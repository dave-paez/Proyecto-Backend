package com.proyecto.backend.repository;

import com.proyecto.backend.model.FuncionesCrud;
import com.proyecto.backend.model.Proyectos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "proyectos", collectionResourceRel = "proyectos")
public interface ProyectosRepository extends JpaRepository<Proyectos, String>, FuncionesCrud<Proyectos> {

    @Override
    default void guardar(Proyectos entidad) {
        save(entidad);
    }

    @Override
    default Proyectos buscar(String id) {
        return findById(id).orElse(null);
    }

    @Override
    default void eliminar(String id) {
        deleteById(id);
    }
}
