package com.proyecto.backend.model;

public interface Funcionescrud<T> {

    void guardar(T entidad);

    T buscar(String id);

    void eliminar(String id);
}
