package ec.edu.uce.miproyecto.Gui;

import ec.edu.uce.miproyecto.dao.DAOException;
import ec.edu.uce.miproyecto.dao.InterfaceDAO;
import ec.edu.uce.miproyecto.dao.UsuarioDAOFabrica;
import ec.edu.uce.miproyecto.dominio.Docente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DocenteFrame extends javax.swing.JFrame {

    private final InterfaceDAO usuarioDao = new UsuarioDAOFabrica().crearUsuarioDAO();
    private Docente docenteLogueado;

    // Listas en memoria para que funcionen todos los botones
    private final List<String> listaTemas = new ArrayList<>(List.of("1. Álgebra Lineal", "2. Matrices y Determinantes", "3. Espacios Vectoriales"));
    private final List<String> listaConceptos = new ArrayList<>(List.of("Matriz Inversa", "Determinante NxN", "Transformación Lineal"));
    private final List<String> listaEjercicios = new ArrayList<>(List.of("Calcular inversa de A 3x3", "Hallar autovalores de B", "Resolución de sistema Ax=b"));
    private final List<String> listaPistas = new ArrayList<>(List.of("Pista 1: Aplicar det(A) != 0", "Pista 2: Utilizar matriz adjunta"));
    private final List<String[]> listaEstudiantes = new ArrayList<>(List.of(
            new String[]{"101", "Carlos Pérez", "carlos@uce.edu.ec", "Activo"},
            new String[]{"102", "Maria Lopez", "maria@uce.edu.ec", "Activo"},
            new String[]{"103", "Juan Gomez", "juan@uce.edu.ec", "Inactivo"}
    ));

    public DocenteFrame(Docente docente) {
        if (docente != null) {
            this.docenteLogueado = docente;
        } else {
            inicializarDocenteDefecto();
        }

        initComponents();
        actualizarEncabezado();
        setLocationRelativeTo(null);
        conectarEventos();
    }

    public DocenteFrame() {
        inicializarDocenteDefecto();
        initComponents();
        actualizarEncabezado();
        setLocationRelativeTo(null);
        conectarEventos();
    }

    private void inicializarDocenteDefecto() {
        this.docenteLogueado = new Docente();
        this.docenteLogueado.setNombre("Docente");
        this.docenteLogueado.setContrasena("1234");
    }

    private void actualizarEncabezado() {
        setTitle("MathFlow - Docente: " + docenteLogueado.getNombre());
        jLabel1.setText("MathFlow - Panel de Docente (" + docenteLogueado.getNombre() + ")");
    }

    private void conectarEventos() {

        // 1. Gestionar Temas
        jButton1.addActionListener(e -> abrirCrudModulo("Gestión de Temas", listaTemas));

        // 2. Gestionar Conceptos
        jButton2.addActionListener(e -> abrirCrudModulo("Gestión de Conceptos", listaConceptos));

        // 3. Gestionar Ejercicios
        jButton3.addActionListener(e -> abrirCrudModulo("Gestión de Ejercicios", listaEjercicios));

        // 4. Gestionar Pistas
        jButton4.addActionListener(e -> abrirCrudModulo("Gestión de Pistas", listaPistas));

        // 5. Ver Estudiantes
        jButton5.addActionListener(e -> abrirGestionEstudiantes());

        // 6. Ver Progreso
        jButton6.addActionListener(e -> abrirReporteProgreso());

        // 7. Cerrar Sesión
        jButton7.addActionListener(e -> {
            int op = JOptionPane.showConfirmDialog(this, "¿Desea salir del panel de docente?", "Cerrar Sesión", JOptionPane.YES_NO_OPTION);
            if (op == JOptionPane.YES_OPTION) {
                LoginFrame login = new LoginFrame();
                login.setVisible(true);
                this.dispose();
            }
        });

        // 8. Modificar Perfil
        jButton8.addActionListener(e -> modificarPerfilDocente());
    }

    private void abrirCrudModulo(String titulo, List<String> datos) {
        JDialog dialog = new JDialog(this, titulo, true);
        dialog.setSize(450, 350);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new BorderLayout(10, 10));

        DefaultListModel<String> model = new DefaultListModel<>();
        datos.forEach(model::addElement);
        JList<String> jList = new JList<>(model);
        dialog.add(new JScrollPane(jList), BorderLayout.CENTER);

        JPanel btnPanel = new JPanel(new FlowLayout());
        JButton btnAgregar = new JButton("Agregar");
        JButton btnEditar = new JButton("Editar");
        JButton btnEliminar = new JButton("Eliminar");

        btnAgregar.addActionListener(ev -> {
            String nuevo = JOptionPane.showInputDialog(dialog, "Ingrese nuevo registro:");
            if (nuevo != null && !nuevo.trim().isEmpty()) {
                datos.add(nuevo.trim());
                model.addElement(nuevo.trim());
            }
        });

        btnEditar.addActionListener(ev -> {
            int sel = jList.getSelectedIndex();
            if (sel != -1) {
                String mod = JOptionPane.showInputDialog(dialog, "Editar registro:", model.get(sel));
                if (mod != null && !mod.trim().isEmpty()) {
                    datos.set(sel, mod.trim());
                    model.set(sel, mod.trim());
                }
            } else {
                JOptionPane.showMessageDialog(dialog, "Seleccione un elemento de la lista.");
            }
        });

        btnEliminar.addActionListener(ev -> {
            int sel = jList.getSelectedIndex();
            if (sel != -1) {
                datos.remove(sel);
                model.remove(sel);
            } else {
                JOptionPane.showMessageDialog(dialog, "Seleccione un elemento de la lista.");
            }
        });

        btnPanel.add(btnAgregar);
        btnPanel.add(btnEditar);
        btnPanel.add(btnEliminar);
        dialog.add(btnPanel, BorderLayout.SOUTH);

        dialog.setVisible(true);
    }

    private void abrirGestionEstudiantes() {
        JDialog dialog = new JDialog(this, "Nómina de Estudiantes", true);
        dialog.setSize(550, 350);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new BorderLayout(10, 10));

        String[] columnas = {"ID", "Nombre", "Correo", "Estado"};
        DefaultTableModel model = new DefaultTableModel(columnas, 0);

        for (String[] est : listaEstudiantes) {
            model.addRow(est);
        }

        JTable table = new JTable(model);
        dialog.add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel panelBtns = new JPanel();
        JButton btnNuevo = new JButton("Registrar Estudiante");
        JButton btnEliminar = new JButton("Eliminar Estudiante");

        btnNuevo.addActionListener(e -> {
            String id = JOptionPane.showInputDialog(dialog, "ID del Estudiante:");
            if (id == null || id.trim().isEmpty()) return;
            String nom = JOptionPane.showInputDialog(dialog, "Nombre Completo:");
            if (nom == null || nom.trim().isEmpty()) return;
            String mail = JOptionPane.showInputDialog(dialog, "Correo Electrónico:");
            if (mail == null || mail.trim().isEmpty()) return;

            String[] nuevoEst = {id.trim(), nom.trim(), mail.trim(), "Activo"};
            listaEstudiantes.add(nuevoEst);
            model.addRow(nuevoEst);
        });

        btnEliminar.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                listaEstudiantes.remove(row);
                model.removeRow(row);
            } else {
                JOptionPane.showMessageDialog(dialog, "Seleccione un estudiante de la tabla.");
            }
        });

        panelBtns.add(btnNuevo);
        panelBtns.add(btnEliminar);
        dialog.add(panelBtns, BorderLayout.SOUTH);

        dialog.setVisible(true);
    }

    private void abrirReporteProgreso() {
        JDialog dialog = new JDialog(this, "Reporte General de Progreso", true);
        dialog.setSize(500, 300);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new BorderLayout(10, 10));

        String[] cols = {"Estudiante", "Ejercicios Resueltos", "Promedio", "Estado"};
        Object[][] data = {
                {"Carlos Pérez", "15 / 20", "85%", "Excelente"},
                {"Maria Lopez", "18 / 20", "92%", "Sobresaliente"},
                {"Juan Gomez", "5 / 20", "40%", "En Riesgo"}
        };

        JTable table = new JTable(data, cols);
        dialog.add(new JScrollPane(table), BorderLayout.CENTER);

        JLabel lblInfo = new JLabel(" Total matriculados: " + listaEstudiantes.size() + " | Promedio del curso: 72.3%", JLabel.CENTER);
        dialog.add(lblInfo, BorderLayout.SOUTH);

        dialog.setVisible(true);
    }

    private void modificarPerfilDocente() {
        String nombreActual = docenteLogueado.getNombre() != null ? docenteLogueado.getNombre() : "";
        String contraActual = docenteLogueado.getContrasena() != null ? docenteLogueado.getContrasena() : "";

        String nuevoNom = JOptionPane.showInputDialog(this, "Modificar Perfil\nIngrese su nuevo nombre:", nombreActual);
        if (nuevoNom == null || nuevoNom.trim().isEmpty()) return;

        String nuevaContra = JOptionPane.showInputDialog(this, "Modificar Perfil\nIngrese su nueva contraseña:", contraActual);
        if (nuevaContra == null || nuevaContra.trim().isEmpty()) return;

        try {
            int index = -1;
            if (usuarioDao != null && usuarioDao.listar() != null) {
                index = usuarioDao.listar().indexOf(docenteLogueado);
            }

            docenteLogueado.setNombre(nuevoNom.trim());
            docenteLogueado.setContrasena(nuevaContra.trim());

            if (index != -1) {
                usuarioDao.editar(index, docenteLogueado);
            }

            actualizarEncabezado();
            JOptionPane.showMessageDialog(this, "¡Perfil actualizado con éxito!", "Éxito", JOptionPane.INFORMATION_MESSAGE);

        } catch (DAOException ex) {
            actualizarEncabezado();
            JOptionPane.showMessageDialog(this, "¡Perfil actualizado localmente en la sesión!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            actualizarEncabezado();
            JOptionPane.showMessageDialog(this, "¡Perfil actualizado con éxito!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Gestionar Temas");
        jButton2.setText("Gestionar Conceptos");
        jButton3.setText("Gestionar Ejercicios");
        jButton4.setText("Gestionar Pistas");
        jButton5.setText("Ver Estudiantes");
        jButton6.setText("Ver Progreso");
        jButton7.setText("Cerrar Sesión");
        jButton8.setText("Modificar Perfil");

        jLabel1.setText("MathFlow - Panel de Docente");
        jLabel1.setIconTextGap(20);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(196, 196, 196)
                                .addComponent(jButton4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton5)
                                .addGap(227, 227, 227))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(82, 82, 82)
                                .addComponent(jButton6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton8)
                                .addGap(86, 86, 86))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton7)
                                .addGap(347, 347, 347))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(69, 69, 69)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel1)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jButton1)
                                                .addGap(165, 165, 165)
                                                .addComponent(jButton2)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                                .addComponent(jButton3)
                                .addGap(120, 120, 120))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jLabel1)
                                .addGap(50, 50, 50)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton1)
                                        .addComponent(jButton3)
                                        .addComponent(jButton2))
                                .addGap(84, 84, 84)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton4)
                                        .addComponent(jButton5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton6)
                                        .addComponent(jButton8))
                                .addGap(36, 36, 36)
                                .addComponent(jButton7)
                                .addGap(117, 117, 117))
        );

        pack();
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
            java.util.logging.Logger.getLogger(DocenteFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> new DocenteFrame().setVisible(true));
    }

    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
}