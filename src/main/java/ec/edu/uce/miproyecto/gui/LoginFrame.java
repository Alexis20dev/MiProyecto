package ec.edu.uce.miproyecto.gui;

import ec.edu.uce.miproyecto.dao.DAOException;
import ec.edu.uce.miproyecto.dao.InterfaceDAO;
import ec.edu.uce.miproyecto.dao.UsuarioDAOFabrica;
import ec.edu.uce.miproyecto.dominio.Docente;
import ec.edu.uce.miproyecto.dominio.Ejercicio;
import ec.edu.uce.miproyecto.dominio.Estudiante;
import ec.edu.uce.miproyecto.dominio.Usuario;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    private final InterfaceDAO<Usuario> usuarioDao = new UsuarioDAOFabrica().crearUsuarioDAO();

    private JTextField txtCredencial;
    private JPasswordField txtContrasena;
    private JButton btnEntrar;
    private JButton btnCrearCuenta;

    public LoginFrame() {
        initComponents();
        setTitle("MathFlow - Inicio de sesión");
        setLocationRelativeTo(null);
        conectarEventos();
    }

    private void conectarEventos() {
        btnEntrar.addActionListener(e -> iniciarSesion());
        txtContrasena.addActionListener(e -> iniciarSesion());

        btnCrearCuenta.addActionListener(e -> {
            new RegistroFrame().setVisible(true);
            dispose();
        });
    }

    private void iniciarSesion() {
        String credencial = txtCredencial.getText().trim();
        String contrasena = new String(txtContrasena.getPassword());

        if (credencial.isEmpty() || contrasena.trim().isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Ingrese su nombre de usuario o correo y la contraseña.",
                    "Campos vacíos",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        if (credencial.equalsIgnoreCase("admin") && contrasena.equals("1234")) {
            new DocenteFrame().setVisible(true);
            dispose();
            return;
        }

        try {
            Usuario usuarioLogueado = usuarioDao.buscar(credencial);

            // iniciarSesion() valida explícitamente nombre O correo, además de la contraseña.
            if (usuarioLogueado == null || !usuarioLogueado.iniciarSesion(credencial, contrasena)) {
                JOptionPane.showMessageDialog(
                        this,
                        "Nombre de usuario/correo o contraseña incorrectos.",
                        "Credenciales inválidas",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            if (usuarioLogueado instanceof Estudiante estudiante) {
                Ejercicio ejercicioPrueba = new Ejercicio(
                        "Calcular la integral directa: ∫ 3x^2 dx",
                        "x^3",
                        "Fácil"
                );
                new EstudianteFrame(estudiante, ejercicioPrueba).setVisible(true);
            } else if (usuarioLogueado instanceof Docente docente) {
                // Se pasa el docente real que inició sesión, no uno creado por defecto.
                new DocenteFrame(docente).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Tipo de cuenta no reconocido.",
                        "Error de autenticación",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            dispose();
        } catch (DAOException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Error en el sistema: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        JLabel lblTitulo = new JLabel("Inicio de sesión", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Dialog", Font.BOLD, 18));

        JLabel lblCredencial = new JLabel("Nombre de usuario o correo:");
        JLabel lblContrasena = new JLabel("Contraseña:");

        txtCredencial = new JTextField(22);
        txtContrasena = new JPasswordField(22);
        btnEntrar = new JButton("Entrar");
        btnCrearCuenta = new JButton("Crear una cuenta");

        JPanel formulario = new JPanel(new GridBagLayout());
        formulario.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(7, 7, 7, 7);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        formulario.add(lblTitulo, gbc);

        gbc.gridy++;
        gbc.gridwidth = 1;
        formulario.add(lblCredencial, gbc);

        gbc.gridx = 1;
        formulario.add(txtCredencial, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        formulario.add(lblContrasena, gbc);

        gbc.gridx = 1;
        formulario.add(txtContrasena, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        formulario.add(btnEntrar, gbc);

        gbc.gridy++;
        formulario.add(new JSeparator(), gbc);

        gbc.gridy++;
        formulario.add(new JLabel("¿No tienes una cuenta?", SwingConstants.CENTER), gbc);

        gbc.gridy++;
        formulario.add(btnCrearCuenta, gbc);

        setContentPane(formulario);
        pack();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
    }
}
