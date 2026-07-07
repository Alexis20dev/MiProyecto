package ec.edu.uce.miproyecto.interfaz;

import ec.edu.uce.miproyecto.dominio.*;
import java.util.Scanner;
import ec.edu.uce.miproyecto.util.Validaciones;

public class MenuUsuario {
    private final Scanner sc = new Scanner(System.in);
    private final Estudiante estudiante;
    private final Ejercicio ejercicio;

    public MenuUsuario(Estudiante estudiante, Ejercicio ejercicio) {
        this.estudiante = estudiante;
        this.ejercicio = ejercicio;
    }

    public void mostrarMenuUsuario() {
        int opcion = 0;
        String entradaOpcion;

        do {
            System.out.println("\n===== MENÚ USUARIO =====");
            System.out.println("1) Ver perfil");
            System.out.println("2) Ver temas");
            System.out.println("3) Resolver ejercicios");
            System.out.println("4) Ver conceptos");
            System.out.println("5) Solicitar pistas");
            System.out.println("6) Ver progreso");
            System.out.println("7) Cerrar sesión");

            // 1. VALIDACIÓN DEL MENÚ
            System.out.print("Seleccione una opción: ");
            entradaOpcion = sc.nextLine().trim();

            if (!Validaciones.validarOpcionMenu(entradaOpcion)) {
                System.out.println("⚠️ Error: Debe ingresar un número del 1 al 7.");
                continue; // Vuelve al inicio del do-while sin ejecutar el switch
            }

            // Si pasó la validación, lo convertimos a int (es 100% seguro hacerlo aquí)
            opcion = Integer.parseInt(entradaOpcion);

            switch (opcion) {
                case 1:
                    System.out.println("\n  ------------ Perfil -----------  ");
                    System.out.println("Nombre: " + estudiante.getNombre());
                    System.out.println("Email: " + estudiante.getEmail());
                    System.out.println("ID Usuario: " + estudiante.getIdUsuario());
                    System.out.println("Nivel: " + estudiante.getNivel());
                    break;

                case 2:
                    System.out.println("\n--------- Tema -----------");
                    System.out.println("Tema de Estudio: Cálculo Integral / Derivadas");
                    System.out.println("Dificultad del Ejercicio: " + ejercicio.getDificultad());
                    break;

                case 3:
                    System.out.println("\n--------- Ejercicio -------------");
                    System.out.println("Enunciado: " + ejercicio.getEnunciado());

                    String respuestaUsuario;
                    boolean respuestaValida = false;

                    // VALIDACIÓN
                    do {
                        System.out.print("Ingrese Respuesta: ");
                        respuestaUsuario = sc.nextLine().trim();

                        if (Validaciones.validarRespuesta(respuestaUsuario)) {
                            respuestaValida = true;
                        } else {
                            System.out.println("⚠️ Error: La respuesta no puede estar vacía.");
                        }
                    } while (!respuestaValida);

                    if (ejercicio.getRespuesta().equalsIgnoreCase(respuestaUsuario)) {
                        System.out.println("¡Resultado Correcto! 🎉");
                        if (estudiante.getProgreso() != null) {
                            System.out.println("¡Buen trabajo! Ejercicio completado.");
                        }
                    } else {
                        System.out.println("❌ Resultado Incorrecto. ¡Sigue intentando!");
                    }
                    break;

                case 4:
                    System.out.println("\n----------- Concepto --------------");
                    System.out.println("Concepto Clave: Integración por partes.");
                    System.out.println("Descripción: Método que permite calcular la integral de un producto de funciones.");
                    break;
                case 5:
                    System.out.println("\n----------- Pistas ---------------");
                    if (ejercicio.getPistas() != null && ejercicio.getPistas().length > 0) {
                        System.out.println("💡 Pista disponible: " + ejercicio.getPistas()[0].getDescripcion());
                    } else {
                        System.out.println("📢 No hay pistas asignadas a este ejercicio todavía.");
                    }
                    break;

                case 6:
                    System.out.println("\n------------ Progreso ------------- ");
                    if (estudiante.getProgreso() != null) {
                        System.out.println("EstadoTema actual del progreso: " + estudiante.getProgreso().getEstado());
                    } else {
                        System.out.println("No se ha inicializado el progreso de este estudiante.");
                    }
                    break;

                case 7:
                    System.out.println("\nSesión Finalizada. ¡Regresa pronto!");
                    break;
            }
        } while (opcion != 7);
    }
}