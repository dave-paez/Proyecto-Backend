package com.proyecto.backend.model;

public interface Funcionescrud {

    void guardar(RecursoBase recurso);

    RecursoBase buscar(String id);

    void eliminar(String id);
}
