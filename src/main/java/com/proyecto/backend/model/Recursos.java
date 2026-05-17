package models;

public class Recursos extends RecursoBase {

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