package ec.edu.uce.miproyecto.dominio;

public class Pista {
    private int idpista;
    private String descripcion;
    private int orden;

    // --- ESTE ES EL MÉTODO QUE FALTA Y SOLUCIONA EL ERROR ---
    public void mostrarPista() {
        System.out.println("\n[ AYUDA DISPONIBLE ]");
        System.out.println("Orden de la pista: " + orden);
        System.out.println("Sugerencia: " + descripcion);
        System.out.println("---------------------");
    }

    public int getIdpista() {
        return idpista;
    }

    public void setIdpista(int idpista) {
        this.idpista = idpista;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public Pista(int i, String usaSustitución) {
    }

    public Pista(int idpista, String descripcion, int orden) {
        this.idpista = idpista;
        this.descripcion = descripcion;
        this.orden = orden;
    }

    @Override
    public String toString() {
        return "Pista{" +
                "idpista=" + idpista +
                ", descripcion='" + descripcion + '\'' +
                ", orden=" + orden +
                '}';
    }
}
