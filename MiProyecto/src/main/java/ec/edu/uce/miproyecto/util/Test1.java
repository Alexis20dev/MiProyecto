package ec.edu.uce.miproyecto.util;

public record Test1() {

        public static void main(String[] args) {

            System.out.println(
                    Validaciones.validarNumero("123")
            );

            System.out.println(
                    Validaciones.validarNumero("abc")
            );

            System.out.println(
                    Validaciones.validarNombre("Jeremy")
            );

            System.out.println(
                    Validaciones.validarNombre("Jeremy123")
            );

            System.out.println(
                    Validaciones.validarEmail("jeremy@gmail.com")
            );

            System.out.println(
                    Validaciones.validarEmail("jeremygmail.com")
            );

            System.out.println(
                    Validaciones.validarContrasena("1234")
            );

            System.out.println(
                    Validaciones.validarContrasena("12")
            );
        }
}
