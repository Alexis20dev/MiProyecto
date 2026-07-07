package ec.edu.uce.miproyecto.dominio;

public class Tema {

    private int idTema;
    private String nombre;
    private String descripcion;
    private Concepto[] conceptos;
    private Ejercicio[] ejercicios;

    public Tema() {
        this.idTema = 0;
        this.nombre = "Sin nombre";
        this.descripcion = "Sin descripción";
        this.conceptos = new Concepto[5];
        this.ejercicios = new Ejercicio[5];
    }

    public Tema(int idTema, String nombre, String descripcion, Concepto[] conceptos, Ejercicio[] ejercicios) {
        this.idTema = idTema;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.conceptos = conceptos;
        this.ejercicios = ejercicios;
    }

    public int getIdTema() {
        return idTema;
    }

    public void setIdTema(int idTema) {
        this.idTema = idTema;
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

    public Concepto[] getConceptos() {
        return conceptos;
    }

    public void setConceptos(Concepto[] conceptos) {
        this.conceptos = conceptos;
    }

    public Ejercicio[] getEjercicios() {
        return ejercicios;
    }

    public void setEjercicios(Ejercicio[] ejercicios) {
        this.ejercicios = ejercicios;
    }

    @Override
    public String toString() {
        return "Tema( " +
                "idTema: " + idTema +
                ", nombre: " + nombre +
                ", descripcion: " + descripcion +
                ", cantidadConceptos: " + (conceptos != null ? conceptos.length : 0) +
                ", cantidadEjercicios: " + (ejercicios != null ? ejercicios.length : 0) +
                ')';
    }
}

