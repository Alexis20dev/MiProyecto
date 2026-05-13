package ec.edu.uce.miproyecto.dominio;

import java.util.Date;

public class InicioSesion {
    private int idSesion;
    private Date fechainicio;
    private Date fechafin;
    private String estado;

    public Date getFechainicio() {
        return fechainicio;
    }

    public void setFechainicio(Date fechainicio) {
        this.fechainicio = fechainicio;
    }

    public Date getFechafin() {
        return fechafin;
    }

    public void setFechafin(Date fechafin) {
        this.fechafin = fechafin;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIdSesion() {
        return idSesion;
    }

    public void setIdSesion(int idSesion) {
        this.idSesion = idSesion;
    }

    public InicioSesion() {
    }

    @Override
    public String toString() {
        return "InicioSesion{" +
                "idSesion=" + idSesion +
                ", fechainicio=" + fechainicio +
                ", fechafin=" + fechafin +
                ", estado='" + estado + '\'' +
                '}';
    }

    public InicioSesion(Date fechainicio, Date fechafin, String estado, int idSesion) {
        this.fechainicio = fechainicio;
        this.fechafin = fechafin;
        this.estado = estado;
        this.idSesion = idSesion;
    }
}
