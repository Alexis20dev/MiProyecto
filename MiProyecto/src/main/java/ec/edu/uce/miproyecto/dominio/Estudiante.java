package ec.edu.uce.miproyecto.dominio;

import java.util.Date;

public class Estudiante extends Usuario {
    private String nivel;
    private Progreso progreso;

    public Estudiante() {
        super();
        this.nivel = "Principiante";
        this.progreso = new Progreso();
    }

    public Estudiante(int idUsuario, String nombre, String email, String contrasena, Date fechaRegistro,
                      String nivel, Progreso progreso) {
        super(idUsuario, nombre, email, contrasena, fechaRegistro);
        this.nivel = nivel;
        this.progreso = progreso;
    }

    public String getNivel() {
        return nivel;
    }

    public void setLevel(String nivel) {
        this.nivel = nivel;
    }

    public Progreso getProgreso() {
        return progreso;
    }

    public void setProgreso(Progreso progreso) {
        this.progreso = progreso;
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                super.toString() + // Reutiliza los datos impresos de la clase Usuario
                ", nivel='" + nivel + '\'' +
                ", progreso=" + (progreso != null ? "Asignado" : "No asignado") +
                '}';
    }
}