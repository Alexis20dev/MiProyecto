package ec.edu.uce.miproyecto.dominio;

import java.util.Date;

public class Progreso {
    private int idProgreso;
    private String estado;
    private Date fecha;
    private int puntaje;
    private int tiempo;

    public void actualizarProgreso(boolean esCorrecto) {
        this.fecha = new Date(); // Actualiza la fecha al momento del intento
        if (esCorrecto) {
            this.puntaje += 10; // Suma puntos por acierto
            this.estado = "Aprobado";
        } else {
            this.estado = "Reprobado";
        }
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

    public Progreso() {
    }

    public Progreso(int idProgreso, String estado, Date fecha, int puntaje, int tiempo) {
        this.idProgreso = idProgreso;
        this.estado = estado;
        this.fecha = fecha;
        this.puntaje = puntaje;
        this.tiempo = tiempo;
    }

    @Override
    public String toString() {
        return "Progreso{" +
                "idProgreso=" + idProgreso +
                ", estado='" + estado + '\'' +
                ", fecha=" + fecha +
                ", puntaje=" + puntaje +
                ", tiempo=" + tiempo +
                '}';
    }
}
