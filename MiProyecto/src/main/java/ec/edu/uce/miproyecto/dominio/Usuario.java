package ec.edu.uce.miproyecto.dominio;
import java.util.Date;
public class Usuario {

    private int idUsuario;
    private String nombre;
    private String email; // Corregido: decía 'prte'
    private String contrasena; // Corregido: decía 'privaivate'
    private Date fechaRegistro;
    private InicioSesion inicioSesion;

    // 1. CONSTRUCTOR VACÍO
    public Usuario() {
    }

    // 2. CONSTRUCTOR COMPLETO (Asegúrate que el orden coincida con Estudiante y Docente)
    public Usuario(int idUsuario, String nombre, String email, String contrasena, Date fechaRegistro, InicioSesion inicioSesion) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.email = email;
        this.contrasena = contrasena;
        this.fechaRegistro = fechaRegistro;
        this.inicioSesion = inicioSesion;
    }

    // 3. EL MÉTODO QUE FALTA (Esto quita el error de MenuPrincipal.java)
    public boolean iniciarSesion(String username, String password) {
        // Valida si el nombre o el email coinciden con el 'username' ingresado
        return (this.nombre.equalsIgnoreCase(username) || this.email.equalsIgnoreCase(username))
                && this.contrasena.equals(password);
    }

    // 4. GETTERS Y SETTERS
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public InicioSesion getInicioSesion() {
        return inicioSesion;
    }

    public void setInicioSesion(InicioSesion inicioSesion) {
        this.inicioSesion = inicioSesion;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", fechaRegistro=" + fechaRegistro +
                '}';
    }
}
