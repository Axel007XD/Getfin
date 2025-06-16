package org.getfin.vistas;

import org.getfin.Componentes.Barras;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {

    public VentanaPrincipal(){
        initComponents();
    }

    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        setAutoRequestFocus(false);
        setBackground(new java.awt.Color(83, 102, 119));
        setSize(new java.awt.Dimension(1920, 1080));
        setLocationRelativeTo(null);
        Barras barras = new  Barras();
        add(barras);
        setVisible(true);

    }


}
