package ec.edu.uce.miproyecto.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validaciones {
    public static boolean validarNumero(String texto) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(texto);

        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }
    public static boolean validarNombre(String texto) {
        Pattern pattern = Pattern.compile("[a-zA-ZáéíóúÁÉÍÓÚ ]+");
        Matcher matcher = pattern.matcher(texto);

        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }

    //Validar para correo
    public static boolean validarEmail(String texto) {
        Pattern pattern = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
        Matcher matcher = pattern.matcher(texto);

        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }

    //Validar contraseñas
    public static boolean validarContrasena(String texto) {
        Pattern pattern = Pattern.compile(".{4,}");
        Matcher matcher = pattern.matcher(texto);

        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }
    //------------------------------------------------------------------------------------------------------
    // Validaciones del MenuUsuario
    public static boolean validarOpcionMenu(String texto) {
        Pattern pattern = Pattern.compile("[1-7]");
        Matcher matcher = pattern.matcher(texto);

        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }

    // Validacion para el ejercicio no este vacio, ni tenga espacios en blaco
    public static boolean validarRespuesta(String texto) {
        Pattern pattern = Pattern.compile(".\\S."); // La \S ayuda a evitar que el usuario unicamente ponga espacios en blanco
        Matcher matcher = pattern.matcher((texto));
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }
}