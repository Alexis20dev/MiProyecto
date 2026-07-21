package ec.edu.uce.miproyecto.dominio;

import java.util.ArrayList;
import java.util.List;

public class Tema {

    private final int idTema;
    private static int idTemaContador = 1; // Empieza en 1 (o en 0, según prefieras)

    private String nombre;
    private String descripcion;
    private List<Concepto> conceptos;
    private List<Ejercicio> ejercicios;

    public Tema() {
        this.idTema = idTemaContador++; // Asigna el ID automáticamente e incrementa
        this.nombre = "Sin nombre";
        this.descripcion = "Sin descripción";
    }

    public Tema(String nombre, String descripcion) {
        this.idTema = idTemaContador++; // Asigna el ID automáticamente de forma secuencial
        this.nombre = nombre;
        this.descripcion = descripcion;
        conceptos = new ArrayList<Concepto>(3);
        ejercicios = new ArrayList<Ejercicio>(3);
    }


    public int getIdTema() {
        return idTema;
    }

    // ⚠️ ELIMINADO: Se quita el método setIdTema(int idTema) porque el atributo ahora es 'final'.

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
        if (this == obj){
            return true;
        }
        if (!(obj instanceof Tema)){
            return false;
        }
        //Casting
        Tema tema = (Tema) obj;
        return this.idTema == tema.idTema;
    }

    @Override
    public String toString() {
        return "Tema( " +
                "idTema: " + idTema +
                ", nombre: " + nombre +
                ", descripcion: " + descripcion +
                ", cantidadConceptos: " + (conceptos != null ? conceptos.size() : 0) +
                ", cantidadEjercicios: " + (ejercicios != null ? ejercicios.size() : 0) +
                ')';
    }
}