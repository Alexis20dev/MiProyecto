package ec.edu.uce.miproyecto.dominio;

public class Concepto {
    private String titulo;
    private String explicacion;
    private String ejemplo;
    public Concepto(String titulo, String explicacion, String ejemplo) {
        this.titulo = titulo;
        this.explicacion = explicacion;
        this.ejemplo = ejemplo;
    }
    public String mostrarExplicacion() {
        return explicacion;
    }
    public String mostrarEjemplo() {
        return ejemplo;
    }
    public String getTitulo() {
        return titulo;
    }
}