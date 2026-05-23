package ec.edu.uce.miproyecto.dominio;

public class Concepto {

    private int idConcepto;
    private String nombre;
    private String descripcion;

    public Concepto() {
        this.idConcepto = 0;
        this.nombre = "Sin nombre";
        this.descripcion = "Sin descripción";
    }

    public Concepto(int idConcepto, String nombre, String descripcion) {
        this.idConcepto = idConcepto;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
    public int getIdConcepto() {
        return idConcepto;
    }

    public void setIdConcepto(int idConcepto) {
        this.idConcepto = idConcepto;
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
    @Override
    public String toString() {
        return "Concepto{" +
                "idConcepto=" + idConcepto +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}