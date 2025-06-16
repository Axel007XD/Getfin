package org.getfin.modelos;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.getfin.modelos.Raza;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "animales")
@Data
@NoArgsConstructor

public class Animal {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAnimal;

    @Column(nullable = false)
    private String chapa;

    @Column(nullable = false)
    private LocalDate fechaAdquisicion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoAnimal estado;
    public enum EstadoAnimal { cria, desarrollo, produccion, vendido, muerto, extrabiado }

    @ManyToOne
    @JoinColumn(name = "id_raza")
    private Raza raza;

    @OneToMany(mappedBy = "animal")
    private List<DetalleIngreso> detallesIngresos;

    public Animal (String chapa, LocalDate fechaAdquisicion, EstadoAnimal estado) {
        this.chapa = chapa;
        this.fechaAdquisicion = fechaAdquisicion;
        this.estado = estado;
    }


}
