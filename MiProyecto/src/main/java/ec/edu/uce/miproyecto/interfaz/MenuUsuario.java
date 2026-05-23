package ec.edu.uce.miproyecto.interfaz;

import ec.edu.uce.miproyecto.dominio.*;
import java.util.Scanner;

public class MenuUsuario {
    private final Scanner sc = new Scanner(System.in);
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
            System.out.println("2) Ver temas");
            System.out.println("3) Resolver ejercicios");
            System.out.println("4) Ver conceptos");
            System.out.println("5) Solicitar pistas");
            System.out.println("6) Ver progreso");
            System.out.println("7) Cerrar sesión");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("\n  ------------ Perfil -----------  ");
                    System.out.println("Nombre: " + estudiante.getNombre());
                    System.out.println("Email: " + estudiante.getEmail());
                    System.out.println("ID Usuario: " + estudiante.getIdUsuario());
                    // 🚀 AJUSTADO: Tu diagrama usa el atributo "nivel"
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
                    System.out.print("Ingrese Respuesta: ");
                    sc.nextLine(); // Limpiar el buffer
                    String respuestaUsuario = sc.nextLine();

                    if (ejercicio.getRespuesta().equalsIgnoreCase(respuestaUsuario.trim())) {
                        System.out.println("¡Resultado Correcto! 🎉");
                        // 🚀 AJUSTADO: Incremento directo usando el objeto Progreso si está disponible
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
                        System.out.println("Estado actual del progreso: " + estudiante.getProgreso().getEstado());
                    } else {
                        System.out.println("No se ha inicializado el progreso de este estudiante.");
                    }
                    break;

                case 7:
                    System.out.println("\nSesión Finalizada. ¡Regresa pronto!");
                    break;

                default:
                    System.out.println("\nOpción Inválida");
            }
        } while (opcion != 7);
    }
}