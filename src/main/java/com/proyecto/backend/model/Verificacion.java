package models;

public class Verificacion extends Persona {

    private String contraseña_personaladiministrativo;
    private String telefono_personaladministrativo;

    public Verificacion(String id, String contraseña, String telefono, String correo, String nombre) {
        super(id, nombre, correo);
        this.contraseña_personaladiministrativo = contraseña;
        this.telefono_personaladministrativo = telefono;
    }

    @Override
    public String rol() {
        return "Personal Administrativo";
    }

    @Override
    public void mostrarinfo() {
        System.out.println("ID: " + id);
        System.out.println("Nombre: " + nombre);
        System.out.println("Correo: " + correo);
        System.out.println("Telefono: " + telefono_personaladministrativo);
    }

    public String getPersonaladiministrativoId() {
        return id;
    }

    public String getContraseña_personaladiministrativo() {
        return contraseña_personaladiministrativo;
    }

    public String getTelefono_personaladministrativo() {
        return telefono_personaladministrativo;
    }

    public void setContraseña_personaladiministrativo(String contraseña) {
        this.contraseña_personaladiministrativo = contraseña;
    }

    public void setTelefono_personaladministrativo(String telefono) {
        this.telefono_personaladministrativo = telefono;
    }

}