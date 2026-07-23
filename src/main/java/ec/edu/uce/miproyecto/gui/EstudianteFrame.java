package ec.edu.uce.miproyecto.gui;

import ec.edu.uce.miproyecto.dao.*;
import ec.edu.uce.miproyecto.dominio.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class EstudianteFrame extends JFrame {

    private final InterfaceDAO<Usuario> usuarioDao = new UsuarioDAOFabrica().crearUsuarioDAO();
    private final TemaDAO temaDao = new TemaDAOMemoriaImpl(); // Mismo DAO compartido
    private Estudiante estudianteLogueado;
    private Ejercicio ejercicioActual;
    private int contadorPistas = 0;

    public EstudianteFrame(Estudiante estudiante, Ejercicio ejercicio) {
        if (estudiante != null) {
            this.estudianteLogueado = estudiante;
        } else {
            inicializarEstudianteDefecto();
        }

        this.ejercicioActual = ejercicio;

        initComponents();

        setTitle("MathFlow - Estudiante: " + estudianteLogueado.getNombre());
        jLabel1.setText("Bienvenido, " + estudianteLogueado.getNombre());
        setLocationRelativeTo(null);

        conectarEventos();

        // Si no se pasó un ejercicio por parámetro, intentamos cargar el primero creado por el docente
        if (this.ejercicioActual == null) {
            cargarPrimerEjercicioDisponible();
        }
    }

    public EstudianteFrame() {
        this(null, null);
    }

    private void inicializarEstudianteDefecto() {
        this.estudianteLogueado = new Estudiante();
        this.estudianteLogueado.setNombre("Estudiante");
        this.estudianteLogueado.setContrasena("1234");
        this.estudianteLogueado.setNivel("Principiante");
    }

    /**
     * Carga dinámicamente el primer ejercicio registrado por el docente en el TemaDAO
     */
    private void cargarPrimerEjercicioDisponible() {
        try {
            List<Tema> temas = temaDao.listar();
            for (Tema t : temas) {
                if (t.getEjercicios() != null && !t.getEjercicios().isEmpty()) {
                    this.ejercicioActual = t.getEjercicios().get(0);
                    break;
                }
            }
        } catch (Exception ex) {
            System.err.println("Error al cargar ejercicios del DAO: " + ex.getMessage());
        }
    }

    private void conectarEventos() {

        // 1. Ver Ejercicio o Seleccionar uno de los creados por el Docente
        jButton1.addActionListener(e -> {
            try {
                List<Tema> temas = temaDao.listar();
                if (temas.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "El docente aún no ha registrado temas.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                // Permitir al estudiante elegir un tema de los existentes
                Tema temaSel = (Tema) JOptionPane.showInputDialog(
                        this,
                        "Selecciona un Tema para practicar:",
                        "Temas Disponibles",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        temas.toArray(),
                        temas.get(0)
                );

                if (temaSel != null && temaSel.getEjercicios() != null && !temaSel.getEjercicios().isEmpty()) {
                    // Seleccionar ejercicio de ese tema
                    Ejercicio ejSel = (Ejercicio) JOptionPane.showInputDialog(
                            this,
                            "Selecciona un Ejercicio:",
                            "Ejercicios de " + temaSel.getNombre(),
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            temaSel.getEjercicios().toArray(),
                            temaSel.getEjercicios().get(0)
                    );

                    if (ejSel != null) {
                        this.ejercicioActual = ejSel;
                        this.contadorPistas = 0; // Reiniciar contador de pistas para el nuevo ejercicio
                        String msg = "Tema: " + temaSel.getNombre() + "\n"
                                + "Dificultad: " + ejercicioActual.getDificultad() + "\n\n"
                                + "Enunciado:\n" + ejercicioActual.getEnunciado();
                        JOptionPane.showMessageDialog(this, msg, "Ejercicio Carga Directa", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else if (temaSel != null) {
                    JOptionPane.showMessageDialog(this, "Este tema no tiene ejercicios registrados por el docente.", "Aviso", JOptionPane.WARNING_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al obtener ejercicios: " + ex.getMessage());
            }
        });

        // 2. Ver Progreso
        jButton2.addActionListener(e -> {
            String reporte = "=== REPORTE DE RENDIMIENTO ===\n\n"
                    + "Estudiante: " + estudianteLogueado.getNombre() + "\n"
                    + "Nivel Actual: " + (estudianteLogueado.getNivel() != null ? estudianteLogueado.getNivel() : "General") + "\n"
                    + "Pistas solicitadas en esta sesión: " + contadorPistas + "\n"
                    + "Estado del Tema: " + (estudianteLogueado.getProgreso() != null ? estudianteLogueado.getProgreso().getEstado() : "Activo");
            JOptionPane.showMessageDialog(this, reporte, "Mi Progreso", JOptionPane.INFORMATION_MESSAGE);
        });

        // 3. Ver Conceptos creados por el Docente
        jButton4.addActionListener(e -> {
            try {
                List<Tema> temas = temaDao.listar();
                StringBuilder sb = new StringBuilder("=== CONCEPTOS REGISTRADOS POR EL DOCENTE ===\n\n");
                boolean hayConceptos = false;

                for (Tema t : temas) {
                    if (t.getConceptos() != null && !t.getConceptos().isEmpty()) {
                        sb.append("📌 Tema: ").append(t.getNombre()).append("\n");
                        for (Concepto c : t.getConceptos()) {
                            sb.append("  • ").append(c.getNombre()).append(": ").append(c.getDescripcion()).append("\n");
                            hayConceptos = true;
                        }
                        sb.append("\n");
                    }
                }

                if (!hayConceptos) {
                    sb.append("Aún no hay conceptos teóricos cargados por el docente.");
                }

                JOptionPane.showMessageDialog(this, sb.toString(), "Contenido Teórico", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al cargar conceptos: " + ex.getMessage());
            }
        });

        // 4. Enviar Respuesta
        jButton5.addActionListener(e -> {
            if (ejercicioActual == null) {
                JOptionPane.showMessageDialog(this, "Selecciona primero un ejercicio haciendo clic en 'Ver Ejercicio'.", "Sin Ejercicio", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String respuesta = jTextField1.getText().trim();
            if (respuesta.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, ingresa una respuesta antes de evaluar.", "Campo Vacío", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (ejercicioActual.getRespuesta() != null && ejercicioActual.getRespuesta().equalsIgnoreCase(respuesta)) {
                JOptionPane.showMessageDialog(this, "¡Excelente Trabajo! La respuesta es correcta. 🎉", "¡Correcto!", JOptionPane.INFORMATION_MESSAGE);
                jTextField1.setText("");
                contadorPistas = 0;
            } else {
                JOptionPane.showMessageDialog(this, "Respuesta incorrecta. Sigue intentándolo o solicita una pista. 💡", "Intento Fallido", JOptionPane.ERROR_MESSAGE);
            }
        });

        // 5. Solicitar Pista (Lee las pistas reales del ejercicio creado por el docente)
        jButton6.addActionListener(e -> {
            if (ejercicioActual == null) {
                JOptionPane.showMessageDialog(this, "No hay un ejercicio activo para solicitar pistas.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            contadorPistas++;
            String pistaMsg;
            List<Pista> pistasReales = ejercicioActual.getPistas();

            if (pistasReales != null && !pistasReales.isEmpty() && pistasReales.size() >= contadorPistas) {
                Pista p = pistasReales.get(contadorPistas - 1);
                pistaMsg = "Pista " + p.getOrden() + ": " + p.getDescripcion();
            } else {
                pistaMsg = "No hay más pistas disponibles creadas para este ejercicio.";
            }

            JOptionPane.showMessageDialog(this, pistaMsg, "Asistencia de MathFlow", JOptionPane.INFORMATION_MESSAGE);
        });

        // 6. Modificar Perfil
        jButton7.addActionListener(e -> {
            String nuevoNom = JOptionPane.showInputDialog(this, "Modificar Perfil\nIngrese su nuevo nombre:", estudianteLogueado.getNombre());
            if (nuevoNom == null || nuevoNom.trim().isEmpty()) return;

            String nuevaContra = JOptionPane.showInputDialog(this, "Modificar Perfil\nIngrese su nueva contraseña:", estudianteLogueado.getContrasena());
            if (nuevaContra == null || nuevaContra.trim().isEmpty()) return;

            try {
                int index = usuarioDao.listar().indexOf(estudianteLogueado);
                estudianteLogueado.setNombre(nuevoNom.trim());
                estudianteLogueado.setContrasena(nuevaContra.trim());

                if (index != -1) {
                    usuarioDao.editar(index, estudianteLogueado);
                }

                jLabel1.setText("Bienvenido, " + estudianteLogueado.getNombre());
                setTitle("MathFlow - Estudiante: " + estudianteLogueado.getNombre());
                JOptionPane.showMessageDialog(this, "¡Perfil actualizado con éxito!", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            } catch (DAOException ex) {
                JOptionPane.showMessageDialog(this, "Error en persistencia: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jLabel1 = new JLabel();
        jButton1 = new JButton();
        jButton2 = new JButton();
        jButton3 = new JButton();
        jTextField1 = new JTextField();
        jLabel2 = new JLabel();
        jButton5 = new JButton();
        jButton4 = new JButton();
        jButton6 = new JButton();
        jButton7 = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setFont(new Font("MathJax_Typewriter", 0, 14));

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

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
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
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                        .addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jButton1))
                                                .addGap(14, 14, 14)))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(226, 226, 226)
                                .addComponent(jLabel1)
                                .addContainerGap(254, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(256, 256, 256)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(jButton7)
                                        .addComponent(jButton3))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(55, 55, 55)
                                .addComponent(jButton4)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton2)
                                .addGap(100, 100, 100))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(45, 45, 45)
                                .addComponent(jButton1)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel2)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton5)
                                .addGap(29, 29, 29)
                                .addComponent(jButton6)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton4)
                                        .addComponent(jButton2))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton7)
                                .addGap(99, 99, 99)
                                .addComponent(jButton3)
                                .addGap(48, 48, 48))
        );

        pack();
    }

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            LoginFrame login = new LoginFrame();
            login.setVisible(true);
        } catch (Exception ex) {
            System.err.println("Redirigiendo a Login: " + ex.getMessage());
        }
        this.dispose();
    }

    public static void main(String args[]) {
        EventQueue.invokeLater(() -> new EstudianteFrame().setVisible(true));
    }

    private JButton jButton1;
    private JButton jButton2;
    private JButton jButton3;
    private JButton jButton4;
    private JButton jButton5;
    private JButton jButton6;
    private JButton jButton7;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JTextField jTextField1;
}