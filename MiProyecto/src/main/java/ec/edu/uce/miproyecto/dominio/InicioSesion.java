package ec.edu.uce.miproyecto.dominio;

import java.util.Date;

public class InicioSesion {

    private int idSesion;
    private Date fechainicio;
    private Date fechafin;
    private String estado;

    // Constructor vacío (Muy importante para que no dé error al crear sesiones vacías)
    public InicioSesion() {
    }

    // Constructor completo organizado
    public InicioSesion(int idSesion, Date fechainicio, Date fechafin, String estado) {
        this.idSesion = idSesion;
        this.fechainicio = fechainicio;
        this.fechafin = fechafin;
        this.estado = estado;
    }

    // Getters y Setters
    public int getIdSesion() { return idSesion; }
    public void setIdSesion(int idSesion) { this.idSesion = idSesion; }

    public Date getFechainicio() { return fechainicio; }
    public void setFechainicio(Date fechainicio) { this.fechainicio = fechainicio; }

    public Date getFechafin() { return fechafin; }
    public void setFechafin(Date fechafin) { this.fechafin = fechafin; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    @Override
    public String toString() {
        return "InicioSesion{" +
                "idSesion=" + idSesion +
                ", estado='" + estado + '\'' +
                '}';
    }
}