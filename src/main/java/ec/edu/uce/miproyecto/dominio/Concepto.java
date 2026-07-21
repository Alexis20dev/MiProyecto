package ec.edu.uce.miproyecto.dominio;

import java.util.Objects;

public class Concepto {

    private final int idConcepto;
    protected static int idConceptoContador = 1;
    private String nombre;
    private String descripcion;

    public Concepto() {
        this.idConcepto = idConceptoContador++;
        this.nombre = "Sin nombre";
        this.descripcion = "Sin descripción";
    }

    public Concepto(String nombre, String descripcion) {
        this.idConcepto = idConceptoContador++;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public int getIdConcepto() { return idConcepto; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Concepto)) return false;
        Concepto concepto = (Concepto) obj;
        return this.idConcepto == concepto.idConcepto;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idConcepto);
    }

    @Override
    public String toString() {
        return "Concepto{" +
                "idConcepto=" + idConcepto +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}