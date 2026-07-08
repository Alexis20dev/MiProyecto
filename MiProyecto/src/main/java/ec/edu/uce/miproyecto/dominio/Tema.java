package ec.edu.uce.miproyecto.dominio;

public class Tema {

    private final int idTema;
    private static int idTemaContador = 1; // Empieza en 1 (o en 0, según prefieras)

    private String nombre;
    private String descripcion;
    private Concepto[] conceptos;
    private Ejercicio[] ejercicios;

    public Tema() {
        this.idTema = idTemaContador++; // Asigna el ID automáticamente e incrementa
        this.nombre = "Sin nombre";
        this.descripcion = "Sin descripción";
        this.conceptos = new Concepto[5];
        this.ejercicios = new Ejercicio[5];
    }

    public Tema(String nombre, String descripcion, Concepto[] conceptos, Ejercicio[] ejercicios) {
        this.idTema = idTemaContador++; // Asigna el ID automáticamente de forma secuencial
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.conceptos = conceptos;
        this.ejercicios = ejercicios;
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
                ", cantidadConceptos: " + (conceptos != null ? conceptos.length : 0) +
                ", cantidadEjercicios: " + (ejercicios != null ? ejercicios.length : 0) +
                ')';
    }
}