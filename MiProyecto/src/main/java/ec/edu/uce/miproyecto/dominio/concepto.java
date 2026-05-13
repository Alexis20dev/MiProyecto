package ec.edu.uce.miproyecto.dominio;
  public class concepto {
    private int idConcepto;
    private String nombre;
    private String descripcion;

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

    public concepto() {
    }

    public concepto(int idConcepto, String nombre, String descripcion) {
        this.idConcepto = idConcepto;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "concepto{" +
                "idConcepto=" + idConcepto +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
