package ec.edu.uce.miproyecto.interfaz;

import ec.edu.uce.miproyecto.dominio.*;
import ec.edu.uce.miproyecto.util.Validaciones;
import ec.edu.uce.miproyecto.util.Consola;
import java.util.Scanner;
import java.util.Date;

public class MenuPrincipal {
    private final Scanner sc = new Scanner(System.in);
    private final Gestion controlador = new Gestion();

    public MenuPrincipal() {
        inicializar();
    }

    public void inicializar() {
        Progreso p1 = new Progreso();
        Progreso p2 = new Progreso();

        Estudiante e1 = new Estudiante("Jeremy", "jeremy@uce.edu.ec", "1234", new Date(), Genero.M, "Principiante", p1);
        Docente d1 = new Docente("Ing. Lara", "lara@uce.edu.ec", "abcd", new Date(), Genero.M, "Cálculo");
        Estudiante e2 = new Estudiante("Carlos", "carlos@uce.edu.ec", "5678", new Date(), Genero.M, "Intermedio", p2);
        Docente d2 = new Docente("Dra. Mena", "mena@uce.edu.ec", "9876", new Date(), Genero.F, "Álgebra");
        Estudiante e3 = new Estudiante("Daniela", "daniela@uce.edu.ec", "0000", new Date(), Genero.F, "Avanzado", new Progreso());

        Usuario[] loteInicial = new Usuario[] { e1, d1, e2, d2, e3 };
        controlador.registrarUsuario(loteInicial);
    }

    public void mostrarMenuPrincipal() {
        String opcionInput;
        int opcion = 0;

        do {
            System.out.println("\n----------------------------------------------");
            System.out.println("│                  MATHFLOW                    │");
            System.out.println("│         SISTEMA ACADÉMICO TUTOR - UCE        │");
            System.out.println("  1. Registrar nuevo Usuario (Interactivo)   ");
            System.out.println("  2. Iniciar Sesión                           ");
            System.out.println("  3. [ADMIN] Ver todos los Usuarios (Read)    ");
            System.out.println("  4. [ADMIN] Modificar Usuario (Update)       ");
            System.out.println("  5. Salir del Sistema                        ");
            System.out.println("");
            System.out.print("➤ Seleccione una opción: ");
            opcionInput = sc.next();

            if (!Validaciones.validarNumero(opcionInput)) {
                System.out.println("\n Entrada no válida. Ingrese un número del 1 al 5.");
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
                    System.out.println("\n=== LISTADO GENERAL DE USUARIOS ===");
                    System.out.print(controlador.consultarUsuarios());
                    System.out.println("===================================\n");
                    break;
                case 4:
                    interfazEditarUsuario();
                    break;
                case 5:
                    System.out.println("\n Saliendo del sistema...");
                    break;
                default:
                    System.out.println("\n Opción fuera de rango (1-5).");
            }
        } while (opcion != 5);
    }

    public void registrarUsuario() {
        System.out.println("\n-------------------------------------------------");
        System.out.println("│             REGISTRO DE USUARIO              │");
        System.out.println("---------------------------------------------------");
        sc.nextLine(); // Limpiar búfer

        String nom;
        while (true) {
            System.out.print("  ▪ Nombre: ");
            nom = sc.nextLine();
            if (Validaciones.validarNombre(nom)) break;
            System.out.println("    ⚠️ Use solo letras.");
        }

        String mail;
        while (true) {
            System.out.print("  ▪ Email: ");
            mail = sc.nextLine();
            if (Validaciones.validarEmail(mail)) break;
            System.out.println("   ️ Correo no válido.");
        }

        Usuario temporal = new Estudiante(nom, mail, "temp", new Date(), Genero.M, "Principiante", null);
        if (controlador.validarDuplicado(temporal)) {
            System.out.println("Registro cancelado: Los datos ingresados ya existen.");
            return;
        }

        String pass;
        while (true) {
            System.out.print("  ▪ Contraseña: ");
            pass = sc.nextLine();
            if (Validaciones.validarContrasena(pass)) break;
            System.out.println("    ⚠️ Mínimo 4 caracteres.");
        }

        System.out.println("  ▪ Seleccione el Género (1: Masculino / 2: Femenino): ");
        int opGen = sc.nextInt();
        Genero generoSeleccionado = (opGen == 2) ? Genero.F : Genero.M;

        System.out.println("  ▪ Tipo de Usuario (1: Estudiante / 2: Docente): ");
        int tipo = sc.nextInt();
        sc.nextLine();

        Date fecha = new Date();
        if (tipo == 1) {
            Progreso nuevoProgreso = new Progreso();
            Estudiante nuevoEstudiante = new Estudiante(nom, mail, pass, fecha, generoSeleccionado, "Principiante", nuevoProgreso);
            controlador.registrarUsuario(nuevoEstudiante);
        } else {
            Docente nuevoDocente = new Docente(nom, mail, pass, fecha, generoSeleccionado, "Desarrollo de Software");
            controlador.registrarUsuario(nuevoDocente);
        }

        System.out.println("\nn¡Usuario registrado correctamente con su género asignado!");
    }

    private void interfazEditarUsuario() {
        System.out.println("\n=== SECCIÓN DE EDICIÓN (CRUD UPDATE) ===");
        System.out.print(controlador.consultarUsuarios());
        if(controlador.getNumUsuarios() == 0) return;

        System.out.print("➤ Ingrese la posición (índice) a editar (Ej: 0 para el primero): ");
        int pos = sc.nextInt();
        sc.nextLine();

        if (pos >= 0 && pos < controlador.getNumUsuarios()) {
            System.out.print("  ▪ Nuevo Nombre: ");
            String nuevoNom = sc.nextLine();
            System.out.print("  ▪ Nuevo Email: ");
            String nuevoMail = sc.nextLine();
            System.out.print("  ▪ Nueva Contraseña: ");
            String nuevaPass = sc.nextLine();

            boolean exito = controlador.editarUsuario(pos, nuevoNom, nuevoMail, nuevaPass);
            if (exito) {
                System.out.println("\n¡Usuario actualizado correctamente en el arreglo!");
            } else {
                System.out.println("\nNo se pudo actualizar el registro.");
            }
        } else {
            System.out.println("\n Posición inválida.");
        }
    }

    public void iniciarSesion() {
        System.out.println("\n-------------------------------------------------");
        System.out.println("(              INICIO DE SESIÓN                   ");
        System.out.println("----------------------------------------------------");
        sc.nextLine();
        System.out.print("  ▪ Usuario o Email: ");
        String credencialInput = sc.nextLine();
        System.out.print("  ▪ Contraseña: ");
        String passwordInput = sc.nextLine();

        Usuario usuarioLogueado = controlador.buscarUsuarioPorCorreo(credencialInput);

        if (usuarioLogueado == null) {
            System.out.println("\nError: Credenciales no encontradas.");
            return;
        }

        if (!usuarioLogueado.getContrasena().equals(passwordInput)) {
            System.out.println("\nError: Contraseña incorrecta.");
            return;
        }

        if (usuarioLogueado instanceof Estudiante) {
            Estudiante estudiante = (Estudiante) usuarioLogueado;
            System.out.println("\nACCESO CONCEDIDO | ROL: ESTUDIANTE (" + estudiante.getNombre() + ")");

            Concepto concepto = new Concepto(1, "Sustitución", "Cambio de variable");
            Pista pista = new Pista(1, "Usa la regla de la potencia", 1);
            Ejercicio ejercicio = new Ejercicio(1, "Integral de 2x", "x^2", "Fácil", new Pista[]{pista});

            MenuUsuario menuUsuario = new MenuUsuario(estudiante, ejercicio);
            menuUsuario.mostrarMenuUsuario();

        } else if (usuarioLogueado instanceof Docente) {
            Docente docente = (Docente) usuarioLogueado;
            System.out.println("\nACCESO CONCEDIDO | ROL: DOCENTE (" + docente.getNombre() + ")");

            Concepto c1 = new Concepto(1, "Sustitución", "Cambio de variable");
            Ejercicio e1 = new Ejercicio(1, "Integral de 2x", "x^2", "Fácil", new Pista[0]);
            Tema temaSimulado = new Tema(1, "Integrales", "Cálculo integral", new Concepto[]{c1}, new Ejercicio[]{e1});

            int opDocente;
            do {
                System.out.println("\n=== PANEL DEL DOCENTE ===");
                System.out.println("1. Revisar Estructura (UML)");
                System.out.println("2. Agregar Ejercicio (ItemEjercicio)");
                System.out.println("3. Salir del Panel");
                System.out.print("➤ Seleccione: ");
                opDocente = sc.nextInt();
                sc.nextLine();

                switch (opDocente) {
                    case 1:
                        System.out.println("\n--- DATOS DEL TEMA ---");
                        System.out.println(temaSimulado.toString());
                        break;
                    case 2:
                        System.out.print("\nEnunciado del nuevo ejercicio: ");
                        String nuevoEnunciado = sc.nextLine();
                        int siguienteId = controlador.getNumItemE() + 1;
                        Ejercicio nuevoEj = new Ejercicio(siguienteId, nuevoEnunciado, "0", "Medio", new Pista[0]);
                        controlador.agregarEjercicio(nuevoEj);
                        System.out.println("Ejercicio indexado de forma correcta.");
                        break;
                    case 3:
                        System.out.println("Cerrando sesión docente...");
                        break;
                    default:
                        System.out.println("Opción inválida.");
                }
            } while (opDocente != 3);
        }
    }
}