package org.getfin.modelos;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "detalles_egreso")
@Data
@NoArgsConstructor

public class DetalleEgreso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double cantidad;

    private double precioUnitario;

    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "id_egreso", nullable = false)
    private Egreso egreso;

    @ManyToOne
    @JoinColumn(name = "id_producto")
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "id_animal")
    private Animal animal;

    @ManyToOne
    @JoinColumn(name = "id_animal_agrupado")
    private AnimalAgrupado animalAgrupado;

    @ManyToOne
    @JoinColumn(name = "id_herramienta")
    private Herramienta herramienta;

    public DetalleEgreso(double cantidad, double precioUnitario, String descripcion,
                         Egreso egreso, Producto producto, Animal animal,
                         AnimalAgrupado animalAgrupado, Herramienta herramienta) {
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.descripcion = descripcion;
        this.egreso = egreso;
        this.producto = producto;
        this.animal = animal;
        this.animalAgrupado = animalAgrupado;
        this.herramienta = herramienta;
    }

}
