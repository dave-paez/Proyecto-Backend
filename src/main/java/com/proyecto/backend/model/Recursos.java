package com.proyecto.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "RECURSOS")
@AttributeOverride(name = "id", column = @Column(name = "RECURSO_ID"))
public class Recursos extends RecursoBase {

    @Column(name = "FECHA_INGRESO")
    private String fechaIngreso;

    @Column(name = "PROYECTO_ID")
    private String proyectoId;

    protected Recursos() {
        super(null, null, null, null, null);
    }

    public Recursos(String id, String nombre, String categoria, String estado, String ubicacion) {
        super(id, nombre, categoria, estado, ubicacion);
    }

    @Override
    public String traerDetalles() {
        return "RECURSO\n"
                + "ID: " + id + "\n"
                + "Nombre: " + nombre + "\n"
                + "Categoria: " + categoria + "\n"
                + "Estado: " + estado + "\n"
                + "Ubicacion: " + ubicacion;
    }

    public String getRecursoId() {
        return id;
    }
    public String getNombre_delrecurso(){
        return nombre;
    }
    public String getCategoria_delrecurso(){
        return categoria;
    }
    public String getEstado_delrecurso(){
        return estado;
    }
    public String getUbicacion_delrecurso(){
        return ubicacion;
    }

    public void setNombre_delrecurso(String nombre){
        this.nombre = nombre;
    }
    public void setCategoria_delrecurso(String categoria){
        this.categoria = categoria;
    }
    public void setEstado_delrecurso(String estado){
        this.estado = estado;
    }
    public void setUbicacion_delrecurso(String ubicacion){
        this.ubicacion = ubicacion;
    }
}
