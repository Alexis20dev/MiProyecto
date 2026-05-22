package ec.edu.uce.miproyecto.dominio;

import java.util.ArrayList;
import java.util.List;

public class Gestion {
    private List<Usuario> listaUsuarios;
    private List<Tema> listaTemas;
    private List<Ejercicio> listaEjercicios;

    public Gestion() {
        this.listaUsuarios = new ArrayList<>();
        this.listaTemas = new ArrayList<>();
        this.listaEjercicios = new ArrayList<>();
    }

    public void registrarUsuario(Usuario nuevo) {
        listaUsuarios.add(nuevo);
    }

    public Usuario buscarUsuarioPorCorreo(String correo) {
        for (Usuario u : listaUsuarios) {
            if (u.getEmail().equalsIgnoreCase(correo)) {
                return u;
            }
        }
        return null; // No existe
    }


    public void crearTema(Tema nuevoTema) {
        listaTemas.add(nuevoTema);
    }

    public List<Tema> listarTemas() {
        return listaTemas;
    }


    public void agregarEjercicio(Ejercicio nuevoEj) {
        listaEjercicios.add(nuevoEj);
    }

    public List<Ejercicio> listarEjercicios() {
        return listaEjercicios;
    }
}