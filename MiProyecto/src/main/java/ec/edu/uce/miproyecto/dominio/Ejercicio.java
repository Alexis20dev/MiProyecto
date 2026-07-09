package ec.edu.uce.miproyecto.dominio;

import java.util.Arrays;

public class Ejercicio {

    private final int idEjercicio;
    private static int idEjercicioContador =0;
    private String enunciado;
    private String respuesta;
    private String dificultad;
    private Pista[] pistas;

    public Ejercicio() {
        this.idEjercicio = idEjercicioContador++;
        this.enunciado = "Sin enunciado";
        this.respuesta = "Sin respuesta";
        this.dificultad = "Baja";
        this.pistas = new Pista[3];
    }

    public Ejercicio(String enunciado, String respuesta, String dificultad, Pista[] pistas) {
        this.idEjercicio = idEjercicioContador++;
        this.enunciado = enunciado;
        this.respuesta = respuesta;
        this.dificultad = dificultad;
        this.pistas = pistas;
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
    public Pista[] getPistas() {
        return pistas;
    }
    public void setPistas(Pista[] pistas) {
        this.pistas = pistas;
    }

    @Override
    public String toString() {
        return "Ejercicio{" +
                "idEjercicio=" + idEjercicio +
                ", enunciado='" + enunciado + '\'' +
                ", dificultad='" + dificultad + '\'' +
                ", cantidadPistas=" + (pistas != null ? pistas.length : 0) + // Muestra cuántas pistas tiene asignadas
                '}';
    }
}
