package org.getfin.Componentes;

import java.awt.*;
import javax.swing.*;
import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

public class Barras extends JPanel {

    private JPanel jpnBarrasV;
    private JPanel jpnBarrasP;
    private JLabel lblConsejo;

    public Barras() {
        initComponents();
        crearGraficos();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        JPanel panelSuperior = new JPanel(new GridLayout(1, 4, 10, 10));
        panelSuperior.setPreferredSize(new Dimension(100, 70));

        panelSuperior.add(crearPanel("Gasto", new Color(183, 209, 163)));
        panelSuperior.add(crearPanel("Ingreso", new Color(255, 200, 0)));
        panelSuperior.add(crearPanel("Capital", new Color(94, 57, 41), Color.WHITE));
        panelSuperior.add(crearPanel("% de ganancia", new Color(255, 200, 0)));

        JPanel panelCentro = new JPanel(new GridLayout(1, 2, 10, 10));
        jpnBarrasV = new JPanel(new BorderLayout());
        jpnBarrasP = new JPanel(new BorderLayout());
        jpnBarrasV.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        jpnBarrasP.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        panelCentro.add(jpnBarrasV);
        panelCentro.add(jpnBarrasP);

        JPanel panelInferior = new JPanel(new BorderLayout());
        lblConsejo = new JLabel(" ", SwingConstants.CENTER);
        lblConsejo.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        lblConsejo.setPreferredSize(new Dimension(100, 100));
        panelInferior.add(lblConsejo, BorderLayout.CENTER);

        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(panelSuperior, BorderLayout.NORTH);
        add(panelCentro, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);
    }

    private JPanel crearPanel(String texto, Color fondo) {
        return crearPanel(texto, fondo, Color.BLACK);
    }

    private JPanel crearPanel(String texto, Color fondo, Color textoColor) {
        JPanel panel = new JPanel();
        panel.setBackground(fondo);
        JLabel label = new JLabel(texto);
        label.setFont(new Font("Open Sans", Font.BOLD, 14));
        label.setForeground(textoColor);
        panel.add(label);
        return panel;
    }

    private void crearGraficos() {
        // Gráfico de barras
        DefaultCategoryDataset datasetBarra = new DefaultCategoryDataset();
        datasetBarra.addValue(1500, "Gastos", "Enero");
        datasetBarra.addValue(2000, "Ingresos", "Enero");
        datasetBarra.addValue(2000, "Gastos", "Febrero");
        datasetBarra.addValue(3000, "Ingresos", "Febrero");
        datasetBarra.addValue(2500, "Gastos", "Marzo");
        datasetBarra.addValue(3000, "Ingresos", "Marzo");
        datasetBarra.addValue(1200, "Gastos", "Abril");
        datasetBarra.addValue(2000, "Ingresos", "Abril");

        JFreeChart chartBarra = ChartFactory.createBarChart(
                "Resumen anual", "Mes", "Cantidad", datasetBarra);

        CategoryPlot plot = chartBarra.getCategoryPlot();
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, new Color(183, 209, 163));
        renderer.setSeriesPaint(1, new Color(255, 200, 0));

        ChartPanel chartPanelBarra = new ChartPanel(chartBarra);
        chartPanelBarra.setMouseWheelEnabled(true);
        jpnBarrasV.add(chartPanelBarra, BorderLayout.CENTER);

        // Gráfico de pastel
        DefaultPieDataset datasetPastel = new DefaultPieDataset();
        datasetPastel.setValue("Gastos", 10000);
        datasetPastel.setValue("Ingresos", 12500);

        JFreeChart chartPastel = ChartFactory.createPieChart(
                "Resumen anual", datasetPastel, true, true, false);
        PiePlot piePlot = (PiePlot) chartPastel.getPlot();
        piePlot.setSectionPaint("Gastos", new Color(183, 209, 163));
        piePlot.setSectionPaint("Ingresos", new Color(100, 180, 255));

        ChartPanel chartPanelPastel = new ChartPanel(chartPastel);
        chartPanelPastel.setMouseWheelEnabled(true);
        jpnBarrasP.add(chartPanelPastel, BorderLayout.CENTER);
        setVisible(true);
    }
}
