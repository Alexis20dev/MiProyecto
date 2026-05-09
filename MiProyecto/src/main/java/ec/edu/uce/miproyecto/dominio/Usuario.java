package ec.edu.uce.miproyecto.dominio;
import java.util.Date;
public class Usuario {

    private int idUsuario;
    private String nombre;
    private String email;
    private String contrasena;
    private Date fechaRegistro;

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", contrasena='" + contrasena + '\'' +
                ", fechaRegistro=" + fechaRegistro +
                ", inicioSesion=" + inicioSesion +
                '}';
    }

    private InicioSesion inicioSesion;

    public InicioSesion getInicioSesion() {
        return inicioSesion;
    }

    public void setInicioSesion(InicioSesion inicioSesion) {
       this.inicioSesion = inicioSesion;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    public Usuario() {
    }
    public Usuario(String email, String contrasena, Date fechaRegistro, InicioSesion inicioSesion, String nombre, int idUsuario){
        this.email = email;
        this.contrasena = contrasena;
        this.fechaRegistro = fechaRegistro;
       this.inicioSesion = inicioSesion;
        this.nombre = nombre;
        this.idUsuario = idUsuario;
    }
}
