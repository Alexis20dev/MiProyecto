package ec.edu.uce.miproyecto.dominio;
import java.util.ArrayList;
import java.util.List;

public class Ejercicio {

    private String enunciado;
    private double respuestaCorrecta;
    private int dificultad;
    private Tema tema;
    private Concepto concepto;
    private List<Pista> pistas;
    public Ejercicio(String enunciado, double respuestaCorrecta, int dificultad, Tema tema, Concepto concepto) {
        this.enunciado = enunciado;
        this.respuestaCorrecta = respuestaCorrecta;
        this.dificultad = dificultad;
        this.tema = tema;
        this.concepto = concepto;
        pistas = new ArrayList<>();
    }
    public boolean validarRespuesta(double respuesta) {
        return respuesta == respuestaCorrecta;
    }
    public String mostrarEjercicio() {
        return enunciado;
    }
    public void agregarPista(Pista pista) {
        pistas.add(pista);
    }
    public Pista mostrarPista() {
        if (pistas.isEmpty()) {
            return null;
        }
        return pistas.get(0);
    }
    public Tema getTema() {
        return tema;
    }
    public Concepto getConcepto() {
        return concepto;
    }
}