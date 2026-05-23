package com.proyecto.backend.repository;

import com.proyecto.backend.model.FuncionesCrud;
import com.proyecto.backend.model.Mantenimientoderecursos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "mantenimientos", collectionResourceRel = "mantenimientos")
public interface MantenimientoRepository extends JpaRepository<Mantenimientoderecursos, String>, FuncionesCrud<Mantenimientoderecursos> {

    @Override
    default void guardar(Mantenimientoderecursos entidad) {
        save(entidad);
    }

    @Override
    default Mantenimientoderecursos buscar(String id) {
        return findById(id).orElse(null);
    }

    @Override
    default void eliminar(String id) {
        deleteById(id);
    }
}
