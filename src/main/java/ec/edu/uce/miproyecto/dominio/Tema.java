package ec.edu.uce.miproyecto.dominio;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Tema {

    private final int idTema;
    private static int idTemaContador = 1;

    private String nombre;
    private String descripcion;
    private List<Concepto> conceptos;
    private List<Ejercicio> ejercicios;


    public Tema() {
        this.idTema = idTemaContador++;
        this.nombre = "Sin nombre";
        this.descripcion = "Sin descripción";
        this.conceptos = new ArrayList<>();
        this.ejercicios = new ArrayList<>();
    }

    public Tema(String nombre, String descripcion) {
        this.idTema = idTemaContador++;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.conceptos = new ArrayList<>();
        this.ejercicios = new ArrayList<>();
    }

    public void agregarConcepto(Concepto concepto) {
        if (concepto != null && !this.conceptos.contains(concepto)) {
            this.conceptos.add(concepto);
        }
    }

    public void agregarEjercicio(Ejercicio ejercicio) {
        if (ejercicio != null && !this.ejercicios.contains(ejercicio)) {
            this.ejercicios.add(ejercicio);
        }
    }

    public int getIdTema() {
        return idTema;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Concepto> getConceptos() {
        return conceptos;
    }

    public List<Ejercicio> getEjercicios() {
        return ejercicios;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Tema)) return false;
        Tema tema = (Tema) obj;
        return this.idTema == tema.idTema;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTema);
    }

    @Override
    public String toString() {
        return nombre + " (" + (conceptos != null ? conceptos.size() : 0) + " conc, "
                + (ejercicios != null ? ejercicios.size() : 0) + " ej) ";
    }
}