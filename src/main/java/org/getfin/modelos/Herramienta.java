package org.getfin.modelos;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "herramientas")
@Data
@NoArgsConstructor

public class Herramienta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    private int cantidad;


    @Temporal(TemporalType.DATE)
    private LocalDate fechaAdquisicion;

    public Herramienta(String nombre, String descripcion, int cantidad, LocalDate fechaAdquisicion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.fechaAdquisicion = fechaAdquisicion;
    }



}
