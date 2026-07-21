package ec.edu.uce.miproyecto.dominio;

import ec.edu.uce.miproyecto.enums.Genero;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Gestion {

    private static final Gestion gestion = new Gestion("MathFlow");
    private String nombre;

    private static List<Usuario> usuarios;
    private static List<Tema> temas;
    private static List<Ejercicio> ejercicios;

    public Gestion() {
        this.nombre = "sin nombre";
    }

    public Gestion(String nombre) {
        this.nombre = nombre;
        usuarios = new ArrayList<>();
        temas = new ArrayList<>();
        ejercicios = new ArrayList<>();
        cargarDatosIniciales();
    }

    public static Gestion getGestion() {
        return gestion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumUsuarios() { return usuarios.size(); }
    public int getNumTemas() { return temas.size(); }

    public Usuario getUsuario(int pos) { return usuarios.get(pos); }

    public static List<Usuario> getUsuarios() { return usuarios; }
    public static List<Tema> getTemas() { return temas; }

    public static List<Ejercicio> getEjercicios() { return ejercicios; }

    private void cargarDatosIniciales() {
        Date fecha = new Date();
        Progreso progreso = new Progreso();
        usuarios.add(new Estudiante("jeremy", "jeremy@uce.edu.ec", "1234", fecha, Genero.S, "Principiante", progreso));
        usuarios.add(new Docente("docente", "docente@uce.edu.ec", "1234", fecha, Genero.S, "Desarrollo de Software"));
    }
}