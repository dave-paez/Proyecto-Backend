package models;

//primera clase padre - creado por dave paez.
//modificado por juan duarte..

public abstract class Persona {
  protected String id;
  protected String nombre;
  protected String correo;

  public Persona() {
  }

  public Persona(String id, String nombre, String correo) {
    this.id = id;
    this.nombre = nombre;
    this.correo = correo;
  }

  public abstract String rol();

  public abstract void mostrarinfo();

  public String getId() {
    return id;
  }

  public String getNombre() {
    return nombre;
  }

  public String getCorreo() {
    return correo;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public void setCorreo(String correo) {
    this.correo = correo;
  }
  
}
