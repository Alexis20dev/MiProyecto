package ec.edu.uce.miproyecto.dominio;

import java.util.Objects;

public class Pista {
    private final int idpista;
    private static int idPistaContador = 1;
    private String descripcion;
    private int orden;

    public Pista() {
        this.idpista = idPistaContador++;
        this.descripcion = "Sin descripción";
        this.orden = 0;
    }

    public Pista(String descripcion, int orden) {
        this.idpista = idPistaContador++;
        this.descripcion = descripcion;
        this.orden = orden;
    }

    public int getIdpista() {
        return idpista;
    }


    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public int getOrden() { return orden; }
    public void setOrden(int orden) { this.orden = orden; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Pista)){
            return false;
        }
        //Casting
        Pista pista = (Pista) obj;
        return this.idpista == pista.idpista;
    }
    @Override
    public int hashCode() {
        return Objects.hashCode(getIdpista());
    }

    @Override
    public String toString() {
        return "Pista{" + "idpista=" + idpista + ", descripcion='" + descripcion + '\'' + ", orden=" + orden + '}';
    }
}