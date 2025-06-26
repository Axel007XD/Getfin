package org.getfin.vistas;

import org.getfin.controlador.IngresoController;
import org.getfin.modelos.Ingreso;
import org.getfin.util.HibernateUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class IngresoVista extends JPanel {

    private static final Logger logger = Logger.getLogger(IngresoVista.class.getName());

    // Componentes
    private JPanel jpnBarragris;
    private JPanel jPanelBotones;
    private JLabel lblImagen;
    private JLabel lblIngreso;
    private JScrollPane jScrollPane1;
    private JTable tblTablaingreso;
    private JButton btnIngresar;
    private JButton btnEditar;
    private JButton btnEliminar;

    public IngresoVista() {
        initComponents();
        actualizarTabla();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Barra superior
        jpnBarragris = new JPanel();
        jpnBarragris.setBackground(new Color(65, 68, 75));
        jpnBarragris.setLayout(new BorderLayout(10, 10));

        lblIngreso = new JLabel("Ingreso");
        lblIngreso.setFont(new Font("Open Sans", Font.PLAIN, 24));
        lblIngreso.setForeground(Color.WHITE);

        lblImagen = new JLabel(new ImageIcon(getClass().getResource("/Close.png")));
        lblImagen.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblImagen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblImagenMouseClicked(evt);
            }
        });

        jpnBarragris.add(lblIngreso, BorderLayout.WEST);
        jpnBarragris.add(lblImagen, BorderLayout.EAST);
        add(jpnBarragris, BorderLayout.NORTH);

        // Tabla de ingresos
        tblTablaingreso = new JTable();
        tblTablaingreso.setModel(new DefaultTableModel(
                new Object[][] {},
                new String[] { "Id", "Fecha", "Tipo", "Factura", "Cliente", "Descripción", "Base", "IVA", "Retención", "Total" }
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        tblTablaingreso.getTableHeader().setReorderingAllowed(false);
        tblTablaingreso.getTableHeader().setResizingAllowed(false);
        tblTablaingreso.getTableHeader().setBackground(new Color(189, 195, 199));
        tblTablaingreso.getTableHeader().setForeground(Color.BLACK);
        tblTablaingreso.getTableHeader().setFont(new Font("Open Sans", Font.BOLD, 14));
        tblTablaingreso.setRowHeight(25);
        tblTablaingreso.setShowGrid(true);
        tblTablaingreso.setGridColor(Color.LIGHT_GRAY);

        jScrollPane1 = new JScrollPane(tblTablaingreso);
        add(jScrollPane1, BorderLayout.CENTER);

        // Botones inferiores
        jPanelBotones = new JPanel();
        jPanelBotones.setBackground(new Color(245, 245, 245));
        jPanelBotones.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        btnIngresar = new JButton("Nuevo ingreso");
        btnIngresar.setBackground(new Color(17, 143, 55));
        btnIngresar.setForeground(Color.WHITE);
        btnIngresar.setFont(new Font("Open Sans", Font.BOLD, 14));
        btnIngresar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnIngresar.addActionListener(evt -> btnIngresarActionPerformed());

        btnEditar = new JButton("Editar");
        btnEditar.setBackground(new Color(102, 117, 127));
        btnEditar.setForeground(Color.WHITE);
        btnEditar.setFont(new Font("Open Sans", Font.BOLD, 14));
        btnEditar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnEditar.addActionListener(evt -> btnEditarActionPerformed());

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBackground(new Color(186, 14, 14));
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setFont(new Font("Open Sans", Font.BOLD, 14));
        btnEliminar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnEliminar.addActionListener(evt -> btnEliminarActionPerformed());

        jPanelBotones.add(btnIngresar);
        jPanelBotones.add(btnEditar);
        jPanelBotones.add(btnEliminar);

        add(jPanelBotones, BorderLayout.SOUTH);
    }

    private void lblImagenMouseClicked(java.awt.event.MouseEvent evt) {
        try {
            System.exit(0);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al cerrar la aplicación", e);
        }
    }

    private void btnIngresarActionPerformed() {
        try {
            DefaultTableModel model = (DefaultTableModel) tblTablaingreso.getModel();
            if (model.getRowCount() > 0) {
                RegistroIngreso formulario = new RegistroIngreso();
                formulario.setVisible(true);
                actualizarTabla();
            } else {
                model.setRowCount(0);
                JOptionPane.showMessageDialog(this, "NO hay datos para mostrar, por favor ingrese un nuevo ingreso.");
                RegistroIngreso formulario = new RegistroIngreso();
                formulario.setVisible(true);
                actualizarTabla();
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al abrir formulario de nuevo ingreso", e);
            JOptionPane.showMessageDialog(this, "Error al abrir el formulario: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnEditarActionPerformed() {
        try {
            int filaSeleccionada = tblTablaingreso.getSelectedRow();
            if (filaSeleccionada >= 0) {
                DefaultTableModel model = (DefaultTableModel) tblTablaingreso.getModel();
                Object idValue = model.getValueAt(filaSeleccionada, 0);

                if (idValue == null) {
                    JOptionPane.showMessageDialog(this, "El ingreso seleccionado no tiene un ID válido.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int idIngreso = Integer.parseInt(idValue.toString());
                Ingreso ingreso = obtenerIngresoPorId(idIngreso);

                if (ingreso != null) {
                    RegistroIngreso formulario = new RegistroIngreso();
                    formulario.setVisible(true);
                    actualizarTabla();
                } else {
                    JOptionPane.showMessageDialog(this, "No se pudo obtener el ingreso seleccionado.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, seleccione un ingreso para editar.", "Información", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (NumberFormatException e) {
            logger.log(Level.WARNING, "Error al convertir ID del ingreso", e);
            JOptionPane.showMessageDialog(this, "Error al procesar el ingreso seleccionado.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al editar ingreso", e);
            JOptionPane.showMessageDialog(this, "Error al editar el ingreso: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnEliminarActionPerformed() {
        try {
            int filaSeleccionada = tblTablaingreso.getSelectedRow();
            if (filaSeleccionada >= 0) {
                int confirmacion = JOptionPane.showConfirmDialog(this,
                        "¿Está seguro de que desea eliminar el ingreso seleccionado?",
                        "Confirmación",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);

                if (confirmacion == JOptionPane.YES_OPTION) {
                    DefaultTableModel model = (DefaultTableModel) tblTablaingreso.getModel();
                    Object idValue = model.getValueAt(filaSeleccionada, 0);

                    if (idValue == null) {
                        JOptionPane.showMessageDialog(this, "El ingreso seleccionado no tiene un ID válido.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    int idIngreso = Integer.parseInt(idValue.toString());
                    Ingreso ingreso = obtenerIngresoPorId(idIngreso);

                    if (ingreso != null) {
                        IngresoController.getInstance().eliminarIngreso(ingreso);
                        actualizarTabla();
                        JOptionPane.showMessageDialog(this, "Ingreso eliminado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "No se pudo obtener el ingreso seleccionado.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, seleccione un ingreso para eliminar.", "Información", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (NumberFormatException e) {
            logger.log(Level.WARNING, "Error al convertir ID del ingreso", e);
            JOptionPane.showMessageDialog(this, "Error al procesar el ingreso seleccionado.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al eliminar ingreso", e);
            JOptionPane.showMessageDialog(this, "Error al eliminar el ingreso: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private Ingreso obtenerIngresoPorId(int idIngreso) {
        try {
            return IngresoController.getInstance().getIngresos().stream()
                    .filter(i -> i.getIdIngreso() != null && i.getIdIngreso().equals(idIngreso))
                    .findFirst()
                    .orElse(null);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al obtener ingreso por ID: " + idIngreso, e);
            return null;
        }
    }

    private void actualizarTabla() {
        try {
            if (!HibernateUtil.isSessionFactoryAvailable()) {
                String errorMsg = "No se puede conectar a la base de datos.\n\n" +
                        "Verifica que:\n" +
                        "1. MariaDB esté ejecutándose\n" +
                        "2. La base de datos 'Getfin' exista\n" +
                        "3. El usuario 'axel' tenga permisos\n" +
                        "4. La contraseña sea 'axel123'";

                JOptionPane.showMessageDialog(this, errorMsg, "Error de Conexión", JOptionPane.ERROR_MESSAGE);
                logger.severe("SessionFactory no disponible al actualizar tabla");
                return;
            }

            DefaultTableModel model = (DefaultTableModel) tblTablaingreso.getModel();
            model.setRowCount(0);

            logger.info("Obteniendo lista de ingresos...");
            List<Ingreso> ingresos = IngresoController.getInstance().getIngresos();

            for (Ingreso ingreso : ingresos) {
                if (ingreso != null) {
                    model.addRow(new Object[]{
                            ingreso.getIdIngreso(),
                            ingreso.getFecha(),
                            ingreso.getTipoIngreso(),
                            ingreso.getNumeroFactura(),
                            ingreso.getCliente() != null ? ingreso.getCliente().getNombre() : "N/A",
                            ingreso.getDescripcion(),
                            ingreso.getTotalBase(),
                            ingreso.getTotalIva(),
                            ingreso.getTotalRetencion(),
                            ingreso.getTotalFinal()
                    });
                }
            }

            logger.info("Tabla actualizada exitosamente con " + ingresos.size() + " registros");

        } catch (RuntimeException e) {
            String errorMsg = "Error al actualizar la tabla:\n\n";

            if (e.getMessage().contains("conexion") || e.getMessage().contains("base de datos")) {
                errorMsg += "Problema de conexión a la base de datos.\n\n" +
                        "Verifica que:\n" +
                        "1. MariaDB esté ejecutándose\n" +
                        "2. La base de datos 'Getfin' exista\n" +
                        "3. El usuario 'axel' tenga permisos\n" +
                        "4. La contraseña sea 'axel123'\n\n" +
                        "Error técnico: " + e.getMessage();
            } else {
                errorMsg += e.getMessage();
            }

            JOptionPane.showMessageDialog(this, errorMsg, "Error al Actualizar Tabla", JOptionPane.ERROR_MESSAGE);
            logger.log(Level.SEVERE, "Error al actualizar tabla de ingresos", e);
        } catch (Exception e) {
            String errorMsg = "Error inesperado al actualizar la tabla:\n" + e.getMessage();
            JOptionPane.showMessageDialog(this, errorMsg, "Error Inesperado", JOptionPane.ERROR_MESSAGE);
            logger.log(Level.SEVERE, "Error inesperado al actualizar tabla de ingresos", e);
        }
    }

    // Método de prueba
    public static void main(String[] args) {
        JFrame frame = new JFrame("Ingreso Panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new IngresoVista());
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}