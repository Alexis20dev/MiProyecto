package ec.edu.uce.miproyecto.dominio;

import java.util.Date;

public class Docente extends Usuario {

    // El constructor debe pedir los mismos datos que Usuario para poder pasárselos
    public Docente(int idUsuario, String nombre, String email, String contrasena,
                   Date fechaRegistro) {
        // El orden debe ser: ID, Nombre, Email, Contraseña, Fecha, Sesión
        super(idUsuario, nombre, email, contrasena, fechaRegistro);
    }

    public void crearEjercicio(Ejercicio ejercicio) {
        System.out.println("Ejercicio creado: " + ejercicio.getEnunciado());
    }

    public boolean editarEjercicio(Ejercicio ejercicio) {
        System.out.println("Ejercicio editado");
        return true;
    }

    public boolean eliminarEjercicio(Ejercicio ejercicio) {
        System.out.println("Ejercicio eliminado");
        return true;
    }

    public void revisarEjercicio(Ejercicio ejercicio) {
        System.out.println("Revisando ejercicio: " + ejercicio.getEnunciado());
    }
}