package ec.edu.uce.miproyecto.dominio;
import java.util.Date;

public class Usuario {

    private int idUsuario;
    private String nombre;
    private String email;
    private String contrasena;
    private Date fechaRegistro;

    // Constructor Vacío
    public Usuario() {
    }

    public Usuario(int idUsuario, String nombre, String email, String contrasena, Date fechaRegistro) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.email = email;
        this.contrasena = contrasena;
        this.fechaRegistro = fechaRegistro;
    }

    public boolean iniciarSesion(String username, String password) {
        return (this.nombre.equalsIgnoreCase(username) || this.email.equalsIgnoreCase(username))
                && this.contrasena.equals(password);
    }


    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }

    public Date getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(Date fechaRegistro) { this.fechaRegistro = fechaRegistro; }

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
