package ec.edu.uce.miproyecto.util;

public class Consola {

    public static void menuPrincipal() {
        System.out.println("\n========== MATHFLOW ==========");
        System.out.println("1. Registrar Usuario");
        System.out.println("2. Iniciar Sesión");
        System.out.println("3. Salir");
        System.out.print("Seleccione una opción: ");
    }

    public static void tituloRegistro() {
        System.out.println("\n=== REGISTRO USUARIO ===");
    }

    public static void pedirTipoUsuario() {
        System.out.println("Tipo de Usuario: 1) Estudiante  2) Docente");
        System.out.print("Seleccione el tipo: ");
    }

    public static void menuDocente(String nombreDocente) {
        System.out.println("\n===== BIENVENIDO DOCENTE: " + nombreDocente + " =====");
        System.out.println("1) Revisar Temas y Arreglos de Conceptos");
        System.out.println("2) Simular Crear Ejercicio");
        System.out.println("3) Cerrar Sesión");
        System.out.print("Seleccione una opción: ");
    }

    // Centralización de alertas rápidas
    public static void error(String mensaje) {
        System.out.println("❌ Error: " + mensaje);
    }

    public static void info(String mensaje) {
        System.out.println("📢 [Sistema]: " + mensaje);
    }

    public static void exitoLogin(String rol) {
        System.out.println("\n🔑 ¡Inicio de sesión exitoso como " + rol + "!");
    }
}