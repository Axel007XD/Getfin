package org.getfin.vistas;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class RegistroIngreso extends JFrame {
    private final JPanel panelRegistros;
    private final java.util.Map<String, List<String>> encabezadosPorTipo = new java.util.HashMap<>();

    private final List<RegistroData> registros = new ArrayList<>();

    private FormularioIAnimales form;
    private FormularioICosecha formCosecha;

    public RegistroIngreso() {
        setTitle("Registro de Ingresos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Botones para abrir formularios
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnAnimal = new JButton("Formulario Animal");
        JButton btnCosecha = new JButton("Formulario Cosecha");
        JButton btnGuardar = new JButton("Guardar");
        JButton btnCancelar = new JButton("Cancelar");
        panelBotones.add(btnAnimal);
        panelBotones.add(btnCosecha);
        panelBotones.add(btnGuardar);
        panelBotones.add(btnCancelar);
        mainPanel.add(panelBotones, BorderLayout.NORTH);

        // Panel de registros
        panelRegistros = new JPanel();
        panelRegistros.setLayout(new BoxLayout(panelRegistros, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(panelRegistros);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        setContentPane(mainPanel);

        // Eventos
        btnAnimal.addActionListener(e -> inizializarAnimales());
        btnCosecha.addActionListener(e -> inizializarCosecha());
        btnCancelar.addActionListener(e -> dispose());
    }

    public void agregarRegistro(List<String> nuevosEncabezados, List<String> valores, String tipoFormulario) {
        List<String> encabezados = encabezadosPorTipo.computeIfAbsent(tipoFormulario, k -> new ArrayList<>());
        if (encabezados.isEmpty()) {
            encabezados.addAll(nuevosEncabezados);
        }

        // Crear copias de encabezados y valores para manipular la visualización
        List<String> encabezadosFinal = new ArrayList<>(nuevosEncabezados);
        List<String> valoresFinal = new ArrayList<>(valores);

        if (tipoFormulario.equals("animal")) {
            // Agregar "Tipo" como campo visible al inicio (valor[0] es el tipo: "Animal Individual" o "Animal en Grupo")
            encabezadosFinal.add(0, "Tipo");
            valoresFinal.add(0, valores.get(0));
        }

        JPanel panelRegistro = new JPanel(new BorderLayout(5, 5));
        panelRegistro.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(Color.GRAY), new EmptyBorder(5, 5, 5, 5)));

        JPanel datosPanel = new JPanel(new GridLayout(encabezadosFinal.size(), 2, 5, 5));
        for (int i = 0; i < encabezadosFinal.size(); i++) {
            String header = encabezadosFinal.get(i);
            String valor = i < valoresFinal.size() ? valoresFinal.get(i) : "";
            datosPanel.add(new JLabel(header + ":"));
            datosPanel.add(new JLabel(valor));
        }

        panelRegistro.add(datosPanel, BorderLayout.CENTER);

        JPanel botonesPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnEditar = new JButton("Editar");
        JButton btnEliminar = new JButton("Eliminar");
        botonesPanel.add(btnEditar);
        botonesPanel.add(btnEliminar);
        panelRegistro.add(botonesPanel, BorderLayout.SOUTH);

        RegistroData registro = new RegistroData(panelRegistro, new ArrayList<>(valores), tipoFormulario);
        registros.add(registro);

        btnEliminar.addActionListener(e -> {
            panelRegistros.remove(panelRegistro);
            registros.remove(registro);
            panelRegistros.revalidate();
            panelRegistros.repaint();
        });

        btnEditar.addActionListener(e -> {
            if (tipoFormulario.equals("cosecha")) {
                FormularioICosecha form = new FormularioICosecha(this);
                form.setRegistroAEditar(registro);
                form.cargarDatos(registro.valores);
                form.setVisible(true);
            } else if (tipoFormulario.equals("animal")) {
                FormularioIAnimales form = new FormularioIAnimales(this);
                form.setRegistroAEditar(registro);
                form.cargarDatos(registro.valores);
                form.setVisible(true);
            }
        });

        panelRegistros.add(panelRegistro);
        panelRegistros.add(Box.createVerticalStrut(10));
        panelRegistros.revalidate();
        panelRegistros.repaint();
    }


    public void actualizarRegistro(RegistroData registro, List<String> nuevosValores) {
        registro.valores.clear();
        registro.valores.addAll(nuevosValores);

        JPanel datosPanel = (JPanel) registro.panel.getComponent(0); // panel de datos
        List<String> encabezados = encabezadosPorTipo.get(registro.tipoFormulario);
        for (int i = 0; i < encabezados.size(); i++) {
            JLabel lblValor = (JLabel) datosPanel.getComponent(i * 2 + 1);
            lblValor.setText(nuevosValores.get(i));
        }


        panelRegistros.revalidate();
        panelRegistros.repaint();
    }

    private FormularioIAnimales inizializarAnimales() {
        if (form == null || !form.isDisplayable()) {
            form = new FormularioIAnimales(this);
            form.setVisible(true);
        } else {
            form.toFront();
            form.requestFocus();
        }
        return form;
    }

    private FormularioICosecha inizializarCosecha() {
        if (formCosecha == null || !formCosecha.isVisible()) {
            formCosecha = new FormularioICosecha(this);
            formCosecha.setVisible(true);
        } else {
            formCosecha.toFront();
            formCosecha.requestFocus();
        }
        return formCosecha;
    }

    // Clase interna
    static class RegistroData {
        JPanel panel;
        List<String> valores;
        String tipoFormulario;

        public RegistroData(JPanel panel, List<String> valores, String tipoFormulario) {
            this.panel = panel;
            this.valores = valores;
            this.tipoFormulario = tipoFormulario;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RegistroIngreso().setVisible(true));
    }
}
