package ec.edu.uce.miproyecto.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validaciones {

    public static boolean validarNumero(String texto) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(texto);
        return matcher.matches();
    }

    public static boolean validarNombre(String texto) {
        Pattern pattern = Pattern.compile("[a-zA-ZáéíóúÁÉÍÓÚ ]+");
        Matcher matcher = pattern.matcher(texto);
        return matcher.matches();
    }

    // Validar para correo
    public static boolean validarEmail(String texto) {
        Pattern pattern = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
        Matcher matcher = pattern.matcher(texto);
        return matcher.matches();
    }

    // Validar contraseñas
    public static boolean validarContrasena(String texto) {
        Pattern pattern = Pattern.compile(".{4,}");
        Matcher matcher = pattern.matcher(texto);
        return matcher.matches();
    }

    public static boolean validarOpcionMenu(String texto) {
        Pattern pattern = Pattern.compile("[1-8]");
        Matcher matcher = pattern.matcher(texto);
        return matcher.matches();
    }

    public static boolean validarRespuesta(String texto) {
        Pattern pattern = Pattern.compile("^.*\\S.*$");
        Matcher matcher = pattern.matcher(texto);
        return matcher.matches();
    }

}