package ec.edu.uce.miproyecto.gui;

import ec.edu.uce.miproyecto.dao.*;
import ec.edu.uce.miproyecto.dominio.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DocenteFrame extends JFrame {

    private final InterfaceDAO usuarioDao = new UsuarioDAOFabrica().crearUsuarioDAO();
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

        // 1. Gestionar Temas Académicos
        jButton1.addActionListener(e -> {
            String nombreTema = JOptionPane.showInputDialog(this, "Ingrese el nombre del nuevo Tema:");
            if (nombreTema != null && !nombreTema.trim().isEmpty()) {
                Tema nuevoTema = new Tema();
                nuevoTema.setNombre(nombreTema.trim());
                try {
                    temaDao.nuevo(nuevoTema);
                    JOptionPane.showMessageDialog(this, "¡Tema '" + nombreTema + "' creado exitosamente!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } catch (DAOException ex) {
                    JOptionPane.showMessageDialog(this, "Error al crear tema: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // 2. Gestionar Ejercicios
        jButtonEjercicio.addActionListener(e -> {
            try {
                List<Tema> temas = temaDao.listar();
                if (temas.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Primero debe crear al menos un Tema académico.", "Aviso", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                Tema temaSel = (Tema) JOptionPane.showInputDialog(this, "Seleccione el Tema para el ejercicio:",
                        "Crear Ejercicio", JOptionPane.QUESTION_MESSAGE, null, temas.toArray(), temas.get(0));

                if (temaSel != null) {
                    String enunciado = JOptionPane.showInputDialog(this, "Ingrese el Enunciado del Ejercicio:");
                    if (enunciado == null || enunciado.trim().isEmpty()) return;

                    String respuesta = JOptionPane.showInputDialog(this, "Ingrese la Respuesta Correcta:");
                    if (respuesta == null || respuesta.trim().isEmpty()) return;

                    String dificultad = (String) JOptionPane.showInputDialog(this, "Seleccione la Dificultad:",
                            "Dificultad", JOptionPane.QUESTION_MESSAGE, null, new String[]{"Fácil", "Medio", "Difícil"}, "Fácil");

                    Ejercicio ej = new Ejercicio(enunciado.trim(), respuesta.trim(), dificultad);
                    temaSel.agregarEjercicio(ej);
                    JOptionPane.showMessageDialog(this, "¡Ejercicio añadido con éxito al tema " + temaSel.getNombre() + "!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // 3. Agregar Concepto Teórico
        jButtonConcepto.addActionListener(e -> {
            try {
                List<Tema> temas = temaDao.listar();
                if (temas.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Primero debe crear al menos un Tema académico.", "Aviso", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                Tema temaSel = (Tema) JOptionPane.showInputDialog(this, "Seleccione el Tema para el concepto:",
                        "Crear Concepto", JOptionPane.QUESTION_MESSAGE, null, temas.toArray(), temas.get(0));

                if (temaSel != null) {
                    String nomConcepto = JOptionPane.showInputDialog(this, "Nombre del Concepto/Fórmula:");
                    if (nomConcepto == null || nomConcepto.trim().isEmpty()) return;

                    String descConcepto = JOptionPane.showInputDialog(this, "Descripción o Explicación:");
                    if (descConcepto == null || descConcepto.trim().isEmpty()) return;

                    Concepto c = new Concepto(nomConcepto.trim(), descConcepto.trim());
                    temaSel.agregarConcepto(c);
                    JOptionPane.showMessageDialog(this, "¡Concepto agregado con éxito!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        // 4. Agregar Pista a Ejercicio
        jButtonPista.addActionListener(e -> {
            try {
                List<Tema> temas = temaDao.listar();
                if (temas.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "No hay temas ni ejercicios registrados.", "Aviso", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                Tema temaSel = (Tema) JOptionPane.showInputDialog(this, "Seleccione el Tema:",
                        "Selección Tema", JOptionPane.QUESTION_MESSAGE, null, temas.toArray(), temas.get(0));

                if (temaSel != null && temaSel.getEjercicios() != null && !temaSel.getEjercicios().isEmpty()) {
                    Ejercicio ejSel = (Ejercicio) JOptionPane.showInputDialog(this, "Seleccione el Ejercicio:",
                            "Selección Ejercicio", JOptionPane.QUESTION_MESSAGE, null, temaSel.getEjercicios().toArray(), temaSel.getEjercicios().get(0));

                    if (ejSel != null) {
                        String descPista = JOptionPane.showInputDialog(this, "Texto de la Pista:");
                        if (descPista != null && !descPista.trim().isEmpty()) {
                            int orden = (ejSel.getPistas() != null) ? ejSel.getPistas().size() + 1 : 1;
                            ejSel.agregarPista(new Pista(descPista.trim(), orden));
                            JOptionPane.showMessageDialog(this, "¡Pista añadida con éxito!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                } else if (temaSel != null) {
                    JOptionPane.showMessageDialog(this, "Este tema no tiene ejercicios asignados aún.", "Aviso", JOptionPane.WARNING_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        // 5. Modificar Perfil
        jButton2.addActionListener(e -> {
            String nuevoNom = JOptionPane.showInputDialog(this, "Ingrese nuevo nombre:", docenteLogueado.getNombre());
            if (nuevoNom == null || nuevoNom.trim().isEmpty()) return;

            String nuevaContra = JOptionPane.showInputDialog(this, "Ingrese nueva contraseña:", docenteLogueado.getContrasena());
            if (nuevaContra == null || nuevaContra.trim().isEmpty()) return;

            try {
                int index = usuarioDao.listar().indexOf(docenteLogueado);
                docenteLogueado.setNombre(nuevoNom.trim());
                docenteLogueado.setContrasena(nuevaContra.trim());

                if (index != -1) {
                    usuarioDao.editar(index, docenteLogueado);
                }

                jLabel1.setText("MathFlow - Panel de Docente (" + docenteLogueado.getNombre() + ")");
                setTitle("MathFlow - Docente: " + docenteLogueado.getNombre());
                JOptionPane.showMessageDialog(this, "Perfil actualizado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } catch (DAOException ex) {
                JOptionPane.showMessageDialog(this, "Error al actualizar perfil: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // 6. Cerrar Sesión
        jButton3.addActionListener(e -> {
            try {
                LoginFrame login = new LoginFrame();
                login.setVisible(true);
            } catch (Exception ex) {
                System.err.println("Redirigiendo: " + ex.getMessage());
            }
            this.dispose();
        });
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jLabel1 = new JLabel();
        jButton1 = new JButton();
        jButtonEjercicio = new JButton();
        jButtonConcepto = new JButton();
        jButtonPista = new JButton();
        jButton2 = new JButton();
        jButton3 = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new Font("Dialog", 1, 14));
        jLabel1.setText("MathFlow - Panel de Docente");

        jButton1.setText("Gestionar Temas Académicos");
        jButtonEjercicio.setText("Crear Ejercicio");
        jButtonConcepto.setText("Agregar Concepto Teórico");
        jButtonPista.setText("Agregar Pista a Ejercicio");
        jButton2.setText("Modificar Perfil");
        jButton3.setText("Cerrar Sesión");

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel1, GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                                        .addComponent(jButton1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButtonEjercicio, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButtonConcepto, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButtonPista, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(50, Short.MAX_VALUE))
        );

        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(jLabel1)
                                .addGap(25, 25, 25)
                                .addComponent(jButton1)
                                .addGap(12, 12, 12)
                                .addComponent(jButtonEjercicio)
                                .addGap(12, 12, 12)
                                .addComponent(jButtonConcepto)
                                .addGap(12, 12, 12)
                                .addComponent(jButtonPista)
                                .addGap(20, 20, 20)
                                .addComponent(jButton2)
                                .addGap(12, 12, 12)
                                .addComponent(jButton3)
                                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }

    public static void main(String args[]) {
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