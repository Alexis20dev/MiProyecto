package ec.edu.uce.miproyecto.dominio;

import java.util.Date;

public class Progreso {
    private int idProgreso;
    private String estado;
    private Date fecha;
    private int puntaje;
    private int tiempo;
    private ItemEjercicio[] items;

    public Progreso() {
        this.idProgreso = 0;
        this.estado = "En curso";
        this.fecha = new Date();
        this.puntaje = 0;
        this.tiempo = 0;
        this.items = new ItemEjercicio[10];
    }

    public Progreso(int idProgreso, String estado, Date fecha, int puntaje, int tiempo, ItemEjercicio[] items) {
        this.idProgreso = idProgreso;
        this.estado = estado;
        this.fecha = fecha;
        this.puntaje = puntaje;
        this.tiempo = tiempo;
        this.items = items;
    }

    public int getIdProgreso() {
        return idProgreso;
    }

    public void setIdProgreso(int idProgreso) {
        this.idProgreso = idProgreso;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public ItemEjercicio[] getItems() {
        return items;
    }

    public void setItems(ItemEjercicio[] items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Progreso{" +
                "idProgreso=" + idProgreso +
                ", estado='" + estado + '\'' +
                ", fecha=" + fecha +
                ", puntaje=" + puntaje +
                ", tiempo=" + tiempo +
                ", cantidadItems=" + (items != null ? items.length : 0) +
                '}';
    }
}