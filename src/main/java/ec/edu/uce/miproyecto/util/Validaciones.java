package ec.edu.uce.miproyecto.util;

import java.util.regex.Pattern;

public class Validaciones {

    // Validar números para menús
    public static boolean validarNumero(String entrada) {
        return entrada != null && entrada.trim().matches("^\\d+$");
    }

    public static boolean validarOpcionMenu(String entrada) {
        return entrada != null && entrada.trim().matches("[1-8]");
    }

    public static boolean validarRespuesta(String respuesta) {
        return respuesta != null && !respuesta.trim().isEmpty();
    }

    // Validar campos de texto y usuarios
    public static boolean validarNombre(String nombre) {
        return nombre != null && Pattern.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$", nombre.trim());
    }

    public static boolean validarEmail(String email) {
        if (email == null) return false;
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return Pattern.matches(regex, email.trim().toLowerCase());
    }

    public static boolean validarCorreoUCE(String correo) {
        if (correo == null) return false;
        String regexUCE = "^[a-zA-Z0-9._%+-]+@uce\\.edu\\.ec$";
        return Pattern.matches(regexUCE, correo.trim().toLowerCase());
    }

    public static boolean validarContrasena(String contrasena) {
        return contrasena != null && contrasena.trim().length() >= 4;
    }
}