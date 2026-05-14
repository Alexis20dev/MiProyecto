package ec.edu.uce.miproyecto.dominio;

public class Logro {
    private int idLogro;
    private String nombre;
    private String descripcion;
    private String condicion;


    public int getIdLogro() {
        return idLogro;
    }

    public void setIdLogro(int idLogro) {
        this.idLogro = idLogro;
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

    public String getCondicion() {
        return condicion;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }

    public Logro() {
    }

    public Logro(int idLogro, String nombre, String descripcion, String condicion) {
        this.idLogro = idLogro;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.condicion = condicion;
    }

    @Override
    public String toString() {
        return "Logro{" +
                "idLogro=" + idLogro +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", condicion='" + condicion + '\'' +
                '}';
    }
}
