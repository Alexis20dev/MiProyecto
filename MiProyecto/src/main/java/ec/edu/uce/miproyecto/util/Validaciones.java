package ec.edu.uce.miproyecto.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validaciones {
        // Validar que solo sean números
        public static boolean validarNumero(String texto) {
            Pattern pattern = Pattern.compile("\\d+");
            Matcher matcher = pattern.matcher(texto);

            if (matcher.matches()) {
                return true;
            } else {
                return false;
            }
        }

        // Validar que solo sean letras
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
    }

