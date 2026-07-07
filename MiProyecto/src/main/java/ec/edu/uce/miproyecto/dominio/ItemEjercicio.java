package ec.edu.uce.miproyecto.dominio;

import ec.edu.uce.miproyecto.enums.EstadoEjercicio;
import ec.edu.uce.miproyecto.enums.EstadoTema;

public class ItemEjercicio {

    private Ejercicio ejercicio;
    protected EstadoEjercicio estado;
    public ItemEjercicio() {
        this.ejercicio = new Ejercicio();
        this.estado = EstadoEjercicio.No_Asignado;
    }

    public ItemEjercicio(Ejercicio ejercicio, EstadoEjercicio estado) {
        this.ejercicio = ejercicio;
        this.estado = estado;
    }

    public Ejercicio getEjercicio() {
        return ejercicio;
    }

    public void setEjercicio(Ejercicio ejercicio) {
        this.ejercicio = ejercicio;
    }

    public EstadoEjercicio getEstado() {
        return estado;
    }

    public void setEstado(EstadoEjercicio estado) {
        this.estado= estado;
    }

    @Override
    public String toString() {
        return "ItemEjercicio{" +
                "ejercicio=" + (ejercicio != null ? ejercicio.getEnunciado() : "Ninguno") +
                ", estadoTema='" + estado + '\'' +
                '}';
    }
}