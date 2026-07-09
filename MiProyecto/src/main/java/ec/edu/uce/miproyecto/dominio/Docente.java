package ec.edu.uce.miproyecto.dominio;

import ec.edu.uce.miproyecto.enums.Genero;

import java.util.Date;
import static ec.edu.uce.miproyecto.util.Consola.mostrarProgresoDocente;

public final class Docente extends Usuario {

    private String especialidad;

    public Docente() {
        super();
        this.especialidad = "Sin especialidad";
    }

    public Docente(String nombre, String email, String contrasena, Date fechaRegistro, Genero genero, String especialidad) {
        super(nombre, email, contrasena, fechaRegistro, genero);
        this.especialidad = especialidad;
    }

    public Docente(Docente d) {
        super();
        this.especialidad = d.especialidad;
    }

    @Override
    public void verProgreso() {
        mostrarProgresoDocente(this.nombre, this.especialidad);
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

//    @Override
//    public boolean equals(Object o) {
//        if ((this == o)){
//            return true;
//        }                                     ESTE EQUALS ME DABA ERROR AL REGISTRAR UN NUEVO USUARIO
//        if (!(o instanceof Docente)){
//            return false;
//        }
//        //Casting
//        Docente docente = (Docente) o;
//        return this.getIdUsuario() == docente.getIdUsuario();
//    }

    @Override
    public boolean equals(Object o) {
        if ((this == o)){
            return true;
        }
        if (!(o instanceof Docente)){
            return false;
        }
        //Casting
        Docente docente = (Docente) o;
        return this.getIdUsuario() == docente.getIdUsuario();
    }

    @Override
    public String toString() {
        return "Docente{" +
                super.toString() +
                ", especialidad='" + especialidad + '\'' +
                '}';
    }
}