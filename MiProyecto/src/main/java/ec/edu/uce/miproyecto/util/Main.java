package ec.edu.uce.miproyecto.util;
import ec.edu.uce.miproyecto.dominio.*;
import ec.edu.uce.miproyecto.interfaz.MenuPrincipal;
public class Main {
    public static void main(String[] args) {

        MenuPrincipal menu = new MenuPrincipal();
        menu.mostrarMenu();
        // TEMA
        Tema tema = new Tema("Integrales", "Cálculo integral");
        // CONCEPTO
        Concepto concepto = new Concepto("Sustitución", "Cambio de variable", "∫2x dx");
        // EJERCICIO
        Ejercicio ejercicio = new Ejercicio("Integral de 2x", 1, 1, tema, concepto);
        // PISTA
        Pista pista = new Pista("Usa potencia", 1);
        ejercicio.agregarPista(pista);
        // PROGRESO
        Progreso progreso =
                new Progreso();
        // ESTUDIANTE
        Estudiante estudiante = new Estudiante("roberth", "1234", "Roberth", progreso);
        // LOGIN
        boolean acceso = estudiante.iniciarSesion("roberth", "1234");
        System.out.println(acceso);
        // RESOLVER
        boolean correcto = estudiante.resolverEjercicio(ejercicio, 1);
        System.out.println(correcto);
        // ACTUALIZAR PROGRESO
        progreso.actualizarProgreso(correcto);
        // MOSTRAR ESTADÍSTICAS
        System.out.println(
                progreso.mostrarEstadisticas()
        );
    }
}


