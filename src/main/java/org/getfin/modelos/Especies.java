package org.getfin.modelos;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "razas")
@Data
@NoArgsConstructor
public class Especies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRaza;

    @Column(nullable = false)
    private String nombre;

    // ↙ mappedBy coincide con `private Especies especies;` de Animal
    @OneToMany(mappedBy = "especies",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Animal> animales = new ArrayList<>();

    // ↙ mappedBy coincide con `private Especies especies;` de AnimalAgrupado
    @OneToMany(mappedBy = "especies",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<AnimalAgrupado> animalAgrupados = new ArrayList<>();

    public Especies(String nombre) {
        this.nombre = nombre;
    }

    // métodos de conveniencia:
    public void addAnimal(Animal a) {
        animales.add(a);
        a.setEspecies(this);
    }
    public void removeAnimal(Animal a) {
        animales.remove(a);
        a.setEspecies(null);
    }

    public void addAgrupado(AnimalAgrupado ag) {
        animalAgrupados.add(ag);
        ag.setEspecies(this);
    }
    public void removeAgrupado(AnimalAgrupado ag) {
        animalAgrupados.remove(ag);
        ag.setEspecies(null);
    }
}
