package ec.edu.uce.miproyecto.dominio;

public class ItemEjercicio {

    private Ejercicio ejercicio;
    private String estado;

    public ItemEjercicio() {
        this.ejercicio = new Ejercicio();
        this.estado = "Nuevo";
    }

    public ItemEjercicio(Ejercicio ejercicio, String estado) {
        this.ejercicio = ejercicio;
        this.estado = estado;
    }

    public Ejercicio getEjercicio() {
        return ejercicio;
    }

    public void setEjercicio(Ejercicio ejercicio) {
        this.ejercicio = ejercicio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "ItemEjercicio{" +
                "ejercicio=" + (ejercicio != null ? ejercicio.getEnunciado() : "Ninguno") +
                ", estado='" + estado + '\'' +
                '}';
    }
}