package ec.edu.uce.miproyecto.dominio;
public class Concepto {
    private int idConcepto;
    private String nombre;
    private String descripcion;
    public void mostrarExplicacion() {
        System.out.println("\n--- DETALLE DEL CONCEPTO ---");
        System.out.println("Tema: " + nombre);
        System.out.println("Explicación: " + descripcion);
        System.out.println("----------------------------");
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

    public Concepto() {
    }

    public Concepto(int idConcepto, String nombre, String descripcion) {
        this.idConcepto = idConcepto;
        this.nombre = nombre;
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
