package org.getfin.vistas;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class FormularioIAnimales extends JFrame {
    private RegistroIngreso registroIngreso;

    private JTextField txtTipo, txtIdAnimal, txtCantidad, txtFecha,
            txtFactura, txtPrecio, txtPeso, txtChapa;

    public FormularioIAnimales(RegistroIngreso registroIngreso) {
        this.registroIngreso = registroIngreso;
        initComponents();
    }

    private void initComponents() {
        setTitle("Formulario Ingreso Animal");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(0, 2, 5, 5));

        txtTipo = new JTextField();
        txtIdAnimal = new JTextField();
        txtCantidad = new JTextField();
        txtFecha = new JTextField();
        txtFactura = new JTextField();
        txtPrecio = new JTextField();
        txtPeso = new JTextField();
        txtChapa = new JTextField();

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> guardar());

        add(new JLabel("Tipo:")); add(txtTipo);
        add(new JLabel("ID Animal/Grupo:")); add(txtIdAnimal);
        add(new JLabel("Cantidad:")); add(txtCantidad);
        add(new JLabel("Fecha:")); add(txtFecha);
        add(new JLabel("Factura:")); add(txtFactura);
        add(new JLabel("Precio:")); add(txtPrecio);
        add(new JLabel("Peso:")); add(txtPeso);
        add(new JLabel("Chapa:")); add(txtChapa);
        add(new JLabel()); add(btnGuardar);
    }

    private void guardar() {
        List<String> headers = List.of("Tipo", "ID Animal/Grupo", "Cantidad", "Fecha", "Factura", "Precio", "Peso", "Chapa");
        List<String> values = List.of(
                txtTipo.getText(),
                txtIdAnimal.getText(),
                txtCantidad.getText(),
                txtFecha.getText(),
                txtFactura.getText(),
                txtPrecio.getText(),
                txtPeso.getText(),
                txtChapa.getText()
        );
        registroIngreso.agregarRegistro(headers, values);
        this.dispose();
    }
}
