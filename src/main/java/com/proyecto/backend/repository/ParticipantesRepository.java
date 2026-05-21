package com.proyecto.backend.repository;

import com.proyecto.backend.model.Funcionescrud;
import com.proyecto.backend.model.Participantes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "participantes", collectionResourceRel = "participantes")
public interface ParticipantesRepository extends JpaRepository<Participantes, String>, Funcionescrud<Participantes> {

    @Override
    default void guardar(Participantes entidad) {
        save(entidad);
    }

    @Override
    default Participantes buscar(String id) {
        return findById(id).orElse(null);
    }

    @Override
    default void eliminar(String id) {
        deleteById(id);
    }
}
