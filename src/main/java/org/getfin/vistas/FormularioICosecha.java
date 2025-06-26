package org.getfin.vistas;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class FormularioICosecha extends JFrame {
    private RegistroIngreso registroIngreso;

    private JTextField txtFecha, txtFactura, txtNombre,
            txtMontoNeto, txtMonto, txtIva, txtRetencion, txtFinal;

    private RegistroIngreso.RegistroData registroAEditar = null;

    public FormularioICosecha(RegistroIngreso registroIngreso) {
        this.registroIngreso = registroIngreso;
        initComponents();
    }

    public void setRegistroAEditar(RegistroIngreso.RegistroData registroAEditar) {
        this.registroAEditar = registroAEditar;
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

        if (registroAEditar != null) {
            registroIngreso.actualizarRegistro(registroAEditar, values);
            JOptionPane.showMessageDialog(this, "Registro actualizado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            registroIngreso.agregarRegistro(headers, values, "cosecha");
            JOptionPane.showMessageDialog(this, "Registro agregado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }

        this.dispose();
    }

    public void cargarDatos(List<String> datos) {
        if (datos.size() >= 8) {
            txtFecha.setText(datos.get(0));
            txtFactura.setText(datos.get(1));
            txtNombre.setText(datos.get(2));
            txtMontoNeto.setText(datos.get(3));
            txtMonto.setText(datos.get(4));
            txtIva.setText(datos.get(5));
            txtRetencion.setText(datos.get(6));
            txtFinal.setText(datos.get(7));
        }
    }
}
