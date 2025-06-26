package org.getfin.vistas;

import org.getfin.controlador.AnimalAgrupadoController;
import org.getfin.controlador.AnimalController;
import org.getfin.modelos.Animal;
import org.getfin.modelos.AnimalAgrupado;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class FormularioIAnimales extends JFrame {
    private RegistroIngreso registroIngreso;
    private RegistroIngreso.RegistroData registroAEditar = null;

    private JTextField txtCantidad, txtFecha, txtFactura, txtPrecio, txtPeso, txtChapa;
    private JComboBox<String> animalesId, tiposAnimales;

    private AnimalController animalController;
    private AnimalAgrupadoController animalAgrupadoController;
    private List<String> idsAnimales;

    public FormularioIAnimales(RegistroIngreso registroIngreso) {
        this.registroIngreso = registroIngreso;
        initComponents();
    }

    public void setRegistroAEditar(RegistroIngreso.RegistroData registroAEditar) {
        this.registroAEditar = registroAEditar;
    }

    private void initComponents() {
        setTitle("Formulario Ingreso Animal");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(0, 2, 5, 5));

        String[] tipos = {"", "Animal Individual", "Animal en Grupo"};
        tiposAnimales = new JComboBox<>(tipos);

        animalesId = new JComboBox<>();
        txtCantidad = new JTextField();
        txtFecha = new JTextField();
        txtFactura = new JTextField();
        txtPrecio = new JTextField();
        txtPeso = new JTextField();
        txtChapa = new JTextField();

        // Tipo animal: Individual o en grupo
        tiposAnimales.addActionListener(e -> actualizarListaAnimales());

        // Selección de ID animal
        animalesId.addActionListener(e -> {
            if (tiposAnimales.getSelectedItem().equals("Animal Individual")) {
                String id = (String) animalesId.getSelectedItem();
                if (id != null && animalController != null) {
                    Animal a = animalController.getAnimales().stream()
                            .filter(animal -> animal.getIdAnimal().toString().equals(id))
                            .findFirst().orElse(null);
                    if (a != null) {
                        txtChapa.setText(a.getChapa());
                        txtCantidad.setText("1");
                        txtCantidad.setEnabled(false);
                    }
                }
            } else if (tiposAnimales.getSelectedItem().equals("Animal en Grupo")) {
                String id = (String) animalesId.getSelectedItem();
                if (id != null && animalAgrupadoController != null) {
                    AnimalAgrupado grupo = animalAgrupadoController.getClientes().stream()
                            .filter(g -> g.getIdAgrupacion().toString().equals(id))
                            .findFirst().orElse(null);
                    if (grupo != null) {
                        txtChapa.setText("Grupo");
                        txtCantidad.setEnabled(false);
                        txtCantidad.setEnabled(true);
                        txtCantidad.setToolTipText("Disponible: " + grupo.getCantidad());
                    }
                }
            }
        });

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> guardar());

        add(new JLabel("Tipo:")); add(tiposAnimales);
        add(new JLabel("ID Animal/Grupo:")); add(animalesId);
        add(new JLabel("Cantidad:")); add(txtCantidad);
        add(new JLabel("Fecha:")); add(txtFecha);
        add(new JLabel("Factura:")); add(txtFactura);
        add(new JLabel("Precio:")); add(txtPrecio);
        add(new JLabel("Peso:")); add(txtPeso);
        add(new JLabel("Chapa:")); add(txtChapa);
        add(new JLabel()); add(btnGuardar);
    }

    private void actualizarListaAnimales() {
        String tipoSeleccionado = (String) tiposAnimales.getSelectedItem();
        txtChapa.setText("");
        txtCantidad.setText("");
        animalesId.removeAllItems();

        if ("Animal Individual".equals(tipoSeleccionado)) {
            animalController = AnimalController.getInstance();
            idsAnimales = animalController.getAnimales().stream()
                    .map(animal -> animal.getIdAnimal().toString())
                    .toList();
            txtCantidad.setText("1");
            txtCantidad.setEnabled(false);
        } else if ("Animal en Grupo".equals(tipoSeleccionado)) {
            animalAgrupadoController = AnimalAgrupadoController.getInstance();
            idsAnimales = animalAgrupadoController.getClientes().stream()
                    .map(animal -> animal.getIdAgrupacion().toString())
                    .toList();
            txtCantidad.setEnabled(true);
        }

        for (String id : idsAnimales) {
            animalesId.addItem(id);
        }
    }

    private void guardar() {
        // Validaciones básicas
        if (tiposAnimales.getSelectedIndex() == 0 || animalesId.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un tipo e ID de animal.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String tipo = (String) tiposAnimales.getSelectedItem();
        String id = (String) animalesId.getSelectedItem();
        String cantidad = txtCantidad.getText().trim();

        if ("Animal en Grupo".equals(tipo)) {
            try {
                int cantidadIngresada = Integer.parseInt(cantidad);
                AnimalAgrupado grupo = animalAgrupadoController.getClientes().stream()
                        .filter(g -> g.getIdAgrupacion().toString().equals(id))
                        .findFirst().orElse(null);
                if (grupo != null && cantidadIngresada > grupo.getCantidad()) {
                    JOptionPane.showMessageDialog(this, "La cantidad ingresada excede la disponible.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Ingrese una cantidad válida.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        List<String> headers = List.of("ID Animal/Grupo", "Cantidad", "Fecha", "Factura", "Precio", "Peso", "Chapa");
        List<String> values = List.of(
                id,
                cantidad,
                txtFecha.getText(),
                txtFactura.getText(),
                txtPrecio.getText(),
                txtPeso.getText(),
                txtChapa.getText()
        );

        if (registroAEditar != null) {
            registroIngreso.actualizarRegistro(registroAEditar, values);
            JOptionPane.showMessageDialog(this, "Registro actualizado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            registroIngreso.agregarRegistro(headers, values, "animal");
            JOptionPane.showMessageDialog(this, "Registro agregado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }

        this.dispose();
    }

    public void cargarDatos(List<String> datos) {
        if (datos.size() >= 7) {
            txtCantidad.setText(datos.get(1));
            txtFecha.setText(datos.get(2));
            txtFactura.setText(datos.get(3));
            txtPrecio.setText(datos.get(4));
            txtPeso.setText(datos.get(5));
            txtChapa.setText(datos.get(6));
        }
    }
}
