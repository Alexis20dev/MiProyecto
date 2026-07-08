package ec.edu.uce.miproyecto.interfaz;

import ec.edu.uce.miproyecto.dao.EjercicioDAO;
import ec.edu.uce.miproyecto.dao.EjercicioDAOMemoriaImpl;
import ec.edu.uce.miproyecto.dao.UsuarioDAO;
import ec.edu.uce.miproyecto.dao.UsuarioDAOMemoriaImpl;
import ec.edu.uce.miproyecto.dominio.*;
import ec.edu.uce.miproyecto.enums.Genero;
import ec.edu.uce.miproyecto.util.Validaciones;
import ec.edu.uce.miproyecto.util.Consola;
import java.util.Scanner;
import java.util.Date;

public class MenuPrincipal {
    private final Scanner sc = new Scanner(System.in);
    private UsuarioDAO usuarioDao=new UsuarioDAOMemoriaImpl();
    public MenuPrincipal() {
    }

    public void mostrarMenuPrincipal() {
        String opcionInput;
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
        boolean registrado;

        if (tipo == 1) {
            Progreso nuevoProgreso = new Progreso();
            Estudiante nuevoEstudiante = new Estudiante(nom, mail, pass, fecha, Genero.S, "Principiante", nuevoProgreso);
            registrado = usuarioDao.registrar(nuevoEstudiante);
        } else if (tipo == 2) {
            Docente nuevoDocente = new Docente(nom, mail, pass, fecha, Genero.S, "Desarrollo de Software");
            registrado = usuarioDao.registrar(nuevoDocente);
        } else {
            Consola.error("Tipo de usuario no válido");
            return;
        }

        if (registrado) {
            Consola.info("Usuario registrado correctamente en el sistema.");
        } else {
            Consola.error("No se pudo registrar el usuario debido a que ya existe.");
        }
    }

    public void iniciarSesion() {
        sc.nextLine();
        System.out.print("Usuario o Correo Electrónico: ");
        String credencialInput = sc.nextLine();
        System.out.print("Contraseña: ");
        String passwordInput = sc.nextLine();

        Usuario usuarioLogueado = usuarioDao.buscar(credencialInput);

        if (usuarioLogueado == null) {
            Consola.error("El correo electrónico '" + credencialInput + "' no está registrado en el sistema.");
            return;
        }

        if (!usuarioLogueado.getContrasena().equals(passwordInput)) {
            Consola.error("Contraseña incorrecta para el usuario: " + usuarioLogueado.getNombre() + ". Intente de nuevo.");
            return;
        }

        if (usuarioLogueado instanceof Estudiante) {
            Estudiante estudiante = (Estudiante) usuarioLogueado;
            Consola.exitoLogin("ESTUDIANTE");

            Pista pista = new Pista("Usa la regla de la potencia", 1);
            Ejercicio ejercicio = new Ejercicio("Integral de 2x", "x^2", "Fácil");

            MenuUsuario menuUsuario = new MenuUsuario(estudiante, ejercicio);
            menuUsuario.mostrarMenuUsuario();

        } else if (usuarioLogueado instanceof Docente) {
            Docente docente = (Docente) usuarioLogueado;
            Consola.exitoLogin("DOCENTE");

            Concepto c1 = new Concepto("Sustitución", "Cambio de variable");
            Ejercicio e1 = new Ejercicio("Integral de 2x", "x^2", "Fácil");

            Tema temaSimulado = new Tema("Integrales", "Cálculo integral", new Concepto[]{c1}, new Ejercicio[]{e1});

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
                        EjercicioDAO ejercicioDao = new EjercicioDAOMemoriaImpl();
                        System.out.print("\nIngrese el enunciado del nuevo ejercicio: ");
                        sc.nextLine();
                        String nuevoEnunciado = sc.nextLine();

                        Ejercicio nuevoEj = new Ejercicio(nuevoEnunciado, e1.getRespuesta(), "Medio");

                        if (usuarioDao instanceof UsuarioDAOMemoriaImpl) {
                            ((EjercicioDAOMemoriaImpl) ejercicioDao).nuevo(nuevoEj);
                        }
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