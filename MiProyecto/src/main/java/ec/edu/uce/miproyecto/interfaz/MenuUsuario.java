package ec.edu.uce.miproyecto.interfaz;

import ec.edu.uce.miproyecto.dominio.*;

import java.util.Scanner;

public class MenuUsuario {

    private Scanner sc;
    private Estudiante estudiante;
    private Ejercicio ejercicio;

    public MenuUsuario(Estudiante estudiante) {
        this.estudiante = estudiante;
        this.ejercicio = ejercicio;

        sc = new Scanner(System.in);
    }

    public void mostrarMenuUsuario() {

        int opcion;

        do {

            System.out.println(
                    "\n===== MENÚ USUARIO ====="
            );

            System.out.println(
                    "1) Ver perfil"
            );

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

            System.out.print(
                    "Seleccione una opción: "
            );

            opcion = sc.nextInt();

            switch (opcion) {

                case 1:

                    verPerfil();

                    break;

                case 2:

                    verTemas();

                    break;

                case 3:

                    resolverEjercicio();

                    break;

                case 4:

                    verConcepto();

                    break;

                case 5:

                    solicitarPista();

                    break;

                case 6:

                    verProgreso();

                    break;

                case 7:

                    cerrarSesion();

                    break;

                default:

                    System.out.println(
                            "Opción inválida"
                    );
            }

        } while (opcion != 7);
    }

    // PERFIL
    public void verPerfil() {

        System.out.println(
                "\nNombre: "
                        + estudiante.getNombre()
        );

        System.out.println(
                "Usuario: "
                        + estudiante.getUsername()
        );
    }

    // TEMAS
    public void verTemas() {

        System.out.println(
                "\nTema actual: "
                        + ejercicio.getTema().getNombre()
        );
    }

    // EJERCICIOS
    public void resolverEjercicio() {

        System.out.println(
                "\n"
                        + ejercicio.mostrarEjercicio()
        );

        System.out.print(
                "Ingrese respuesta: "
        );

        double respuesta =
                sc.nextDouble();

        boolean correcto =
                estudiante.resolverEjercicio(
                        ejercicio,
                        respuesta
                );

        estudiante
                .verProgreso()
                .actualizarProgreso(correcto);

        if (correcto) {

            System.out.println(
                    "Correcto"
            );

        } else {

            System.out.println(
                    "Incorrecto"
            );
        }
    }

    // CONCEPTOS
    public void verConcepto() {

        estudiante.verConcepto(
                ejercicio.getConcepto()
        );
    }

    // PISTAS
    public void solicitarPista() {

        Pista pista =
                estudiante.solicitarPista(
                        ejercicio
                );

        if (pista != null) {

            System.out.println(
                    pista.mostrarPista()
            );
        }
    }

    // PROGRESO
    public void verProgreso() {

        System.out.println(
                estudiante
                        .verProgreso()
                        .mostrarEstadisticas()
        );
    }

    // CERRAR SESIÓN
    public void cerrarSesion() {

        estudiante.cerrarSesion();

        System.out.println(
                "Sesión finalizada"
        );
    }
}
