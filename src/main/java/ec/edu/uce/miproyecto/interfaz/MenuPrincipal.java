package ec.edu.uce.miproyecto.interfaz;

import ec.edu.uce.miproyecto.dao.*;
import ec.edu.uce.miproyecto.dominio.*;
import ec.edu.uce.miproyecto.enums.Genero;
import ec.edu.uce.miproyecto.util.Validaciones;
import ec.edu.uce.miproyecto.util.Consola;
import ec.edu.uce.miproyecto.dao.DAOException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Date;

public class MenuPrincipal {
    private final Scanner sc = new Scanner(System.in);
    private final InterfaceDAO usuarioDao = new UsuarioDAOFabrica().crearUsuarioDAO();

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

        try {
            boolean registrado;
            if (tipo == 1) {
                Progreso nuevoProgreso = new Progreso();
                Estudiante nuevoEstudiante = new Estudiante(nom, mail, pass, fecha, Genero.S, "Principiante", nuevoProgreso);
                registrado = usuarioDao.nuevo(nuevoEstudiante);
            } else if (tipo == 2) {
                Docente nuevoDocente = new Docente(nom, mail, pass, fecha, Genero.S, "Desarrollo de Software");
                registrado = usuarioDao.nuevo(nuevoDocente);
            } else {
                Consola.error("Tipo de usuario no válido");
                return;
            }

            if (registrado) {
                Consola.info("Usuario registrado correctamente en el sistema.");
            }
        } catch (DAOException e) {
            Consola.error(e.getMessage());
        }
    }

    public void iniciarSesion() {
        sc.nextLine();
        System.out.print("Usuario o Correo Electrónico: ");
        String credencialInput = sc.nextLine();
        System.out.print("Contraseña: ");
        String passwordInput = sc.nextLine();

        try {
            Usuario usuarioLogueado = (Usuario) usuarioDao.buscar(credencialInput);
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
                List<Ejercicio> ejercicios = Gestion.getEjercicios();

                Ejercicio ejercicio = new Ejercicio("Integral de 2x", "x^2", "Fácil");
                ejercicio.agregarPista(new Pista("Usa la regla de la potencia", 1));
                ejercicio.agregarPista(new Pista("Suma uno al exponente y divide para el nuevo exponente", 2));

                Ejercicio ejercicio1 = new Ejercicio("Resuelva la integral de e^2x", "e^2x/2", "Facil");
                ejercicio1.agregarPista(new Pista("Recuerda que la antiderivada de e^x es e^x", 1));
                ejercicio1.agregarPista(new Pista("La integral de una exponencial de base e es la misma exponencial sobre la derivada de su exponente", 2));

                ejercicios.add(ejercicio);
                ejercicios.add(ejercicio1);
                MenuUsuario menuUsuario = new MenuUsuario(estudiante, ejercicio);
                menuUsuario.mostrarMenuUsuario();               menuUsuario.mostrarMenuUsuario();

            } else if (usuarioLogueado instanceof Docente) {
                InterfaceDAO conceptoDao = new ConceptoDAOFabrica().crearConceptoDAO();
                TemaDAO temaDao = new TemaDAOMemoriaImpl();

                Docente docente = (Docente) usuarioLogueado;
                Consola.exitoLogin("DOCENTE");

                Concepto c1 = new Concepto("Integrales Directas", "Son la operación inversa y más básica del cálculo integral." +
                        " Consisten en deshacer una derivada de forma inmediata.");
                Ejercicio e1 = new Ejercicio("Integral de 2x", "x^2", "Fácil");
                Tema temaSimulado = new Tema("Calculo Integral", "\t\nEl cálculo integral es una rama de las matemáticas que estudia la acumulación de cantidades y el área bajo una curva. Funciona como el proceso inverso a la derivada y consiste en sumar infinitas secciones rectangulares infinitesimales para determinar medidas exactas");

                try {
                    conceptoDao.nuevo(c1);
                    temaDao.agregarConcepto(temaSimulado, c1);
                    temaDao.agregarEjercicio(temaSimulado, e1);
                } catch (DAOException ex) {
                }

                int opDocente;
                do {
                    Consola.menuDocente(docente.getNombre());
                    opDocente = sc.nextInt();

                    switch (opDocente) {
                        case 1:
                            System.out.println("\n--- REVISANDO ESTRUCTURA DE DATOS (UML) ---");
                            System.out.println(temaSimulado.toString());
                            if (temaSimulado.getConceptos() != null && !temaSimulado.getConceptos().isEmpty()) {
                                System.out.println("Concepto asociado: " + temaSimulado.getConceptos().get(0).getNombre());
                            }
                            break;
                        case 2:
                            InterfaceDAO ejercicioDao = new EjercicioDAOFabrica().crearEjercicioDAO();
                            System.out.print("\nIngrese el enunciado del nuevo ejercicio: ");
                            sc.nextLine();
                            String nuevoEnunciado = sc.nextLine();
                            System.out.println("\nIngrese la respuesta del ejercicio: ");
                            String respuesta = sc.nextLine();

                            Ejercicio nuevoEj = new Ejercicio(nuevoEnunciado, respuesta, "Medio");

                            try {
                                if (ejercicioDao.nuevo(nuevoEj)) {
                                    Consola.info("Ejercicio registrado con éxito");
                                }
                            } catch (DAOException ex) {
                                Consola.error("No se pudo registrar el Ejercicio: " + ex.getMessage());
                            }
                            break;
                        case 3:
                            System.out.println("Cerrando sesión de docente...");
                            break;
                        default:
                            Consola.error("Opción inválida");
                    }
                } while (opDocente != 3);
            }
        } catch (DAOException e) {
            Consola.error("Error en base de datos: " + e.getMessage());
        }
    }
}