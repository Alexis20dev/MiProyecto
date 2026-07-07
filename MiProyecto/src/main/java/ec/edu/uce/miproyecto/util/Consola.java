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
    public static void mostrarProgresoEstudiante(String nombre, String nivel, String estado, int puntaje, int tiempo, boolean tieneProgreso) {
        System.out.println("\n----------------------------------------");
        System.out.println("     PROGRESO DEL ESTUDIANTE: " + nombre);
        System.out.println("----------------------------------------");
        System.out.println("• Nivel de aprendizaje: " + nivel);

        if (tieneProgreso) {
            System.out.println("• EstadoTema del módulo: " + estado);
            System.out.println("• Puntaje acumulado: " + puntaje + " pts");
            System.out.println("• Tiempo invertido: " + tiempo + " minutos");
        } else {
            System.out.println(" No se registran datos de progreso todavía.");
        }
    }
    public static void mostrarProgresoDocente(String nombre, String especialidad) {
        System.out.println("\n----------------------------------------");
        System.out.println(" 👨‍🏫 VISTA DE CONTROL - DOCENTE: " + nombre);
        System.out.println("----------------------------------------");
        System.out.println("• Especialidad a cargo: " + especialidad);
        System.out.println("• Acceso: Autorizado para la revisión y gestión de reactivos.");
    }
}