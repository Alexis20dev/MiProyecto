package ec.edu.uce.miproyecto.dominio;

import ec.edu.uce.miproyecto.enums.Genero;

import java.util.Date;

import static ec.edu.uce.miproyecto.util.Consola.mostrarProgresoEstudiante;

public final class Estudiante extends Usuario {
    private String nivel;
    private Progreso progreso;

    public Estudiante() {
        super();
        this.nivel = "Sin nivel";
        this.progreso = new Progreso();
    }

    public Estudiante(String nombre, String email, String contrasena, Date fechaRegistro, Genero genero, String nivel, Progreso progreso) {
        super(nombre, email, contrasena, fechaRegistro, genero);
        this.nivel = nivel;
        this.progreso = progreso;
    }

    public Estudiante (Estudiante e){
        super(e);
        this.nivel = e.nivel;
        this.progreso = e.progreso;
    }
    @Override
    public void verProgreso() {
        boolean tieneProgreso = (this.progreso != null);
        String estado = tieneProgreso ? this.progreso.getEstado() : "";
        int puntaje = tieneProgreso ? this.progreso.getPuntaje() : 0;
        int tiempo = tieneProgreso ? this.progreso.getTiempo() : 0;

        mostrarProgresoEstudiante(this.nombre, this.nivel, estado, puntaje, tiempo, tieneProgreso);
    }

    public String getNivel() {
        return nivel;
    }
    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public Progreso getProgreso() {
        return progreso;
    }

    public void setProgreso(Progreso progreso) {
        this.progreso = progreso;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (!(o instanceof  Estudiante)){
            return false;
        }
        //Casting
        Estudiante estudiante = (Estudiante) o;
        return this.getIdUsuario() == estudiante.getIdUsuario();
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                super.toString() +
                ", nivel='" + nivel + '\'' +
                ", progreso=" + (progreso != null ? "Asignado" : "No asignado") +
                '}';
    }
}