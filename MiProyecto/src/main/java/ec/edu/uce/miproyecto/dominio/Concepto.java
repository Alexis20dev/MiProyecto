package ec.edu.uce.miproyecto.dominio;
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
