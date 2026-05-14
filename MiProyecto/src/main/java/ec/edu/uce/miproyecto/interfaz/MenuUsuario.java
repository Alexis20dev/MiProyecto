package ec.edu.uce.miproyecto.interfaz;

import ec.edu.uce.miproyecto.dominio.*;
import java.util.Scanner;

public class MenuUsuario {
    private Scanner sc = new Scanner(System.in);
    private final Estudiante estudiante;
    private final Ejercicio ejercicio;

    public MenuUsuario(Estudiante estudiante, Ejercicio ejercicio) {
        this.estudiante = estudiante;
        this.ejercicio = ejercicio;
    }

    public void mostrarMenuUsuario() {
        int opcion;
        do {
            System.out.println("\n===== MENÚ USUARIO =====");
            System.out.println("1) Ver perfil\n2) Ver temas\n3) Resolver ejercicios\n4) Ver conceptos\n5) Solicitar pistas\n6) Ver progreso\n7) Cerrar sesión");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("\n  ------------Perfil-----------  ");
                    System.out.println("Nombre: " + estudiante.getNombre());
                    System.out.println("Usuario: " + estudiante.getEmail());                    break;

                case 2:
                    System.out.println("\n--------- Tema -----------");
                    // Asegúrate de que Ejercicio tenga el método getTema()
                    System.out.println("Tema Actual: " + ejercicio.getTema().getNombre());
                    break;

                case 3:
                    System.out.println("\n---------Ejercicio-------------");
                    ejercicio.mostrarEjercicio(); // Se llama directo porque es void
                    System.out.print("Ingrese Respuesta: ");

                    String respuesta = sc.next(); // Cambiado de double a String según diagrama

                    boolean correcto = estudiante.resolverEjercicio(ejercicio, respuesta);

                    estudiante.verProgreso().actualizarProgreso(correcto);

                    if (correcto) {
                        System.out.println("¡Resultado Correcto!");
                    } else {
                        System.out.println("Resultado Incorrecto");
                    }
                    break;

                case 4:
                    System.out.println("\n----------- Concepto --------------");
                    if (ejercicio.getTema().getConceptos().length > 0) {
                        estudiante.verConcepto(ejercicio.getTema().getConceptos()[0]);
                    }
                    break;

                case 5:
                    System.out.println("\n----------- Pistas ---------------");
                    Pista pista = estudiante.solicitarPista(ejercicio);
                    if (pista != null) {
                        pista.mostrarPista();
                    }
                    break;

                case 6:
                    System.out.println("\n------------ Progreso ------------- ");
                    System.out.println(estudiante.verProgreso().toString());
                    break;

                case 7:
                    System.out.println("\nSesión Finalizada");
                    break;

                default:
                    System.out.println("\nOpción Inválida");
            }
        } while (opcion != 7);
    }
}