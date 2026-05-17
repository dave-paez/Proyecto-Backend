package models;

public class Participantes extends Persona {

    private String ubicacion_participante;
    private String rol_participante;

    public Participantes(String id, String nombre, String ubicacion, String correo, String rol) {
        super(id, nombre, correo);
        this.ubicacion_participante = ubicacion;
        this.rol_participante = rol;
    }

    @Override
    public String rol() {
        return "Rol: Participante - " + this.rol_participante;
    }

    @Override
    public void mostrarinfo() {
        System.out.println("PARTICIPANTE");
        System.out.println("ID: " + this.id);
        System.out.println("Nombre: " + this.nombre);
        System.out.println("Correo: " + this.correo);
        System.out.println("Ubicacion: " + this.ubicacion_participante);
        System.out.println("Rol: " + this.rol_participante);
    }

    public String getParticipanteId() {
        return id;
    }

    public String getNombre_participante() {
        return nombre;
    }

    public String getCorreo_participante() {
        return correo;
    }

    public String getUbicacion_participante() {
        return ubicacion_participante;
    }

    public String getRol_participante() {
        return rol_participante;
    }

    public void setNombre_participante(String nombre) {
        this.nombre = nombre;
    }

    public void setCorreo_participante(String correo) {
        this.correo = correo;
    }

    public void setUbicacion_participante(String ubicacion) {
        this.ubicacion_participante = ubicacion;
    }

    public void setRol_participante(String rol) {
        this.rol_participante = rol;
    }

}