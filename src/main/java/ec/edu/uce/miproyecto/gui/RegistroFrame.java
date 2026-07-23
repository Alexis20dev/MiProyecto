package ec.edu.uce.miproyecto.gui;

import ec.edu.uce.miproyecto.dao.DAOException;
import ec.edu.uce.miproyecto.dao.InterfaceDAO;
import ec.edu.uce.miproyecto.dao.UsuarioDAOFabrica;
import ec.edu.uce.miproyecto.dominio.Docente;
import ec.edu.uce.miproyecto.dominio.Estudiante;
import ec.edu.uce.miproyecto.dominio.Usuario;
import ec.edu.uce.miproyecto.enums.Genero;
import ec.edu.uce.miproyecto.util.Validaciones;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

public class RegistroFrame extends JFrame {

    private final InterfaceDAO<Usuario> usuarioDao = new UsuarioDAOFabrica().crearUsuarioDAO();

    private JTextField txtNombre;
    private JTextField txtEmail;
    private JPasswordField txtContrasena;
    private JComboBox<String> cmbRol;
    private JComboBox<Genero> cmbGenero;
    private JButton btnRegistrar;
    private JButton btnVolver;

    public RegistroFrame() {
        initComponents();
        setTitle("MathFlow - Registro");
        setLocationRelativeTo(null);
        conectarEventos();
    }

    private void conectarEventos() {
        btnRegistrar.addActionListener(e -> registrarUsuario());
        btnVolver.addActionListener(e -> volverAlLogin());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                volverAlLogin();
            }
        });
    }

    private void registrarUsuario() {
        String nombre = txtNombre.getText().trim();
        String email = txtEmail.getText().trim();
        String contrasena = new String(txtContrasena.getPassword());
        String rol = (String) cmbRol.getSelectedItem();
        Genero genero = (Genero) cmbGenero.getSelectedItem();

        if (nombre.isEmpty() || email.isEmpty() || contrasena.trim().isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Complete todos los campos del registro.",
                    "Campos vacíos",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        if (nombre.contains(",") || email.contains(",") || contrasena.contains(",")) {
            JOptionPane.showMessageDialog(
                    this,
                    "Los datos no pueden contener comas porque se guardan en un archivo de texto.",
                    "Dato inválido",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        if (!Validaciones.validarNombre(nombre)) {
            JOptionPane.showMessageDialog(
                    this,
                    "El nombre debe contener únicamente letras y espacios.",
                    "Nombre inválido",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        if (!Validaciones.validarEmail(email) || !email.toLowerCase().endsWith("@uce.edu.ec")) {
            JOptionPane.showMessageDialog(
                    this,
                    "El correo debe terminar exactamente en '@uce.edu.ec'.",
                    "Correo inválido",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        if (!Validaciones.validarContrasena(contrasena)) {
            JOptionPane.showMessageDialog(
                    this,
                    "La contraseña debe tener al menos 4 caracteres.",
                    "Contraseña inválida",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        Usuario nuevoUsuario;
        if ("Docente".equalsIgnoreCase(rol)) {
            Docente docente = new Docente();
            docente.setEspecialidad("Sin especialidad");
            nuevoUsuario = docente;
        } else {
            Estudiante estudiante = new Estudiante();
            estudiante.setNivel("Segundo Semestre");
            nuevoUsuario = estudiante;
        }

        nuevoUsuario.setNombre(nombre);
        nuevoUsuario.setEmail(email);
        nuevoUsuario.setContrasena(contrasena);
        nuevoUsuario.setFechaRegistro(new Date());
        nuevoUsuario.setGenero(genero != null ? genero : Genero.S);

        try {
            usuarioDao.nuevo(nuevoUsuario);
            JOptionPane.showMessageDialog(
                    this,
                    "Usuario registrado correctamente. Ahora puede iniciar sesión.",
                    "Registro exitoso",
                    JOptionPane.INFORMATION_MESSAGE
            );
            volverAlLogin();
        } catch (DAOException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Error de registro",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void volverAlLogin() {
        new LoginFrame().setVisible(true);
        dispose();
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);

        JLabel lblTitulo = new JLabel("Crear cuenta", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Dialog", Font.BOLD, 18));

        txtNombre = new JTextField(20);
        txtEmail = new JTextField(20);
        txtContrasena = new JPasswordField(20);
        cmbRol = new JComboBox<>(new String[]{"Estudiante", "Docente"});
        cmbGenero = new JComboBox<>(Genero.values());
        btnRegistrar = new JButton("Registrar cuenta");
        btnVolver = new JButton("Volver al inicio de sesión");

        JPanel formulario = new JPanel(new GridBagLayout());
        formulario.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        formulario.add(lblTitulo, gbc);

        agregarFila(formulario, gbc, 1, "Nombre de usuario:", txtNombre);
        agregarFila(formulario, gbc, 2, "Correo institucional:", txtEmail);
        agregarFila(formulario, gbc, 3, "Contraseña:", txtContrasena);
        agregarFila(formulario, gbc, 4, "Tipo de usuario:", cmbRol);
        agregarFila(formulario, gbc, 5, "Género:", cmbGenero);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        formulario.add(btnRegistrar, gbc);

        gbc.gridy = 7;
        formulario.add(btnVolver, gbc);

        setContentPane(formulario);
        pack();
    }

    private void agregarFila(JPanel panel, GridBagConstraints gbc, int fila,
                             String texto, JComponent componente) {
        gbc.gridy = fila;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        panel.add(new JLabel(texto), gbc);

        gbc.gridx = 1;
        panel.add(componente, gbc);
    }
}
