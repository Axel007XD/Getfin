package org.getfin.modelos;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "animales_agrupados")
@Data
@NoArgsConstructor
public class AnimalAgrupado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAgrupacion;

    @Column(nullable = false)
    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoAgrupado tipo;
    public enum TipoAgrupado { peces, abejas, otro }

    @Column(nullable = false)
    private Integer cantidad;

    @Column(nullable = false)
    private LocalDate fechaInicio;

    @ManyToOne
    @JoinColumn(name = "idEspecie", nullable = false)
    private Especies especies;

    @OneToMany(mappedBy = "agrupacion")
    private List<DetalleIngreso> detallesIngresos;

    public AnimalAgrupado(String nombre, TipoAgrupado tipo, Integer cantidad, LocalDate fechaInicio) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.fechaInicio = fechaInicio;
    }
}
