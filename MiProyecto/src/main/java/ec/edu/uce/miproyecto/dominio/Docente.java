package ec.edu.uce.miproyecto.dominio;

import java.util.Date;

public class Docente extends Usuario {

    private String especialidad;

    public Docente() {
        super();
        this.especialidad = "Sin especialidad";
    }

    public Docente(int idUsuario, String nombre, String email, String contrasena, Date fechaRegistro, String especialidad) {
        super(idUsuario, nombre, email, contrasena, fechaRegistro);
        this.especialidad = especialidad;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    @Override
    public String toString() {
        return "Docente{" +
                super.toString() +
                ", especialidad='" + especialidad + '\'' +
                '}';
    }
}