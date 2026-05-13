package ec.edu.uce.miproyecto.dominio;

public class Pista {
    private int idpista;
    private String descripcion;
    private int orden;

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

    public Pistas() {
    }

    public Pistas(int idpista, String descripcion, int orden) {
        this.idpista = idpista;
        this.descripcion = descripcion;
        this.orden = orden;
    }

    @Override
    public String toString() {
        return "Pistas{" +
                "idpista=" + idpista +
                ", descripcion='" + descripcion + '\'' +
                ", orden=" + orden +
                '}';
    }
}
