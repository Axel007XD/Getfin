package org.getfin.vistas;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class FormularioICosecha extends JFrame {
    private RegistroIngreso registroIngreso;

    private JTextField txtFecha, txtFactura, txtNombre,
            txtMontoNeto, txtMonto, txtIva, txtRetencion, txtFinal;

    public FormularioICosecha(RegistroIngreso registroIngreso) {
        this.registroIngreso = registroIngreso;
        initComponents();
    }

    private void initComponents() {
        setTitle("Formulario Ingreso Cosecha");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(0, 2, 5, 5));

        txtFecha = new JTextField();
        txtFactura = new JTextField();
        txtNombre = new JTextField();
        txtMontoNeto = new JTextField();
        txtMonto = new JTextField();
        txtIva = new JTextField();
        txtRetencion = new JTextField();
        txtFinal = new JTextField();

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> guardar());

        add(new JLabel("Fecha:")); add(txtFecha);
        add(new JLabel("Factura:")); add(txtFactura);
        add(new JLabel("Nombre:")); add(txtNombre);
        add(new JLabel("Monto neto:")); add(txtMontoNeto);
        add(new JLabel("Monto:")); add(txtMonto);
        add(new JLabel("IVA:")); add(txtIva);
        add(new JLabel("Retención:")); add(txtRetencion);
        add(new JLabel("Monto final:")); add(txtFinal);
        add(new JLabel()); add(btnGuardar);
    }

    private void guardar() {
        List<String> headers = List.of("Fecha", "Factura", "Nombre", "Monto neto", "Monto", "IVA", "Retención", "Monto final");
        List<String> values = List.of(
                txtFecha.getText(),
                txtFactura.getText(),
                txtNombre.getText(),
                txtMontoNeto.getText(),
                txtMonto.getText(),
                txtIva.getText(),
                txtRetencion.getText(),
                txtFinal.getText()
        );
        registroIngreso.agregarRegistro(headers, values);
        this.dispose();
    }
}
