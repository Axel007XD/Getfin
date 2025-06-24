package org.getfin.vistas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
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
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        jpnBarragris = new JPanel();
        jPanelBotones = new JPanel();
        lblImagen = new JLabel();
        lblIngreso = new JLabel();
        jScrollPane1 = new JScrollPane();
        tblTablaingreso = new JTable();
        btnIngresar = new JButton();
        btnEditar = new JButton();
        btnEliminar = new JButton();

        setBackground(Color.WHITE);

        // Barra superior
        jpnBarragris.setBackground(new Color(65, 68, 75));
        lblIngreso.setFont(new Font("Open Sans", Font.PLAIN, 24));
        lblIngreso.setForeground(Color.WHITE);
        lblIngreso.setText("Ingreso");

        lblImagen.setIcon(new ImageIcon(getClass().getResource("/Close.png")));

        jpnBarragris.setLayout(new BorderLayout(10, 10));
        jpnBarragris.add(lblIngreso, BorderLayout.WEST);
        jpnBarragris.add(lblImagen, BorderLayout.EAST);
        add(jpnBarragris, BorderLayout.NORTH);

        // Tabla de ingresos
        tblTablaingreso.setModel(new DefaultTableModel(
                new Object[][] {},
                new String[] { "Id", "Fecha", "Precio", "Producto", "Cantidad", "Total", "Cliente", "Descripcion" }
        ) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        jScrollPane1.setViewportView(tblTablaingreso);
        add(jScrollPane1, BorderLayout.CENTER);

        // Botones inferiores
        btnIngresar.setBackground(new Color(17, 143, 55));
        btnIngresar.setForeground(Color.WHITE);
        btnIngresar.setText("Nuevo ingreso");
        btnIngresar.addActionListener(evt -> btnIngresarActionPerformed());

        btnEditar.setBackground(new Color(102, 117, 127));
        btnEditar.setForeground(Color.WHITE);
        btnEditar.setText("Editar");
        btnEditar.addActionListener(evt -> btnEditarActionPerformed());

        btnEliminar.setBackground(new Color(186, 14, 14));
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setText("Eliminar");

        jPanelBotones.setBackground(new Color(245, 245, 245));
        jPanelBotones.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        jPanelBotones.add(btnIngresar);
        jPanelBotones.add(btnEditar);
        jPanelBotones.add(btnEliminar);

        add(jPanelBotones, BorderLayout.SOUTH);
    }

    private void btnIngresarActionPerformed() {

        Registroingreso registroingreso = new Registroingreso();
        registroingreso.setVisible(true);

//        DefaultTableModel model = (DefaultTableModel) tblTablaingreso.getModel();
//        if (model.getRowCount() > 0) {
//            model.addRow(new Object[] { null, null, null, null, null, null, null, null });
//        } else {
//            model.setRowCount(0);
//            JOptionPane.showMessageDialog(this, "NO hay datos para mostrar, por favor ingrese un nuevo ingreso.");
//        }
    }

    private void btnEditarActionPerformed() {
        JOptionPane.showMessageDialog(this, "Editar en desarrollo");
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