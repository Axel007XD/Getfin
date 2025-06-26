package org.getfin;

import org.getfin.Componentes.Barras;
import org.getfin.controlador.AnimalAgrupadoController;
import org.getfin.controlador.AnimalController;
import org.getfin.controlador.EspecieController;
import org.getfin.modelos.Animal;
import org.getfin.modelos.AnimalAgrupado;
import org.getfin.modelos.Especies;
import org.getfin.vistas.*;

import java.time.LocalDate;

public class App {

    public static void main(String[] args) {

        /*
        String nombre= JTEXFIELS.getText();
        Animal animal = new Animal(nombre, LocalDate.now(), Animal.EstadoAnimal.desarrollo, especies);
        AnimalController animalController = AnimalController.getInstance();
        animalController.guardarAnimal(animal);
        */
        Especies especies = new  Especies("Tilapia");
        EspecieController especieController = EspecieController.getInstance();
        especieController.guardarCliente(especies);
        AnimalAgrupado animalAgrupado = new AnimalAgrupado("Grupo A",especies , 100, LocalDate.now());
        AnimalAgrupadoController animalAgrupadoController = AnimalAgrupadoController.getInstance();
        animalAgrupadoController.guardarCliente(animalAgrupado);
        new Login().setVisible(true);
        //new VentanaPrincipal();
        //new VentanaPrincipal();
        //new Barraopciones().setVisible(true);
    }
}
