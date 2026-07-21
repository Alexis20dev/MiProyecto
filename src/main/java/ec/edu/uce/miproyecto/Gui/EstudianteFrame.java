package ec.edu.uce.miproyecto.Gui;

import ec.edu.uce.miproyecto.dominio.Estudiante;
import ec.edu.uce.miproyecto.dominio.Ejercicio;
import ec.edu.uce.miproyecto.dao.InterfaceDAO;
import ec.edu.uce.miproyecto.dao.UsuarioDAOFabrica;
import ec.edu.uce.miproyecto.dao.DAOException;
import javax.swing.JOptionPane;

public class EstudianteFrame extends javax.swing.JFrame {

    private final InterfaceDAO usuarioDao = new UsuarioDAOFabrica().crearUsuarioDAO();
    private Estudiante estudianteLogueado;
    private Ejercicio ejercicioActual;
    private int contadorPistas = 0;


    public EstudianteFrame(Estudiante estudiante, Ejercicio ejercicio) {
        this.estudianteLogueado = estudiante;
        this.ejercicioActual = ejercicio;

        initComponents();

        setTitle("MathFlow - Estudiante: " + estudianteLogueado.getNombre());
        jLabel1.setText("Bienvenido, " + estudianteLogueado.getNombre());
        setLocationRelativeTo(null); // Centra la ventana

        conectarEventos();
    }

    public EstudianteFrame() {
        initComponents();
    }

    private void conectarEventos() {

        jButton1.addActionListener(e -> {
            if (ejercicioActual != null) {
                String msg = "Dificultad: " + ejercicioActual.getDificultad() + "\n\n"
                        + "Enunciado:\n" + ejercicioActual.getEnunciado();
                JOptionPane.showMessageDialog(this, msg, "Ejercicio del Módulo", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "No hay un ejercicio asignado actualmente.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });

        jButton2.addActionListener(e -> {
            String reporte = "=== REPORTE DE RENDIMIENTO ===\n\n"
                    + "Estudiante: " + estudianteLogueado.getNombre() + "\n"
                    + "Nivel Actual: " + estudianteLogueado.getNivel() + "\n"
                    + "Pistas solicitadas en esta sesión: " + contadorPistas + "\n"
                    + "Estado del Tema: " + (estudianteLogueado.getProgreso() != null ? estudianteLogueado.getProgreso().getEstado() : "Activo");
            JOptionPane.showMessageDialog(this, reporte, "Mi Progreso", JOptionPane.INFORMATION_MESSAGE);
        });

        jButton4.addActionListener(e -> {
            String teoria = "=== CONCEPTO: INTEGRACIÓN DIRECTA ===\n\n"
                    + "Es el proceso que permite hallar la primitiva de una función de forma inmediata.\n\n"
                    + "Fórmulas Útiles:\n"
                    + "• ∫ x^n dx = (x^(n+1)) / (n+1) + C\n"
                    + "• ∫ e^x dx = e^x + C\n"
                    + "• ∫ 1/x dx = ln|x| + C";
            JOptionPane.showMessageDialog(this, teoria, "Contenido Teórico", JOptionPane.INFORMATION_MESSAGE);
        });

        jButton5.addActionListener(e -> {
            String respuesta = jTextField1.getText().trim();
            if (respuesta.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, ingresa una respuesta antes de evaluar.", "Campo Vacío", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (ejercicioActual.getRespuesta().equalsIgnoreCase(respuesta)) {
                JOptionPane.showMessageDialog(this, "¡Excelente Trabajo! La respuesta es correcta. 🎉", "¡Correcto!", JOptionPane.INFORMATION_MESSAGE);
                jTextField1.setText("");
                contadorPistas = 0;
            } else {
                JOptionPane.showMessageDialog(this, "Respuesta incorrecta. Sigue intentándolo o solicita una pista. 💡", "Intento Fallido", JOptionPane.ERROR_MESSAGE);
            }
        });

        jButton6.addActionListener(e -> {
            contadorPistas++;
            String pistaMsg;
            if (ejercicioActual.getPistas() != null && ejercicioActual.getPistas().size() >= contadorPistas) {
                pistaMsg = "Pista " + contadorPistas + ": " + ejercicioActual.getPistas().get(contadorPistas - 1).getDescripcion();
            } else {
                if (contadorPistas == 1) {
                    pistaMsg = "Pista 1: Identifica si es una integral directa o requiere sustitución.";
                } else {
                    pistaMsg = "Pista Final: Revisa si olvidaste dividir para el nuevo exponente.";
                }
            }
            JOptionPane.showMessageDialog(this, pistaMsg, "Asistencia de MathFlow", JOptionPane.INFORMATION_MESSAGE);        });

        jButton7.addActionListener(e -> {
            String nuevoNom = JOptionPane.showInputDialog(this, "Modificar Perfil\nIngrese su nuevo nombre:", estudianteLogueado.getNombre());
            if (nuevoNom == null || nuevoNom.trim().isEmpty()) return;

            String nuevaContra = JOptionPane.showInputDialog(this, "Modificar Perfil\nIngrese su nueva contraseña:", estudianteLogueado.getContrasena());
            if (nuevaContra == null || nuevaContra.trim().isEmpty()) return;

            try {
                int index = usuarioDao.listar().indexOf(estudianteLogueado);
                if (index != -1) {
                    estudianteLogueado.setNombre(nuevoNom);
                    estudianteLogueado.setContrasena(nuevaContra);
                    usuarioDao.editar(index, estudianteLogueado);

                    jLabel1.setText("Bienvenido, " + estudianteLogueado.getNombre());
                    JOptionPane.showMessageDialog(this, "¡Perfil actualizado con éxito!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (DAOException ex) {
                JOptionPane.showMessageDialog(this, "Error en persistencia: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setFont(new java.awt.Font("MathJax_Typewriter", 0, 14)); // NOI18N

        jLabel1.setText("MathFlow - Panel de Estudiante");

        jButton1.setText("Ver Ejercicio");

        jButton2.setText("Ver Progreso");

        jButton3.setText("Cerrar Sesion");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel2.setText("Ingresa tu respuesta");

        jButton5.setText("Enviar Respuesta");

        jButton4.setText("Ver Conceptos");

        jButton6.setText("Solicitar Pista");

        jButton7.setText("Modificar Perfil");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(76, 76, 76)
                                                .addComponent(jButton5))
                                        .addComponent(jLabel2)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(85, 85, 85)
                                                .addComponent(jButton6)
                                                .addGap(11, 11, 11))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(89, 89, 89)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jButton1))
                                                .addGap(14, 14, 14)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(226, 226, 226)
                                .addComponent(jLabel1)
                                .addContainerGap(254, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(256, 256, 256)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jButton7)
                                        .addComponent(jButton3))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(55, 55, 55)
                                .addComponent(jButton4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton2)
                                .addGap(100, 100, 100))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(45, 45, 45)
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton5)
                                .addGap(29, 29, 29)
                                .addComponent(jButton6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton4)
                                        .addComponent(jButton2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton7)
                                .addGap(99, 99, 99)
                                .addComponent(jButton3)
                                .addGap(48, 48, 48))
        );

        pack();
    }
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:initComponents
        LoginFrame login = new LoginFrame();
        login.setVisible(true);
        this.dispose();
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(EstudianteFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EstudianteFrame().setVisible(true);
            }
        });
    }

    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField jTextField1;
}