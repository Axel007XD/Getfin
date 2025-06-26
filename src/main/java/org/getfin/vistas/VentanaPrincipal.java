package org.getfin.vistas;

import org.getfin.Componentes.Barras;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {

    private Barraopciones barraopciones;
    private JPanel contenido;
    public VentanaPrincipal(){
        initComponents();
        configurarBarraopciones();
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAutoRequestFocus(false);
        setLayout(new BorderLayout());
        setBackground(new java.awt.Color(83, 102, 119));
        setSize(new java.awt.Dimension(1000, 600));
        setLocationRelativeTo(null);

        // Barra de opciones a la izquierda
        barraopciones = new Barraopciones();
        barraopciones.setPreferredSize(new Dimension(90, 100));
        add(barraopciones, BorderLayout.WEST);

        // Panel de contenido central con layout correcto
        contenido = new JPanel(new BorderLayout());
        contenido.setBackground(new Color(83, 102, 119));

        Barras barras = new Barras();
        contenido.add(barras, BorderLayout.CENTER);

        add(contenido, BorderLayout.CENTER);

        setVisible(true);

    }
    private void configurarBarraopciones(){
        barraopciones.setOpcionSeleccionadaListener(new Barraopciones.OpcionSeleccionadaListener() {
            @Override
            public void onOpcionSeleccionada(String opcion) {
                switch (opcion) {
                    case "Ingreso" -> mostrarPanelIngreso();
                    case "Egreso" -> mostrarPanelEgreso();
                    case "Salir" -> System.exit(0);
                    default -> System.out.println("Opción no manejada: " + opcion);
                }
            }
        });
    }
    public void mostrarPanelIngreso() {
        IngresoVista ingresoVista = new IngresoVista();
        ingresoVista.setVisible(true);
        // Aquí puedes agregar el panel de ingreso a un contenedor principal
        //Por ejemplo, si tienes un JPanel principal:
         contenido.removeAll();         contenido.add(ingresoVista);
         contenido.revalidate();
         contenido.repaint();
    }
    public void mostrarPanelEgreso() {
        Egresovista egresoVista = new Egresovista();
        egresoVista.setVisible(true);
        contenido.removeAll();
        contenido.add(egresoVista);
        contenido.revalidate();
        contenido.repaint();

    }
}
