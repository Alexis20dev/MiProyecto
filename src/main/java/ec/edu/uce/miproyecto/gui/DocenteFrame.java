package ec.edu.uce.miproyecto.gui;

import ec.edu.uce.miproyecto.dao.*;
import ec.edu.uce.miproyecto.dominio.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DocenteFrame extends JFrame {

    private final InterfaceDAO<Usuario> usuarioDao = new UsuarioDAOFabrica().crearUsuarioDAO();
    private final TemaDAO temaDao = new TemaDAOMemoriaImpl();
    private Docente docenteLogueado;

    public DocenteFrame(Docente docente) {
        if (docente != null) {
            this.docenteLogueado = docente;
        } else {
            inicializarDocenteDefecto();
        }

        initComponents();
        setTitle("MathFlow - Docente: " + docenteLogueado.getNombre());
        jLabel1.setText("MathFlow - Panel de Docente (" + docenteLogueado.getNombre() + ")");
        setLocationRelativeTo(null);
        conectarEventos();
    }

    public DocenteFrame() {
        this(null);
    }

    private void inicializarDocenteDefecto() {
        this.docenteLogueado = new Docente();
        this.docenteLogueado.setNombre("Docente");
        this.docenteLogueado.setContrasena("1234");
    }

    private void conectarEventos() {
        // El mismo botón administra creación y eliminación, así no se cambia mucho la interfaz.
        jButton1.addActionListener(e -> gestionarTemasYContenido());

        jButtonEjercicio.addActionListener(e -> crearEjercicio());
        jButtonConcepto.addActionListener(e -> crearConcepto());
        jButtonPista.addActionListener(e -> agregarPista());
        jButton2.addActionListener(e -> modificarPerfil());
        jButton3.addActionListener(e -> cerrarSesion());
    }

    private void gestionarTemasYContenido() {
        String[] opciones = {
                "Crear tema",
                "Eliminar tema",
                "Eliminar concepto",
                "Eliminar ejercicio"
        };

        String opcion = (String) JOptionPane.showInputDialog(
                this,
                "Seleccione la operación que desea realizar:",
                "Gestionar temas y contenido",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]
        );

        if (opcion == null) return;

        try {
            switch (opcion) {
                case "Crear tema" -> crearTema();
                case "Eliminar tema" -> eliminarTema();
                case "Eliminar concepto" -> eliminarConcepto();
                case "Eliminar ejercicio" -> eliminarEjercicio();
                default -> { }
            }
        } catch (DAOException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void crearTema() throws DAOException {
        String nombre = JOptionPane.showInputDialog(this, "Ingrese el nombre del nuevo tema:");
        if (nombre == null || nombre.trim().isEmpty()) return;

        String descripcion = JOptionPane.showInputDialog(this, "Ingrese una descripción del tema:");
        if (descripcion == null) return;

        Tema nuevoTema = new Tema(nombre.trim(), descripcion.trim());
        temaDao.nuevo(nuevoTema);

        JOptionPane.showMessageDialog(
                this,
                "Tema '" + nombre.trim() + "' creado correctamente.",
                "Éxito",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void eliminarTema() throws DAOException {
        Tema tema = seleccionarTema("Seleccione el tema que desea eliminar:", "Eliminar tema");
        if (tema == null) return;

        int respuesta = JOptionPane.showConfirmDialog(
                this,
                "¿Eliminar el tema '" + tema.getNombre() + "'?\n"
                        + "También se eliminarán sus conceptos y ejercicios.",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (respuesta != JOptionPane.YES_OPTION) return;

        int posicion = temaDao.listar().indexOf(tema);
        temaDao.eliminar(posicion);
        JOptionPane.showMessageDialog(this, "Tema eliminado correctamente.");
    }

    private void eliminarConcepto() throws DAOException {
        Tema tema = seleccionarTema("Seleccione el tema del concepto:", "Eliminar concepto");
        if (tema == null) return;

        if (tema.getConceptos().isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "El tema seleccionado no tiene conceptos.",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        Concepto concepto = (Concepto) JOptionPane.showInputDialog(
                this,
                "Seleccione el concepto que desea eliminar:",
                "Eliminar concepto",
                JOptionPane.QUESTION_MESSAGE,
                null,
                tema.getConceptos().toArray(),
                tema.getConceptos().get(0)
        );

        if (concepto == null) return;

        int respuesta = JOptionPane.showConfirmDialog(
                this,
                "¿Eliminar el concepto '" + concepto.getNombre() + "'?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (respuesta == JOptionPane.YES_OPTION) {
            temaDao.eliminarConcepto(tema, concepto);
            JOptionPane.showMessageDialog(this, "Concepto eliminado correctamente.");
        }
    }

    private void eliminarEjercicio() throws DAOException {
        Tema tema = seleccionarTema("Seleccione el tema del ejercicio:", "Eliminar ejercicio");
        if (tema == null) return;

        if (tema.getEjercicios().isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "El tema seleccionado no tiene ejercicios.",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        Ejercicio ejercicio = (Ejercicio) JOptionPane.showInputDialog(
                this,
                "Seleccione el ejercicio que desea eliminar:",
                "Eliminar ejercicio",
                JOptionPane.QUESTION_MESSAGE,
                null,
                tema.getEjercicios().toArray(),
                tema.getEjercicios().get(0)
        );

        if (ejercicio == null) return;

        int respuesta = JOptionPane.showConfirmDialog(
                this,
                "¿Eliminar el ejercicio seleccionado?\n" + ejercicio.getEnunciado(),
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (respuesta == JOptionPane.YES_OPTION) {
            temaDao.eliminarEjercicio(tema, ejercicio);
            JOptionPane.showMessageDialog(this, "Ejercicio eliminado correctamente.");
        }
    }

    private Tema seleccionarTema(String mensaje, String titulo) {
        List<Tema> temas = temaDao.listar();
        if (temas.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "No hay temas registrados.",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE
            );
            return null;
        }

        return (Tema) JOptionPane.showInputDialog(
                this,
                mensaje,
                titulo,
                JOptionPane.QUESTION_MESSAGE,
                null,
                temas.toArray(),
                temas.get(0)
        );
    }

    private void crearEjercicio() {
        try {
            Tema tema = seleccionarTema("Seleccione el tema para el ejercicio:", "Crear ejercicio");
            if (tema == null) return;

            String enunciado = JOptionPane.showInputDialog(this, "Ingrese el enunciado del ejercicio:");
            if (enunciado == null || enunciado.trim().isEmpty()) return;

            String respuesta = JOptionPane.showInputDialog(this, "Ingrese la respuesta correcta:");
            if (respuesta == null || respuesta.trim().isEmpty()) return;

            String dificultad = (String) JOptionPane.showInputDialog(
                    this,
                    "Seleccione la dificultad:",
                    "Dificultad",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    new String[]{"Fácil", "Medio", "Difícil"},
                    "Fácil"
            );
            if (dificultad == null) return;

            Ejercicio ejercicio = new Ejercicio(enunciado.trim(), respuesta.trim(), dificultad);
            temaDao.agregarEjercicio(tema, ejercicio);

            JOptionPane.showMessageDialog(
                    this,
                    "Ejercicio añadido al tema " + tema.getNombre() + ".",
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE
            );
        } catch (DAOException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void crearConcepto() {
        try {
            Tema tema = seleccionarTema("Seleccione el tema para el concepto:", "Crear concepto");
            if (tema == null) return;

            String nombre = JOptionPane.showInputDialog(this, "Nombre del concepto o fórmula:");
            if (nombre == null || nombre.trim().isEmpty()) return;

            String descripcion = JOptionPane.showInputDialog(this, "Descripción o explicación:");
            if (descripcion == null || descripcion.trim().isEmpty()) return;

            Concepto concepto = new Concepto(nombre.trim(), descripcion.trim());
            temaDao.agregarConcepto(tema, concepto);

            JOptionPane.showMessageDialog(
                    this,
                    "Concepto agregado correctamente.",
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE
            );
        } catch (DAOException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void agregarPista() {
        try {
            Tema tema = seleccionarTema("Seleccione el tema:", "Agregar pista");
            if (tema == null) return;

            if (tema.getEjercicios().isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "El tema seleccionado no tiene ejercicios.",
                        "Aviso",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            Ejercicio ejercicio = (Ejercicio) JOptionPane.showInputDialog(
                    this,
                    "Seleccione el ejercicio:",
                    "Agregar pista",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    tema.getEjercicios().toArray(),
                    tema.getEjercicios().get(0)
            );
            if (ejercicio == null) return;

            String descripcion = JOptionPane.showInputDialog(this, "Texto de la pista:");
            if (descripcion == null || descripcion.trim().isEmpty()) return;

            int orden = ejercicio.getPistas().size() + 1;
            ejercicio.agregarPista(new Pista(descripcion.trim(), orden));
            JOptionPane.showMessageDialog(this, "Pista añadida correctamente.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void modificarPerfil() {
        String nuevoNombre = JOptionPane.showInputDialog(
                this,
                "Ingrese el nuevo nombre de usuario:",
                docenteLogueado.getNombre()
        );
        if (nuevoNombre == null || nuevoNombre.trim().isEmpty()) return;

        String nuevaContrasena = JOptionPane.showInputDialog(
                this,
                "Ingrese la nueva contraseña:",
                docenteLogueado.getContrasena()
        );
        if (nuevaContrasena == null || nuevaContrasena.trim().isEmpty()) return;

        try {
            int posicion = usuarioDao.listar().indexOf(docenteLogueado);
            if (posicion == -1) {
                throw new DAOException("No se encontró la cuenta en el archivo de usuarios.");
            }

            docenteLogueado.setNombre(nuevoNombre.trim());
            docenteLogueado.setContrasena(nuevaContrasena);
            usuarioDao.editar(posicion, docenteLogueado);

            jLabel1.setText("MathFlow - Panel de Docente (" + docenteLogueado.getNombre() + ")");
            setTitle("MathFlow - Docente: " + docenteLogueado.getNombre());
            JOptionPane.showMessageDialog(this, "Perfil actualizado correctamente.");
        } catch (DAOException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Error al actualizar perfil: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void cerrarSesion() {
        new LoginFrame().setVisible(true);
        dispose();
    }

    private void initComponents() {
        jLabel1 = new JLabel();
        jButton1 = new JButton();
        jButtonEjercicio = new JButton();
        jButtonConcepto = new JButton();
        jButtonPista = new JButton();
        jButton2 = new JButton();
        jButton3 = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel1.setFont(new Font("Dialog", Font.BOLD, 14));
        jLabel1.setText("MathFlow - Panel de Docente");

        jButton1.setText("Gestionar Temas y Contenido");
        jButtonEjercicio.setText("Crear Ejercicio");
        jButtonConcepto.setText("Agregar Concepto Teórico");
        jButtonPista.setText("Agregar Pista a Ejercicio");
        jButton2.setText("Modificar Perfil");
        jButton3.setText("Cerrar Sesión");

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(25, 50, 30, 50));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        jLabel1.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(jLabel1);
        panel.add(Box.createVerticalStrut(25));

        JButton[] botones = {
                jButton1,
                jButtonEjercicio,
                jButtonConcepto,
                jButtonPista,
                jButton2,
                jButton3
        };

        for (JButton boton : botones) {
            boton.setAlignmentX(Component.CENTER_ALIGNMENT);
            boton.setMaximumSize(new Dimension(280, 30));
            panel.add(boton);
            panel.add(Box.createVerticalStrut(12));
        }

        setContentPane(panel);
        pack();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new DocenteFrame().setVisible(true));
    }

    private JLabel jLabel1;
    private JButton jButton1;
    private JButton jButtonEjercicio;
    private JButton jButtonConcepto;
    private JButton jButtonPista;
    private JButton jButton2;
    private JButton jButton3;
}
