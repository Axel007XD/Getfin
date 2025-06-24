package org.getfin.vistas;

import org.getfin.controlador.AnimalAgrupadoController;
import org.getfin.controlador.AnimalController;
import org.getfin.modelos.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class FormularioIAnimales extends JFrame {

    private final AnimalController animalController;
    private final AnimalAgrupadoController agrupadoController;

    private List<Animal> animalesDisponibles;
    private List<AnimalAgrupado> gruposDisponibles;

    // Variables Swing
    private JComboBox<String> comboId;
    private JLabel lblCantidad;
    private JTextField txtCantidad;
    private JTextField txtFecha;
    private JTextField txtNumeroFactura;
    private JTextField txtPrecio;
    private JTextField txtPeso;
    private JTextField txtChapa;
    private JRadioButton radioIndividual;
    private JRadioButton radioGrupo;
    private ButtonGroup buttonGroup;

    public FormularioIAnimales() {
        this.animalController = AnimalController.getInstance();
        this.agrupadoController = AnimalAgrupadoController.getInstance();

        initUI();
        initCustomComponents();
        cargarDatosDeBD();
    }

    private void initUI() {
        setTitle("Formulario de Ingreso de Animales");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(0, 2));

        comboId = new JComboBox<>();
        lblCantidad = new JLabel("Cantidad:");
        txtCantidad = new JTextField();
        txtFecha = new JTextField(LocalDate.now().toString());
        txtNumeroFactura = new JTextField();
        txtPrecio = new JTextField();
        txtPeso = new JTextField();
        txtChapa = new JTextField();

        radioIndividual = new JRadioButton("Individual");
        radioGrupo = new JRadioButton("Grupo");
        buttonGroup = new ButtonGroup();
        buttonGroup.add(radioIndividual);
        buttonGroup.add(radioGrupo);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> guardarIngreso());

        add(new JLabel("Tipo:"));
        add(radioIndividual);
        add(new JLabel());
        add(radioGrupo);
        add(new JLabel("ID Animal/Grupo:"));
        add(comboId);
        add(lblCantidad);
        add(txtCantidad);
        add(new JLabel("Fecha:"));
        add(txtFecha);
        add(new JLabel("Factura:"));
        add(txtNumeroFactura);
        add(new JLabel("Precio:"));
        add(txtPrecio);
        add(new JLabel("Peso:"));
        add(txtPeso);
        add(new JLabel("Chapa:"));
        add(txtChapa);
        add(new JLabel());
        add(btnGuardar);
    }

    private void initCustomComponents() {
        configurarComboBusqueda();
        radioIndividual.setSelected(true);
        toggleCampoCantidad(false);

        radioIndividual.addItemListener(e -> {
            toggleCampoCantidad(e.getStateChange() == ItemEvent.DESELECTED);
            actualizarOpcionesBusqueda();
        });

        radioGrupo.addItemListener(e -> {
            toggleCampoCantidad(e.getStateChange() == ItemEvent.SELECTED);
            actualizarOpcionesBusqueda();
        });
    }

    private void cargarDatosDeBD() {
        animalesDisponibles = animalController.getAnimales();
        gruposDisponibles = agrupadoController.getClientes();
        actualizarOpcionesBusqueda();
    }

    private void configurarComboBusqueda() {
        comboId.setEditable(true);
        JTextField editor = (JTextField) comboId.getEditor().getEditorComponent();
        editor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                filterComboItems(editor.getText());
            }
        });
    }

    private void filterComboItems(String filter) {
        comboId.removeAllItems();
        List<String> items;

        if (radioIndividual.isSelected()) {
            items = animalesDisponibles.stream()
                    .map(Animal::getIdAnimal)
                    .map(Object::toString)
                    .filter(codigo -> codigo.toLowerCase().contains(filter.toLowerCase()))
                    .collect(Collectors.toList());
        } else {
            items = gruposDisponibles.stream()
                    .map(AnimalAgrupado::getIdAgrupacion)
                    .map(Object::toString)
                    .filter(codigo -> codigo.toLowerCase().contains(filter.toLowerCase()))
                    .collect(Collectors.toList());
        }

        for (String item : items) comboId.addItem(item);
        comboId.setPopupVisible(true);
    }

    private void toggleCampoCantidad(boolean visible) {
        lblCantidad.setVisible(visible);
        txtCantidad.setVisible(visible);
        revalidate();
        repaint();
    }

    private void actualizarOpcionesBusqueda() {
        comboId.removeAllItems();
        List<String> items = (radioIndividual.isSelected() ? animalesDisponibles : gruposDisponibles)
                .stream().map(obj -> radioIndividual.isSelected()
                        ? String.valueOf(((Animal) obj).getIdAnimal())
                        : String.valueOf(((AnimalAgrupado) obj).getIdAgrupacion()))
                .collect(Collectors.toList());

        for (String item : items) comboId.addItem(item);
    }

    private void guardarIngreso() {
        try {
            if (comboId.getSelectedItem() == null || comboId.getSelectedItem().toString().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar un animal/grupo", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (radioGrupo.isSelected() && txtCantidad.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe ingresar la cantidad para grupos", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Ingreso ingreso = new Ingreso();
            ingreso.setFecha(LocalDate.parse(txtFecha.getText()));
            ingreso.setTipoIngreso(Ingreso.TipoIngreso.venta_animal);
            ingreso.setNumeroFactura(txtNumeroFactura.getText());

            DetalleIngreso detalle = new DetalleIngreso();
            detalle.setIngreso(ingreso);

            if (radioIndividual.isSelected()) {
                Animal animal = animalesDisponibles.stream()
                        .filter(a -> a.getIdAnimal().equals(comboId.getSelectedItem().toString()))
                        .findFirst().orElse(null);
                if (animal != null) {
                    detalle.setAnimal(animal);
                    detalle.setCantidad(BigDecimal.ONE);
                }
            } else {
                AnimalAgrupado grupo = gruposDisponibles.stream()
                        .filter(g -> g.getIdAgrupacion().equals(comboId.getSelectedItem().toString()))
                        .findFirst().orElse(null);
                if (grupo != null) {
                    detalle.setAgrupacion(grupo);
                    detalle.setCantidad(new BigDecimal(txtCantidad.getText()));
                }
            }

            detalle.setPrecioUnitario(new BigDecimal(txtPrecio.getText()));
            ingreso.setDetalles(List.of(detalle));

            JOptionPane.showMessageDialog(this, "Registro guardado exitosamente");
            limpiarFormulario();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al guardar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarFormulario() {
        txtNumeroFactura.setText("");
        txtPrecio.setText("");
        txtPeso.setText("");
        txtChapa.setText("");
        txtCantidad.setText("");
        actualizarOpcionesBusqueda();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FormularioIAnimales().setVisible(true));
    }
}
