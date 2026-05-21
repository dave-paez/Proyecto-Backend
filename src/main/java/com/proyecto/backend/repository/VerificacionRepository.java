package com.proyecto.backend.repository;

import com.proyecto.backend.model.Funcionescrud;
import com.proyecto.backend.model.Verificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "usuarios", collectionResourceRel = "usuarios")
public interface VerificacionRepository extends JpaRepository<Verificacion, String>, Funcionescrud<Verificacion> {

    @Override
    default void guardar(Verificacion entidad) {
        save(entidad);
    }

    @Override
    default Verificacion buscar(String id) {
        return findById(id).orElse(null);
    }

    @Override
    default void eliminar(String id) {
        deleteById(id);
    }
}
