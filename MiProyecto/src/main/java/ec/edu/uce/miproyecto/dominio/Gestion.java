package ec.edu.uce.miproyecto.dominio;

public class Gestion {

    private Usuario[] listaUsuarios;
    private Tema[] listaTemas;
    private ItemEjercicio[] listaItemE;

    private int numUsuarios;
    private int numTemas;
    private int numItemE;

    public Gestion() {
        listaUsuarios = new Usuario[100];
        listaTemas = new Tema[100];
        listaItemE = new ItemEjercicio[100];

        numUsuarios = 0;
        numTemas = 0;
        numItemE = 0;
    }

    public void registrarUsuario(Usuario usuario) {
        if (usuario != null && numUsuarios < listaUsuarios.length) {
            listaUsuarios[numUsuarios] = usuario;
            numUsuarios++;
        }
    }

    public Usuario buscarUsuarioPorCorreo(String credencial) {
        for (int i = 0; i < numUsuarios; i++) {
            Usuario u = listaUsuarios[i];

            if (u.getEmail().equalsIgnoreCase(credencial)
                    || u.getNombre().equalsIgnoreCase(credencial)) {
                return u;
            }
        }
        return null;
    }

    public void agregarEjercicio(Ejercicio ejercicio) {
        if (ejercicio != null && numItemE < listaItemE.length) {
            ItemEjercicio nuevoItem = new ItemEjercicio(ejercicio, "Nuevo");
            listaItemE[numItemE] = nuevoItem;
            numItemE++;
        }
    }

    public Usuario[] getListaUsuarios() {
        return listaUsuarios;
    }

    public Tema[] getListaTemas() {
        return listaTemas;
    }

    public ItemEjercicio[] getListaItemE() {
        return listaItemE;
    }

    public int getNumUsuarios() {
        return numUsuarios;
    }

    public int getNumTemas() {
        return numTemas;
    }

    public int getNumItemE() {
        return numItemE;
    }

    @Override
    public String toString() {
        return "Gestion{" +
                "numUsuarios=" + numUsuarios +
                ", numTemas=" + numTemas +
                ", numItemE=" + numItemE +
                '}';
    }

    public void setListaUsuarios(Usuario[] listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public void setListaTemas(Tema[] nuevaListaTemas) {
        this.listaTemas = listaTemas;
    }

    public void setListaItemE(ItemEjercicio[] nuevaListaItems) {
        this.listaItemE = listaItemE;
    }
}