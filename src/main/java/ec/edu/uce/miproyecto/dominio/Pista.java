package ec.edu.uce.miproyecto.dominio;

import java.util.Objects;

public class Pista {

    private final int idPista;
    private static int idPistaContador = 1;
    private String descripcion;
    private int orden;

    public Pista() {
        this.idPista = idPistaContador++;
        this.descripcion = "Sin descripción";
        this.orden = 1;
    }

    public Pista(String descripcion, int orden) {
        this.idPista = idPistaContador++;
        this.descripcion = descripcion;
        this.orden = orden;
    }

    public int getIdPista() {
        return idPista;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pista)) return false;
        Pista pista = (Pista) o;
        return idPista == pista.idPista;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPista);
    }

    @Override
    public String toString() {
        return "Pista{" +
                "idPista=" + idPista +
                ", descripcion='" + descripcion + '\'' +
                ", orden=" + orden +
                '}';
    }
}