package models;

//juan duarte
public class Patrocinios extends Persona {

    private String contacto_patrocinador;
    private String tipo_patrocinador;
    private String aporte_patrocinador;

    public Patrocinios(String id, String nombre, String correo, String contacto_patrocinador, String tipo_patrocinador,
            String aporte_patrocinador) {
        super(id, nombre, correo);
        this.contacto_patrocinador = contacto_patrocinador;
        this.tipo_patrocinador = tipo_patrocinador;
        this.aporte_patrocinador = aporte_patrocinador;
    }

    @Override
    public String rol() {
        return "el rol de este patrocinador es" + this.tipo_patrocinador;
    }

    @Override
    public void mostrarinfo() {
        System.out.println("el ID: " + id);
        System.out.println("Nombre: " + nombre);
        System.out.println("Correo: " + correo);
        System.out.println("Contacto : " + contacto_patrocinador);
        System.out.println("Tipo : " + tipo_patrocinador);
        System.out.println("Aporte : " + aporte_patrocinador);
    }

    public String getContacto_patrocinador() {
        return contacto_patrocinador;
    }

    public void setContacto_patrocinador(String contacto_patrocinador) {
        this.contacto_patrocinador = contacto_patrocinador;
    }

    public String getTipo_patrocinador() {
        return tipo_patrocinador;
    }

    public void setTipo_patrocinador(String tipo_patrocinador) {
        this.tipo_patrocinador = tipo_patrocinador;
    }

    public String getAporte_patrocinador() {
        return aporte_patrocinador;
    }

    public void setAporte_patrocinador(String aporte_patrocinador) {
        this.aporte_patrocinador = aporte_patrocinador;
    }
    
}
