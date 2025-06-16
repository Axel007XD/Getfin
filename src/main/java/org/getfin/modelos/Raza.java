package org.getfin.modelos;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "razas")
@Data
@NoArgsConstructor

public class Raza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRaza;

    @Column(nullable = false)
    private String nombre;

    @OneToMany(mappedBy = "raza")
    private List<Animal> animales;

    public Raza(String nombre) {
        this.nombre = nombre;
    }

}
