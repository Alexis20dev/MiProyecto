package ec.edu.uce.miproyecto.dominio;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Ejercicio {

    private final int idEjercicio;
    private static int idEjercicioContador = 1;
    private String enunciado;
    private String respuesta;
    private String dificultad;

    private List<ItemEjercicio> items;
    private List<Pista> pistas;

    public Ejercicio() {
        this.idEjercicio = idEjercicioContador++;
        this.enunciado = "Sin enunciado";
        this.respuesta = "Sin respuesta";
        this.dificultad = "Sin dificultad";
        this.items = new ArrayList<>();
        this.pistas = new ArrayList<>();
    }

    public Ejercicio(String enunciado, String respuesta, String dificultad) {
        this.idEjercicio = idEjercicioContador++;
        this.enunciado = enunciado;
        this.respuesta = respuesta;
        this.dificultad = dificultad;
        this.items = new ArrayList<>();
        this.pistas = new ArrayList<>();
    }

    public void agregarPista(Pista pista) {
        if (pista != null && !pistas.contains(pista)) {
            pistas.add(pista);
        }
    }

    public int getIdEjercicio() {
        return idEjercicio;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getDificultad() {
        return dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }

    public List<Pista> getPistas() {
        return pistas;
    }

    public List<ItemEjercicio> getItems() {
        return items;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Ejercicio)) return false;
        Ejercicio ejercicio = (Ejercicio) obj;
        return this.idEjercicio == ejercicio.idEjercicio;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEjercicio);
    }

    @Override
    public String toString() {
        return "Ejercicio: " + enunciado + " [" + dificultad + "] ("
                + (pistas != null ? pistas.size() : 0) + " pistas)";
    }
}