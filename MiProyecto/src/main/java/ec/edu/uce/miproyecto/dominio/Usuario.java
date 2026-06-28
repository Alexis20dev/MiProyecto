package ec.edu.uce.miproyecto.dominio;

import java.util.Date;

public abstract class Usuario {

    protected int idUsuario;
    protected String nombre;
    protected String email;
    protected String contrasena;
    protected Date fechaRegistro;
    private static int contadorId = 0;
    protected Genero genero;

    public Usuario() {
        contadorId++;
        this.idUsuario = contadorId;
        this.nombre = "Sin nombre";
        this.email = "Sin email";
        this.contrasena = "1234";
        this.fechaRegistro = new Date();
        this.genero = Genero.M;
    }

    public Usuario(String nombre, String email, String contrasena, Date fechaRegistro, Genero genero) {
        contadorId++;
        this.idUsuario = contadorId;
        this.nombre = nombre;
        this.email = email;
        this.contrasena = contrasena;
        this.fechaRegistro = fechaRegistro;
        this.genero = genero;
    }
    public Genero getGenero() { return genero; }
    public void setGenero(Genero genero) { this.genero = genero; }

    public final boolean iniciarSesion(String username, String password) {
        return (this.nombre.equalsIgnoreCase(username) || this.email.equalsIgnoreCase(username))
                && this.contrasena.equals(password);
    }

    public boolean cerrarSesion() {
        return false;
    }

    public abstract void verProgreso();

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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Usuario)) {
            return false;
        }
        Usuario otroUsuario = (Usuario) o;

        return this.idUsuario == otroUsuario.idUsuario;
    }

    @Override
    public String toString() {
        return "idUsuario=" + idUsuario +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", género=" + (genero != null ? genero.getDescripcion() : "N/A") +
                ", fechaRegistro=" + fechaRegistro;
    }
}