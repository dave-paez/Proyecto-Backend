package models;

public interface funcionescrud {

    void guardar(RecursoBase recurso);
 
    RecursoBase buscar(String id);
 
    void eliminar(String id);
}
