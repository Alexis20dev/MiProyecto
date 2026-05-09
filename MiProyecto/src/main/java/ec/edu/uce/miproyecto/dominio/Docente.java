package ec.edu.uce.miproyecto.dominio;

public class Docente extends Usuario {

    public Docente(String username, String password, String nombre) {
        super(username, password, nombre);
    }
    public void crearEjercicio(Ejercicio ejercicio) {
        System.out.println("Ejercicio creado");
    }
    public boolean editarEjercicio(Ejercicio ejercicio) {
        System.out.println("Ejercicio editado");
        return true;
    }
    public boolean eliminarEjercicio(Ejercicio ejercicio) {
        System.out.println("Ejercicio eliminado");
        return true;
    }
    public void revisarEjercicio(Ejercicio ejercicio) {
        System.out.println("Revisando ejercicio");
    }
}