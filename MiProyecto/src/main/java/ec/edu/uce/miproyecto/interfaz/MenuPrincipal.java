package ec.edu.uce.miproyecto.interfaz;
import ec.edu.uce.miproyecto.dominio.*;
import java.util.Scanner;
public class MenuPrincipal {
    private final Scanner sc = new Scanner (System.in);

    public void mostrarMenuPrincipal() {
        int opcion;
        do {
            System.out.println("\n===== MENÚ PRINCIPAL =====");
            System.out.println("1) Iniciar sesión");
            System.out.println("2) Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    iniciarSesion();
                    break;
                case 2:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        } while (opcion != 2);
    }
    // LOGIN
    public void iniciarSesion() {
        sc.nextLine();
        System.out.print("Usuario: ");
        String username = sc.nextLine();
        System.out.print("Contraseña: ");
        String password = sc.nextLine();
        // Crear Progreso
        Progreso progreso = new Progreso();
        // ESTUDIANTE
        Estudiante estudiante = new Estudiante("paco", "1234", "Paco", progreso);
        // VALIDAR LOGIN
        boolean acceso = estudiante.iniciarSesion(username, password);
        if (acceso) {
            System.out.println("Inicio de sesión exitoso");
            // TEMA
            Tema tema = new Tema("Integrales", "Cálculo integral");
            // CONCEPTO
            Concepto concepto = new Concepto("Sustitución", "Cambio de variable", "∫2x dx");
            // EJERCICIO
            Ejercicio ejercicio = new Ejercicio("Resolver ∫2x dx", 1, 1, tema, concepto);
            // PISTA
            Pista pista = new Pista("Usa potencia", 1);
            ejercicio.agregarPista(pista);
            // ABRIR MENÚ USUARIO
            MenuUsuario menuUsuario = new MenuUsuario(estudiante, ejercicio);
            menuUsuario.mostrarMenuUsuario();
        } else {
            System.out.println("Credenciales incorrectas");
        }
    }
}