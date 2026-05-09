package ec.edu.uce.miproyecto.dominio;

public class Sesion {
    private Usuario usuarioActivo;
    public boolean validarCredenciales(Usuario usuario, String username, String password) {
        return usuario.iniciarSesion(username, password);
    }
    public void iniciarSesion(Usuario usuario) {
        usuarioActivo = usuario;
        System.out.println(
                "Sesión iniciada"
        );
    }
    public void cerrarSesion() {
        usuarioActivo = null;
        System.out.println(
                "Sesión cerrada"
        );
    }
    public Usuario getUsuarioActivo() {
        return usuarioActivo;
    }
}
