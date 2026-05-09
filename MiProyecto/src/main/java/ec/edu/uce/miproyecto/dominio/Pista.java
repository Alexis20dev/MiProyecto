package ec.edu.uce.miproyecto.dominio;

public class Pista {
    private String descripcion;
    private int nivelAyuda;
    public Pista(String descripcion, int nivelAyuda) {
        this.descripcion = descripcion;
        this.nivelAyuda = nivelAyuda;
    }
    public String mostrarPista() {
        return descripcion;
    }
    public int getNivelAyuda() {
        return nivelAyuda;
    }
}
