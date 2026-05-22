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
                    registrarUsuario();
                    break;
                case 2:
                    iniciarSesion();
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

        String idT;
        while (true) {
            System.out.print("ID Usuario: ");
            idT = sc.next();
            if (Validaciones.validarNumero(idT)) break;
            System.out.println("❌ Error: Use solo números.");
        }
        int id = Integer.parseInt(idT);
        sc.nextLine();

        String nom;
        while (true) {
            System.out.print("Nombre: ");
            nom = sc.nextLine();
            if (Validaciones.validarNombre(nom)) break;
            System.out.println("❌ Error: Use solo letras.");
        }

        String mail;
        while (true) {
            System.out.print("Email: ");
            mail = sc.nextLine();
            if (Validaciones.validarEmail(mail)) break;
            System.out.println("❌ Error: Correo no válido.");
        }

        String pass;
        while (true) {
            System.out.print("Contraseña: ");
            pass = sc.nextLine();
            if (Validaciones.validarContrasena(pass)) break;
            System.out.println("❌ Error: Mínimo 4 caracteres.");
        }

        Date fecha = new Date();
        Usuario nuevo = new Usuario(id, nom, mail, pass, fecha);
        System.out.println("Usuario registrado correctamente.");
    }

    public void iniciarSesion() {
        sc.nextLine();
        System.out.print("Usuario: ");
        String username = sc.nextLine();
        System.out.print("Contraseña: ");
        String password = sc.nextLine();

        Progreso progreso = new Progreso();
        Estudiante estudiantePrueba = new Estudiante(1, "Paco", "paco@uce.edu.ec", "1234", new Date(), progreso);
        Docente docentePrueba = new Docente(2, "Ing. Lara", "lara@uce.edu.ec", "abcd", new Date());

        if (estudiantePrueba.iniciarSesion(username, password)) {
            System.out.println("\n🔑 ¡Inicio de sesión exitoso como ESTUDIANTE!");

            Concepto concepto = new Concepto(1, "Sustitución", "Cambio de variable");
            Pista pista = new Pista(1, "Usa la regla de la potencia", 1);
            Ejercicio ejercicio = new Ejercicio(1, "Integral de 2x", "x^2", "Fácil", null, pista);
            Tema tema = new Tema(1, "Integrales", "Cálculo integral", new Concepto[]{concepto}, new Ejercicio[]{ejercicio});
            ejercicio.setTema(tema);

            MenuUsuario menuUsuario = new MenuUsuario(estudiantePrueba, ejercicio);
            menuUsuario.mostrarMenuUsuario();

        } else if (docentePrueba.iniciarSesion(username, password)) {
            System.out.println("\n🔑 ¡Inicio de sesión exitoso como DOCENTE!");

            // Datos iniciales para que el docente tenga algo que revisar en sus arreglos
            Concepto c1 = new Concepto(1, "Sustitución", "Cambio de variable");
            Ejercicio e1 = new Ejercicio(1, "Integral de 2x", "x^2", "Fácil", null, null);
            Tema temaSimulado = new Tema(1, "Integrales", "Cálculo integral", new Concepto[]{c1}, new Ejercicio[]{e1});

            int opDocente;
            do {
                System.out.println("\n===== BIENVENIDO DOCENTE: " + docentePrueba.getNombre() + " =====");
                System.out.println("1) Revisar Temas y Arreglos de Conceptos");
                System.out.println("2) Simular Crear Ejercicio");
                System.out.println("3) Cerrar Sesión");
                System.out.print("Seleccione una opción: ");
                opDocente = sc.nextInt();

                switch (opDocente) {
                    case 1:
                        System.out.println("\n--- REVISANDO ESTRUCTURA DE DATOS (UML) ---");
                        // Usamos el toString que analiza los arreglos de conceptos
                        System.out.println(temaSimulado.toString());
                        System.out.println("Concepto asociado en el arreglo: " + temaSimulado.getConceptos()[0].getNombre());
                        break;
                    case 2:
                        System.out.print("\nIngrese el enunciado del nuevo ejercicio: ");
                        sc.nextLine(); // Limpiar buffer
                        String nuevoEnunciado = sc.nextLine();
                        Ejercicio nuevoEj = new Ejercicio(2, nuevoEnunciado, "0", "Medio", null, null);
                        docentePrueba.crearEjercicio(nuevoEj); // Llama al método de la clase Docente
                        break;
                    case 3:
                        System.out.println("Cerrando sesión de docente...");
                        break;
                    default:
                        System.out.println("Opción inválida");
                }
            } while (opDocente != 3);

        } else {
            System.out.println("❌ Credenciales incorrectas. Intente de nuevo.");
        }
    }
}