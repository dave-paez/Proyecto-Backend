package models;

//Segunda clase abstracta - creado por juan duarte
public abstract class RecursoBase implements Gestionable {
  protected String id;
  protected String nombre;
  protected String categoria;
  protected String estado;
  protected String ubicacion;

  public RecursoBase(String id, String nombre, String categoria, String estado, String ubicacion) {
    this.id = id;
    this.nombre = nombre;
    this.categoria = categoria;
    this.estado = estado;
    this.ubicacion = ubicacion;
  }

  @Override
  public void cambiarEstado(String nuevoEstado) {
    this.estado = nuevoEstado;
  }

  @Override
  public String traerDetalles() {
    return "ID: " + id + ", Nombre: " + nombre + ", Categoria: " + categoria + ", Estado: " + estado + ", Ubicacion: "
        + ubicacion;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getCategoria() {
    return categoria;
  }

  public void setCategoria(String categoria) {
    this.categoria = categoria;
  }

  public String getEstado() {
    return estado;
  }

  public void setEstado(String estado) {
    this.estado = estado;
  }

  public String getUbicacion() {
    return ubicacion;
  }

  public void setUbicacion(String ubicacion) {
    this.ubicacion = ubicacion;
  }

}
