package ec.edu.uce.miproyecto.gui;

import ec.edu.uce.miproyecto.dao.DAOException;
import ec.edu.uce.miproyecto.dao.InterfaceDAO;
import ec.edu.uce.miproyecto.dao.UsuarioDAOFabrica;
import ec.edu.uce.miproyecto.dominio.Docente;
import ec.edu.uce.miproyecto.dominio.Ejercicio;
import ec.edu.uce.miproyecto.dominio.Estudiante;
import ec.edu.uce.miproyecto.dominio.Usuario;
import ec.edu.uce.miproyecto.util.Validaciones; // <--- Importamos la clase de validaciones

import javax.swing.*;
import java.util.Date;

public class LoginFrame extends JFrame {

    private final InterfaceDAO usuarioDao = new UsuarioDAOFabrica().crearUsuarioDAO();
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel jLabel7;
    private JLabel jLabel8;
    private JLabel jLabel9;
    private JSeparator jSeparator1;
    private JPasswordField txtContraseña;
    private JPasswordField txtContraseñaR;
    private JTextField txtEmail;
    private JButton txtEntrarR;
    private JComboBox<String> txtItem;
    private JTextField txtNombre;
    private JTextField txtNombreR;
    private JButton txtRegistro;

    public LoginFrame() {
        initComponents();

        setTitle("MathFlow - Sistema Académico");
        setLocationRelativeTo(null); // Centra la ventana en la pantalla

        txtContraseñaR.setText("");
        txtContraseña.setText("");

        txtItem.setModel(new DefaultComboBoxModel<>(new String[] { "Estudiante", "Docente" }));

        txtEntrarR.addActionListener(e -> {
            String emailLogin = txtNombreR.getText().trim();
            String passwordLogin = new String(txtContraseñaR.getPassword()).trim();

            if (emailLogin.isEmpty() || passwordLogin.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, llene todos los campos de inicio de sesión.", "Campos Vacíos", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (emailLogin.equalsIgnoreCase("admin") && passwordLogin.equals("1234")) {
                new DocenteFrame().setVisible(true);
                this.dispose();
                return;
            }

            try {
                Usuario usuarioLogueado = (Usuario) usuarioDao.buscar(emailLogin);

                if (usuarioLogueado != null && usuarioLogueado.getContrasena().equals(passwordLogin)) {

                    if (usuarioLogueado instanceof Estudiante) {
                        Ejercicio ejercicioPrueba = new Ejercicio();
                        ejercicioPrueba.setEnunciado("Calcular la integral directa: ∫ 3x^2 dx");
                        ejercicioPrueba.setRespuesta("x^3");
                        ejercicioPrueba.setDificultad("Fácil");

                        Estudiante estudiante = (Estudiante) usuarioLogueado;
                        EstudianteFrame menuEstudiante = new EstudianteFrame(estudiante, ejercicioPrueba);
                        menuEstudiante.setVisible(true);

                    } else if (usuarioLogueado instanceof Docente) {
                        DocenteFrame menuDocente = new DocenteFrame();
                        menuDocente.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(this, "Tipo de cuenta no reconocido.", "Error de Autenticación", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    this.dispose();

                } else {
                    JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos.", "Credenciales Inválidas", JOptionPane.ERROR_MESSAGE);
                }
            } catch (DAOException ex) {
                JOptionPane.showMessageDialog(this, "Error en el sistema: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        txtRegistro.addActionListener(e -> {
            String nombreReg = txtNombre.getText().trim();
            String emailReg = txtEmail.getText().trim();
            String passwordReg = new String(txtContraseña.getPassword()).trim();
            String rolSeleccionado = (String) txtItem.getSelectedItem();

            if (nombreReg.isEmpty() || emailReg.isEmpty() || passwordReg.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos del registro.", "Campos Vacíos", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (!Validaciones.validarNombre(nombreReg)) {
                JOptionPane.showMessageDialog(this, "El nombre debe contener únicamente letras y espacios.", "Nombre Inválido", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!Validaciones.validarEmail(emailReg) || !emailReg.toLowerCase().endsWith("@uce.edu.ec")) {
                JOptionPane.showMessageDialog(
                        this,
                        "El correo debe terminar exactamente en '@uce.edu.ec'.",
                        "Correo Inválido",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }
            if (!Validaciones.validarContrasena(passwordReg)) {
                JOptionPane.showMessageDialog(this, "La contraseña debe tener al menos 4 caracteres.", "Contraseña Inválida", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Usuario nuevoUsuario;
            Date fechaActual = new Date();

            if ("Docente".equalsIgnoreCase(rolSeleccionado)) {
                nuevoUsuario = new Docente();
                nuevoUsuario.setNombre(nombreReg);
                nuevoUsuario.setEmail(emailReg);
                nuevoUsuario.setContrasena(passwordReg);
                nuevoUsuario.setFechaRegistro(fechaActual);
            } else {
                nuevoUsuario = new Estudiante();
                nuevoUsuario.setNombre(nombreReg);
                nuevoUsuario.setEmail(emailReg);
                nuevoUsuario.setContrasena(passwordReg);
                nuevoUsuario.setFechaRegistro(fechaActual);
                ((Estudiante) nuevoUsuario).setNivel("Segundo Semestre");
            }

            try {
                usuarioDao.nuevo(nuevoUsuario);

                JOptionPane.showMessageDialog(this, "¡Usuario " + nombreReg + " registrado con éxito como " + rolSeleccionado + "!", "Registro Exitoso", JOptionPane.INFORMATION_MESSAGE);

                txtNombre.setText("");
                txtEmail.setText("");
                txtContraseña.setText("");
            } catch (DAOException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error de Registro", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        txtNombreR = new JTextField();
        jLabel3 = new JLabel();
        txtContraseñaR = new JPasswordField();
        txtEntrarR = new JButton();
        jSeparator1 = new JSeparator();
        jLabel4 = new JLabel();
        jLabel5 = new JLabel();
        jLabel6 = new JLabel();
        txtNombre = new JTextField();
        jLabel7 = new JLabel();
        txtEmail = new JTextField();
        jLabel8 = new JLabel();
        txtContraseña = new JPasswordField();
        jLabel9 = new JLabel();
        txtItem = new JComboBox<>();
        txtRegistro = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Inicio de Sesion");
        jLabel2.setText("Email/Usuario:");
        jLabel3.setText("Contraseña:");
        txtContraseñaR.setText("jPasswordField1");
        txtEntrarR.setText("Entrar");

        jLabel5.setText("¿No tienes cuenta? Regístrate aquí");
        jLabel6.setText("Nombre:");
        jLabel7.setText("Email:");
        jLabel8.setText("Contraseña:");
        txtContraseña.setText("jPasswordField2");
        jLabel9.setText("Tipo de Usuario:");

        txtItem.setModel(new DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        txtRegistro.setText("Registrar Cuenta");

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(txtEntrarR, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(250, 250, 250)
                                                .addComponent(jLabel1))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(67, 67, 67)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel3)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jLabel2)
                                                                .addGap(18, 18, 18)
                                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                        .addComponent(txtNombreR, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(txtContraseñaR, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))))))
                                .addContainerGap(239, Short.MAX_VALUE))
                        .addComponent(jSeparator1, GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(112, 112, 112)
                                                .addComponent(jLabel5, GroupLayout.PREFERRED_SIZE, 352, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(49, 49, 49)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel7)
                                                        .addComponent(jLabel8)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jLabel6)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(jLabel9)
                                                                .addGap(44, 44, 44))))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(92, 92, 92)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(txtItem, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)))))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(94, 94, 94)
                                .addComponent(txtContraseña, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(223, 223, 223)
                                .addComponent(txtRegistro)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel2))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(31, 31, 31)
                                                .addComponent(txtNombreR, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                .addGap(24, 24, 24)
                                .addComponent(jLabel3)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtContraseñaR, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(49, 49, 49)
                                .addComponent(txtEntrarR, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator1, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel6)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel4)
                                                        .addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                .addGap(22, 22, 22)
                                                .addComponent(jLabel7)
                                                .addGap(3, 3, 3)
                                                .addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel8)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtContraseña, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE))
                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(jLabel9)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtItem, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addGap(177, 177, 177)))
                                .addComponent(txtRegistro)
                                .addGap(42, 42, 42))
        );

        pack();
    }
}