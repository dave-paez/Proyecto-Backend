package models;

import jakarta.persistence.*;

@Entity
@Table(name = "MANTENIMIENTO_RECURSOS")
@AttributeOverrides({
    @AttributeOverride(name = "id",        column = @Column(name = "MMTO_ID")),
    @AttributeOverride(name = "nombre",    column = @Column(name = "NOMBRE_TECNICO", nullable = false)),
    @AttributeOverride(name = "categoria", column = @Column(name = "DESCRIPCION")),
    @AttributeOverride(name = "estado",    column = @Column(name = "DESCRIPCION", insertable = false, updatable = false)),
    @AttributeOverride(name = "ubicacion", column = @Column(name = "NOMBRE_TECNICO", insertable = false, updatable = false))
})
public class Mantenimientoderecursos extends RecursoBase {

    @Column(name = "FECHA")
    private String fechadeingreso_mantenimientorecursos;

    @Column(name = "RECURSO_ID")
    private Integer recursoId;

    protected Mantenimientoderecursos() {
        super(null, null, null, null, null);
    }

    public Mantenimientoderecursos(String id, String nombre, String categoria, String estado,
            String ubicacion, String fechadeingreso_mantenimientorecursos) {
        super(id, nombre, categoria, estado, ubicacion);
        this.fechadeingreso_mantenimientorecursos = fechadeingreso_mantenimientorecursos;
    }

    @Override
    public String traerDetalles() {
        return "MANTENIMIENTO\n"
                + "ID: " + id + "\n"
                + "Nombre: " + nombre + "\n"
                + "Descripcion: " + categoria + "\n"
                + "Estado: " + estado + "\n"
                + "Categoria: " + ubicacion + "\n"
                + "Fecha ingreso: " + fechadeingreso_mantenimientorecursos;
    }

    public String getManteni_recursoId() {
        return id;
    }

    public String getNombre_mantenimientorecursos() {
        return nombre;
    }

    public String getCategoria_mantenimientorecursos() {
        return categoria;
    }

    public String getEstado_mantenimientorecursos() {
        return estado;
    }

    public String getUbicacion_mantenimientorecursos() {
        return ubicacion;
    }

    public String getFechadeingreso_mantenimientorecursos() {
        return fechadeingreso_mantenimientorecursos;
    }

    public void setNombre_mantenimientorecursos(String nombre) {
        this.nombre = nombre;
    }

    public void setCategoria_mantenimientorecursos(String categoria) {
        this.categoria = categoria;
    }

    public void setEstado_mantenimientorecursos(String estado) {
        this.estado = estado;
    }

    public void setUbicacion_mantenimientorecursos(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public void setFechadeingreso_mantenimientorecursos(String fecha) {
        this.fechadeingreso_mantenimientorecursos = fecha;
    }

}
