package ec.edu.uce.miproyecto.dominio;

import ec.edu.uce.miproyecto.enums.EstadoEjercicio;
import ec.edu.uce.miproyecto.enums.EstadoTema;
import ec.edu.uce.miproyecto.enums.Genero;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Gestion {

    private static final Gestion gestion = new Gestion("MathFlow");
    public static List<Usuario> usuarios;
    private static List<Tema> temas;
    private static List<Ejercicio> ejercicios;

//    private int numUsuarios;
//    private int numTemas;
//    private int numItemE;

    public Gestion() {
        this.nombre="sin nombre";
    }
    private String nombre;
    public Gestion(String nombre) {
        this.nombre = nombre;
//        numUsuarios = 0;
//        numTemas = 0;
//        numItemE = 0;

        usuarios = new ArrayList<Usuario>(3);
        temas = new ArrayList<Tema>(3);
        ejercicios = new ArrayList<Ejercicio>(3);

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
    public int getNumEjercicios() { return ejercicios.size(); }

    public Usuario getUsuario(int pos) {return usuarios.get(pos);}

    public static List<Usuario> getUsuarios() { return usuarios; }
    public static List<Tema> getTemas() { return temas; }
    public static List<Ejercicio> getEjercicios() { return ejercicios; }

//    public boolean validarDuplicado(List<?> lista,Object object) {
//        for (Object o: lista){
//            if(o.equals(object))
//                return true;
//        }
//        return false;
//    }

    private void cargarDatosIniciales() {
        Date fecha = new Date();
        Progreso progreso = new Progreso();
        usuarios.add(new Estudiante(
                "jeremy","jeremy@uce.edu.ec","1234",fecha,Genero.S, "Principiante", progreso));

        usuarios.add(new Docente("docente", "docente@uce.edu.ec", "1234", fecha, Genero.S, "Desarrollo de Software"));
    }
}