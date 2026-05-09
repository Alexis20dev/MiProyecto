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

            System.out.println("1) Ver perfil");

            System.out.println(
                    "2) Ver temas"
            );

            System.out.println(
                    "3) Resolver ejercicios"
            );

            System.out.println(
                    "4) Ver conceptos"
            );

            System.out.println(
                    "5) Solicitar pistas"
            );

            System.out.println(
                    "6) Ver progreso"
            );

            System.out.println(
                    "7) Cerrar sesión"
            );

            System.out.print("Seleccione una opción: ");

            opcion = sc.nextInt();

            switch (opcion) {

                case 1:
                    System.out.println();
                    System.out.println("  ------------Perfil-----------  ");
                    System.out.println("\nNombre: " + estudiante.getNombre());
                    System.out.println("Usuario: " + estudiante.getUsername());
                    break;

                case 2:
                    System.out.println();
                    System.out.println("--------- Tema -----------");
                    System.out.println("\nTema Actual: " + ejercicio.getTema().getNombre());
                    break;

                case 3:
                    System.out.println();
                    System.out.println("---------Ejercicio-------------");
                    System.out.println("\n" + ejercicio.mostrarEjercicio());
                    System.out.print("Ingrese Respuesta: ");

                    double respuesta = sc.nextDouble();

                    boolean correcto =
                            estudiante.resolverEjercicio(ejercicio, respuesta);

                    estudiante
                            .verProgreso()
                            .actualizarProgreso(correcto);

                    if (correcto) {
                        System.out.println("Correcto");

                    } else {
                        System.out.println("Incorrecto");
                    }
                    break;

                case 4:
                    System.out.println();
                    System.out.println("----------- Concepto --------------");
                    estudiante.verConcepto(ejercicio.getConcepto());
                    break;

                case 5:
                    System.out.println();
                    System.out.println("----------- Pitas ---------------");
                    Pista pista = estudiante.solicitarPista(ejercicio);

                    if (pista != null) {
                        System.out.println(pista.mostrarPista());
                    }
                    break;

                case 6:
                    System.out.println();
                    System.out.println("------------ Progreso ------------- ");
                    System.out.println(estudiante.verProgreso().mostrarEstadisticas());
                    break;

                case 7:
                    System.out.println();
                    System.out.println("Sesión Finalizada");
                    break;

                default:
                    System.out.println();
                    System.out.println("Opción Inválida");
            }

        } while (opcion != 7);
    }
}