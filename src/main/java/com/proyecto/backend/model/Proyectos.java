package com.proyecto.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "PROYECTOS")
@AttributeOverride(name = "id", column = @Column(name = "PROYECTO_ID"))
public class Proyectos extends Persona implements Gestionable {

    @Column(name = "TIPO")
    private String tipo;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    @Column(name = "FECHA_INICIO", nullable = false)
    private String fechaInicio;

    @Column(name = "FECHA_FIN")
    private String fechaFin;

    @Column(name = "ESTADO", nullable = false)
    private String estado;

    protected Proyectos() {
    }

    public Proyectos(String id, String nombre, String correo, String tipo, String descripcion, String fechaInicio,
            String fechaFin, String estado) {
        super(id, nombre, correo);
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
    }

    @Override
    public String rol() {
        return "Tipo de proyecto: " + this.tipo;
    }

    @Override
    public void cambiarEstado(String nuevoEstado) {
        this.estado = nuevoEstado;
    }

    @Override
    public String traerDetalles() {
        return "ID: " + id + "\n"
                + "Nombre:  " + nombre + "\n"
                + "Tipo:  " + tipo + "\n"
                + "Descripcion: " + descripcion + "\n"
                + "Fecha inicio: " + fechaInicio + "\n"
                + "Fecha fin: " + fechaFin + "\n"
                + "Estado: " + estado;
    }

    @Override
    public void mostrarinfo() {
        System.out.println(traerDetalles());
    }

    public String getProyectoId(){
        return id;
    }
    public String getNombre_proyecto(){
        return nombre;
    }
    public String getTipo_proyecto(){
        return tipo;
    }
    public String getDescripcion_proyecto(){
        return descripcion;
    }
    public String getFechaInicio_proyecto(){
        return fechaInicio;
    }
    public String getFechaFin_proyecto(){
        return fechaFin;
    }
    public String getEstado_proyecto(){
        return estado;
    }

    public void setNombre_proyecto(String nombre){
        this.nombre = nombre;
    }
    public void setTipo_proyecto(String tipo){
        this.tipo = tipo;
    }
    public void setDescripcion_proyecto(String descripcion){
        this.descripcion = descripcion;
    }
    public void setFechaInicio_proyecto(String fechaInicio){
        this.fechaInicio = fechaInicio;
    }
    public void setFechaFin_proyecto(String fechaFin){
        this.fechaFin = fechaFin;
    }
    public void setEstado_proyecto(String estado){
        this.estado = estado;
    }

}
