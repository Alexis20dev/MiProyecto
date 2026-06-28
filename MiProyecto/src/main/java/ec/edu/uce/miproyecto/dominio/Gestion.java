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

    public boolean validarDuplicado(Object o) {
        if (o == null || !(o instanceof Usuario)) {
            return false;
        }
        Usuario nuevoUsuario = (Usuario) o;
        for (int i = 0; i < numUsuarios; i++) {
            if (listaUsuarios[i] != null && listaUsuarios[i].equals(nuevoUsuario)) {
                System.out.println("❌ Error: El usuario ya existe.");
                return true;
            }
        }
        return false;
    }

    private void expandirUsuarios() {
        Usuario[] aux = new Usuario[listaUsuarios.length * 2];
        System.arraycopy(listaUsuarios, 0, aux, 0, listaUsuarios.length);
        this.listaUsuarios = aux;
    }

    public void registrarUsuario(Usuario usuario) {
        if (usuario == null) return;

        if (!validarDuplicado(usuario)) {
            if (numUsuarios >= listaUsuarios.length) {
                expandirUsuarios();
            }
            listaUsuarios[numUsuarios] = usuario;
            numUsuarios++;
        }
    }

    public void registrarUsuario(Usuario[] nuevosUsuarios) {
        if (nuevosUsuarios == null) return;
        for (Usuario u : nuevosUsuarios) {
            if (u != null) {
                registrarUsuario(u);
            }
        }
    }

    // ✨ ESTE ES EL MÉTODO QUE LE FALTA A TU ARCHIVO PARA QUITAR EL ERROR ROJO
    public String consultarUsuarios() {
        String texto = "== LISTA DE USUARIOS REGISTRADOS ==\n";
        for (int i = 0; i < numUsuarios; i++) {
            if (listaUsuarios[i] != null) {
                texto += listaUsuarios[i].toString() + "\n";
            }
        }
        if (numUsuarios == 0) {
            texto += "No hay usuarios registrados en el sistema.\n";
        }
        return texto;
    }

    public boolean editarUsuario(int pos, String nombre, String email, String contrasena) {
        if (pos >= 0 && pos < numUsuarios && listaUsuarios[pos] != null) {
            listaUsuarios[pos].setNombre(nombre);
            listaUsuarios[pos].setEmail(email);
            listaUsuarios[pos].setContrasena(contrasena);
            return true;
        }
        return false;
    }

    public Usuario buscarUsuarioPorCorreo(String credencial) {
        for (int i = 0; i < numUsuarios; i++) {
            Usuario u = listaUsuarios[i];
            if (u != null && (u.getEmail().equalsIgnoreCase(credencial) || u.getNombre().equalsIgnoreCase(credencial))) {
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

    public Usuario[] getListaUsuarios() { return listaUsuarios; }
    public int getNumUsuarios() { return numUsuarios; }
    public int getNumItemE() { return numItemE; }
}