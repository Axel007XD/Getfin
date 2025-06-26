package org.getfin.vistas;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class RegistroIngreso extends JFrame {
    private final JPanel panelRegistros;
    private final List<String> encabezados = new ArrayList<>();

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
        panelBotones.add(btnAnimal);
        panelBotones.add(btnCosecha);
        mainPanel.add(panelBotones, BorderLayout.NORTH);

        // Panel de registros
        panelRegistros = new JPanel();
        panelRegistros.setLayout(new BoxLayout(panelRegistros, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(panelRegistros);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        setContentPane(mainPanel);

        // Eventos de los botones
        btnAnimal.addActionListener(e -> {
            FormularioIAnimales form = new FormularioIAnimales(this);
            form.setVisible(true);
        });

        btnCosecha.addActionListener(e -> {
            FormularioICosecha form = new FormularioICosecha(this);
            form.setVisible(true);
        });
    }

    public void agregarRegistro(List<String> nuevosEncabezados, List<String> valores) {
        // Inicializar encabezados solo la primera vez
        if (encabezados.isEmpty()) {
            encabezados.addAll(nuevosEncabezados);
        }

        JPanel panelRegistro = new JPanel(new GridLayout(encabezados.size(), 2, 5, 5));
        panelRegistro.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(Color.GRAY), new EmptyBorder(5, 5, 5, 5)));

        for (int i = 0; i < encabezados.size(); i++) {
            String header = encabezados.get(i);
            String valor = i < valores.size() ? valores.get(i) : "";
            panelRegistro.add(new JLabel(header + ":"));
            panelRegistro.add(new JLabel(valor));
        }

        panelRegistros.add(panelRegistro);
        panelRegistros.add(Box.createVerticalStrut(10));
        panelRegistros.revalidate();
        panelRegistros.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RegistroIngreso().setVisible(true));
    }
}
