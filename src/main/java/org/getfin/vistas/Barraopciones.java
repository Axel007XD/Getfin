package org.getfin.vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Logger;

public class Barraopciones extends JPanel {

    public interface OpcionSeleccionadaListener {
        void onOpcionSeleccionada(String opcion);
    }

    private static final Logger logger = Logger.getLogger(Barraopciones.class.getName());
    private OpcionSeleccionadaListener listener;
    private Color colorFondo = new Color(12, 55, 15);
    private Color colorHover = new Color(30, 80, 30);
    private Color colorTexto = Color.WHITE;
    private Font fuenteTexto = new Font("Bahnschrift", Font.BOLD, 12);
    private int alturaItem = 80; // Más altura para acomodar icono + texto
    private int anchoItem = 90; // Ancho suficiente para texto e icono

    public Barraopciones() {
        initComponents();
    }

    public void setOpcionSeleccionadaListener(OpcionSeleccionadaListener listener) {
        this.listener = listener;
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        setBackground(colorFondo);
        setPreferredSize(new Dimension(anchoItem + 20, getHeight()));

        JPanel panelOpciones = new JPanel();
        panelOpciones.setBackground(colorFondo);
        panelOpciones.setLayout(new BoxLayout(panelOpciones, BoxLayout.Y_AXIS));
        panelOpciones.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));

        // Agregar opciones
        agregarItemOpcion(panelOpciones, "/Receive Euro.png", "Ingreso");
        agregarItemOpcion(panelOpciones, "/Receive Euro.png", "Egreso");
        agregarItemOpcion(panelOpciones, "/Animal.png", "Animal");
        agregarItemOpcion(panelOpciones, "/Visit (1).png", "Visita");
        agregarItemOpcion(panelOpciones, "/Wheat.png", "Cultivo");
        agregarItemOpcion(panelOpciones, "/Clipboard List.png", "Inventario");

        panelOpciones.add(Box.createVerticalGlue());
        agregarItemOpcion(panelOpciones, "/garden_exit-fill-12.png", "Salir");

        add(panelOpciones, BorderLayout.CENTER);
    }

    private void agregarItemOpcion(JPanel panelContenedor, String iconPath, String texto) {
        // Panel principal para cada opción
        JPanel panelOpcion = new JPanel();
        panelOpcion.setLayout(new BoxLayout(panelOpcion, BoxLayout.Y_AXIS));
        panelOpcion.setBackground(colorFondo);
        panelOpcion.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panelOpcion.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelOpcion.setPreferredSize(new Dimension(anchoItem, alturaItem));
        panelOpcion.setMaximumSize(new Dimension(anchoItem, alturaItem));

        // Panel para el icono (centrado)
        JPanel panelIcono = new JPanel();
        panelIcono.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelIcono.setBackground(colorFondo);
        panelIcono.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblIcono = new JLabel();
        try {
            ImageIcon icono = new ImageIcon(getClass().getResource(iconPath));
            Image img = icono.getImage();
            int iconSize = Math.min(40, alturaItem - 30); // Tamaño adecuado para el espacio
            Image scaledImg = img.getScaledInstance(iconSize, iconSize, Image.SCALE_SMOOTH);
            lblIcono.setIcon(new ImageIcon(scaledImg));
            lblIcono.setHorizontalAlignment(JLabel.CENTER);
        } catch (Exception e) {
            logger.warning("No se encontró el recurso: " + iconPath);
            lblIcono.setText("X");
            lblIcono.setFont(fuenteTexto.deriveFont(Font.BOLD, 16f));
            lblIcono.setForeground(Color.RED);
        }
        panelIcono.add(lblIcono);

        // Label para el texto (centrado)
        JLabel lblTexto = new JLabel(texto);
        lblTexto.setFont(fuenteTexto);
        lblTexto.setForeground(colorTexto);
        lblTexto.setHorizontalAlignment(JLabel.CENTER);
        lblTexto.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTexto.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));

        // Agregar componentes al panel de opción
        panelOpcion.add(panelIcono);
        panelOpcion.add(lblTexto);

        // Configurar eventos del mouse
        panelOpcion.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (listener != null) {
                    listener.onOpcionSeleccionada(texto);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                panelOpcion.setBackground(colorHover);
                panelIcono.setBackground(colorHover);
                lblTexto.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                panelOpcion.setBackground(colorFondo);
                panelIcono.setBackground(colorFondo);
                lblTexto.setForeground(colorTexto);
            }
        });

        panelContenedor.add(panelOpcion);
        panelContenedor.add(Box.createVerticalStrut(5));
    }

    // Métodos para personalización
    public void setColorFondo(Color color) {
        this.colorFondo = color;
        setBackground(color);
    }

    public void setColorHover(Color color) {
        this.colorHover = color;
    }

    public void setColorTexto(Color color) {
        this.colorTexto = color;
    }

    public void setFuenteTexto(Font fuente) {
        this.fuenteTexto = fuente;
    }

    public void setAlturaItem(int altura) {
        this.alturaItem = altura;
    }

    public void setAnchoItem(int ancho) {
        this.anchoItem = ancho;
    }
}