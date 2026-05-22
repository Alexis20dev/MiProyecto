package ec.edu.uce.miproyecto.dominio;

import java.util.Date;

public class Estudiante extends Usuario {

    private Progreso progreso;

    // CONSTRUCTOR CORREGIDO (Arregla el error de la imagen image_cc5f03.png)
    public Estudiante(int idUsuario, String nombre, String email, String contrasena,
                      Date fechaRegistro, Progreso progreso) {
        // Pasamos los datos al padre (Usuario) en el orden correcto
        super(idUsuario, nombre, email, contrasena, fechaRegistro);
        this.progreso = progreso;
    }

    // MÉTODOS DE LÓGICA DEL ESTUDIANTE

    public Pista solicitarPista(Ejercicio ejercicio) {
        // Retorna la pista asociada al ejercicio actual
        return ejercicio.getPista();
    }

    public void verConcepto(Concepto concepto) {
        // Imprime la información del concepto (asegúrate que mostrarExplicacion() exista en Concepto)
        System.out.println("--- Concepto: " + concepto.getNombre() + " ---");
        System.out.println(concepto.getDescripcion());
    }

    public boolean resolverEjercicio(Ejercicio ejercicio, String respuesta) {
        // Compara la respuesta del usuario con la respuesta correcta sin importar mayúsculas
        boolean esCorrecto = ejercicio.getRespuesta().equalsIgnoreCase(respuesta);
        if (esCorrecto) {
            System.out.println("¡Respuesta correcta!");
        } else {
            System.out.println("Respuesta incorrecta. Inténtalo de nuevo.");
        }
        return esCorrecto;
    }

    public Progreso verProgreso() {
        return this.progreso;
    }

    // GETTERS Y SETTERS
    public Progreso getProgreso() {
        return progreso;
    }

    public void setProgreso(Progreso progreso) {
        this.progreso = progreso;
    }
}
