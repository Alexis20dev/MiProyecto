package ec.edu.uce.miproyecto.dominio;

public class Pista {
    private int idpista;
    private String descripcion;
    private int orden;

    public Pista() {
        this.idpista = 0;
        this.descripcion = "Sin descripción";
        this.orden = 1;
    }

    public Pista(int idpista, String descripcion, int orden) {
        this.idpista = idpista;
        this.descripcion = descripcion;
        this.orden = orden;
    }

    public int getIdpista() {
        return idpista;
    }

    public void setIdpista(int idpista) {
        this.idpista = idpista;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    @Override
    public String toString() {
        return "Pista{" +
                "idpista=" + idpista +
                ", descripcion='" + descripcion + '\'' +
                ", orden=" + orden +
                '}';
    }
}