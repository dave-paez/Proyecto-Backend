package models;

public class Proyectos extends Persona implements Gestionable {

    private String tipo;
    private String descripcion;
    private String fechaInicio;
    private String fechaFin;
    private String estado;

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