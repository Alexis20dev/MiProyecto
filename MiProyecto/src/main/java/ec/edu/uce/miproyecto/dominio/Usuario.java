package ec.edu.uce.miproyecto.dominio;

public class Usuario {

    protected String username;
    protected String password;
    protected String nombre;
    // CONSTRUCTOR
    public Usuario(String username, String password, String nombre){
        this.username = username;
        this.password = password;
        this.nombre = nombre;
    }
    public boolean iniciarSesion(String user, String pass){
        return username.equals(user)
                &&
                password.equals(pass);
    }
    public void cerrarSesion() {
        System.out.println("Sesión cerrada");
    }
    public void cambiarPassword(String nuevaPassword) {
        this.password = nuevaPassword;
    }
    public String getUsername() {
        return username;
    }
    public String getNombre() {
        return nombre;
    }
}

