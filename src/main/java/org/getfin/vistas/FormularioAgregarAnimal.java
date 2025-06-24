package org.getfin.vistas;

import org.getfin.controlador.AnimalController;
import org.getfin.controlador.ClienteController;
import org.getfin.modelos.Animal;
import org.getfin.modelos.Cliente;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FormularioAgregarAnimal extends javax.swing.JFrame {

    private static final Logger logger = Logger.getLogger(FormularioAgregarAnimal.class.getName());
    private final AnimalController animalController;
    private final ClienteController clienteController;
    private List<Animal> animalesDisponibles;
    private List<Cliente> clientesDisponibles;

    public FormularioAgregarAnimal() {
        this.animalController = AnimalController.getInstance();
        this.clienteController = ClienteController.getInstance(); // Asume que existe ClienteController
        initComponents();
        initCustomComponents();
    }

    private void initCustomComponents() {
        cargarDatos();
        configurarAutocompletadoCodigo();
        configurarAutocompletadoCliente();
        txtFecha.setText(LocalDate.now().toString());
    }

    private void cargarDatos() {
        try {
            // Cargar animales
            animalesDisponibles = animalController.getAnimales();
            if (animalesDisponibles == null || animalesDisponibles.isEmpty()) {
                logger.warning("No se encontraron animales en la base de datos");
            }

            // Cargar clientes
            clientesDisponibles = clienteController.getClientes(); // Asume método getClientes()
            if (clientesDisponibles == null || clientesDisponibles.isEmpty()) {
                logger.warning("No se encontraron clientes en la base de datos");
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al cargar datos", e);
            JOptionPane.showMessageDialog(this,
                    "Error al cargar datos iniciales",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void configurarAutocompletadoCodigo() {
        // Configurar el combo box para el código de animal
        comboCodigo.setEditable(true);

        // Llenar con códigos disponibles
        animalesDisponibles.forEach(animal ->
                comboCodigo.addItem(String.valueOf(animal.getIdAnimal()))
        );

        // Configurar autocompletado
        JTextField editorCodigo = (JTextField) comboCodigo.getEditor().getEditorComponent();
        editorCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() != KeyEvent.VK_ENTER &&
                        evt.getKeyCode() != KeyEvent.VK_UP &&
                        evt.getKeyCode() != KeyEvent.VK_DOWN) {
                    buscarAnimalPorCodigo(editorCodigo.getText());
                }
            }
        });

        // Buscar cuando se selecciona un item
        comboCodigo.addActionListener(e -> {
            if (comboCodigo.getSelectedItem() != null) {
                buscarAnimalPorCodigo(comboCodigo.getSelectedItem().toString());
            }
        });
    }

    private void configurarAutocompletadoCliente() {
        // Configurar el combo box para el cliente
        comboCliente.setEditable(true);

        // Llenar con nombres de clientes disponibles

        // Configurar autocompletado
        JTextField editorCliente = (JTextField) comboCliente.getEditor().getEditorComponent();
        editorCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() != KeyEvent.VK_ENTER &&
                        evt.getKeyCode() != KeyEvent.VK_UP &&
                        evt.getKeyCode() != KeyEvent.VK_DOWN) {
                    // Filtrar mientras escribe
                    String filter = editorCliente.getText().toLowerCase();
                    comboCliente.removeAllItems();
                    
                    comboCliente.setPopupVisible(true);
                    editorCliente.setText(filter);
                }
            }
        });
    }

    private void buscarAnimalPorCodigo(String codigo) {
        try {
            if (codigo == null || codigo.trim().isEmpty()) {
                limpiarCamposAnimal();
                return;
            }

            Animal animalEncontrado = animalesDisponibles.stream()
                    .filter(a -> String.valueOf(a.getIdAnimal()).equalsIgnoreCase(codigo))
                    .findFirst()
                    .orElse(null);

            if (animalEncontrado != null) {
                txtChapa.setText(animalEncontrado.getChapa() != null ? animalEncontrado.getChapa() : "");

            } else {
                limpiarCamposAnimal();
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al buscar animal", e);
            JOptionPane.showMessageDialog(this,
                    "Error al buscar datos del animal",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCamposAnimal() {
        txtChapa.setText("");
        txtPeso.setText("");
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        lblAnimal = new javax.swing.JLabel();
        lblCliente = new javax.swing.JLabel();
        comboCliente = new javax.swing.JComboBox<>(); // Cambiado a JComboBox
        lblFecha = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        lblChapa = new javax.swing.JLabel();
        txtChapa = new javax.swing.JTextField();
        lblPeso = new javax.swing.JLabel();
        txtPeso = new javax.swing.JTextField();
        lblCodigo = new javax.swing.JLabel();
        comboCodigo = new javax.swing.JComboBox<>();
        lblPrecio = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        btnCancelar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Registro de Animales");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        lblAnimal.setFont(new java.awt.Font("Bebas Neue", 0, 36));
        lblAnimal.setText("ANIMAL");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 20, 0);
        jPanel1.add(lblAnimal, gridBagConstraints);

        lblCliente.setFont(new java.awt.Font("Open Sans", 0, 14));
        lblCliente.setText("Cliente:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 20, 5, 10);
        jPanel1.add(lblCliente, gridBagConstraints);

        comboCliente.setEditable(true);
        comboCliente.setPreferredSize(new java.awt.Dimension(200, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 10, 10);
        jPanel1.add(comboCliente, gridBagConstraints);

        lblFecha.setFont(new java.awt.Font("Open Sans", 0, 14));
        lblFecha.setText("Fecha:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 20);
        jPanel1.add(lblFecha, gridBagConstraints);

        txtFecha.setColumns(20);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 10, 20);
        jPanel1.add(txtFecha, gridBagConstraints);

        lblChapa.setFont(new java.awt.Font("Open Sans", 0, 14));
        lblChapa.setText("Chapa:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 20, 5, 10);
        jPanel1.add(lblChapa, gridBagConstraints);

        txtChapa.setColumns(20);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 10, 10);
        jPanel1.add(txtChapa, gridBagConstraints);

        lblPeso.setFont(new java.awt.Font("Open Sans", 0, 14));
        lblPeso.setText("Peso:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 20);
        jPanel1.add(lblPeso, gridBagConstraints);

        txtPeso.setColumns(20);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 10, 20);
        jPanel1.add(txtPeso, gridBagConstraints);

        lblCodigo.setFont(new java.awt.Font("Open Sans", 0, 14));
        lblCodigo.setText("Código Animal:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 20, 5, 10);
        jPanel1.add(lblCodigo, gridBagConstraints);

        comboCodigo.setEditable(true);
        comboCodigo.setPreferredSize(new java.awt.Dimension(200, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 10, 10);
        jPanel1.add(comboCodigo, gridBagConstraints);

        lblPrecio.setFont(new java.awt.Font("Open Sans", 0, 14));
        lblPrecio.setText("Precio:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 20);
        jPanel1.add(lblPrecio, gridBagConstraints);

        txtPrecio.setColumns(20);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 10, 20);
        jPanel1.add(txtPrecio, gridBagConstraints);

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(this::btnCancelarActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 20, 10);
        jPanel1.add(btnCancelar, gridBagConstraints);

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(this::btnGuardarActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.insets = new java.awt.Insets(20, 10, 20, 0);
        jPanel1.add(btnGuardar, gridBagConstraints);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
    }

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {
        // Validación y lógica para guardar
        try {
            if (validarFormulario()) {
                // Lógica para guardar en base de datos
                JOptionPane.showMessageDialog(this,
                        "Registro guardado exitosamente",
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al guardar animal", e);
            JOptionPane.showMessageDialog(this,
                    "Error al guardar el registro",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean validarFormulario() {
        if (comboCodigo.getEditor().getItem() == null ||
                comboCodigo.getEditor().getItem().toString().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Debe ingresar un código de animal",
                    "Validación",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (comboCliente.getEditor().getItem() == null ||
                comboCliente.getEditor().getItem().toString().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Debe seleccionar un cliente",
                    "Validación",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (txtPrecio.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Debe ingresar un precio",
                    "Validación",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }

        return true;
    }

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            Logger.getLogger(FormularioAgregarAnimal.class.getName()).log(Level.SEVERE, "Error al configurar look and feel", ex);
        }

        java.awt.EventQueue.invokeLater(() -> {
            new FormularioAgregarAnimal().setVisible(true);
        });
    }

    // Variables declaration - do not modify
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<String> comboCliente;
    private javax.swing.JComboBox<String> comboCodigo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblAnimal;
    private javax.swing.JLabel lblChapa;
    private javax.swing.JLabel lblCliente;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblPeso;
    private javax.swing.JLabel lblPrecio;
    private javax.swing.JTextField txtChapa;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtPeso;
    private javax.swing.JTextField txtPrecio;
    // End of variables declaration
}