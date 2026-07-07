package ec.edu.uce.miproyecto.dominio;

import ec.edu.uce.miproyecto.enums.Genero;

import java.util.Date;

public abstract class Usuario {

    protected final int idUsuario;
    protected String nombre;
    protected String email;
    protected String contrasena;
    protected Date fechaRegistro;
    private static int contadorId = 0;
    protected Genero genero;

    public Usuario() {
        this.idUsuario = contadorId++;
        this.nombre = "Sin nombre";
        this.email = "Sin email";
        this.contrasena = "Sin contraseña";
        this.fechaRegistro = new Date();
        this.genero = Genero.S;
    }

    public Usuario(String nombre, String email, String contrasena, Date fechaRegistro, Genero genero) {
        this.idUsuario = contadorId++;
        this.nombre = nombre;
        this.email = email;
        this.contrasena = contrasena;
        this.fechaRegistro = fechaRegistro;
        this.genero = genero;
    }

    public Usuario(Usuario usuario) {
        this.idUsuario= usuario.idUsuario;
        this.nombre = usuario.nombre;
        this.email = usuario.email;
        this.contrasena = usuario.contrasena;
        this.fechaRegistro = usuario.fechaRegistro;
        this.genero = usuario.genero;
    }

    public final boolean iniciarSesion(String username, String password) {
        return (this.nombre.equalsIgnoreCase(username) || this.email.equalsIgnoreCase(username))
                && this.contrasena.equals(password);
    }

    public boolean cerrarSesion() {
        return false;
    }

    public abstract void verProgreso();

    public int  getIdUsuario() {
        return idUsuario;
    }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) {
        if(nombre != null && !nombre.equals("")){
        this.nombre = nombre;
        }
    }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }

    public Date getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(Date fechaRegistro) { this.fechaRegistro = fechaRegistro; }


//    @Override
//    public boolean equals(Object o) {
//        boolean resp=false;
//        if (o instanceof Usuario) {
//            Usuario u= ((Usuario) o);
//            if(this.nombre.equals(u.getNombre()))
//                return true;
//        }
//        return resp;
//    }
//
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