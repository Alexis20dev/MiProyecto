package ec.edu.uce.miproyecto.interfaz;
import ec.edu.uce.miproyecto.dominio.*;
import ec.edu.uce.miproyecto.util.Validaciones;
import java.util.Scanner;
import java.util.Date;

public class MenuPrincipal {
    private final Scanner sc = new Scanner(System.in);

    public void mostrarMenuPrincipal() {
        int opcion;
        do {
            System.out.println("\n========== FLOWTASKS ==========");
            System.out.println("1. Registrar Usuario");
            System.out.println("2. Iniciar Sesión");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opcion: ");

            opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    registrarUsuario(); // Llama al proceso de registro
                    break;
                case 2:
                    iniciarSesion(); // Llama al proceso de login
                    break;
                case 3:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        } while (opcion != 3);
    }

    public void registrarUsuario() {
        System.out.println("\n=== REGISTRO USUARIO ===");

        // Validar ID
        String idT;
        while (true) {
            System.out.print("ID Usuario: ");
            idT = sc.next();
            if (Validaciones.validarNumero(idT)) break;
            System.out.println("❌ Error: Use solo números.");
        }
        int id = Integer.parseInt(idT);
        sc.nextLine();

        // Validar Nombre
        String nom;
        while (true) {
            System.out.print("Nombre: ");
            nom = sc.nextLine();
            if (Validaciones.validarNombre(nom)) break;
            System.out.println("❌ Error: Use solo letras.");
        }

        // Validar Email
        String mail;
        while (true) {
            System.out.print("Email: ");
            mail = sc.nextLine();
            if (Validaciones.validarEmail(mail)) break;
            System.out.println("❌ Error: Correo no válido.");
        }

        // Validar Contraseña
        String pass;
        while (true) {
            System.out.print("Contraseña: ");
            pass = sc.nextLine();
            if (Validaciones.validarContrasena(pass)) break;
            System.out.println("❌ Error: Mínimo 4 caracteres.");
        }

        Date fecha = new Date();
        Usuario nuevo = new Usuario(id, nom, mail, pass, fecha, null);
        System.out.println("Usuario registrado correctamente.");
    }

    public void iniciarSesion() {
        sc.nextLine();
        System.out.print("Usuario: ");
        String username = sc.nextLine();
        System.out.print("Contraseña: ");
        String password = sc.nextLine();

        // Usuario de prueba para que puedas entrar
        Progreso progreso = new Progreso();
        InicioSesion sesion = new InicioSesion();
        Estudiante estudiante = new Estudiante(1, "Paco", "paco@uce.edu.ec", "1234", new Date(), sesion, progreso);

        if (estudiante.iniciarSesion(username, password)) {
            System.out.println("Inicio de sesión exitoso");

            // Lógica de ejercicios
            Concepto concepto = new Concepto(1, "Sustitución", "Cambio de variable");
            Pista pista = new Pista(1, "Usa la regla de la potencia", 1);
            Ejercicio ejercicio = new Ejercicio(1, "Integral de 2x", "x^2", "Fácil", null, pista);
            Tema tema = new Tema(1, "Integrales", "Cálculo integral", new Concepto[]{concepto}, new Ejercicio[]{ejercicio});
            ejercicio.setTema(tema);

            MenuUsuario menuUsuario = new MenuUsuario(estudiante, ejercicio);
            menuUsuario.mostrarMenuUsuario();
        } else {
            System.out.println("Credenciales incorrectas");
        }
    }
}