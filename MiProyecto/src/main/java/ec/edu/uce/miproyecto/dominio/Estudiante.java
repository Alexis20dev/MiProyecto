package ec.edu.uce.miproyecto.dominio;

public class Estudiante extends Usuario {
    private Progreso progreso;
    public Estudiante(String username, String password, String nombre, Progreso progreso) {
        super(username, password, nombre);
        this.progreso = progreso;
    }
    public Pista solicitarPista(Ejercicio ejercicio) {
        return ejercicio.mostrarPista();
    }
    public void verConcepto(Concepto concepto) {
        System.out.println(concepto.mostrarExplicacion());
    }
    public boolean resolverEjercicio(Ejercicio ejercicio, double respuesta) {
        return ejercicio.validarRespuesta(respuesta);
    }
    public Progreso verProgreso() {
        return progreso;
    }
}
