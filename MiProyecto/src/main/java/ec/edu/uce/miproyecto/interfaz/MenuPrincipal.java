package ec.edu.uce.miproyecto.interfaz;

import ec.edu.uce.miproyecto.dominio.*;
import ec.edu.uce.miproyecto.util.Validaciones;
import ec.edu.uce.miproyecto.util.Consola; // 🚀 Importamos tu nueva clase de textos
import java.util.Scanner;
import java.util.Date;

public class MenuPrincipal {
    private final Scanner sc = new Scanner(System.in);
    private final Gestion controlador = new Gestion();

    public MenuPrincipal() {
        Progreso progresoInicial = new Progreso();
        Estudiante ePrueba = new Estudiante(1, "Jeremy", "jeremy@uce.edu.ec", "1234", new Date(), "Principiante", progresoInicial);
        Docente dPrueba = new Docente(2, "Ing. Lara", "lara@uce.edu.ec", "abcd", new Date(), "Cálculo");

        controlador.registrarUsuario(ePrueba);
        controlador.registrarUsuario(dPrueba);
    }

    public void mostrarMenuPrincipal() {
        String opcionInput; // Cambiamos a String para poder recibir CUALQUIER entrada sin que se caiga
        int opcion = 0;

        do {
            Consola.menuPrincipal();
            opcionInput = sc.next();

            if (!Validaciones.validarNumero(opcionInput)) {
                Consola.error("Entrada no válida. Por favor, ingrese solo los números 1, 2 o 3.");
                continue;
            }

            opcion = Integer.parseInt(opcionInput);

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
                    Consola.error("Opción fuera de rango. Seleccione 1, 2 o 3.");
            }
        } while (opcion != 3);
    }
    public void registrarUsuario() {
        Consola.tituloRegistro();

        String idT;
        while (true) {
            System.out.print("ID Usuario: ");
            idT = sc.next();
            if (Validaciones.validarNumero(idT)) break;
            Consola.error("Use solo números.");
        }
        int id = Integer.parseInt(idT);
        sc.nextLine();

        String nom;
        while (true) {
            System.out.print("Nombre: ");
            nom = sc.nextLine();
            if (Validaciones.validarNombre(nom)) break;
            Consola.error("Use solo letras.");
        }

        String mail;
        while (true) {
            System.out.print("Email: ");
            mail = sc.nextLine();
            if (Validaciones.validarEmail(mail)) break;
            Consola.error("Correo no válido.");
        }

        String pass;
        while (true) {
            System.out.print("Contraseña: ");
            pass = sc.nextLine();
            if (Validaciones.validarContrasena(pass)) break;
            Consola.error("Mínimo 4 caracteres.");
        }

        Consola.pedirTipoUsuario();
        int tipo = sc.nextInt();

        Date fecha = new Date();
        if (tipo == 1) {
            Progreso nuevoProgreso = new Progreso();
            Estudiante nuevoEstudiante = new Estudiante(id, nom, mail, pass, fecha, "Principiante", nuevoProgreso);
            controlador.registrarUsuario(nuevoEstudiante);
        } else {
            Docente nuevoDocente = new Docente(id, nom, mail, pass, fecha, "Desarrollo de Software");
            controlador.registrarUsuario(nuevoDocente);
        }

        Consola.info("Usuario registrado correctamente en el sistema.");
    }

    public void iniciarSesion() {
        sc.nextLine(); // Limpiar buffer
        System.out.print("Usuario o Correo Electrónico: ");
        String credencialInput = sc.nextLine();
        System.out.print("Contraseña: ");
        String passwordInput = sc.nextLine();

        // 1. Buscamos primero si el usuario existe en el sistema
        Usuario usuarioLogueado = controlador.buscarUsuarioPorCorreo(credencialInput);

        if (usuarioLogueado == null) {
            // ❌ CASO A: El correo no está registrado
            Consola.error("El correo electrónico '" + credencialInput + "' no está registrado en el sistema.");
            return; // Cortamos la ejecución aquí para que intente de nuevo
        }

        // 2. Si el usuario existe, validamos si la contraseña coincide
        if (!usuarioLogueado.getContrasena().equals(passwordInput)) {
            // ❌ CASO B: El usuario existe, pero la contraseña está mal
            Consola.error("Contraseña incorrecta para el usuario: " + usuarioLogueado.getNombre() + ". Intente de nuevo.");
            return; // Cortamos la ejecución
        }

        // 3. Si pasó los dos filtros anteriores, las credenciales son correctas
        if (usuarioLogueado instanceof Estudiante) {
            Estudiante estudiante = (Estudiante) usuarioLogueado;
            Consola.exitoLogin("ESTUDIANTE");

            Concepto concepto = new Concepto(1, "Sustitución", "Cambio de variable");
            Pista pista = new Pista(1, "Usa la regla de la potencia", 1);
            Ejercicio ejercicio = new Ejercicio(1, "Integral de 2x", "x^2", "Fácil", new Pista[]{pista});

            MenuUsuario menuUsuario = new MenuUsuario(estudiante, ejercicio);
            menuUsuario.mostrarMenuUsuario();

        } else if (usuarioLogueado instanceof Docente) {
            Docente docente = (Docente) usuarioLogueado;
            Consola.exitoLogin("DOCENTE");

            Concepto c1 = new Concepto(1, "Sustitución", "Cambio de variable");
            Ejercicio e1 = new Ejercicio(1, "Integral de 2x", "x^2", "Fácil", new Pista[0]);
            Tema temaSimulado = new Tema(1, "Integrales", "Cálculo integral", new Concepto[]{c1}, new Ejercicio[]{e1});

            int opDocente;
            do {
                Consola.menuDocente(docente.getNombre());
                opDocente = sc.nextInt();

                switch (opDocente) {
                    case 1:
                        System.out.println("\n--- REVISANDO ESTRUCTURA DE DATOS (UML) ---");
                        System.out.println(temaSimulado.toString());
                        if (temaSimulado.getConceptos() != null && temaSimulado.getConceptos().length > 0) {
                            System.out.println("Concepto asociado en el arreglo: " + temaSimulado.getConceptos()[0].getNombre());
                        }
                        break;
                    case 2:
                        System.out.print("\nIngrese el enunciado del nuevo ejercicio: ");
                        sc.nextLine();
                        String nuevoEnunciado = sc.nextLine();

                        int siguienteId = controlador.getListaItemE().size() + 1;
                        Ejercicio nuevoEj = new Ejercicio(siguienteId, nuevoEnunciado, "0", "Medio", new Pista[0]);

                        controlador.agregarEjercicio(nuevoEj);
                        Consola.info("Ejercicio registrado con éxito en el controlador central como ItemEjercicio.");
                        break;
                    case 3:
                        usuarioLogueado.cerrarSesion();
                        System.out.println("Cerrando sesión de docente...");
                        break;
                    default:
                        Consola.error("Opción inválida");
                }
            } while (opDocente != 3);
        }
    }
}