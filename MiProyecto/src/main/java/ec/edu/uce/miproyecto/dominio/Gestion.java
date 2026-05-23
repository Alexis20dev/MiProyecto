package ec.edu.uce.miproyecto.dominio;

import java.util.ArrayList;

public class Gestion {

    private ArrayList<Usuario> listaUsuarios;
    private ArrayList<Tema> listaTemas;
    private ArrayList<ItemEjercicio> listaItemE;

    public Gestion() {
        this.listaUsuarios = new ArrayList<>();
        this.listaTemas = new ArrayList<>();
        this.listaItemE = new ArrayList<>();
    }

    public void registrarUsuario(Usuario usuario) {
        if (usuario != null) {
            this.listaUsuarios.add(usuario);
        }
    }

    public Usuario buscarUsuarioPorCorreo(String credencial) {
        for (Usuario u : listaUsuarios) {
            if (u.getEmail().equalsIgnoreCase(credencial) || u.getNombre().equalsIgnoreCase(credencial)) {
                return u;
            }
        }
        return null;
    }

    public void agregarEjercicio(Ejercicio ejercicio) {
        if (ejercicio != null) {
            ItemEjercicio nuevoItem = new ItemEjercicio(ejercicio, "Nuevo");
            this.listaItemE.add(nuevoItem);
        }
    }

    public ArrayList<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(ArrayList<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public ArrayList<Tema> getListaTemas() {
        return listaTemas;
    }

    public void setListaTemas(ArrayList<Tema> listaTemas) {
        this.listaTemas = listaTemas;
    }

    public ArrayList<ItemEjercicio> getListaItemE() {
        return listaItemE;
    }

    public void setListaItemE(ArrayList<ItemEjercicio> listaItemE) {
        this.listaItemE = listaItemE;
    }

    @Override
    public String toString() {
        return "Gestion{" +
                "listaUsuarios=" + listaUsuarios +
                ", listaTemas=" + listaTemas +
                ", listaItemE=" + listaItemE +
                '}';
    }
}