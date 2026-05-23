package ec.edu.uce.miproyecto.dominio;

import java.util.Date;

public class Docente extends Usuario {

    // 1. ATRIBUTOS
    private String especialidad;

    // 2. CONSTRUCTOR POR DEFECTO
    public Docente() {
        super();
        this.especialidad = "Sin especialidad";
    }

    // 3. CONSTRUCTOR CON PARÁMETROS
    public Docente(int idUsuario, String nombre, String email, String contrasena, Date fechaRegistro, String especialidad) {
        super(idUsuario, nombre, email, contrasena, fechaRegistro);
        this.especialidad = especialidad;
    }

    // (MÉTODOS DE LÓGICA ELIMINADOS POR COMPLETO) ❌

    // 4. MÉTODOS GETTERS Y SETTERS
    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    // 5. MÉTODO TOSTRING
    @Override
    public String toString() {
        return "Docente{" +
                super.toString() +
                ", especialidad='" + especialidad + '\'' +
                '}';
    }
}